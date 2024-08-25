Aqui está um exemplo de um arquivo README que você pode usar para postar no GitHub e compartilhar com o seu professor:

---

# Projeto de Grafo em Java

## Descrição

Este projeto implementa um grafo genérico em Java, incluindo várias funcionalidades e algoritmos clássicos de grafos. O grafo é representado utilizando listas de adjacência e suporta operações como inserção e remoção de vértices e arestas, busca em profundidade (DFS), busca em largura (BFS), verificação de bipartição, identificação de vértice raiz (VR), e geração de árvore geradora mínima (MST) utilizando os algoritmos de Prim, Kruskal e Boruvka. Além disso, também é possível gerar um ciclo mínimo baseado na MST.

## Estrutura do Projeto

O projeto está dividido nas seguintes partes:

1. **Adições e Remoções**:
   - **Adicionar Vértice**: Permite a adição de um vértice ao grafo.
   - **Remover Vértice**: Remove um vértice existente do grafo.
   - **Adicionar Aresta**: Adiciona uma aresta entre dois vértices, com um peso definido.
   - **Remover Aresta**: Remove uma aresta existente entre dois vértices.

2. **Buscar**:
   - **Busca em Largura (BFS)**: Executa a busca em largura a partir de um vértice inicial.
   - **Busca em Profundidade (DFS)**: Executa a busca em profundidade.
   - **Imprimir Tempos de Chegada e Partida (DFS)**: Exibe os tempos de chegada e partida de cada vértice após a execução da DFS.

3. **Bipartido e Raiz**:
   - **Verificar se o Grafo é Bipartido**: Determina se o grafo pode ser bipartido em dois conjuntos disjuntos.
   - **Encontrar Vértice Raiz (VR)**: Identifica se existe um vértice raiz no grafo, do qual todos os outros vértices podem ser alcançados.

4. **Algoritmos de MST**:
   - **Gerar MST com Prim**: Gera a árvore geradora mínima utilizando o algoritmo de Prim.
   - **Gerar MST com Kruskal**: Gera a árvore geradora mínima utilizando o algoritmo de Kruskal.
   - **Gerar MST com Boruvka**: Gera a árvore geradora mínima utilizando o algoritmo de Boruvka.

5. **Gerar Ciclo Mínimo com Base na MST**:
   - Gera um ciclo mínimo que visita todos os vértices, baseado na MST.

## Estrutura de Diretórios

```
Atividade/
├── Arquivos/
│   └── Arquivo.txt  # Arquivo de entrada contendo os vértices e arestas do grafo
├── Util/
│   ├── Grafo.java   # Implementação da classe de Grafo com todas as funcionalidades
│   ├── Vertice.java # Classe que representa um vértice no grafo
│   ├── Aresta.java  # Classe que representa uma aresta no grafo
│   └── Menu.java    # Implementação do menu de interação com o usuário
└── README.md        # Arquivo de documentação do projeto
```

## Como Executar

1. **Clone o Repositório**:
   - Use o comando `git clone <URL do repositório>` para clonar este repositório em sua máquina local.

2. **Configuração do Arquivo de Entrada**:
   - No diretório `Atividade/Arquivos/`, edite ou crie o arquivo `Arquivo.txt` com os vértices e arestas do grafo no formato:
     ```
     VerticeA;VerticeB;peso
     VerticeA;VerticeC;peso
     VerticeB;VerticeD;peso
     ...
     ```

3. **Compilação e Execução**:
   - Compile as classes Java usando um compilador Java (como `javac`).
   - Execute a classe `Menu` para interagir com o grafo via linha de comando.

4. **Interação com o Menu**:
   - O menu principal permite navegar por submenus para adicionar/remover vértices e arestas, realizar buscas, verificar bipartição e raiz, gerar MSTs e ciclos mínimos.

## Exemplo de Uso

Ao executar o programa, você verá o menu principal:

```
--- Menu de Operações no Grafo ---
1. Adições e Remoções
2. Buscar
3. Bipartido e Raiz
4. Algoritmos de MST
5. Gerar Ciclo Mínimo com Base na MST
0. Sair
Escolha uma opção:
```

Escolha uma das opções para acessar as funcionalidades correspondentes.

## Notas Importantes

- **Tratamento de Erros**: O programa está preparado para lidar com entradas malformadas no arquivo de texto, ignorando linhas malformadas e emitindo avisos.
- **Recarregamento Automático**: O grafo é recarregado automaticamente do arquivo de entrada a cada operação, garantindo que sempre trabalhe com os dados mais recentes.

## Contato

Para dúvidas ou mais informações, entre em contato com [Seu Nome] via [Seu Email].

---

### Observação:

Certifique-se de ajustar o arquivo `Arquivo.txt` no diretório `Atividade/Arquivos/` para incluir as arestas e vértices que deseja testar. As instruções acima pressupõem que você esteja familiarizado com a estrutura básica de um projeto Java e a execução via linha de comando. 

Este README foi projetado para fornecer uma visão geral clara e completa do projeto, permitindo que seu professor compreenda rapidamente as funcionalidades e estrutura do código.
