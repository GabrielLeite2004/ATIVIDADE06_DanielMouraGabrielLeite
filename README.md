# Projeto de Implementação de Grafos #

Este projeto consiste na implementação de um sistema de manipulação de grafos em Java. Ele suporta operações como adicionar e remover vértices e arestas, realizar buscas em largura (BFS) e profundidade (DFS), verificar se o grafo é bipartido, encontrar o vértice raiz (VR) e gerar a árvore geradora mínima (MST) usando os algoritmos de Prim, Kruskal e Boruvka. Além disso, o projeto inclui a funcionalidade de gerar um ciclo mínimo baseado na MST.

## Funcionalidades ##

### Operações Básicas ###

- *Adicionar Vértice*: Adiciona um novo vértice ao grafo, verificando previamente se o vértice já existe para evitar duplicações.
- *Remover Vértice*: Remove um vértice existente e todas as arestas associadas a ele. Informa se o vértice não foi encontrado.
- *Adicionar Aresta*: Adiciona uma nova aresta entre dois vértices, garantindo que a aresta não exista previamente.
- *Remover Aresta*: Remove uma aresta entre dois vértices. Informa se a aresta ou os vértices não foram encontrados.
- *Pesquisar Vértice*: Verifica se um determinado vértice existe no grafo.
- *Pesquisar Aresta*: Verifica se uma determinada aresta existe entre dois vértices.
- *Obter Adjacentes*: Retorna todos os vértices adjacentes a um determinado vértice.
- *Imprimir Grafo*: Exibe a estrutura do grafo, mostrando todos os vértices e suas conexões adjacentes.

### Operações de Busca ###

- *Busca em Largura (BFS)*: Percorre o grafo começando de um vértice inicial, visitando todos os vértices adjacentes antes de mover-se para os próximos níveis.
- *Busca em Profundidade (DFS)*: Percorre o grafo buscando o caminho mais profundo a partir de cada vértice. Esta função agora também imprime cada componente conectado e a ordem em que os vértices foram visitados.

### Verificação e Análise ###

- *Verificar Grafo Bipartido*: Usa DFS para determinar se o grafo pode ser dividido em dois conjuntos disjuntos, onde não há arestas entre vértices do mesmo conjunto.
- *Encontrar Vértice Raiz (VR)*: Encontra e imprime o vértice que, a partir de uma busca em profundidade, é capaz de alcançar todos os outros vértices no grafo.

### Geração de Árvores Geradoras Mínimas (MST) ###

- *Algoritmo de Prim*: Gera a MST utilizando o algoritmo de Prim, adicionando arestas de menor peso que conectam um novo vértice à árvore já existente.
- *Algoritmo de Kruskal*: Gera a MST utilizando o algoritmo de Kruskal, que conecta componentes diferentes do grafo até que todos os vértices estejam conectados em uma única árvore.
- *Algoritmo de Boruvka*: Gera a MST utilizando o algoritmo de Boruvka, adicionando as menores arestas para cada componente até que todos os componentes sejam unidos em uma única árvore.

### Ciclo Mínimo ###

- *Gerar Ciclo Mínimo*: A partir da MST gerada, este método constrói um ciclo mínimo que passa por todos os vértices da árvore. Utiliza funções auxiliares de DFS e evita a duplicação de vértices e arestas durante o processo.

## Implementação Técnica ##

### Estrutura de Dados ###

- **Vertice**: Representa um vértice no grafo e contém métodos para gerenciar suas arestas de saída e entrada.
- **Aresta**: Representa uma aresta que conecta dois vértices no grafo, associada a um peso.

### Funções Auxiliares

- *Pesquisar Vértice e Aresta*: Funções para verificar a existência de um vértice ou aresta no grafo.
- *Obter Adjacentes*: Retorna uma lista dos vértices adjacentes a um dado vértice.
- *Imprimir Grafo*: Exibe a estrutura atual do grafo, listando cada vértice seguido de seus vértices adjacentes.

## Como Usar ##

### Execução do Projeto ###

Para executar o projeto, compile o código Java e execute o programa principal. O sistema apresentará um menu interativo onde você pode selecionar as operações que deseja realizar no grafo.

### Entrada de Dados ###

Você pode fornecer a estrutura do grafo através de um arquivo de texto formatado da seguinte maneira:

```
A;B;1.0
A;C;2.0
B;D;3.0
```

Cada linha representa uma aresta entre dois vértices com um peso associado.

### Geração de Ciclo Mínimo (Baseado no Algoritmo Prim) ###

Após gerar a MST usando qualquer um dos algoritmos disponíveis, você pode gerar e visualizar o ciclo mínimo que percorre todos os vértices da árvore.

## Exemplo de Saída

Aqui está um exemplo de saída para uma execução que utiliza DFS, verifica se o grafo é bipartido e gera a MST usando o algoritmo de Prim:

Execução DFS: 
DFS a partir do vértice A: A B D 
DFS a partir do vértice E: E F G 
Resultado -> O VR é: Vértice A
O grafo É bipartido: Partição 1 [A, C, E] e Partição 2 [B, D, F]
MST Gerada:
A -- B : 1.0
A -- C : 2.0
B -- D : 3.0

## Contribuição ##

Sinta-se à vontade para abrir issues e enviar pull requests para melhorias e correções no projeto.

## Licença ##

Este projeto é licenciado sob a.
