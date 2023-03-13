#include <iostream>
#include <queue>
#include <stack>
#include <set>
#include <vector>

using namespace std;

class Tabuleiro {
    public:
        int tabuleiro[16]; // representa o tabuleiro
        int posEspaco; // posição do espaço vazio

        // construtor
        Tabuleiro() {
            posEspaco = 0;
        }

        // le o tabuleiro
        void read() {
            for (int i = 0; i < 16; i++) {
                cin >> tabuleiro[i];
                if (tabuleiro[i] == 0) posEspaco = i;
            }
        }

        // imprime o tabuleiro
        void print() {
            for (int i = 0; i < 16; i++) {
                if (i % 4 == 0) {
                    if (i == 0) cout << endl;
                    else cout << "|\n";
                    cout << "+---------------+\n";
                }
                if (tabuleiro[i] < 10) {
                    cout << "|" << tabuleiro[i] << "  ";
                } else {
                    cout << "|" << tabuleiro[i] << " ";
                }
            }
            cout << "|" << "\n+---------------+\n";
        }

        //metodo que retorna uma copia do tabuleiro atual
        Tabuleiro clone() {
            Tabuleiro novo;
            for (int i = 0; i < 16; i++) {
                novo.tabuleiro[i] = tabuleiro[i];
            }
            novo.posEspaco = posEspaco;
            return novo;
        }

        // move o espaço vazio para cima
        bool moveUp() {
            if (posEspaco >= 4) {
                int temp = tabuleiro[posEspaco-4];
                tabuleiro[posEspaco-4] = 0;
                tabuleiro[posEspaco] = temp;
                posEspaco -= 4;
                return true;
            }
            return false;
        }

        // move o espaço vazio para baixo
        bool moveDown() {
            if (posEspaco <= 11) {
                int temp = tabuleiro[posEspaco+4];
                tabuleiro[posEspaco+4] = 0;
                tabuleiro[posEspaco] = temp;
                posEspaco += 4;
                return true;
            }
            return false;
        }

        // move o espaço vazio para a esquerda
        bool moveLeft() {
            if (posEspaco % 4 != 0) {
                int temp = tabuleiro[posEspaco-1];
                tabuleiro[posEspaco-1] = 0;
                tabuleiro[posEspaco] = temp;
                posEspaco--;
                return true;
            }
            return false;
        }

        // move o espaço vazio para a direita
        bool moveRight() {
            if (posEspaco % 4 != 3) {
                int temp = tabuleiro[posEspaco+1];
                tabuleiro[posEspaco+1] = 0;
                tabuleiro[posEspaco] = temp;
                posEspaco++;
                return true;
            }
            return false;
        }

        // verifica se o tabuleiro atual é igual ao tabuleiro passado como parâmetro
        bool equals(Tabuleiro t) {
            for (int i = 0; i < 16; i++) {
                if (tabuleiro[i] == t.tabuleiro[i]) return false;
            }
            return true;
        }

        // compara a distancia positiva vertical e horizontal do 0 da sua posicao com o 0 de outro tabuleiro
        int compareDis(Tabuleiro t) {
            int dis = 0;
            for (int i = 0; i < 16; i++) {
                if (tabuleiro[i] == 0) {
                    dis += abs(i/4 - t.posEspaco/4);
                    dis += abs(i%4 - t.posEspaco%4);
                }
            }
            return dis;
        }

        //verifica se o tabuleiro tem solucao a comparar com outro tabuleiro
        bool hasSolution(Tabuleiro t) {
            int inv = 0;
            for (int i = 0; i < 16; i++) {
                if (tabuleiro[i] == 0) continue;
                for (int j = i+1; j < 16; j++) {
                    if (tabuleiro[j] == 0) continue;
                    if (tabuleiro[i] > tabuleiro[j]) inv++;
                }
            }
            if (inv % 2 == 0) return true;
            return false;
        }

        bool operator<(const Tabuleiro &other) const {
            for (int i = 0; i < 16; i++) {
                if (tabuleiro[i] < other.tabuleiro[i]) {
                    return true;
                } else if (tabuleiro[i] > other.tabuleiro[i]) {
                    return false;
                }
            }
            return false;
        }

};

// classe que representa um nó da árvore de busca
class Node {
    public:
        Tabuleiro tabuleiro;
        Node *pai;
        int depth;
        char movimento;

        // construtor
        Node(Tabuleiro t, Node *p, int d, char m) {
            tabuleiro = t;
            pai = p;
            depth = d;
            movimento = m;
        }

        // imprime o caminho da raiz até o nó atual
        void printPath() {
            if (pai != NULL) {
                pai->printPath();
                cout << movimento << " ";
            }
        }

        //comparar nos para saber se sao iguais
        bool equals(Node n) {
            return tabuleiro.equals(n.tabuleiro);
        }

        //retorna uma copia do no atual
        Node clone() {
            Node novo(tabuleiro.clone(), pai, depth, movimento);
            return novo;
        }
};

// classe que representa a Depth First Search
class DFS {
    public:
        stack<Node> pilha;
        set<Tabuleiro> visitados;

        // construtor
        DFS() {
            pilha = stack<Node>();
            visitados = set<Tabuleiro>();
        }

        // busca o caminho de um tabuleiro inicial até um tabuleiro final
        bool search(Tabuleiro inicial, Tabuleiro final) {
            Node raiz(inicial, NULL, 0, ' ');
            pilha.push(raiz);
            while (!pilha.empty()) {
                Node atual = pilha.top();
                pilha.pop();
                if (atual.tabuleiro.equals(final)) {
                    atual.printPath();
                    return true;
                }
                if (visitados.find(atual.tabuleiro) == visitados.end()) {
                    visitados.insert(atual.tabuleiro);
                    Node novo(atual.tabuleiro.clone(), &atual, atual.depth+1, ' ');
                    if (novo.tabuleiro.moveUp()) {
                        novo.movimento = 'U';
                        pilha.push(novo);
                    }
                    if (novo.tabuleiro.moveDown()) {
                        novo.movimento = 'D';
                        pilha.push(novo);
                    }
                    if (novo.tabuleiro.moveLeft()) {
                        novo.movimento = 'L';
                        pilha.push(novo);
                    }
                    if (novo.tabuleiro.moveRight()) {
                        novo.movimento = 'R';
                        pilha.push(novo);
                    }
                }
            }
            return false;
        }
};

