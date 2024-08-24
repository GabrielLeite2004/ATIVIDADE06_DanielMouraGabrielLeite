package Atividade.Q3;

import Atividade.Q1.Grafo;

public class MenuVR {
    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();

        grafo.adicionarVertice("A");
        grafo.adicionarVertice("B");
        grafo.adicionarVertice("C");
        grafo.adicionarVertice("D");

        grafo.adicionarAresta(1.0, "A", "B");
        grafo.adicionarAresta(1.0, "B", "C");
        grafo.adicionarAresta(1.0, "C", "D");
        grafo.adicionarAresta(1.0, "D", "A");

        grafo.encontrarVR();
    }
}
