package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import structures.lista.ListaSucessores;

/**
 * @author Leon Junio Martins Ferreira
 * @version 1.3 - 2023
 */
public final class Main {
    private static ListaSucessores lista;
    private static Scanner scan = new Scanner(System.in);
    private static long inic = 0, fim = 0;

    public static void main(String[] args) {
        try {
            if (buildGraph()) {
                System.out.println("Menu de opções:");
                System.out.println("1 - Ver predessores e sucessores de um vertice no grafo G:");
                System.out.println("2 - Realizar busca em profundidade no Grafo G:");
                System.out.println("3 - Encontrar todos os caminhos disjuntos em arestas no Grafo G:");
                System.out.println("0 - Finalizar execução!");
                switch (scan.nextInt()) {
                    case 1:
                        showVertice();
                        break;
                    case 2:
                        buscaProfundidade();
                        break;
                    case 3:
                        caminhosDisjuntos();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } else {
                throw new Exception("Falha ao contruir grafo!");
            }
        } catch (Exception e) {
            System.err.println("Erro fatal na execução:\nErro: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        System.out.println("Fim da execução!");
    }

    /**
     * Constrói o grafo a partir de um arquivo
     */
    public static boolean buildGraph() throws Exception {
        boolean status = false;
        int vertices, arestas;
        try {
            System.out.println("Digite o nome do arquivo: (O mesmo deve estar na raiz do programa)");
            String nomeArq = scan.nextLine();
            File arquivo = new File(nomeArq);
            /*
             * Build do grafo
             */
            inic = System.currentTimeMillis();
            try (Scanner scanArq = new Scanner(arquivo)) {
                vertices = scanArq.nextInt();
                arestas = scanArq.nextInt();
                lista = new ListaSucessores(vertices, arestas, true);
                while (scanArq.hasNextInt()) {
                    int arc1 = scanArq.nextInt();
                    int arc2 = scanArq.nextInt();
                    lista.insertOnVertice(arc1, arc2);
                }
            } catch (FileNotFoundException fe) {
                System.err.println("Arquivo não encontrado na raiz do programa.");
                throw fe;
            }
            fim = System.currentTimeMillis();
            System.out.println(fim - inic + " ms para construir grafo");
            status = true;
        } catch (Exception e) {
            System.err.println("Erro em tempo de execução:\nErro: " + e.getLocalizedMessage());
            throw e;
        }
        return status;
    }

    /**
     * Mostra os dados de um vertice como sua lista de sucessores e predecessores
     * e o grau de entrada e saída
     */
    public static void showVertice() {
        int verticeAlvo = 0;
        List<Integer> predecessores, sucessores;// respostas
        do {
            System.out.println("Digite o número do vertice desejado ou\n" +
                    "digite 0 para sair!");
            verticeAlvo = scan.nextInt();
            /*
             * busca dos dados
             */
            if (verticeAlvo != 0) {
                sucessores = lista.getSucessores(verticeAlvo);
                predecessores = lista.getPredecessores(verticeAlvo);
                System.out.println("Grau de entrada: " + predecessores.size());
                System.out.println("Lista de predecessores: " + predecessores.toString());
                System.out.println("Grau de saída: " + sucessores.size());
                System.out.println("Lista de sucessores: " + sucessores.toString());
                System.out.println(fim - inic + " ms para localizar dados do vertice");
            }
        } while (verticeAlvo != 0);
    }

    /**
     * Realiza a busca em profundidade no grafo
     */
    public static void buscaProfundidade() {
        int verticeAlvo = 0;
        do {
            System.out.println("Digite o número do vertice desejado ou " +
                    "digite -1 para buscar e mostrar todos os dados!\nDigite 0 para sair!");
            verticeAlvo = scan.nextInt();
            /*
             * busca dos dados
             */
            if (verticeAlvo != 0) {
                if (verticeAlvo == -1)
                    lista.buscaProfundidade();
                else
                    System.out.println(lista.buscaProfundidade(verticeAlvo).toString());
            }
        } while (verticeAlvo != 0);
    }

    /**
     * menu para busca de caminhos disjuntos
     */
    public static void caminhosDisjuntos() {
        int origem = 0, destino = 0;
        System.out.println("Digite a origem do caminho desejado:");
        origem = scan.nextInt();
        System.out.println("Digite o destino do caminho desejado:");
        destino = scan.nextInt();
        for (String resp : lista.caminhosDisjuntos(origem, destino)) {
            System.out.println(resp.substring(0, resp.length() - 3));
        }
    }
}