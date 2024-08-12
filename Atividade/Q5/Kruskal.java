package Atividade.Q5;

import Atividade.Q1.Grafo;

import java.util.*;

public class Kruskal {

    public void kruskalMST(Grafo grafo) {
        List<Aresta> arestas = new ArrayList<>();

        for (String vertice : grafo.obterGrafo().keySet()) {
            for (String adjacente : grafo.obterAdjacentes(vertice)) {
                arestas.add(new Aresta(vertice, adjacente, grafo.getPesoAresta(vertice, adjacente)));
            }
        }

        Collections.sort(arestas, Comparator.comparingInt(a -> a.peso));

        UnionFind uf = new UnionFind(grafo.obterGrafo().size());

        List<Aresta> mst = new ArrayList<>();

        for (Aresta aresta : arestas) {
            if (uf.union(aresta.origem, aresta.destino)) {
                mst.add(aresta);
            }
        }

        imprimirMST(mst);
    }

    private void imprimirMST(List<Aresta> mst) {
        for (Aresta aresta : mst) {
            System.out.println(aresta.origem + " - " + aresta.destino + " (" + aresta.peso + ")");
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        grafo.inserirArestaPonderada("A", "B", 1);
        grafo.inserirArestaPonderada("A", "C", 4);
        grafo.inserirArestaPonderada("B", "C", 3);
        grafo.inserirArestaPonderada("B", "D", 2);
        grafo.inserirArestaPonderada("C", "D", 5);
        grafo.inserirArestaPonderada("D", "E", 7);

        Kruskal kruskal = new Kruskal();
        kruskal.kruskalMST(grafo);
    }

    // Classe auxiliar para gerenciar união e busca em conjuntos disjuntos (Union-Find)
    class UnionFind {
        private Map<String, String> parent;

        public UnionFind(int size) {
            parent = new HashMap<>();
        }

        public String find(String vertex) {
            if (parent.get(vertex) == null) {
                parent.put(vertex, vertex);
            }
            if (parent.get(vertex).equals(vertex)) {
                return vertex;
            }
            parent.put(vertex, find(parent.get(vertex)));
            return parent.get(vertex);
        }

        public boolean union(String vertex1, String vertex2) {
            String root1 = find(vertex1);
            String root2 = find(vertex2);

            if (root1.equals(root2)) {
                return false;
            }

            parent.put(root1, root2);
            return true;
        }
    }
}
