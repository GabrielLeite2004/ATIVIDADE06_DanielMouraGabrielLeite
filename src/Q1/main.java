package Q1;

public class main {
    // Método principal para teste
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Inserindo vértices
        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");

        // Inserindo arestas
        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("B", "C");

        // Imprimindo o grafo
        grafo.imprimirGrafo();

        // Removendo uma aresta
        grafo.removerAresta("A", "B");

        // Imprimindo o grafo após remoção
        grafo.imprimirGrafo();
    }
}
