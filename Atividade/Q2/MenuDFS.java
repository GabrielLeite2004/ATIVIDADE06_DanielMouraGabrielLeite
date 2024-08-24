package Atividade.Q2;

import Atividade.Q1.Grafo;

import java.util.Scanner;

public class MenuDFS {

    private Grafo<String> grafo;
    private Scanner scanner;

    public MenuDFS() {
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
            System.out.println("5. Executar DFS e Obter Tempos de Chegada e Partida");
            System.out.println("6. Imprimir Grafo");
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
                    executarDFS();
                    break;
                case 6:
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

    private void executarDFS() {
        System.out.println("Executando DFS e obtendo tempos de chegada e partida...");
        grafo.executarDFS();
    }

    private void imprimirGrafo() {
        System.out.println("Grafo:");
        grafo.imprimirGrafo();
    }

    public static void main(String[] args) {
        MenuDFS menu = new MenuDFS();
        menu.exibirMenu();
    }
}
