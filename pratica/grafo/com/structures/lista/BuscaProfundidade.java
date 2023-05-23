package structures.lista;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class BuscaProfundidade {
    private static int TD[]; // tempo de descoberta
    private static int TT[]; // tempo de visita
    private static int pai[]; // pai do vertice
    private static int tempo; // contador de tempo
    private static int[] sucessoresVisitados; // contador de sucessores visitados
    private ListaSucessores lista;
    private static List<String> respostas = new ArrayList<String>();
    private long inic, fim;

    /**
     * Construtor da classe para inicar a busca em profundidade
     * 
     * @param lista lista de vertices e arestas
     */
    public BuscaProfundidade(ListaSucessores lista) {
        TD = new int[lista.getTotalVertices()];
        TT = new int[lista.getTotalVertices()];
        pai = new int[lista.getTotalVertices()];
        sucessoresVisitados = new int[lista.getTotalVertices()];
        this.lista = lista;
        inic = System.currentTimeMillis();
        buscaProfundidade();
        fim = System.currentTimeMillis();
        System.out.println("Tempo de execução da busca em profundidade: " + (fim - inic) + "ms");
    }

    /**
     * Construtor da classe para inicar a busca em profundidade
     * 
     * @param lista   lista de vertices e arestas
     * @param vertice vertice alvo para busca
     */
    public BuscaProfundidade(ListaSucessores lista, int vertice) {
        TD = new int[lista.getTotalVertices()];
        TT = new int[lista.getTotalVertices()];
        pai = new int[lista.getTotalVertices()];
        sucessoresVisitados = new int[lista.getTotalVertices()];
        this.lista = lista;
        inic = System.currentTimeMillis();
        buscaProfundidade(vertice);
        fim = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (fim - inic) + "ms");
    }

    /**
     * Metodo para iniciar a busca em profundidade
     */
    private void buscaProfundidade() {
        for (int v = 1; v < lista.getVertices().length; v++) {
            if (TD[v - 1] == 0)
                buscaEmProfundidadeV(v);
        }
    }

    /**
     * Metodo para iniciar a busca em profundidade a partir de um vertice
     * 
     * @param vertice vertice alvo para busca
     */
    private void buscaProfundidade(int vertice) {
        for (int v = 1; v < lista.getVertices().length; v++) {
            if (TD[v - 1] == 0)
                buscaEmProfundidadeV(v);
        }
        classificarDivergentes(vertice);
    }

    /**
     * Função que realizar a busca em profundidade em um grafo G
     * 
     * @param vertice vertice raiz para busca
     * @param tempo   tempo de descoberta com contador global
     */
    private void buscaEmProfundidadeV(int vertice) {
        Stack<Integer> pilha = new Stack<Integer>();
        pilha.push(vertice - 1);
        TD[vertice - 1] = ++tempo;
        while (!pilha.empty()) {
            int v = pilha.peek();
            if (sucessoresVisitados[v] == lista.getSucessoresSize(v + 1)) {
                pilha.pop();
                TT[v] = ++tempo;
                if (!pilha.empty())
                    v = pilha.peek();
            }
            if (sucessoresVisitados[v] < lista.getSucessoresSize(v + 1)) {
                int w = lista.getSucessores(v + 1).get(sucessoresVisitados[v]);
                if (w != 0 && TD[w - 1] == 0) {
                    pai[w - 1] = v + 1;
                    TD[w - 1] = ++tempo;
                    pilha.push(w - 1);
                }
                sucessoresVisitados[v]++;
            }
        }
    }

    /**
     * Metodo para classificar as arestas divergentes de um vertice
     * 
     * @param vertice vertice alvo para classificar as arestas
     */
    private void classificarDivergentes(int vertice) {
        for (int w : lista.getSucessores(vertice)) {
            if (pai[w - 1] == vertice) {
                respostas.add("\n" + "Aresta de árvore: " + vertice + " -> " + w);
            } else if (TD[w - 1] < TD[vertice - 1] && TT[w - 1] > TT[vertice - 1]) {
                respostas.add("\n" + "Aresta de retorno: " + vertice + " -> " + w);
            } else if (TD[w - 1] > TD[vertice - 1] && TT[w - 1] < TT[vertice - 1]) {
                respostas.add("\n" + "Aresta de avanço: " + vertice + " -> " + w);
            } else {
                respostas.add("\n" + "Aresta de cruzamento: " + vertice + " -> " + w);
            }
        }
        respostas.add("\n");
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public int[] getTD() {
        return TD;
    }

    public int[] getTT() {
        return TT;
    }

    public int[] getPais() {
        return pai;
    }
}
