package Atividade.Q3;
import Atividade.Q1.Grafo;

public class main {
    // Método principal para teste
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

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

        VerificaVR verificaVR = new VerificaVR();
        verificaVR.encontrarVR(grafo);
    }
}
