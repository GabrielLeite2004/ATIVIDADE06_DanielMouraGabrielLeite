package Atividade.Q6;
import java.util.*;

class Grafo {
    private Map<String, List<Aresta>> adjacencias;

    public Grafo() {
        adjacencias = new HashMap<>();
    }

    // Inserir vértice
    public void inserirVertice(String vertice) {
        adjacencias.putIfAbsent(vertice, new ArrayList<>());
    }

    // Inserir aresta
    public void inserirAresta(String vertice1, String vertice2, int peso) {
        adjacencias.putIfAbsent(vertice1, new ArrayList<>());
        adjacencias.putIfAbsent(vertice2, new ArrayList<>());
        Aresta aresta = new Aresta(vertice1, vertice2, peso);
        adjacencias.get(vertice1).add(aresta);
        adjacencias.get(vertice2).add(aresta); // Para grafo não direcionado
    }

    // Retornar arestas do grafo
    public List<Aresta> getArestas() {
        List<Aresta> arestas = new ArrayList<>();
        for (List<Aresta> listaArestas : adjacencias.values()) {
            for (Aresta aresta : listaArestas) {
                if (!arestas.contains(aresta)) {
                    arestas.add(aresta);
                }
            }
        }
        return arestas;
    }

    // Algoritmo de Kruskal para encontrar a MST
    public List<Aresta> algoritmoDeKruskal() {
        List<Aresta> arestas = getArestas();
        Collections.sort(arestas);
        List<Aresta> MST = new ArrayList<>();
        Map<String, String> pai = new HashMap<>();

        for (String vertice : getVertices()) {
            pai.put(vertice, vertice);
        }

        for (Aresta aresta : arestas) {
            String raiz1 = encontrarRaiz(pai, aresta.vertice1);
            String raiz2 = encontrarRaiz(pai, aresta.vertice2);

            if (!raiz1.equals(raiz2)) {
                MST.add(aresta);
                pai.put(raiz1, raiz2); // União das árvores
            }
        }

        return MST;
    }

    private String encontrarRaiz(Map<String, String> pai, String vertice) {
        while (!pai.get(vertice).equals(vertice)) {
            vertice = pai.get(vertice);
        }
        return vertice;
    }

    // Executar DFS para gerar o ciclo a partir da MST
    public void gerarCicloMinimo(List<Aresta> MST) {
        Map<String, List<String>> mstGrafo = new HashMap<>();
        for (Aresta aresta : MST) {
            mstGrafo.putIfAbsent(aresta.vertice1, new ArrayList<>());
            mstGrafo.putIfAbsent(aresta.vertice2, new ArrayList<>());
            mstGrafo.get(aresta.vertice1).add(aresta.vertice2);
            mstGrafo.get(aresta.vertice2).add(aresta.vertice1);
        }

        Set<String> visitado = new HashSet<>();
        List<String> ciclo = new ArrayList<>();
        String verticeInicial = MST.get(0).vertice1;
        DFS(verticeInicial, mstGrafo, visitado, ciclo);

        // Fechar o ciclo voltando ao ponto inicial
        ciclo.add(verticeInicial);

        // Imprimir o ciclo
        for (int i = 0; i < ciclo.size() - 1; i++) {
            System.out.println(ciclo.get(i) + " -- " + ciclo.get(i + 1));
        }
    }

    private void DFS(String vertice, Map<String, List<String>> mstGrafo, Set<String> visitado, List<String> ciclo) {
        visitado.add(vertice);
        ciclo.add(vertice);

        for (String adjacente : mstGrafo.getOrDefault(vertice, new ArrayList<>())) {
            if (!visitado.contains(adjacente)) {
                DFS(adjacente, mstGrafo, visitado, ciclo);
            }
        }
    }

    // Retornar vértices do grafo
    public Set<String> getVertices() {
        return adjacencias.keySet();
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        grafo.inserirAresta("A", "B", 1);
        grafo.inserirAresta("B", "C", 2);
        grafo.inserirAresta("C", "D", 3);
        grafo.inserirAresta("D", "E", 4);
        grafo.inserirAresta("E", "A", 5);
        grafo.inserirAresta("B", "D", 6);

        List<Aresta> MST = grafo.algoritmoDeKruskal();

        System.out.println("Ciclo mínimo que visita todos os vértices:");
        grafo.gerarCicloMinimo(MST);
    }
}
