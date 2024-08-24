package Atividade.Q5;

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

    // Retornar vértices do grafo
    public Set<String> getVertices() {
        return adjacencias.keySet();
    }

    // Algoritmo de Prim
    public List<Aresta> algoritmoDePrim() {
        Set<String> verticesIncluidos = new HashSet<>();
        PriorityQueue<Aresta> arestasDisponiveis = new PriorityQueue<>();
        List<Aresta> MST = new ArrayList<>();

        String verticeInicial = getVertices().iterator().next();
        verticesIncluidos.add(verticeInicial);
        arestasDisponiveis.addAll(adjacencias.get(verticeInicial));

        while (!arestasDisponiveis.isEmpty()) {
            Aresta aresta = arestasDisponiveis.poll();
            String vertice1 = aresta.vertice1;
            String vertice2 = aresta.vertice2;

            if (verticesIncluidos.contains(vertice1) && verticesIncluidos.contains(vertice2)) {
                continue; // Ignorar arestas que formariam ciclos
            }

            MST.add(aresta);
            String novoVertice = verticesIncluidos.contains(vertice1) ? vertice2 : vertice1;
            verticesIncluidos.add(novoVertice);
            arestasDisponiveis.addAll(adjacencias.get(novoVertice));
        }

        return MST;
    }

    // Algoritmo de Kruskal
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

    // Algoritmo de Boruvka
    public List<Aresta> algoritmoDeBoruvka() {
        List<Aresta> MST = new ArrayList<>();
        Map<String, String> componente = new HashMap<>();
        for (String vertice : getVertices()) {
            componente.put(vertice, vertice);
        }

        int componentesRestantes = getVertices().size();

        while (componentesRestantes > 1) {
            Map<String, Aresta> menorAresta = new HashMap<>();

            for (Aresta aresta : getArestas()) {
                String componente1 = encontrarRaiz(componente, aresta.vertice1);
                String componente2 = encontrarRaiz(componente, aresta.vertice2);

                if (!componente1.equals(componente2)) {
                    if (!menorAresta.containsKey(componente1) || menorAresta.get(componente1).peso > aresta.peso) {
                        menorAresta.put(componente1, aresta);
                    }
                    if (!menorAresta.containsKey(componente2) || menorAresta.get(componente2).peso > aresta.peso) {
                        menorAresta.put(componente2, aresta);
                    }
                }
            }

            for (Aresta aresta : menorAresta.values()) {
                String componente1 = encontrarRaiz(componente, aresta.vertice1);
                String componente2 = encontrarRaiz(componente, aresta.vertice2);

                if (!componente1.equals(componente2)) {
                    MST.add(aresta);
                    componentesRestantes--;
                    componente.put(componente1, componente2);
                }
            }
        }

        return MST;
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

        System.out.println("Árvore Geradora Mínima - Prim:");
        List<Aresta> mstPrim = grafo.algoritmoDePrim();
        for (Aresta aresta : mstPrim) {
            System.out.println(aresta);
        }

        System.out.println("\nÁrvore Geradora Mínima - Kruskal:");
        List<Aresta> mstKruskal = grafo.algoritmoDeKruskal();
        for (Aresta aresta : mstKruskal) {
            System.out.println(aresta);
        }

        System.out.println("\nÁrvore Geradora Mínima - Boruvka:");
        List<Aresta> mstBoruvka = grafo.algoritmoDeBoruvka();
        for (Aresta aresta : mstBoruvka) {
            System.out.println(aresta);
        }
    }
}

