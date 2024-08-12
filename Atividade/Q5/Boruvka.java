package Atividade.Q5;

import Atividade.Q1.Grafo;

import java.util.*;

public class Boruvka {

    public void boruvkaMST(Grafo grafo) {
        int numVertices = grafo.obterGrafo().size();
        UnionFind uf = new UnionFind(numVertices);

        List<Grafo.Aresta> mst = new ArrayList<>();
        int mstWeight = 0;

        int[] cheapest = new int[numVertices];

        while (mst.size() < numVertices - 1) {
            Arrays.fill(cheapest, -1);

            for (String vertice : grafo.obterGrafo().keySet()) {
                for (String adjacente : grafo.obterAdjacentes(vertice)) {
                    int peso = grafo.getPesoAresta(vertice, adjacente);
                    int set1 = uf.find(vertice);
                    int set2 = uf.find(adjacente);

                    if (set1 != set2) {
                        if (cheapest[set1] == -1 || peso < grafo.getPesoAresta(grafo.getVertices().get(cheapest[set1]), vertice)) {
                            cheapest[set1] = grafo.getVertices().indexOf(vertice);
                        }
                        if (cheapest[set2] == -1 || peso < grafo.getPesoAresta(grafo.getVertices().get(cheapest[set2]), adjacente)) {
                            cheapest[set2] = grafo.getVertices().indexOf(adjacente);
                        }
                    }
                }
            }

            for (int i = 0; i < numVertices; i++) {
                if (cheapest[i] != -1) {
                    Grafo.Aresta aresta = new Grafo.Aresta(grafo.getVertices().get(i), grafo.getVertices().get(cheapest[i]), grafo.getPesoAresta(grafo.getVertices().get(i), grafo.getVertices().get(cheapest[i])));
                    int set1 = uf.find(aresta.origem);
                    int set2 = uf.find(aresta.destino);

                    if (set1 != set2) {
                        mst.add(aresta);
                        mstWeight += aresta.peso;
                        uf.union(set1, set2);
                    }
                }
            }
        }

        imprimirMST(mst);
    }

    private void imprimirMST(List<Grafo.Aresta> mst) {
        for (Grafo.Aresta aresta : mst) {
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

        Boruvka boruvka = new Boruvka();
        boruvka.boruvkaMST(grafo);
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
