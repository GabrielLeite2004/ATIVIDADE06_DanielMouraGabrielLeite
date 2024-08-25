# Projeto de Implementação de Grafos

Este projeto foi desenvolvido como parte de uma atividade acadêmica que envolve a implementação de diversas operações em grafos, incluindo a adição e remoção de vértices e arestas, pesquisa de vértices e arestas, obtenção de adjacentes de um vértice, execução de algoritmos de busca (BFS e DFS), verificação de grafos bipartidos, identificação de vértices raiz (VR), e execução de algoritmos de árvores geradoras mínimas (Prim, Kruskal, Boruvka), além de gerar ciclos mínimos com base na MST.

## Funcionalidades Implementadas

### 1. Adições e Remoções
- **Adicionar Vértice**: Permite adicionar um novo vértice ao grafo, garantindo que o vértice não exista previamente.
- **Remover Vértice**: Remove um vértice existente do grafo, juntamente com todas as arestas associadas a ele.
- **Adicionar Aresta**: Adiciona uma aresta entre dois vértices, com um peso específico. Garante que a aresta não exista previamente.
- **Remover Aresta**: Remove uma aresta existente entre dois vértices.
- **Pesquisar Vértice**: Verifica se um vértice específico existe no grafo.
- **Pesquisar Aresta**: Verifica se uma aresta específica existe entre dois vértices no grafo.

### 2. Operações de Pesquisa e Busca
- **Busca em Largura (BFS)**: Executa a busca em largura no grafo a partir de um vértice inicial.
- **Busca em Profundidade (DFS)**: Executa a busca em profundidade no grafo, calculando os tempos de chegada e partida de cada vértice.
- **Imprimir Tempos de Chegada e Partida**: Imprime os tempos de chegada e partida de cada vértice após a execução do DFS.

### 3. Verificação de Propriedades
- **Verificar se o Grafo é Bipartido**: Verifica se o grafo pode ser dividido em dois conjuntos disjuntos de vértices.
- **Encontrar Vértice Raiz (VR)**: Identifica se existe um vértice raiz no grafo, que possa alcançar todos os outros vértices.

### 4. Algoritmos de Árvores Geradoras Mínimas (MST)
- **Gerar MST com Prim**: Executa o algoritmo de Prim para gerar a árvore geradora mínima.
- **Gerar MST com Kruskal**: Executa o algoritmo de Kruskal para gerar a árvore geradora mínima.
- **Gerar MST com Boruvka**: Executa o algoritmo de Boruvka para gerar a árvore geradora mínima.

### 5. Ciclo Mínimo com Base na MST
- **Gerar Ciclo Mínimo**: Gera um ciclo mínimo que visita todos os vértices do grafo, utilizando a MST como base.

### 6. Obter Adjacentes de um Vértice
- **Obter Adjacentes**: Dado um vértice, exibe todos os vértices adjacentes a ele.

## Como Executar

1. Clone o repositório para sua máquina local.
2. Compile e execute o programa principal (`MenuGrafo`).
3. Navegue pelas opções do menu para realizar as operações desejadas.

## Estrutura do Código

O código está organizado de maneira modular, com funções específicas para cada operação do grafo. O menu principal oferece uma interface interativa para acessar essas funcionalidades.

### Exemplo de Uso

```plaintext
--- Menu de Operações no Grafo ---
1. Adicionar Vértice
2. Remover Vértice
3. Adicionar Aresta
4. Remover Aresta
5. Imprimir Grafo
6. Obter Adjacentes de um Vértice
0. Sair
Escolha uma opção:
```

### Requisitos

- Java 8 ou superior
- IDE compatível ou linha de comando

## Contribuindo

Sinta-se à vontade para abrir issues e pull requests para melhorar o projeto.

## Licença

Este projeto está sob a licença MIT.
