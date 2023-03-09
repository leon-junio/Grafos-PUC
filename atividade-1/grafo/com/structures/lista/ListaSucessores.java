package structures.lista;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon Junio Martins Ferreira
 * @version 1.0 - 2023
 */
public class ListaSucessores {
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
        vertices[vertice - 1].sucessores.add(aresta);
    }

    /**
     * Retorna o total + a lista de sucessores do vertice escolhido
     * 
     * @param index indice do vertice para busca
     */
    public List<Integer> getSucessores(int index) {
        return vertices[index - 1].sucessores;
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
     * buscar total de arestas
     * 
     * @return inteiro com total de arestas entre vertices
     */
    public int getArestas() {
        return arestas;
    }
}
