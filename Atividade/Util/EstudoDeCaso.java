package Atividade.Util;

import java.io.IOException;
import java.util.*;

public class EstudoDeCaso {

    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();

        try {
            // Substitua "caminhoDoArquivo.txt" pelo caminho do arquivo de entrada
            grafo.lerGrafoDeArquivo("C:\\Users\\jglei\\Documents\\GitHub\\Grafos\\Atividade\\Arquivos\\Arquivo.txt");

            // Usar o algoritmo de Prim para gerar a MST
            List<Aresta<String>> mst = grafo.algoritmoDePrim();
            Grafo<String> mstGrafo = construirGrafoMST(grafo, mst);

            // Realizar DFS na MST para obter um ciclo mínimo
            List<String> ciclo = realizarDFS(mstGrafo);

            // Fechar o ciclo conectando o último vértice ao primeiro
            ciclo.add(ciclo.get(0));

            // Imprimir o ciclo
            imprimirCiclo(ciclo);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Construir um novo grafo com base na MST
    private static Grafo<String> construirGrafoMST(Grafo<String> grafoOriginal, List<Aresta<String>> mst) {
        Grafo<String> mstGrafo = new Grafo<>();

        for (Aresta<String> aresta : mst) {
            String inicio = aresta.getInicio().getDado();
            String fim = aresta.getFim().getDado();
            Double peso = aresta.getPeso();

            mstGrafo.adicionarVertice(inicio);
            mstGrafo.adicionarVertice(fim);
            mstGrafo.adicionarAresta(peso, inicio, fim);
        }

        return mstGrafo;
    }

    // Realizar DFS na MST para criar um ciclo
    private static List<String> realizarDFS(Grafo<String> grafo) {
        List<String> ciclo = new ArrayList<>();
        Set<String> visitados = new HashSet<>();
        Vertice<String> verticeInicial = grafo.getVertices().get(0);
        dfsVisitar(verticeInicial, visitados, ciclo);
        return ciclo;
    }

    // DFS recursivo
    private static void dfsVisitar(Vertice<String> vertice, Set<String> visitados, List<String> ciclo) {
        visitados.add(vertice.getDado());
        ciclo.add(vertice.getDado());

        for (Aresta<String> aresta : vertice.getArestasSaida()) {
            Vertice<String> proximo = aresta.getFim();
            if (!visitados.contains(proximo.getDado())) {
                dfsVisitar(proximo, visitados, ciclo);
            }
        }
    }

    // Imprimir o ciclo resultante
    private static void imprimirCiclo(List<String> ciclo) {
        System.out.println("Ciclo Mínimo:");
        for (int i = 0; i < ciclo.size() - 1; i++) {
            System.out.println(ciclo.get(i) + " -> " + ciclo.get(i + 1));
        }
    }
}
