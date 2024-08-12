package Atividade.Q2;

import Atividade.Q1.Grafo;
import java.util.HashMap;
import java.util.Map;

public class DFS {
    private Map<String, Boolean> visitado;
    private Map<String, Integer> chegada;
    private Map<String, Integer> partida;
    private int tempo;

    public DFS() {
        visitado = new HashMap<>();
        chegada = new HashMap<>();
        partida = new HashMap<>();
        tempo = 0;
    }

    // Executa DFS a partir de um vértice
    public void dfs(Grafo grafo, String vertice) {
        visitado.put(vertice, true);
        chegada.put(vertice, ++tempo);

        // Percorre todos os adjacentes do vértice atual
        for (String adjacente : grafo.obterAdjacentes(vertice)) {
            if (!visitado.getOrDefault(adjacente, false)) {
                dfs(grafo, adjacente);
            }
        }

        partida.put(vertice, ++tempo);
    }

    // Executa DFS para todos os vértices do grafo
    public void executarDFS(Grafo grafo) {
        // Inicializa todos os vértices como não visitados
        for (String vertice : grafo.obterGrafo().keySet()) {
            if (!visitado.containsKey(vertice)) {
                dfs(grafo, vertice);
            }
        }

        // Imprime os tempos de chegada e partida
        for (String vertice : grafo.obterGrafo().keySet()) {
            System.out.println("Vértice " + vertice + " (Chegada: " + chegada.get(vertice) + ", Partida: " + partida.get(vertice) + ")");
        }
    }


}
