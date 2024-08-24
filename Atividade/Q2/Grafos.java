package Atividade.Q2;

import java.util.*;

public class Grafos {
    private Map<String, List<String>> adjacencias;
    private Map<String, Integer> tempoChegada;
    private Map<String, Integer> tempoPartida;
    private int tempo;

    public Grafos() {
        adjacencias = new HashMap<>();
        tempoChegada = new HashMap<>();
        tempoPartida = new HashMap<>();
        tempo = 0;
    }

    // Inserir um vértice
    public void inserirVertice(String vertice) {
        adjacencias.putIfAbsent(vertice, new ArrayList<>());
    }

    // Remover um vértice
    public void removerVertice(String vertice) {
        adjacencias.values().forEach(e -> e.remove(vertice));
        adjacencias.remove(vertice);
    }

    // Inserir uma aresta
    public void inserirAresta(String vertice1, String vertice2) {
        if (!adjacencias.containsKey(vertice1)) {
            inserirVertice(vertice1);
        }
        if (!adjacencias.containsKey(vertice2)) {
            inserirVertice(vertice2);
        }
        adjacencias.get(vertice1).add(vertice2);
        adjacencias.get(vertice2).add(vertice1);  // Para grafo não direcionado
    }

    // Remover uma aresta
    public void removerAresta(String vertice1, String vertice2) {
        List<String> adjVertice1 = adjacencias.get(vertice1);
        List<String> adjVertice2 = adjacencias.get(vertice2);
        if (adjVertice1 != null) {
            adjVertice1.remove(vertice2);
        }
        if (adjVertice2 != null) {
            adjVertice2.remove(vertice1);
        }
    }

    // Pesquisar se um vértice existe
    public boolean pesquisarVertice(String vertice) {
        return adjacencias.containsKey(vertice);
    }

    // Pesquisar se uma aresta existe
    public boolean pesquisarAresta(String vertice1, String vertice2) {
        return adjacencias.containsKey(vertice1) && adjacencias.get(vertice1).contains(vertice2);
    }

    // Obter adjacentes de um vértice
    public List<String> obterAdjacentes(String vertice) {
        return adjacencias.getOrDefault(vertice, new ArrayList<>());
    }

    // Imprimir o grafo
    public void imprimirGrafo() {
        for (Map.Entry<String, List<String>> entry : adjacencias.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // DFS para calcular os tempos de chegada e partida
    public void DFS() {
        Set<String> visitado = new HashSet<>();

        for (String vertice : adjacencias.keySet()) {
            if (!visitado.contains(vertice)) {
                DFSVisit(vertice, visitado);
            }
        }

        // Imprimir tempos de chegada e partida
        for (String vertice : adjacencias.keySet()) {
            System.out.println(vertice + " (Chegada: " + tempoChegada.get(vertice) + ", Partida: " + tempoPartida.get(vertice) + ")");
        }
    }

    private void DFSVisit(String vertice, Set<String> visitado) {
        visitado.add(vertice);
        tempo++;
        tempoChegada.put(vertice, tempo);

        for (String adjacente : adjacencias.get(vertice)) {
            if (!visitado.contains(adjacente)) {
                DFSVisit(adjacente, visitado);
            }
        }

        tempo++;
        tempoPartida.put(vertice, tempo);
    }

    public static void main(String[] args) {
        Grafos grafo = new Grafos();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("A", "C");
        grafo.inserirAresta("B", "D");
        grafo.inserirAresta("C", "E");

        grafo.DFS();
    }
}

