package Atividade.Q4;
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
        grafo.inserirVertice("F");

        grafo.inserirAresta("A", "B");
        grafo.inserirAresta("A", "C");
        grafo.inserirAresta("B", "D");
        grafo.inserirAresta("C", "E");
        grafo.inserirAresta("D", "F");

        VerificaBipartido verificaBipartido = new VerificaBipartido();
        verificaBipartido.verificarBipartido(grafo);
    }
}
