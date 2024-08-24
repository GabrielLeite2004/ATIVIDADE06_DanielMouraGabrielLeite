package Atividade.Q3;

import java.util.*;

public class GrafoDirecionado {
    private Map<String, List<String>> adjacencias;

    public GrafoDirecionado() {
        adjacencias = new HashMap<>();
    }

    // Inserir um vértice
    public void inserirVertice(String vertice) {
        adjacencias.putIfAbsent(vertice, new ArrayList<>());
    }

    // Inserir uma aresta direcionada
    public void inserirAresta(String vertice1, String vertice2) {
        adjacencias.putIfAbsent(vertice1, new ArrayList<>());
        adjacencias.get(vertice1).add(vertice2);
    }

    // Função para verificar se todos os vértices são acessíveis a partir de um dado vértice
    private boolean todosAlcancaveis(String vertice, Map<String, List<String>> grafo) {
        Set<String> visitado = new HashSet<>();
        DFSVisit(vertice, visitado, grafo);
        return visitado.size() == grafo.size();
    }

    // Função DFS auxiliar
    private void DFSVisit(String vertice, Set<String> visitado, Map<String, List<String>> grafo) {
        visitado.add(vertice);
        for (String adjacente : grafo.getOrDefault(vertice, new ArrayList<>())) {
            if (!visitado.contains(adjacente)) {
                DFSVisit(adjacente, visitado, grafo);
            }
        }
    }

    // Inverter o grafo
    private Map<String, List<String>> inverterGrafo() {
        Map<String, List<String>> grafoInvertido = new HashMap<>();
        for (String vertice : adjacencias.keySet()) {
            for (String adjacente : adjacencias.get(vertice)) {
                grafoInvertido.putIfAbsent(adjacente, new ArrayList<>());
                grafoInvertido.get(adjacente).add(vertice);
            }
        }
        return grafoInvertido;
    }

    // Identificar vértices raiz
    public List<String> identificarVerticesRaiz() {
        List<String> verticesRaiz = new ArrayList<>();
        for (String vertice : adjacencias.keySet()) {
            if (todosAlcancaveis(vertice, adjacencias)) {
                Map<String, List<String>> grafoInvertido = inverterGrafo();
                if (todosAlcancaveis(vertice, grafoInvertido)) {
                    verticesRaiz.add(vertice);
                }
            }
        }
        return verticesRaiz;
    }

    public static void main(String[] args) {
        GrafoDirecionado grafo = new GrafoDirecionado();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("B", "C");
        grafo.inserirAresta("C", "D");
        grafo.inserirAresta("D", "E");
        grafo.inserirAresta("E", "A");

        List<String> verticesRaiz = grafo.identificarVerticesRaiz();

        if (verticesRaiz.isEmpty()) {
            System.out.println("Nenhum vértice raiz encontrado.");
        } else {
            System.out.println("Vértices raiz: " + verticesRaiz);
        }
    }
}
