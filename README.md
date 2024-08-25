# Projeto de Implementação de Grafos em Java

Este projeto consiste na implementação de um sistema para manipulação de grafos em Java, suportando operações como adicionar e remover vértices e arestas, realizar buscas em largura (BFS) e profundidade (DFS), verificar se o grafo é bipartido, encontrar o vértice raiz (VR) e gerar árvores geradoras mínimas (MST) usando os algoritmos de Prim, Kruskal e Boruvka. Além disso, o projeto inclui a funcionalidade de gerar um ciclo mínimo baseado na MST.

## Funcionalidades ###

### Operações Básicas

- Adicionar Vértice: Adiciona um novo vértice ao grafo, garantindo que o vértice não seja duplicado.
- Remover Vértice: Remove um vértice existente e todas as arestas associadas a ele, informando o usuário caso o vértice não seja encontrado.
- Adicionar Aresta: Adiciona uma nova aresta entre dois vértices, verificando previamente se a aresta já existe.
- Remover Aresta: Remove uma aresta entre dois vértices, informando o usuário caso a aresta ou os vértices não sejam encontrados.

### Operações de Busca

- Busca em Largura (BFS): Realiza a busca em largura a partir de um vértice inicial, visitando todos os vértices adjacentes antes de passar para o próximo nível.
- Busca em Profundidade (DFS): Realiza a busca em profundidade a partir de um vértice, percorrendo o caminho mais longo possível antes de retroceder. A função agora também imprime cada componente conectado e a ordem em que os vértices foram visitados.

### Verificação e Análise

- Verificar Grafo Bipartido: Verifica, usando DFS, se o grafo pode ser dividido em dois conjuntos disjuntos, onde não há arestas entre vértices do mesmo conjunto.
- Encontrar Vértice Raiz (VR): Identifica e imprime o vértice que, através de uma busca em profundidade, pode alcançar todos os outros vértices no grafo.

### Geração de Árvores Geradoras Mínimas (MST)

- Algoritmo de Prim: Gera a MST utilizando o algoritmo de Prim, selecionando arestas de menor peso que conectam novos vértices à árvore já formada.
- Algoritmo de Kruskal: Gera a MST utilizando o algoritmo de Kruskal, unindo diferentes componentes do grafo até que todos os vértices estejam conectados em uma única árvore.
- Algoritmo de Boruvka: Gera a MST utilizando o algoritmo de Boruvka, adicionando as menores arestas para cada componente até que todos os componentes sejam unidos em uma única árvore.

### Ciclo Mínimo

- Gerar Ciclo Mínimo: A partir da MST gerada, este método constrói um ciclo mínimo que percorre todos os vértices da árvore. Utiliza funções auxiliares de DFS e evita duplicações de vértices e arestas durante o processo.

## Estrutura de Dados

- Vertice: Representa um vértice no grafo, contendo métodos para gerenciar suas arestas de entrada e saída.
- Aresta: Representa uma aresta que conecta dois vértices no grafo, associada a um peso.


### Execução ###

Compile o código Java e execute o programa principal. O sistema apresentará um menu interativo onde você pode selecionar as operações desejadas.

A;B;1.0 A;C;2.0 B;D;3.0

Cada linha representa uma aresta entre dois vértices com um peso associado.

### Geração de Ciclo Mínimo

Após gerar a MST usando um dos algoritmos disponíveis, você pode gerar e visualizar o ciclo mínimo que percorre todos os vértices da árvore.

## Exemplo de Saída

Aqui está um exemplo de execução que realiza DFS, verifica se o grafo é bipartido e gera a MST usando o algoritmo de Prim:

Execução DFS: DFS a partir do vértice A: A B D DFS a partir do vértice E: E F G Resultado -> O VR é: Vértice A O grafo É bipartido: Partição 1 [A, C, E] e Partição 2 [B, D, F] MST Gerada: A -- B : 1.0 A -- C : 2.0 B -- D : 3.0


## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests com melhorias e correções.

### Entrada de Dados

A estrutura do grafo pode ser fornecida por um arquivo de texto com o seguinte formato: txt

