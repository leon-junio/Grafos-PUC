package structures.lista;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CaminhosDisjuntos {

    private int origem = 0;
    private int destino = 0;
    private int n = 0;
    private int graph[][];
    private List<String> caminhos = new ArrayList<>();
    private static long inic = 0, fim = 0;
    private StringBuilder caminho;

    public CaminhosDisjuntos(int graph[][], int n, int origem, int destino) {
        this.graph = graph;
        this.n = n;
        this.origem = origem-1;
        this.destino = destino-1;
    }

    /**
     * BFS modificada para encontrar caminhos disjuntos
     * 
     * @param rGraph  grafo residual
     * @param origem  vertice de origem
     * @param destino vertice de destino
     * @param parent  vetor de pais
     * @return true se existe caminho entre origem e destino
     */
    private boolean bfs(int rGraph[][], int origem,
            int destino, int parent[]) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(origem);
        visited[origem] = true;
        parent[origem] = -1;
        while (!q.isEmpty()) {
            int u = q.peek();
            q.remove();
            for (int v = 0; v < n; v++) {
                if (visited[v] == false &&
                        rGraph[u][v] > 0) {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[destino] == true);
    }

    /**
     * Metodo para encontrar caminhos disjuntos efetivamente
     * 
     * @return lista de caminhos disjuntos
     */
    public List<String> findCaminhosDisjuntos() {
        inic = System.currentTimeMillis();
        int u, v;
        int[][] rGraph = new int[n][n];
        for (u = 0; u < n; u++)
            for (v = 0; v < n; v++)
                rGraph[u][v] = graph[u][v];
        int[] parent = new int[n];
        caminhos.clear();
        int maxFlow = 0;
        while (bfs(rGraph, origem, destino, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (v = destino; v != origem; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }
            for (v = destino; v != origem; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
            caminho = new StringBuilder();
            for (v = destino; v != origem; v = parent[v]) {
                caminho.insert(0, " -> ").insert(0, (v+1));
                u = parent[v];
            }
            caminho.insert(0, " -> ").insert(0, origem+1);
            caminhos.add(caminho.toString());
        }
        System.out.println("Fluxo máximo: " + maxFlow);
        fim = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (fim - inic) + "ms");
        return caminhos;
    }
}
