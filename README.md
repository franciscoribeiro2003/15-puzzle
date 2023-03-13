# 15 PUZZLE & Search Algorithms
```
+----------------------+-------------------+------------------+
|                            15 PUZZLE                        |
+----------------------+-------------------+------------------+

+---------------+
|1  |2  |3  |4  |
+---------------+
|5  |6  |7  |8  |
+---------------+
|9  |10 |11 |12 |
+---------------+
|13 |14 |15 |0  |
+---------------+

+---------------+
|1  |2  |3  |4  |
+---------------+
|5  |6  |7  |8  |
+---------------+
|9  |10 |11 |12 |
+---------------+
|0  |13 |14 |15 |
+---------------+
```
*Puzzle resolvido*

## Descrição

Este é um trabalho feito por Francisco Ribeiro, Matheus Bissacot e Sérgio Coelho para a cadeira de Inteligência Artificial do curso Ciência dos computadores, da faculdade de Ciências da Universidade do Porto.
A estrutura de dados utilizada para representar uma configuração de jogo foi uma lista de inteiros.

### Objetivo

* [ ] Implementar um algoritmo para saber se um dado puzzle 15 é resolvível ou não.
* [ ] Implementação dos operadores que transformam uma configuração de jogo na sua sucessora. (no dir ./Midjourney) 
* [ ] Implementar aglumas buscas não orientadas e orientadas.
* [ ] Chegar a um caminho para chegar uma solução.

### Requisitos
[Java](https://www.oracle.com/java/technologies/downloads/) 8 ou superior.

## Execução

Para executar o programa, basta correr o seguinte comando:

```bash
$ java Pesquisa_a_utilizar.java && java Pesquisa_a_utilizar 
```
Introduzir a configuração inicial e final e apenas se for realizável o jogo será possivel usar as pesquisas.
O intput é do género:

```
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0
1 2 3 4 5 6 7 8 9 10 11 12 0 13 14 15
```

e dps será apresentado o resultado (usando a pesquisa A* como exemplo):


```bash
-----A* Utilizando a Euristica distacia de Manhattan-----
Iniciando a busca ...
Encontrou a solução!
Movimento:  esquerda esquerda esquerda
profundidade: 3

Tempo de execução: 0,002 s
Espaço de memória: 0  MB
Nós gerados: 12


-----A* Utilizando a Euristica pecas Missplaced-----
Iniciando a busca ...
Encontrou a solução!
Movimento:  esquerda esquerda esquerda
profundidade: 3

Tempo de execução: 0,001 s
Espaço de memória: 0  MB
Nós gerados: 12

```
## Pesquisas dísponíveis
- DFS
- BFS
- IDFS
- Greedy/gulosa: com euristica (Missplaced/Manhattan)
- A*: com euristica (Missplaced/Manhattan)


## Referências
[Vídeo sobre solvabilidade de um Puzzle da Numberphile
](https://youtu.be/YI1WqYKHi78)
