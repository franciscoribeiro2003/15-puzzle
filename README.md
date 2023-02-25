# 15 PUZZLE
# Parte 1
## Descrição

Este é o "trabalho para a semana 2" feito por Francisco Ribeiro, Matheus Bissacot e Sérgio Coelho para a cadeira de Inteligência Artificial do curso Ciência dos computadores, da faculdade de Ciências da Universidade do Porto.
A estrutura de dados utilizada para representar uma configuração de jogo foi uma lista de inteiros.

### Objetivo

* [ ] Implementar um algoritmo para saber se um dado puzzle 15 é resolvível ou não.
* [ ] Implementação dos operadores que transformam uma configuração de jogo na sua sucessora.

### Requisitos
[Java](https://www.oracle.com/java/technologies/downloads/) 8 ou superior.

## Execução

Para executar o programa, basta correr o seguinte comando:

```bash
$ java Puzzle 
```
Introduzir a configuração inicial e final e apenas se for realizável o jogo será possivel interagir com os tabuleiros
```bash
+----------------------+-------------------+------------------+
|                 Interacao para a proxima jogada             |
+----------------------+-------------------+------------------+
|Introduzir uma direcao| 'u' para cima     | 'd' para baixo   |
|                      | 'l' para esquerda | 'r' para direita |
+----------------------+-------------------+------------------+
|                      'q' ou 'Q' para sair                   |
+----------------------+-------------------+------------------+
```

### Testes
**Intput:**
```
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0
1 2 3 4 5 6 7 8 9 10 11 12 0 13 14 15
l
l
d
l
```
**Output:**
```
+----------------------+-------------------+------------------+
|                            15 PUZZLE                        |
+----------------------+-------------------+------------------+

Tem solucao

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
|13 |14 |0  |15 |
+---------------+


+---------------+
|1  |2  |3  |4  |
+---------------+
|5  |6  |7  |8  |
+---------------+
|9  |10 |11 |12 |
+---------------+
|13 |0  |14 |15 |
+---------------+

Movimento invalido, tente novamente

+---------------+
|1  |2  |3  |4  |
+---------------+
|5  |6  |7  |8  |
+---------------+
|9  |10 |11 |12 |
+---------------+
|0  |13 |14 |15 |
+---------------+

Puzzle Resolvido

```
## Referências
https://youtu.be/YI1WqYKHi78

#Parte 2
...
