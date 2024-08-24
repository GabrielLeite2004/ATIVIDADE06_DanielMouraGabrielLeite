package Atividade.Q1;

import java.util.Scanner;

public class MenuGrafo {

    private Grafo<String> grafo;
    private Scanner scanner;

    public MenuGrafo() {
        this.grafo = new Grafo<>();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- Menu de Operações no Grafo ---");
            System.out.println("1. Adicionar Vértice");
            System.out.println("2. Remover Vértice");
            System.out.println("3. Adicionar Aresta");
            System.out.println("4. Remover Aresta");
            System.out.println("5. Pesquisar Vértice");
            System.out.println("6. Pesquisar Aresta");
            System.out.println("7. Obter Adjacentes de um Vértice");
            System.out.println("8. Imprimir Grafo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

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

    private void adicionarVertice() {
        System.out.print("Digite o valor do vértice: ");
        String dado = scanner.nextLine();
        grafo.adicionarVertice(dado);
        System.out.println("Vértice '" + dado + "' adicionado com sucesso.");
    }

    private void removerVertice() {
        System.out.print("Digite o valor do vértice a ser removido: ");
        String dado = scanner.nextLine();
        grafo.removerVertice(dado);
        System.out.println("Vértice '" + dado + "' removido com sucesso.");
    }

    private void adicionarAresta() {
        System.out.print("Digite o valor do vértice de início: ");
        String inicio = scanner.nextLine();
        System.out.print("Digite o valor do vértice de fim: ");
        String fim = scanner.nextLine();
        System.out.print("Digite o peso da aresta: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();  // Consumir a nova linha

        grafo.adicionarAresta(peso, inicio, fim);
        System.out.println("Aresta entre '" + inicio + "' e '" + fim + "' adicionada com sucesso.");
    }

    private void removerAresta() {
        System.out.print("Digite o valor do vértice de início: ");
        String inicio = scanner.nextLine();
        System.out.print("Digite o valor do vértice de fim: ");
        String fim = scanner.nextLine();

        grafo.removerAresta(inicio, fim);
        System.out.println("Aresta entre '" + inicio + "' e '" + fim + "' removida com sucesso.");
    }

    private void pesquisarVertice() {
        System.out.print("Digite o valor do vértice a ser pesquisado: ");
        String dado = scanner.nextLine();
        boolean existe = grafo.pesquisarVertice(dado);
        System.out.println("Vértice '" + dado + "' " + (existe ? "existe" : "não existe") + " no grafo.");
    }

    private void pesquisarAresta() {
        System.out.print("Digite o valor do vértice de início: ");
        String inicio = scanner.nextLine();
        System.out.print("Digite o valor do vértice de fim: ");
        String fim = scanner.nextLine();
        boolean existe = grafo.pesquisarAresta(inicio, fim);
        System.out.println("Aresta entre '" + inicio + "' e '" + fim + "' " + (existe ? "existe" : "não existe") + " no grafo.");
    }

    private void obterAdjacentes() {
        System.out.print("Digite o valor do vértice: ");
        String dado = scanner.nextLine();
        System.out.println("Adjacentes de '" + dado + "': " + grafo.obterAdjacentes(dado));
    }

    private void imprimirGrafo() {
        System.out.println("Grafo:");
        grafo.imprimirGrafo();
    }
}
