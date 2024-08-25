package Atividade.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static class MenuGrafo {

        private static Grafo<String> grafo = new Grafo<>();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            carregarGrafo(); // Carrega o grafo do arquivo no início
            int opcao;
            do {
                System.out.println("\n--- Menu de Operações no Grafo ---");
                System.out.println("1. Adições e Remoções");
                System.out.println("2. Buscar");
                System.out.println("3. Bipartido e Raiz");
                System.out.println("4. Algoritmos");
                System.out.println("5. Gerar Ciclo Mínimo com Base na MST");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        menuAdicoesRemocoes();
                        break;
                    case 2:
                        menuBuscar();
                        break;
                    case 3:
                        menuBipartidoRaiz();
                        break;
                    case 4:
                        menuAlgoritmosMST();
                        break;
                    case 5:
                        gerarCicloMinimo();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 0);
        }

        private static void menuAdicoesRemocoes() {
            int opcao;
            do {
                System.out.println("\n--- Adições, Remoções e Pesquisas ---");
                System.out.println("1. Adicionar Vértice");
                System.out.println("2. Remover Vértice");
                System.out.println("3. Adicionar Aresta");
                System.out.println("4. Remover Aresta");
                System.out.println("5. Pesquisar Vértice");
                System.out.println("6. Pesquisar Aresta");
                System.out.println("7. Obter Adjacentes");
                System.out.println("8. Imprimir Grafo");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        adicionarVertice();
                        break;
                    case 2:
                        removerVertice();
                        break;
                    case 3:
                        adicionarAresta();
                        break;
                    case 4:
                        removerAresta();
                        break;
                    case 5:
                        pesquisarVertice();
                        break;
                    case 6:
                        pesquisarAresta();
                        break;
                    case 7:
                        obterAdjacentes();
                        break;
                    case 8:
                        imprimirGrafo();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 0);
        }

        private static void menuBuscar() {
            int opcao;
            do {
                System.out.println("\n--- Buscar ---");
                System.out.println("1. Executar Busca em Largura (BFS)");
                System.out.println("2. Executar Busca em Profundidade (DFS)");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        buscaEmLargura();
                        break;
                    case 2:
                        buscaEmProfundidade();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 0);
        }

        private static void menuBipartidoRaiz() {
            int opcao;
            do {
                System.out.println("\n--- Bipartido e Raiz ---");
                System.out.println("1. Verificar se o Grafo é Bipartido");
                System.out.println("2. Encontrar Vértice Raiz (VR)");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        verificarBipartido();
                        break;
                    case 2:
                        encontrarVR();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 0);
        }

        private static void menuAlgoritmosMST() {
            int opcao;
            do {
                System.out.println("\n--- Algoritmos de MST ---");
                System.out.println("1. Gerar MST com Prim");
                System.out.println("2. Gerar MST com Kruskal");
                System.out.println("3. Gerar MST com Boruvka");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        executarPrim();
                        break;
                    case 2:
                        executarKruskal();
                        break;
                    case 3:
                        executarBoruvka();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 0);
        }

//======================================================================================================================

        private static void carregarGrafo() {
            String caminhoArquivo = "Atividade\\Arquivos\\Arquivo";
            try {
                grafo.lerGrafoDeArquivo(caminhoArquivo);
                System.out.println("Grafo carregado com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }

//======================================================================================================================

        private static void adicionarVertice() {
            System.out.print("Digite o nome do vértice: ");
            String vertice = scanner.nextLine();
            grafo.adicionarVertice(vertice);
            System.out.println("Vértice adicionado com sucesso!");
        }

        private static void removerVertice() {
            System.out.print("Digite o nome do vértice a ser removido: ");
            String vertice = scanner.nextLine();
            grafo.removerVertice(vertice);
        }

        private static void adicionarAresta() {
            System.out.print("Digite o vértice de início: ");
            String inicio = scanner.nextLine();
            System.out.print("Digite o vértice de fim: ");
            String fim = scanner.nextLine();
            System.out.print("Digite o peso da aresta: ");
            double peso = scanner.nextDouble();
            scanner.nextLine(); // Consumir a nova linha

            grafo.adicionarAresta(peso, inicio, fim);
            System.out.println("Aresta adicionada com sucesso!");
        }

        private static void removerAresta() {
            System.out.print("Digite o vértice de início: ");
            String inicio = scanner.nextLine();
            System.out.print("Digite o vértice de fim: ");
            String fim = scanner.nextLine();

            grafo.removerAresta(inicio, fim);
        }

        private static void pesquisarVertice() {
            System.out.print("Digite o nome do vértice a ser pesquisado: ");
            String vertice = scanner.nextLine();
            boolean encontrado = grafo.pesquisarVertice(vertice);
            if (encontrado) {
                System.out.println("Vértice " + vertice + " encontrado no grafo.");
            } else {
                System.out.println("Vértice " + vertice + " não encontrado no grafo.");
            }
        }

        private static void pesquisarAresta() {
            System.out.print("Digite o vértice de início: ");
            String inicio = scanner.nextLine();
            System.out.print("Digite o vértice de fim: ");
            String fim = scanner.nextLine();
            boolean encontrada = grafo.pesquisarAresta(inicio, fim);
            if (encontrada) {
                System.out.println("Aresta de " + inicio + " para " + fim + " encontrada no grafo.");
            } else {
                System.out.println("Aresta de " + inicio + " para " + fim + " não encontrada no grafo.");
            }
        }

        private static void obterAdjacentes() {
            System.out.print("Digite o nome do vértice: ");
            String vertice = scanner.nextLine();
            ArrayList<Vertice<String>> adjacentes = grafo.obterAdjacentes(vertice);

            if (adjacentes.isEmpty()) {
                System.out.println("Nenhum vértice adjacente encontrado ou o vértice não existe.");
            } else {
                System.out.println("Vértices adjacentes a '" + vertice + "':");
                for (Vertice<String> adjacente : adjacentes) {
                    System.out.println(adjacente.getDado());
                }
            }
        }

        private static void imprimirGrafo() {
            System.out.println("Imprimindo o Grafo:");
            grafo.imprimirGrafo();
        }

//======================================================================================================================

        private static void buscaEmLargura() {
            System.out.println("Executando busca em largura (BFS):");
            grafo.ExecutarBFS();
        }

        private static void buscaEmProfundidade() {
            System.out.println("Executando busca em profundidade (DFS):");
            grafo.executarDFS();
        }

//======================================================================================================================

        private static void verificarBipartido() {
            System.out.println("Verificando se o grafo é bipartido:");
            grafo.verificarBipartido();
        }

        private static void encontrarVR() {
            System.out.println("Verificando se há um vértice raiz (VR):");
            grafo.encontrarVR();
        }

//======================================================================================================================

        private static void executarPrim() {
            System.out.println("Gerando Árvore Geradora Mínima com Prim:");
            List<Aresta<String>> mst = grafo.algoritmoDePrim();
            grafo.imprimirMST(mst);
        }

        private static void executarKruskal() {
            System.out.println("Gerando Árvore Geradora Mínima com Kruskal:");
            List<Aresta<String>> mst = grafo.algoritmoDeKruskal();
            grafo.imprimirMST(mst);
        }

        private static void executarBoruvka() {
            System.out.println("Gerando Árvore Geradora Mínima com Boruvka:");
            List<Aresta<String>> mst = grafo.algoritmoDeBoruvka();
            grafo.imprimirMST(mst);
        }

//======================================================================================================================

        private static void gerarCicloMinimo() {
            System.out.println("Gerando Ciclo Mínimo com base na MST:");
            List<String> ciclo = grafo.gerarCicloMinimo();
            grafo.imprimirCiclo(ciclo);
        }
    }
}
