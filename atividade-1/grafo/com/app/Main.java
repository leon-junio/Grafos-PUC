package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import structures.lista.ListaSucessores;

/**
 * @author Leon Junio Martins Ferreira
 * @version 1.0 - 2023
 */
public class Main {
    private static ListaSucessores lista;
    private static File arquivo;
    private static Scanner scan = new Scanner(System.in);
    private static int vertices, arestas;
    private static long inic = 0, fim = 0;

    public static void main(String[] args) {
        try {
            String nomeArq = "";
            int verticeAlvo = 0;
            System.out.println("Digite o nome do arquivo: (O mesmo deve estar na raiz do programa)");
            nomeArq = scan.nextLine();
            arquivo = new File(nomeArq);
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
            List<Integer> predecessores, sucessores;// respostas
            do {
                System.out.println("Digite o número do vertice desejado ou\n" +
                        "digite 0 para sair!");
                verticeAlvo = scan.nextInt();
                /*
                 * busca dos dados
                 */
                if (verticeAlvo != 0) {
                    inic = System.currentTimeMillis();
                    sucessores = lista.getSucessores(verticeAlvo);
                    predecessores = lista.getPredecessores(verticeAlvo);
                    fim = System.currentTimeMillis();
                    System.out.println("Grau de entrada: " + predecessores.size());
                    System.out.println("Lista de predecessores: " + predecessores.toString());
                    System.out.println("Grau de saída: " + sucessores.size());
                    System.out.println("Lista de sucessores: " + sucessores.toString());
                    System.out.println(fim - inic + " ms para localizar dados do vertice");
                }
            } while (verticeAlvo != 0);
        } catch (Exception e) {
            System.err.println("Erro em tempo de execução:\nErro: " + e.getLocalizedMessage());
        }
        System.out.println("Finalizado com sucesso");
    }
}