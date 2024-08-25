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

//======================================================================================================================

    public void adicionarVertice(TIPO dado) {
        // Verificar se o vértice já existe
        for (Vertice<TIPO> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                System.out.println("Vértice '" + dado + "' já existe e não será adicionado.");
                return; // Não adiciona o vértice se já existir
            }
        }

        // Se o vértice não existir, adiciona-o à lista
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
        System.out.println("Vértice '" + dado + "' adicionado com sucesso.");
    }

    public void removerVertice(TIPO dado) {
        Vertice<TIPO> vertice = this.getVertice(dado);
        if (vertice != null) {
            // Remover as arestas associadas ao vértice
            boolean arestaRemovida = this.arestas.removeIf(aresta -> aresta.getInicio().equals(vertice) || aresta.getFim().equals(vertice));

            // Remover o vértice em si, se ele ainda existir na lista
            if (this.vertices.remove(vertice)) {
                System.out.println("Vértice '" + dado + "' removido com sucesso.");
            } else {
                System.out.println("Vértice '" + dado + "' não foi encontrado para remoção.");
            }
        } else {
            System.out.println("Vértice '" + dado + "' não encontrado.");
        }
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);

        if (inicio == null) {
            System.out.println("Vértice de início '" + dadoInicio + "' não encontrado.");
            return;
        }
        if (fim == null) {
            System.out.println("Vértice de fim '" + dadoFim + "' não encontrado.");
            return;
        }

        // Verificar se a aresta já existe
        for (Aresta<TIPO> aresta : this.arestas) {
            if ((aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) || (aresta.getInicio().equals(fim) && aresta.getFim().equals(inicio))) {
                System.out.println("Aresta entre '" + dadoInicio + "' e '" + dadoFim + "' já existe.");
                return; // Não adiciona a aresta se já existir
            }
        }

        // Se a aresta não existir, adiciona-a à lista de arestas
        Aresta<TIPO> novaAresta = new Aresta<TIPO>(peso, inicio, fim);
        inicio.adicionarArestaSaida(novaAresta);
        fim.adicionarArestaEntrada(novaAresta);
        this.arestas.add(novaAresta);
        System.out.println("Aresta entre '" + dadoInicio + "' e '" + dadoFim + "' adicionada com sucesso.");
    }

    public void removerAresta(TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);

        if (inicio == null) {
            System.out.println("Vértice de início '" + dadoInicio + "' não encontrado.");
            return;
        }
        if (fim == null) {
            System.out.println("Vértice de fim '" + dadoFim + "' não encontrado.");
            return;
        }

        boolean arestaRemovida = this.arestas.removeIf(aresta -> aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim));
        if (arestaRemovida) {
            inicio.getArestasSaida().removeIf(aresta -> aresta.getFim().equals(fim));
            fim.getArestasEntrada().removeIf(aresta -> aresta.getInicio().equals(inicio));
            System.out.println("Aresta entre '" + dadoInicio + "' e '" + dadoFim + "' removida com sucesso.");
        } else {
            System.out.println("Aresta entre '" + dadoInicio + "' e '" + dadoFim + "' não encontrada.");
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

//======================================================================================================================

    public void ExecutarBFS(){
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

    public void executarDFS() {
        inicializarTempos(); // Inicializa os arrays de tempos
        this.tempo = 0;
        ArrayList<Vertice<TIPO>> visitados = new ArrayList<>(); // Lista para rastrear vértices visitados

        for (int i = 0; i < vertices.size(); i++) {
            if (tempoChegada[i] == -1) {
                List<TIPO> componenteAtual = new ArrayList<>();
                MétodoRecursivoDFS(i, visitados, componenteAtual);

                // Se o componente tem mais de um vértice, imprimimos
                if (componenteAtual.size() > 1) {
                    System.out.print("DFS a partir do vértice " + vertices.get(i).getDado() + ": ");
                    for (TIPO vertice : componenteAtual) {
                        System.out.print(vertice + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private void MétodoRecursivoDFS(int indiceVertice, ArrayList<Vertice<TIPO>> visitados, List<TIPO> componenteAtual) {
        tempo++;
        tempoChegada[indiceVertice] = tempo;

        Vertice<TIPO> vertice = vertices.get(indiceVertice);
        visitados.add(vertice); // Marca o vértice como visitado
        componenteAtual.add(vertice.getDado()); // Adiciona o vértice ao componente atual

        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            int indiceProximo = vertices.indexOf(aresta.getFim());
            if (!visitados.contains(vertices.get(indiceProximo))) { // Verifica se o vértice já foi visitado
                if (tempoChegada[indiceProximo] == -1) {
                    MétodoRecursivoDFS(indiceProximo, visitados, componenteAtual);
                }
            }
        }

        tempo++;
        tempoPartida[indiceVertice] = tempo;
    }

//======================================================================================================================

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

        System.out.print("Resultado -> ");
        // Verificar se o candidato a VR pode alcançar todos os vértices
        if (candidatoVR != -1) {
            ArrayList<Integer> visitados = new ArrayList<>();
            dfsVerificarRaiz(candidatoVR, visitados);

            if (visitados.size() == vertices.size()) {
                System.out.println("O VR é : Vértice " + vertices.get(candidatoVR).getDado());
            } else {
                System.out.println("O grafo não possui VR");
            }
        } else {
            System.out.println("O grafo não possui VR");
        }
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


//======================================================================================================================
    
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

//======================================================================================================================

    // Algoritmo de Prim
    public List<Aresta<TIPO>> algoritmoDePrim() {
        // Lista que armazenará as arestas da árvore geradora mínima (MST)
        List<Aresta<TIPO>> mst = new ArrayList<>();

        // Array para indicar se um vértice está na árvore
        boolean[] naArvore = new boolean[vertices.size()];

        // Inicializa o primeiro vértice (inicialmente na posição 0) como na árvore
        naArvore[0] = true;

        // Enquanto o número de arestas na MST for menor que o número de vértices - 1
        while (mst.size() < vertices.size() - 1) {
            Aresta<TIPO> menorAresta = null;
            int verticeEscolhido = -1;

            // Para cada vértice que já está na árvore, encontramos a menor aresta que conecta a árvore a um novo vértice
            for (int i = 0; i < vertices.size(); i++) {
                if (naArvore[i]) {
                    Vertice<TIPO> vertice = vertices.get(i);
                    for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
                        int indiceDestino = vertices.indexOf(aresta.getFim());

                        // Se o vértice destino ainda não estiver na árvore e a aresta é a menor até agora
                        if (!naArvore[indiceDestino]) {
                            if (menorAresta == null || aresta.getPeso() < menorAresta.getPeso()) {
                                menorAresta = aresta;
                                verticeEscolhido = indiceDestino;
                            }
                        }
                    }
                }
            }

            // Se encontramos uma aresta válida, adicionamos à MST e marcamos o novo vértice
            if (menorAresta != null && verticeEscolhido != -1) {
                mst.add(menorAresta);
                naArvore[verticeEscolhido] = true; // Marca o vértice como parte da árvore
            } else {
                break; // Não existem mais arestas válidas para adicionar, o algoritmo termina
            }
        }

        return mst;
    }

    // Algoritmo de Kruskal
    public List<Aresta<TIPO>> algoritmoDeKruskal() {
        List<Aresta<TIPO>> mst = new ArrayList<>();

        // Ordena as arestas pelo peso
        Collections.sort(arestas, Comparator.comparing(Aresta::getPeso));

        // Array para rastrear o conjunto ao qual cada vértice pertence (a "floresta")
        int[] conjuntos = new int[vertices.size()];

        // Inicialmente, cada vértice pertence ao seu próprio conjunto (árvore independente)
        for (int i = 0; i < vertices.size(); i++) {
            conjuntos[i] = i; // Cada vértice é o representante de seu próprio conjunto
        }

        // Processa cada aresta, adicionando-a à MST se não formar um ciclo
        for (Aresta<TIPO> aresta : arestas) {
            int indiceU = vertices.indexOf(aresta.getInicio());
            int indiceV = vertices.indexOf(aresta.getFim());

            int conjuntoU = encontrar(conjuntos, indiceU);
            int conjuntoV = encontrar(conjuntos, indiceV);

            // Se os vértices pertencem a conjuntos diferentes, adicione a aresta à MST
            if (conjuntoU != conjuntoV) {
                mst.add(aresta);
                unir(conjuntos, conjuntoU, conjuntoV);
            }

            // Se já temos n-1 arestas na MST, podemos parar
            if (mst.size() == vertices.size() - 1) {
                break;
            }
        }

        return mst;
    }

    // Algoritmo de Boruvka
    public List<Aresta<TIPO>> algoritmoDeBoruvka() {
        List<Aresta<TIPO>> mst = new ArrayList<>();

        // Verifica se o grafo é conexo
        if (!isConexo()) {
            System.out.println("O grafo não é conexo, portanto não é possível gerar uma árvore geradora mínima no algoritmo de Boruvka.");
            return mst; // Retorna uma MST vazia
        }

        // Array para rastrear o componente ao qual cada vértice pertence
        int[] componentes = new int[vertices.size()];

        // Inicializa cada vértice como seu próprio componente
        for (int i = 0; i < vertices.size(); i++) {
            componentes[i] = i;  // Cada vértice é o representante de seu próprio componente
        }

        int componentesRestantes = vertices.size();

        // Enquanto existir mais de um componente
        while (componentesRestantes > 1) {
            Aresta<TIPO>[] menorArestaPorComponente = new Aresta[vertices.size()];

            // Encontrar a menor aresta que conecta dois componentes diferentes
            for (Aresta<TIPO> aresta : arestas) {
                int componenteU = encontrarComponente(componentes, vertices.indexOf(aresta.getInicio()));
                int componenteV = encontrarComponente(componentes, vertices.indexOf(aresta.getFim()));

                if (componenteU != componenteV) {
                    // Verifica e armazena a menor aresta para cada componente
                    if (menorArestaPorComponente[componenteU] == null || aresta.getPeso() < menorArestaPorComponente[componenteU].getPeso()) {
                        menorArestaPorComponente[componenteU] = aresta;
                    }
                    if (menorArestaPorComponente[componenteV] == null || aresta.getPeso() < menorArestaPorComponente[componenteV].getPeso()) {
                        menorArestaPorComponente[componenteV] = aresta;
                    }
                }
            }

            // Adicionar as arestas de menor custo à MST e unir os componentes
            for (Aresta<TIPO> aresta : menorArestaPorComponente) {
                if (aresta != null) {
                    int componenteU = encontrarComponente(componentes, vertices.indexOf(aresta.getInicio()));
                    int componenteV = encontrarComponente(componentes, vertices.indexOf(aresta.getFim()));

                    if (componenteU != componenteV) {
                        mst.add(aresta);
                        unirComponentes(componentes, componenteU, componenteV);

                        // Reduz o número de componentes restantes
                        componentesRestantes--;
                    }
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

//======================================================================================================================

    public List<TIPO> gerarCicloMinimo() {
        List<Aresta<TIPO>> mst = this.algoritmoDePrim(); // Use o algoritmo que preferir
        Grafo<TIPO> mstGrafo = new Grafo<>();

        // Adicionar vértices e arestas ao mstGrafo sem duplicações e sem mensagens
        for (Aresta<TIPO> aresta : mst) {
            TIPO inicioDado = aresta.getInicio().getDado();
            TIPO fimDado = aresta.getFim().getDado();

            // Adicionar vértices somente se ainda não existirem
            if (!mstGrafo.pesquisarVertice(inicioDado)) {
                mstGrafo.adicionarVerticeSemMensagem(inicioDado);
            }
            if (!mstGrafo.pesquisarVertice(fimDado)) {
                mstGrafo.adicionarVerticeSemMensagem(fimDado);
            }

            // Adicionar a aresta somente se ainda não existir
            if (!mstGrafo.pesquisarAresta(inicioDado, fimDado)) {
                mstGrafo.adicionarArestaSemMensagem(aresta.getPeso(), inicioDado, fimDado);
            }
        }

        List<TIPO> ciclo = new ArrayList<>();
        ArrayList<TIPO> visitados = new ArrayList<>();
        Vertice<TIPO> verticeInicial = mstGrafo.getVertices().get(0);
        gerarCicloDFS(verticeInicial, ciclo, visitados);
        ciclo.add(verticeInicial.getDado()); // Fechar o ciclo

        return ciclo;
    }

    // Função auxiliar para DFS na MST
    private void gerarCicloDFS(Vertice<TIPO> vertice, List<TIPO> ciclo, ArrayList<TIPO> visitados) {
        visitados.add(vertice.getDado());
        ciclo.add(vertice.getDado());

        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            Vertice<TIPO> proximo = aresta.getFim();
            if (!visitados.contains(proximo.getDado())) {
                gerarCicloDFS(proximo, ciclo, visitados);
            }
        }
    }


    public void imprimirCiclo(List<TIPO> ciclo) {
        System.out.println("Ciclo Mínimo Gerado:");
        for (int i = 0; i < ciclo.size() - 1; i++) {
            System.out.println(ciclo.get(i) + " -> " + ciclo.get(i + 1));
        }
    }


//======================================================================================================================

    public void lerGrafoDeArquivo(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            linha = linha.trim(); // Remove espaços em branco no início e no final
            if (linha.isEmpty()) {
                continue; // Ignora linhas vazias
            }

            String[] partes = linha.split(";");
            if (partes.length != 3) {
                System.err.println("Linha malformada: " + linha);
                continue; // Ignora linhas malformadas
            }

            TIPO verticeA = (TIPO) partes[0];
            TIPO verticeB = (TIPO) partes[1];
            double peso = Double.parseDouble(partes[2]);

            adicionarVerticeSemMensagem(verticeA);
            adicionarVerticeSemMensagem(verticeB);
            adicionarArestaSemMensagem(peso, verticeA, verticeB);
        }
        reader.close();
    }

    // Função auxiliar para encontrar o representante do conjunto/componente ao qual um vértice pertence
    private int encontrar(int[] representante, int vertice) {
        // Segue o encadeamento até encontrar o representante do conjunto/componente
        if (representante[vertice] != vertice) {
            representante[vertice] = encontrar(representante, representante[vertice]); // Compressão de caminho
        }
        return representante[vertice];
    }

    // Função auxiliar para unir conjuntos/componentes
    private void unir(int[] representante, int conjuntoU, int conjuntoV) {
        // Unir todos os vértices do conjunto/componente V ao conjunto/componente U
        for (int i = 0; i < representante.length; i++) {
            if (representante[i] == conjuntoV) {
                representante[i] = conjuntoU;
            }
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

    public void imprimirTempos() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i).getDado() + " (Chegada: " + tempoChegada[i] + ", Partida: " + tempoPartida[i] + ")");
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

    // Função para adicionar vértice sem mensagem
    public void adicionarVerticeSemMensagem(TIPO dado) {
        Vertice<TIPO> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    // Função para adicionar aresta sem mensagem
    public void adicionarArestaSemMensagem(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);

        Aresta<TIPO> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    // Verifica se o grafo é conexo usando DFS
    private boolean isConexo() {
        ArrayList<Vertice<TIPO>> visitados = new ArrayList<>();
        dfsConectividade(vertices.get(0), visitados);
        return visitados.size() == vertices.size();
    }

    // Realiza DFS para verificar a conectividade
    private void dfsConectividade(Vertice<TIPO> vertice, ArrayList<Vertice<TIPO>> visitados) {
        visitados.add(vertice);
        for (Aresta<TIPO> aresta : vertice.getArestasSaida()) {
            Vertice<TIPO> proximo = aresta.getFim();
            if (!visitados.contains(proximo)) {
                dfsConectividade(proximo, visitados);
            }
        }
    }


    // Encontra o componente ao qual o vértice pertence
    private int encontrarComponente(int[] componentes, int vertice) {
        if (componentes[vertice] != vertice) {
            componentes[vertice] = encontrarComponente(componentes, componentes[vertice]); // Compressão de caminho
        }
        return componentes[vertice];
    }

    // Une dois componentes em um
    private void unirComponentes(int[] componentes, int componenteU, int componenteV) {
        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] == componenteV) {
                componentes[i] = componenteU;
            }
        }
    }

}