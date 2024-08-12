package Atividade.Q6;

import Atividade.Q1.Grafo;
import Atividade.Q1.Grafo.Aresta;
import Atividade.Q5.Prim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CicloMinimo {

    private List<String> ciclo;  // Lista para armazenar o ciclo mínimo
    private Set<String> visitado;

    public CicloMinimo() {
        ciclo = new ArrayList<>();
        visitado = new HashSet<>();
    }

    // Função para gerar o ciclo mínimo usando a MST
    public void gerarCicloMinimo(Grafo grafo, String startVertex) {
        // Executa o algoritmo de Prim para gerar a MST
        Prim prim = new Prim();
        List<Aresta> mst = prim.primMST(grafo, startVertex);

        // Cria um grafo a partir da MST para realizar o DFS
        Grafo mstGrafo = new Grafo();
        for (String vertice : grafo.getVertices()) {
            mstGrafo.inserirVertice(vertice);
        }

        for (Aresta aresta : mst) {
            mstGrafo.inserirArestaPonderada(aresta.origem, aresta.destino, aresta.peso);
        }

        // Executa DFS para gerar o ciclo
        dfs(mstGrafo, startVertex);

        // Adiciona o retorno ao ponto inicial para fechar o ciclo
        ciclo.add(startVertex);

        // Imprime o ciclo mínimo
        imprimirCiclo();
    }

    // Função DFS para percorrer a MST e construir o ciclo
    private void dfs(Grafo grafo, String vertice) {
        visitado.add(vertice);
        ciclo.add(vertice);

        for (String adjacente : grafo.obterAdjacentes(vertice)) {
            if (!visitado.contains(adjacente)) {
                dfs(grafo, adjacente);
            }
        }
    }

    // Função para imprimir o ciclo mínimo
    private void imprimirCiclo() {
        System.out.println("Ciclo mínimo que visita todos os vértices:");
        for (int i = 0; i < ciclo.size() - 1; i++) {
            System.out.println(ciclo.get(i) + " -> " + ciclo.get(i + 1));
        }
        System.out.println(ciclo.get(ciclo.size() - 1) + " -> " + ciclo.get(0));  // Conectando o último vértice ao primeiro
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.inserirVertice("A");
        grafo.inserirVertice("B");
        grafo.inserirVertice("C");
        grafo.inserirVertice("D");
        grafo.inserirVertice("E");

        grafo.inserirArestaPonderada("A", "B", 10);
        grafo.inserirArestaPonderada("A", "C", 15);
        grafo.inserirArestaPonderada("B", "D", 12);
        grafo.inserirArestaPonderada("C", "E", 10);
        grafo.inserirArestaPonderada("D", "E", 2);
        grafo.inserirArestaPonderada("B", "E", 5);

        CicloMinimo cicloMinimo = new CicloMinimo();
        cicloMinimo.gerarCicloMinimo(grafo, "A");
    }
}
