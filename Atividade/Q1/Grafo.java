package Atividade.Q1;

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

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");

        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("A", "C");

        grafo.imprimirGrafo();

        System.out.println("Adjacentes de A: " + grafo.obterAdjacentes("A"));

        System.out.println("Existe aresta entre A e B? " + grafo.pesquisarAresta("A", "B"));

        grafo.removerAresta("A", "B");
        grafo.imprimirGrafo();

        grafo.removerVertice("C");
        grafo.imprimirGrafo();
    }
}
