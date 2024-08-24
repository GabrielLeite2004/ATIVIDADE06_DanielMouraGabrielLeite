package Atividade.Q5;

import Atividade.Q1.Aresta;
import Atividade.Q1.Grafo;

import java.io.IOException;
import java.util.List;

public class MenuAlgoritmos {

    public static void main(String[] args) {
        Grafo<String> grafo = new Grafo<>();

        try {
            // Substitua "caminhoDoArquivo.txt" pelo caminho do arquivo de entrada
            grafo.lerGrafoDeArquivo("C:\\Users\\jglei\\Documents\\GitHub\\Grafos\\Atividade\\Arquivos\\Arquivo.txt");

            System.out.println("Árvore Geradora Mínima - Prim:");
            List<Aresta<String>> mstPrim = grafo.algoritmoDePrim();
            grafo.imprimirMST(mstPrim);

            System.out.println("\nÁrvore Geradora Mínima - Kruskal:");
            List<Aresta<String>> mstKruskal = grafo.algoritmoDeKruskal();
            grafo.imprimirMST(mstKruskal);

            System.out.println("\nÁrvore Geradora Mínima - Boruvka:");
            List<Aresta<String>> mstBoruvka = grafo.algoritmoDeBoruvka();
            grafo.imprimirMST(mstBoruvka);

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
