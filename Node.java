class Node{
    Tabuleiro tab; // tabuleiro do nó
    Node pai; // nó pai
    int profundidade; // profundidade do nó
    int custo; // custo do nó para o objetivo
    String movimento; // movimento que gerou o nó

    // construtor
    Node(Tabuleiro tab, Node pai, int profundidade, int custo, String movimento){
        this.tab = tab;
        this.pai = pai;
        this.profundidade = profundidade;
        this.custo = custo;
        this.movimento = movimento;
    }

        //imprime o nó
        public String toString() { 
            String res = "";
            res += "Movimento: " + movimento + "\n";
            res += "profundidade: " + profundidade + "\n";
            return res;
        }

        //metodo que retorna uma copia do nó atual
        public Node clone(){
            Node novo = new Node(tab.clone(), pai, profundidade, custo, movimento);
            return novo;
        }

        // move o espaço vazio para cima
        public Node moveUp(Tabuleiro objetivo, int opcao, Tabuleiro raiz){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveUp()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" cima");
                novo.custo(objetivo, opcao,raiz);
                return novo;
            }
            return null;
        }

        // move o espaço vazio para baixo
        public Node moveDown(Tabuleiro objetivo, int opcao, Tabuleiro raiz){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveDown()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" baixo");
                novo.custo(objetivo, opcao,raiz);
                return novo;
            }
            return null;
        }

        // move o espaço vazio para a esquerda
        public Node moveLeft(Tabuleiro objetivo, int opcao, Tabuleiro raiz){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveLeft()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" esquerda");
                novo.custo(objetivo, opcao,raiz);
                return novo;
            }
            return null;
        }

        // move o espaço vazio para a direita
        public Node moveRight(Tabuleiro objetivo, int opcao, Tabuleiro raiz){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveRight()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" direira");
                novo.custo(objetivo, opcao,raiz);
                return novo;
            }
            return null;
        }

        // calcula e altera custo do no consoante as opcoes
        public void custo(Tabuleiro objetivo, int opcao, Tabuleiro raiz){
            switch (opcao){
                //para Greedy
                case 1: custo = tab.countManhattan(objetivo);break;
                case 2: custo = tab.countMisplaced(objetivo);break;
                //para Astar
                case 3: custo = tab.countManhattan(objetivo) + profundidade;break;
                case 4: custo = tab.countMisplaced(objetivo) + profundidade;break;
                case 5: custo = (tab.countManhattan(objetivo) + tab.countManhattan(raiz));break;
                case 6: custo = (tab.countMisplaced(objetivo) + tab.countMisplaced(raiz));break;
                case 0: custo = 0;break; // custo nulo para busca em largura e profundidade
            }
        }
}