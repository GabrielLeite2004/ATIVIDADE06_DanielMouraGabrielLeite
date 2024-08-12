package Atividade.Q3;


import Atividade.Q1.Grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VerificaVR {

    private Set<String> visitado;

    public VerificaVR() {
        visitado = new HashSet<>();
    }

    // Método para realizar DFS a partir de um vértice
    private void dfs(Grafo grafo, String vertice) {
        visitado.add(vertice);
        for (String adjacente : grafo.obterAdjacentes(vertice)) {
            if (!visitado.contains(adjacente)) {
                dfs(grafo, adjacente);
            }
        }
    }

    // Método para verificar se um vértice é um Vértice Raiz (VR)
    public boolean isVR(Grafo grafo, String vertice) {
        visitado.clear();
        dfs(grafo, vertice);
        return visitado.size() == grafo.obterGrafo().size();
    }

    // Método para encontrar e verificar se existe um VR no grafo
    public void encontrarVR(Grafo grafo) {
        for (String vertice : grafo.obterGrafo().keySet()) {
            if (isVR(grafo, vertice)) {
                System.out.println("O VR é: Vértice " + vertice);
                return;
            }
        }
        System.out.println("O grafo não possui VR");
    }


}
