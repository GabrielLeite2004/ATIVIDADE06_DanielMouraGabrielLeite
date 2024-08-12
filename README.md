# Projeto de Grafos - Universidade IFMA

## Descrição
Este projeto foi desenvolvido como parte do curso de Sistemas de Informação na Universidade IFMA. O objetivo é implementar e manipular grafos com diferentes algoritmos, aplicando conceitos como DFS, MST, verificação de grafo bipartido, e outros.

## Estrutura do Projeto
O projeto está organizado em pacotes/módulos separados para cada questão, conforme descrito abaixo:

- Questão 1: Implementação básica de um grafo com funcionalidades de inserção, remoção, pesquisa, obtenção de adjacentes e impressão.
- Questão 2: Execução do DFS para calcular e imprimir os tempos de chegada e partida dos vértices.
- Questão 3: Verificação da existência de um Vértice Raiz (VR) no grafo.
- Questão 4: Verificação se o grafo é bipartido e, em caso positivo, exibição das partições.
- Questão 5: Implementação dos algoritmos de Prim, Kruskal e Boruvka para encontrar a Árvore Geradora Mínima (MST) do grafo.
- Questão 6: Solução para encontrar o ciclo mínimo que visita todos os vértices, utilizando um dos algoritmos de MST.

Cada questão possui seu próprio `main()` para executar as funcionalidades associadas.

## Funcionalidades Principais:

### Questão 1: Grafo Básico ###
- Inserir Vértice
- Remover Vértice
- Inserir Aresta
- Remover Aresta
- Pesquisar Vértice
- Pesquisar Aresta
- Obter Adjacentes
- Imprimir Grafo

### Questão 2: DFS com Tempo de Chegada e Partida ###
- DFS: Percorre o grafo registrando os tempos de chegada e partida de cada vértice.

### Questão 3: Vértice Raiz (VR) ###
- Verificação de VR: Identifica se existe um vértice raiz a partir do qual todos os outros vértices podem ser alcançados.

### Questão 4: Grafo Bipartido
- Verificação de Bipartição: Determina se o grafo é bipartido e exibe as partições.

### Questão 5: Árvore Geradora Mínima (MST)
- Prim: Algoritmo de Prim para encontrar a MST.
- Kruskal: Algoritmo de Kruskal para encontrar a MST.
- Boruvka: Algoritmo de Boruvka para encontrar a MST.

### Questão 6: Ciclo Mínimo
- Ciclo Mínimo: Determina um ciclo mínimo que visita todos os vértices do grafo sem repetir arestas.

## Como Executar ###
Para executar o projeto, siga os passos abaixo:

1. Clonar o Repositório:
   ```bash
   git clone https://github.com/usuario/projeto-grafos.git
   cd projeto-grafos
   ```

2. Compilar e Executar:
   Dependendo da linguagem utilizada, compile e execute o código. Por exemplo, em Python:
   ```bash
   python3 questao1/main.py
   python3 questao2/main.py
   ```

3. Testar Funcionalidades:
   Cada questão tem um `main()` específico que executa as funcionalidades da questão. Utilize os exemplos de grafos nos respectivos `main()` para testar as implementações.

## Dependências ###
Se houver dependências, liste-as aqui. Por exemplo:
- 

Para instalar as dependências em Python:
```bash
pip install -r requirements.txt
```

## Autor
- João Gabriel Leite Silva - https://www.linkedin.com/in/joão-gabriel-leite-silva-332602276/?originalSubdomain=br
- Daniel Moura - [LinkedIn]

## Licença
Este projeto é licenciado sob a Licença MIT.
```
