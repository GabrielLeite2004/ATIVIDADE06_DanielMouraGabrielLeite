package Atividade.Q4;

import Atividade.Q1.Grafo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VerificaBipartido {

    private Map<String, Integer> cor;

    public VerificaBipartido() {
        cor = new HashMap<>();
    }

    // Método para realizar DFS e verificar se o grafo pode ser bipartido
    private boolean dfs(Grafo grafo, String vertice, int currentCor) {
        cor.put(vertice, currentCor);

        for (String adjacente : grafo.obterAdjacentes(vertice)) {
            if (!cor.containsKey(adjacente)) {
                // Atribuir cor oposta ao vértice adjacente
                if (!dfs(grafo, adjacente, 1 - currentCor)) {
                    return false;
                }
            } else if (cor.get(adjacente) == cor.get(vertice)) {
                // Se um vértice adjacente tem a mesma cor, o grafo não é bipartido
                return false;
            }
        }
        return true;
    }

    // Método para verificar se o grafo é bipartido
    public void verificarBipartido(Grafo grafo) {
        for (String vertice : grafo.obterGrafo().keySet()) {
            if (!cor.containsKey(vertice)) {
                if (!dfs(grafo, vertice, 0)) {
                    System.out.println("O grafo NÃO é bipartido.");
                    return;
                }
            }
        }

        // Se o grafo é bipartido, imprimir as partições
        List<String> particao1 = new LinkedList<>();
        List<String> particao2 = new LinkedList<>();

        for (Map.Entry<String, Integer> entry : cor.entrySet()) {
            if (entry.getValue() == 0) {
                particao1.add(entry.getKey());
            } else {
                particao2.add(entry.getKey());
            }
        }

        System.out.println("O grafo É bipartido:");
        System.out.println("Partição 1: " + particao1);
        System.out.println("Partição 2: " + particao2);
    }


}
