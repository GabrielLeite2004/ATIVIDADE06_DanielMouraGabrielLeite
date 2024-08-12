package Atividade.Q5;

import Atividade.Q1.Grafo;

import java.util.*;

public class Prim {

    public void primMST(Grafo grafo) {
        // Inicializa a MST com um único vértice (começando arbitrariamente pelo primeiro vértice)
        String startVertex = grafo.obterGrafo().keySet().iterator().next();
        PriorityQueue<Grafo.Aresta> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.peso));

        Set<String> visitado = new HashSet<>();
        List<Grafo.Aresta> mst = new ArrayList<>();

        adicionarArestas(grafo, startVertex, visitado, pq);

        while (!pq.isEmpty()) {
            Grafo.Aresta aresta = pq.poll();

            if (visitado.contains(aresta.destino)) {
                continue;
            }

            mst.add(aresta);
            adicionarArestas(grafo, aresta.destino, visitado, pq);
        }

        imprimirMST(mst);
    }

    private void adicionarArestas(Grafo grafo, String vertice, Set<String> visitado, PriorityQueue<Aresta> pq) {
        visitado.add(vertice);
        for (String adjacente : grafo.obterAdjacentes(vertice)) {
            pq.add(new Grafo.Aresta(vertice, adjacente, grafo.getPesoAresta(vertice, adjacente)));
        }
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

        Prim prim = new Prim();
        prim.primMST(grafo);
    }
}
