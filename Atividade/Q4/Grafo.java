package Atividade.Q4;

import java.util.*;

public class Grafo {
    private Map<String, List<String>> adjacencias;

    public Grafo() {
        adjacencias = new HashMap<>();
    }

    // Inserir um vértice
    public void inserirVertice(String vertice) {
        adjacencias.putIfAbsent(vertice, new ArrayList<>());
    }

    // Inserir uma aresta
    public void inserirAresta(String vertice1, String vertice2) {
        adjacencias.putIfAbsent(vertice1, new ArrayList<>());
        adjacencias.putIfAbsent(vertice2, new ArrayList<>());
        adjacencias.get(vertice1).add(vertice2);
        adjacencias.get(vertice2).add(vertice1); // Para grafo não direcionado
    }

    // Verificar se o grafo é bipartido usando DFS
    public boolean isBipartido() {
        Map<String, Integer> cores = new HashMap<>();

        for (String vertice : adjacencias.keySet()) {
            if (!cores.containsKey(vertice)) {
                if (!DFS(vertice, 0, cores)) {
                    return false;
                }
            }
        }
        imprimirParticoes(cores);
        return true;
    }

    // DFS para verificar bipartição
    private boolean DFS(String vertice, int cor, Map<String, Integer> cores) {
        cores.put(vertice, cor);

        for (String adjacente : adjacencias.get(vertice)) {
            if (!cores.containsKey(adjacente)) {
                if (!DFS(adjacente, 1 - cor, cores)) {
                    return false;
                }
            } else if (cores.get(adjacente) == cor) {
                return false;
            }
        }
        return true;
    }

    // Imprimir as partições do grafo
    private void imprimirParticoes(Map<String, Integer> cores) {
        List<String> particao1 = new ArrayList<>();
        List<String> particao2 = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cores.entrySet()) {
            if (entry.getValue() == 0) {
                particao1.add(entry.getKey());
            } else {
                particao2.add(entry.getKey());
            }
        }

        System.out.println("O grafo É bipartido: Partição 1 " + particao1 + " e Partição 2 " + particao2);
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("A", "C");
        grafo.inserirAresta("B", "D");
        grafo.inserirAresta("C", "E");
        grafo.inserirAresta("D", "E");

        if (!grafo.isBipartido()) {
            System.out.println("O grafo NÃO é bipartido.");
        }
    }
}
