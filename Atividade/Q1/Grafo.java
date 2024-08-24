package Atividade.Q1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    private int tempo;
    private int[] tempoChegada;
    private int[] tempoPartida;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.tempo = 0;
    }

    public void inicializarTempos() {
        int n = this.vertices.size();
        this.tempoChegada = new int[n];
        this.tempoPartida = new int[n];
        for (int i = 0; i < n; i++) {
            this.tempoChegada[i] = -1;
            this.tempoPartida[i] = -1;
        }
    }

    public void adicionarVertice(TIPO dado){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void removerVertice(TIPO dado) {
        Vertice<TIPO> vertice = this.getVertice(dado);
        if (vertice != null) {
            // Remover as arestas associadas ao vértice
            this.arestas.removeIf(aresta -> aresta.getInicio().equals(vertice) || aresta.getFim().equals(vertice));

            // Remover o vértice em si
            this.vertices.remove(vertice);
        }
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim){
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public void removerAresta(TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        if (inicio != null && fim != null) {
            this.arestas.removeIf(aresta -> aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim));
            inicio.getArestasSaida().removeIf(aresta -> aresta.getFim().equals(fim));
            fim.getArestasEntrada().removeIf(aresta -> aresta.getInicio().equals(inicio));
        }
    }


    public Vertice<TIPO> getVertice(TIPO dado){
        Vertice<TIPO> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public boolean pesquisarVertice(TIPO dado) {
        return this.getVertice(dado) != null;
    }

    public boolean pesquisarAresta(TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        if (inicio != null && fim != null) {
            for (Aresta<TIPO> aresta : this.arestas) {
                if (aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Vertice<TIPO>> obterAdjacentes(TIPO dado) {
        Vertice<TIPO> vertice = this.getVertice(dado);
        ArrayList<Vertice<TIPO>> adjacentes = new ArrayList<>();
        if (vertice != null) {
            for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
                adjacentes.add(aresta.getFim());
            }
        }
        return adjacentes;
    }

    public void imprimirGrafo() {
        for (Vertice<TIPO> vertice : this.vertices) {
            System.out.print(vertice.getDado() + " -> ");
            for (Vertice<TIPO> adjacente : this.obterAdjacentes(vertice.getDado())) {
                System.out.print(adjacente.getDado() + " ");
            }
            System.out.println();
        }
    }

    public void buscaEmLargura(){
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<TIPO> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestasSaida().size(); i++){
                Vertice<TIPO> proximo = visitado.getArestasSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ //se o vértice ainda não foi marcado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }

    // Método para executar DFS e calcular os tempos de chegada e partida
    public void executarDFS() {
        inicializarTempos(); // Inicializa os arrays de tempos
        this.tempo = 0;

        for (int i = 0; i < vertices.size(); i++) {
            if (tempoChegada[i] == -1) {
                dfsVisit(i);
            }
        }
    }

    // Método recursivo para DFS
    private void dfsVisit(int indiceVertice) {
        tempo++;
        tempoChegada[indiceVertice] = tempo;

        Vertice<TIPO> vertice = vertices.get(indiceVertice);
        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            int indiceProximo = vertices.indexOf(aresta.getFim());
            if (tempoChegada[indiceProximo] == -1) {
                dfsVisit(indiceProximo);
            }
        }

        tempo++;
        tempoPartida[indiceVertice] = tempo;
    }

    // Método para verificar se um vértice é raiz
    private boolean verificarSeEhRaiz(int indiceVertice) {
        // Realizar DFS a partir deste vértice para verificar se ele alcança todos os outros
        ArrayList<Integer> visitados = new ArrayList<>();
        dfsVerificarRaiz(indiceVertice, visitados);

        return visitados.size() == vertices.size();
    }

    // Método auxiliar para DFS a partir do candidato a VR
    private void dfsVerificarRaiz(int indiceVertice, ArrayList<Integer> visitados) {
        visitados.add(indiceVertice);
        Vertice<TIPO> vertice = vertices.get(indiceVertice);

        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            int indiceProximo = vertices.indexOf(aresta.getFim());
            if (!visitados.contains(indiceProximo)) {
                dfsVerificarRaiz(indiceProximo, visitados);
            }
        }
    }

    // Método para encontrar e imprimir o VR
    public void encontrarVR() {
        // Executar DFS para obter os tempos de partida
        executarDFS();

        // Encontrar o vértice com o maior tempo de partida
        int candidatoVR = -1;
        int maiorTempoDePartida = -1;

        for (int i = 0; i < vertices.size(); i++) {
            if (tempoPartida[i] > maiorTempoDePartida) {
                maiorTempoDePartida = tempoPartida[i];
                candidatoVR = i;
            }
        }

        // Verificar se o candidato a VR pode alcançar todos os vértices
        if (candidatoVR != -1 && verificarSeEhRaiz(candidatoVR)) {
            System.out.println("O VR é : Vértice " + vertices.get(candidatoVR).getDado());
        } else {
            System.out.println("O grafo não possui VR");
        }
    }

    // Método para verificar se o grafo é bipartido usando DFS
    public boolean verificarBipartido() {
        int n = vertices.size();
        int[] cores = new int[n];
        Arrays.fill(cores, -1); // -1 significa que o vértice ainda não foi colorido

        for (int i = 0; i < n; i++) {
            if (cores[i] == -1) { // Se o vértice não foi colorido, execute DFS a partir dele
                if (!dfsVerificarBipartido(i, 0, cores)) {
                    System.out.println("O grafo NÃO é bipartido.");
                    return false;
                }
            }
        }

        // Se chegou até aqui, o grafo é bipartido
        imprimirParticoes(cores);
        return true;
    }

    // Método recursivo DFS para verificar e colorir o grafo
    private boolean dfsVerificarBipartido(int indiceVertice, int cor, int[] cores) {
        cores[indiceVertice] = cor;

        Vertice<TIPO> vertice = vertices.get(indiceVertice);
        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            int indiceProximo = vertices.indexOf(aresta.getFim());

            if (cores[indiceProximo] == -1) { // Se o próximo vértice não foi colorido
                if (!dfsVerificarBipartido(indiceProximo, 1 - cor, cores)) {
                    return false;
                }
            } else if (cores[indiceProximo] == cores[indiceVertice]) { // Se o próximo vértice tem a mesma cor
                return false;
            }
        }
        return true;
    }

    // Método para imprimir as partições se o grafo for bipartido
    private void imprimirParticoes(int[] cores) {
        ArrayList<TIPO> particao1 = new ArrayList<>();
        ArrayList<TIPO> particao2 = new ArrayList<>();

        for (int i = 0; i < cores.length; i++) {
            if (cores[i] == 0) {
                particao1.add(vertices.get(i).getDado());
            } else {
                particao2.add(vertices.get(i).getDado());
            }
        }

        System.out.println("O grafo É bipartido: Partição 1 " + particao1 + " e Partição 2 " + particao2);
    }


}