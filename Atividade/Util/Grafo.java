package Atividade.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

    public void inicializarTempos() {
        int n = this.vertices.size();
        this.tempoChegada = new int[n];
        this.tempoPartida = new int[n];
        for (int i = 0; i < n; i++) {
            this.tempoChegada[i] = -1;
            this.tempoPartida[i] = -1;
        }
    }

    public ArrayList<Vertice<TIPO>> getVertices() {
        return this.vertices;
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
    private boolean VerificarSeÉRaiz(int indiceVertice) {
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
        if (candidatoVR != -1 && VerificarSeÉRaiz(candidatoVR)) {
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

    // Algoritmo de Prim
    public List<Aresta<TIPO>> algoritmoDePrim() {
        PriorityQueue<Aresta<TIPO>> arestasDisponiveis = new PriorityQueue<>(Comparator.comparing(Aresta::getPeso));
        Set<Vertice<TIPO>> verticesNaArvore = new HashSet<>();
        List<Aresta<TIPO>> mst = new ArrayList<>();

        Vertice<TIPO> verticeInicial = vertices.get(0);
        verticesNaArvore.add(verticeInicial);
        arestasDisponiveis.addAll(verticeInicial.getArestasSaida());

        while (!arestasDisponiveis.isEmpty() && mst.size() < vertices.size() - 1) {
            Aresta<TIPO> menorAresta = arestasDisponiveis.poll();
            Vertice<TIPO> verticeU = menorAresta.getInicio();
            Vertice<TIPO> verticeV = menorAresta.getFim();

            if (verticesNaArvore.contains(verticeU) && verticesNaArvore.contains(verticeV)) {
                continue; // Ignora a aresta que formaria um ciclo
            }

            mst.add(menorAresta);

            Vertice<TIPO> novoVertice = verticesNaArvore.contains(verticeU) ? verticeV : verticeU;
            verticesNaArvore.add(novoVertice);
            arestasDisponiveis.addAll(novoVertice.getArestasSaida());
        }

        return mst;
    }

    // Algoritmo de Kruskal
    public List<Aresta<TIPO>> algoritmoDeKruskal() {
        List<Aresta<TIPO>> mst = new ArrayList<>();
        Collections.sort(arestas, Comparator.comparing(Aresta::getPeso));
        Map<Vertice<TIPO>, Vertice<TIPO>> pais = new HashMap<>();

        for (Vertice<TIPO> vertice : vertices) {
            pais.put(vertice, vertice);
        }

        for (Aresta<TIPO> aresta : arestas) {
            Vertice<TIPO> raizU = encontrarRaiz(pais, aresta.getInicio());
            Vertice<TIPO> raizV = encontrarRaiz(pais, aresta.getFim());

            if (!raizU.equals(raizV)) {
                mst.add(aresta);
                pais.put(raizU, raizV); // União dos conjuntos
            }
        }

        return mst;
    }

    private Vertice<TIPO> encontrarRaiz(Map<Vertice<TIPO>, Vertice<TIPO>> pais, Vertice<TIPO> vertice) {
        while (!pais.get(vertice).equals(vertice)) {
            vertice = pais.get(vertice);
        }
        return vertice;
    }

    // Algoritmo de Boruvka
    public List<Aresta<TIPO>> algoritmoDeBoruvka() {
        List<Aresta<TIPO>> mst = new ArrayList<>();
        Map<Vertice<TIPO>, Vertice<TIPO>> componente = new HashMap<>();

        for (Vertice<TIPO> vertice : vertices) {
            componente.put(vertice, vertice);
        }

        int componentesRestantes = vertices.size();

        while (componentesRestantes > 1) {
            Map<Vertice<TIPO>, Aresta<TIPO>> menorAresta = new HashMap<>();

            for (Aresta<TIPO> aresta : arestas) {
                Vertice<TIPO> componenteU = encontrarRaiz(componente, aresta.getInicio());
                Vertice<TIPO> componenteV = encontrarRaiz(componente, aresta.getFim());

                if (!componenteU.equals(componenteV)) {
                    if (!menorAresta.containsKey(componenteU) || aresta.getPeso() < menorAresta.get(componenteU).getPeso()) {
                        menorAresta.put(componenteU, aresta);
                    }
                    if (!menorAresta.containsKey(componenteV) || aresta.getPeso() < menorAresta.get(componenteV).getPeso()) {
                        menorAresta.put(componenteV, aresta);
                    }
                }
            }

            for (Aresta<TIPO> aresta : menorAresta.values()) {
                Vertice<TIPO> componenteU = encontrarRaiz(componente, aresta.getInicio());
                Vertice<TIPO> componenteV = encontrarRaiz(componente, aresta.getFim());

                if (!componenteU.equals(componenteV)) {
                    mst.add(aresta);
                    componente.put(componenteU, componenteV);
                    componentesRestantes--;
                }
            }
        }
        return mst;
    }

    // Método auxiliar para imprimir as arestas da MST
    public void imprimirMST(List<Aresta<TIPO>> mst) {
        for (Aresta<TIPO> aresta : mst) {
            System.out.println(aresta.getInicio().getDado() + " -- " + aresta.getFim().getDado() + " : " + aresta.getPeso());
        }
    }

    public void lerGrafoDeArquivo(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(";");
            TIPO verticeA = (TIPO) partes[0];
            TIPO verticeB = (TIPO) partes[1];
            double peso = Double.parseDouble(partes[2]);

            adicionarVertice(verticeA);
            adicionarVertice(verticeB);
            adicionarAresta(peso, verticeA, verticeB);
        }
        reader.close();
    }

}