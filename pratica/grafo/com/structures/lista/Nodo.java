package structures.lista;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon Junio Martins Ferreira
 * @version 1.0 - 2023
 */
public class Nodo {
    public List<Integer> sucessores;
    public int vertice;

    /**
     * O construtor do nodo
     * 
     * @param vertice  inteiro do vertice
     * @param totalSuc total de sucessores para iniciar o armazenamento
     */
    public Nodo(int vertice, int totalSuc) {
        this.vertice = vertice;
        sucessores = new ArrayList<>(totalSuc);
    }

    /**
     * O construtor do nodo
     * 
     * @param vertice inteiro do vertice
     */
    public Nodo(int vertice) {
        this.vertice = vertice;
        sucessores = new ArrayList<>();
    }

    /**
     * Adicionar lista de sucessores no vertice
     * 
     * @param sucessores lista para salvar como os sucessores no vertice
     */
    public void addSucessores(List<Integer> sucessores) throws Exception {
        this.sucessores = sucessores;
    }

    /**
     * Busca o vertice do nodo
     * 
     * @return o inteiro do indice
     */
    public int getVertice() {
        return vertice;
    }
}
