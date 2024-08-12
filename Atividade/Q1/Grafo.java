package Atividade.Q1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<String, List<Aresta>> grafo;
    private List<String> vertices;

    public Grafo() {
        grafo = new HashMap<>();
        vertices = new LinkedList<>();
    }

    // Classe interna para representar uma aresta
    public static class Aresta {
        public String destino;
        public int peso;

        public Aresta(String destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Inserir um vértice
    public void inserirVertice(String vertice) {
        if (!grafo.containsKey(vertice)) {
            grafo.put(vertice, new LinkedList<>());
            vertices.add(vertice);
        }
    }

    // Inserir uma aresta ponderada
    public void inserirArestaPonderada(String vertice1, String vertice2, int peso) {
        if (grafo.containsKey(vertice1) && grafo.containsKey(vertice2)) {
            grafo.get(vertice1).add(new Aresta(vertice2, peso));
            grafo.get(vertice2).add(new Aresta(vertice1, peso)); // Como o grafo é não direcionado, adiciona nos dois sentidos
        }
    }

    // Remover um vértice
    public void removerVertice(String vertice) {
        if (grafo.containsKey(vertice)) {
            // Remover o vértice das listas adjacentes
            for (Aresta aresta : grafo.get(vertice)) {
                grafo.get(aresta.destino).removeIf(a -> a.destino.equals(vertice));
            }
            // Remover o próprio vértice
            grafo.remove(vertice);
            vertices.remove(vertice);
        }
    }

    // Remover uma aresta
    public void removerAresta(String vertice1, String vertice2) {
        if (grafo.containsKey(vertice1) && grafo.containsKey(vertice2)) {
            grafo.get(vertice1).removeIf(a -> a.destino.equals(vertice2));
            grafo.get(vertice2).removeIf(a -> a.destino.equals(vertice1));
        }
    }

    // Pesquisar por um vértice
    public boolean pesquisarVertice(String vertice) {
        return grafo.containsKey(vertice);
    }

    // Pesquisar por uma aresta
    public boolean pesquisarAresta(String vertice1, String vertice2) {
        return grafo.containsKey(vertice1) && grafo.get(vertice1).stream().anyMatch(a -> a.destino.equals(vertice2));
    }

    // Obter adjacentes de um vértice
    public List<String> obterAdjacentes(String vertice) {
        List<String> adjacentes = new LinkedList<>();
        if (grafo.containsKey(vertice)) {
            for (Aresta aresta : grafo.get(vertice)) {
                adjacentes.add(aresta.destino);
            }
        }
        return adjacentes;
    }

    // Obter o peso de uma aresta
    public int getPesoAresta(String vertice1, String vertice2) {
        if (grafo.containsKey(vertice1)) {
            for (Aresta aresta : grafo.get(vertice1)) {
                if (aresta.destino.equals(vertice2)) {
                    return aresta.peso;
                }
            }
        }
        return Integer.MAX_VALUE; // Retorna um valor infinito se a aresta não existir
    }

    // Imprimir o grafo
    public void imprimirGrafo() {
        System.out.print("Vértice: interliga a...\n");
        for (String vertice : grafo.keySet()) {
            System.out.print(vertice + ": ");
            for (Aresta aresta : grafo.get(vertice)) {
                System.out.print("(" + aresta.destino + ", " + aresta.peso + ") ");
            }
            System.out.println();
        }
    }

    // Método para obter todo o grafo
    public Map<String, List<Aresta>> obterGrafo() {
        return grafo;
    }

    // Método para obter todos os vértices
    public List<String> getVertices() {
        return vertices;
    }
}
