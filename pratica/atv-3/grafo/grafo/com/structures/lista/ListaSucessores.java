package structures.lista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Leon Junio Martins Ferreira
 * @version 1.0 - 2023
 */
public final class ListaSucessores {
    private Nodo vertices[]; // grafo
    private int arestas = 0;

    /**
     * Construtor da lista encadeada de vertices
     * 
     * @param totalVertices total de vertices para iniciar o armazenamento
     * @param totalArestas  total de arestas para iniciar o armazenamento
     * @param fill          booleano para definir se vai preencher ou não o vetor
     */
    public ListaSucessores(int totalVertices, int totalArestas, boolean fill) {
        vertices = new Nodo[totalVertices];
        arestas = totalArestas;
        if (fill) {
            for (int i = 0; i < totalVertices; i++) {
                vertices[i] = new Nodo(i + 1);
            }
        }
    }

    /**
     * Adicionar vertice pronto dentro da lista
     * 
     * @param Nodo vertice alvo para adicionar na lista de vertices
     */
    public void add(Nodo vertice) throws Exception {
        vertices[vertice.getVertice() - 1] = vertice;
    }

    /**
     * Adicionar aresta dentro de vertice pronto
     * 
     * @param vertice vertice alvo para adicionar na lista de vertices
     * @param aresta  valor da aresta para qual o vertice aponta
     */
    public void insertOnVertice(int vertice, int aresta) throws Exception {
        List<Integer> sucessores = vertices[vertice - 1].sucessores;
        int index = Collections.binarySearch(sucessores, aresta);
        if (index < 0) {
            index = -index - 1; // converte o resultado negativo para a posição onde o elemento deve ser
                                // inserido
        }
        sucessores.add(index, aresta);
    }

    /**
     * Retorna o total + a lista de sucessores do vertice escolhido
     * 
     * @param index indice do vertice para busca
     */
    public List<Integer> getSucessores(int index) {
        return vertices[index - 1].sucessores;
    }

    public int getSucessoresSize(int index) {
        return vertices[index - 1].sucessores.size();
    }

    /**
     * Retorna o total + a lista de predecessores do vertice escolhido
     * 
     * @param index indice do vertice para busca
     */
    public List<Integer> getPredecessores(int index) {
        List<Integer> predecessores = new ArrayList<>();
        for (int pos = 0; pos < vertices.length; pos++) {
            if (vertices[pos].sucessores.contains(index)) {
                predecessores.add(pos + 1);
            }
        }
        return predecessores;
    }

    /**
     * Realiza uma busca em profundidade no vertice escolhido e mostra os dados de
     * TD, TT e pai
     */
    public void buscaProfundidade() {
        BuscaProfundidade busca = new BuscaProfundidade(this);
        System.out.println(Arrays.toString(busca.getTD()));
        System.out.println(Arrays.toString(busca.getTT()));
        System.out.println(Arrays.toString(busca.getPais()));
    }

    /**
     * Realiza uma busca em profundidade no vertice escolhido e mostra os dados dos
     * vertices divergentes de V
     * 
     * @param vertice vertice alvo para busca
     * @return Informações dos vertices divergentes de V
     */
    public List<String> buscaProfundidade(int vertice) {
        BuscaProfundidade busca = new BuscaProfundidade(this, vertice);
        return busca.getRespostas();
    }

    /**
     * Realiza uma busca para localizar todos os caminhos disjuntos em arestas
     * 
     * @param origem vertice no qual o caminho vai iniciar
     * @param destino vertice no qual o caminho vai terminar
     * @return lista de arestas que compõem os caminhos disjuntos
     */
    public List<String> caminhosDisjuntos(int origem,int destino) {
       CaminhosDisjuntos caminhos = new CaminhosDisjuntos(getMatrix(),getTotalVertices(), origem,destino);
        return caminhos.findCaminhosDisjuntos();
    }

    /**
     * buscar total de arestas
     * 
     * @return inteiro com total de arestas entre vertices
     */
    public int getArestas() {
        return arestas;
    }

    public void setArestas(int arestas) {
        this.arestas = arestas;
    }

    public Nodo[] getVertices() {
        return vertices;
    }

    public int getTotalVertices() {
        return vertices.length;
    }

    public int[][] getMatrix(){
        int matrix[][] = new int[getTotalVertices()][getTotalVertices()];
        for (int i = 0; i < getTotalVertices(); i++) {
            for (int j = 0; j < getTotalVertices(); j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i < getTotalVertices(); i++) {
            for (int j = 0; j < getSucessoresSize(i+1); j++) {
                matrix[i][getSucessores(i+1).get(j)-1] = 1;
            }
        }
        return matrix;
    }

}
