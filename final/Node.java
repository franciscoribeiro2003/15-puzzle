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
            res += "profundidade: " + profundidade + "\n";
            res += "Movimento: " + movimento + "\n";
            return res;
        }

        //metodo que retorna uma copia do nó atual
        public Node clone(){
            Node novo = new Node(tab.clone(), pai, profundidade, custo, movimento);
            return novo;
        }

        // move o espaço vazio para cima
        public Node moveUp(Tabuleiro objetivo, int opcao){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveUp()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" cima");
                if (opcao==1) novo.custo=tab1.countManhattan(objetivo);
                else if (opcao==2) novo.custo=tab1.countMisplaced(objetivo);
                else if (opcao==0) novo.custo=0; // custo nulo para busca em largura e profundidade
                return novo;
            }
            return null;
        }

        // move o espaço vazio para baixo
        public Node moveDown(Tabuleiro objetivo, int opcao){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveDown()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" baixo");
                if (opcao==1) novo.custo=tab1.countManhattan(objetivo);
                else if (opcao==2) novo.custo=tab1.countMisplaced(objetivo);
                else if (opcao==0) novo.custo=0; // custo nulo para busca em largura e profundidade
                return novo;
            }
            return null;
        }

        // move o espaço vazio para a esquerda
        public Node moveLeft(Tabuleiro objetivo, int opcao){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveLeft()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" esquerda");
                if (opcao==1) novo.custo=tab1.countManhattan(objetivo);
                else if (opcao==2) novo.custo=tab1.countMisplaced(objetivo);
                else if (opcao==0) novo.custo=0; // custo nulo para busca em largura e profundidade
                return novo;
            }
            return null;
        }

        // move o espaço vazio para a direita
        public Node moveRight(Tabuleiro objetivo, int opcao){
            Tabuleiro tab1=tab.clone();
            if (tab1.moveRight()) {
                Node novo = new Node(tab1, this, profundidade+1, custo, movimento+" direira");
                if (opcao==1) novo.custo=tab1.countManhattan(objetivo);
                else if (opcao==2) novo.custo=tab1.countMisplaced(objetivo);
                else if (opcao==0) novo.custo=0; // custo nulo para busca em largura e profundidade
                return novo;
            }
            return null;
        }
}