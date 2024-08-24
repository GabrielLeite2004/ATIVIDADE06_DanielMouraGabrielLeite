package Atividade.Q1;

public class main {

    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<String>();
        grafo.adicionarVertice("João");
        grafo.adicionarVertice("Lorenzo");
        grafo.adicionarVertice("Creuza");
        grafo.adicionarVertice("Créber");
        grafo.adicionarVertice("Cráudio");

        grafo.adicionarAresta(2.0, "João", "Lorenzo");
        grafo.adicionarAresta(3.0, "Lorenzo", "Créber");
        grafo.adicionarAresta(1.0, "Créber", "Creuza");
        grafo.adicionarAresta(1.0, "João", "Creuza");
        grafo.adicionarAresta(3.0, "Cráudio", "João");
        grafo.adicionarAresta(2.0, "Cráudio", "Lorenzo");

        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");

        grafo.adicionarAresta(1.0, "A", "B");
        grafo.adicionarAresta(2.0, "A", "C");

        grafo.imprimirGrafo();

        System.out.println("Adjacentes de A: " + grafo.obterAdjacentes("A"));

        System.out.println("Existe aresta entre A e B? " + grafo.pesquisarAresta("A", "B"));

        grafo.removerAresta("A", "B");
        grafo.imprimirGrafo();

        grafo.removerVertice("C");
        grafo.imprimirGrafo();

        grafo.buscaEmLargura();

        MenuGrafo menu = new MenuGrafo();
        menu.exibirMenu();


        }
}