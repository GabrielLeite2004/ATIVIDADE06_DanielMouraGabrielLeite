package Atividade.Q2;
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
        grafo.inserirAresta("D", "E");
        grafo.inserirAresta("E", "F");

        System.out.println("Execução do DFS com Tempos de Chegada e Partida:");
        DFS dfs = new DFS();
        dfs.executarDFS(grafo);
    }
}
