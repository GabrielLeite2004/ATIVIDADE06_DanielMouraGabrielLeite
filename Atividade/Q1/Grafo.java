package Atividade.Q1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<String, List<String>> Grafo;

    public Grafo() {
        Grafo = new HashMap<>();
    }

    // Inserir um vértice
    public void inserirVertice(String vertice) {
        if (!Grafo.containsKey(vertice)) {
            Grafo.put(vertice, new LinkedList<>());
        }
    }

    // Inserir uma aresta
    public void inserirAresta(String vertice1, String vertice2) {
        if (Grafo.containsKey(vertice1) && Grafo.containsKey(vertice2)) {
            Grafo.get(vertice1).add(vertice2);
            Grafo.get(vertice2).add(vertice1);
        }
    }

    // Remover um vértice
    public void removerVertice(String vertice) {
        if (Grafo.containsKey(vertice)) {
            // Remover o vértice das listas adjacentes
            for (String adjacente : Grafo.get(vertice)) {
                Grafo.get(adjacente).remove(vertice);
            }
            // Remover o próprio vértice
            Grafo.remove(vertice);
        }
    }

    // Remover uma aresta
    public void removerAresta(String vertice1, String vertice2) {
        if (Grafo.containsKey(vertice1) && Grafo.containsKey(vertice2)) {
            Grafo.get(vertice1).remove(vertice2);
            Grafo.get(vertice2).remove(vertice1);
        }
    }

    // Pesquisar por um vértice
    public boolean pesquisarVertice(String vertice) {
        return Grafo.containsKey(vertice);
    }

    // Pesquisar por uma aresta
    public boolean pesquisarAresta(String vertice1, String vertice2) {
        return Grafo.containsKey(vertice1) && Grafo.get(vertice1).contains(vertice2);
    }

    // Obter adjacentes de um vértice
    public List<String> obterAdjacentes(String vertice) {
        return Grafo.getOrDefault(vertice, new LinkedList<>());
    }

    // Imprimir o grafo
    public void imprimirGrafo() {
        System.out.print("Vértice: interliga a...\n");
        for (String vertice : Grafo.keySet()) {
            System.out.print(vertice + ": ");
            for (String adjacente : Grafo.get(vertice)) {
                System.out.print(adjacente + " ");
            }
            System.out.println();
        }
    }


}
