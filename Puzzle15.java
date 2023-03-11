import java.util.*;


class Tabuleiro{
    int tabuleiro[]; // representa o tabuleiro
    int posEspaco; // posição do espaço vazio

    // construtor
    Tabuleiro(){
        tabuleiro = new int[16]; 
        posEspaco = 0;
    }

    // le o tabuleiro
    void read(Scanner in){
        for(int i = 0; i < 16; i++) tabuleiro[i] = in.nextInt();
        findPosEspaco();
    }

    // imprime o tabuleiro
    public String toString() {
        String res = "";
        for(int i = 0; i < 16; i++) {
            if(i%4 == 0) {
                if (i==0) res = "\n";
                else res += "|\n";
                res += "+---------------+\n";
            }
            if (tabuleiro[i]<10){
                res += "|"+ tabuleiro[i] + "  ";
            }
            else{
                res += "|"+tabuleiro[i] + " ";
            }
        }
        res+="|"+"\n+---------------+";
        res += "\n";
        return res;
    }

    // encontra a posicao do espaco vazio
    public void findPosEspaco() {
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] == 0) {
                posEspaco = i;
                break;
            }
        }
    }

    //metodo que retorna uma copia do tabuleiro atual
    public Tabuleiro clone(){
        Tabuleiro novo = new Tabuleiro();
        for (int i = 0; i < 16; i++) {
            novo.tabuleiro[i] = tabuleiro[i];
        }
        novo.posEspaco = posEspaco;
        return novo;
    }

    // move o espaço vazio para cima
    public boolean moveUp() {
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
    public boolean moveDown() {
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
    public boolean moveLeft() {
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
    public boolean moveRight() {
        if (posEspaco % 4 != 3) {
            int temp = tabuleiro[posEspaco+1];
            tabuleiro[posEspaco+1] = 0;
            tabuleiro[posEspaco] = temp;
            posEspaco++;
            return true;
        }
        return false;
    }

    // compara a distancia do 0 da sua posicao com o 0 de outro tabuleiro
    public int compareDis(Tabuleiro t,int num) {
        int pos0 = 0;
        int pos0t = 0;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] == num) pos0 = i;
            if (t.tabuleiro[i] == num) pos0t = i;
        }
        int dist = Math.abs(pos0/4 - pos0t/4) + Math.abs(pos0%4 - pos0t%4);
        return dist;
    }

    //verifica se o tabuleiro tem solucao a comparar com outro tabuleiro
    public boolean hasSolution(Tabuleiro t) {
        int[] temp=this.clone().tabuleiro;
        int inv = 0;
        int dist_brancos = compareDis(t,0);
        for (int i = 0; i < 16; i++) {
            if(temp[i] != t.tabuleiro[i]) {
                for(int j = findIndex(temp, t.tabuleiro[i]); j > i; j--) {
                    int temporario = temp[j - 1];
                    temp[j - 1] = temp[j];
                    temp[j] = temporario;
                    inv++;
                }
        
            }
        }
        if (inv%2 == dist_brancos%2) return true;
        return false;
    }

    //contar as peças fora de posiçao
    public int countMisplaced(Tabuleiro t) {
        int count = 0;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] != t.tabuleiro[i]) count++;
        }
        return count;
    }

    //contar a distancia de manhattan
    public int countManhattan(Tabuleiro t) {
        int count = 0;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] != t.tabuleiro[i]) {
                int pos = findIndex(t.tabuleiro, tabuleiro[i]);
                count += Math.abs(pos/4 - i/4) + Math.abs(pos%4 - i%4);
            }
        }
        return count;
    }

    //encontra o indice da lista de um numero num tabuleiro
    public int findIndex(int[] t,int num){
        for (int i = 0; i < 16; i++) {
            if (t[i] == num) return i;
        }
        return -1;
    }

    //verifica se o tabuleiro é null
    public boolean isNull(){
        System.out.println(this.tabuleiro == null);
        return this.tabuleiro == null;
    }

    
    // verifica se o tabuleiro atual é igual ao tabuleiro passado como parâmetro
    public boolean equals(Tabuleiro t) {
        if (t == null) return false;
        for (int i = 0; i < 16; i++) {
            if (tabuleiro[i] != t.tabuleiro[i]) return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.tabuleiro);
    }

    @Override
    // para hashing
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tabuleiro)) {
            return false;
        }
        Tabuleiro other = (Tabuleiro) obj;
        return Arrays.equals(this.tabuleiro, other.tabuleiro);
    }

}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Node {
    Tabuleiro tab; // tabuleiro do nó
    Node pai; // nó pai
    int profundidade; // profundidade do nó
    String movimento; // movimento que gerou o nó

    //construtor
    public Node(Tabuleiro tab, Node pai, int profundidade, String movimento) {
        this.tab = tab;
        this.pai = pai;
        this.profundidade = profundidade;
        this.movimento = movimento;
    }

    //imprime o nó
    public String toString() { 
        String res = "";
        res += tab;
        res += "profundidade: " + profundidade + "\n";
        res += "Movimento: " + movimento + "\n";
        return res;
    }

    //verifica se o nó é igual a outro nó
    public boolean equals(Node n) {
        if (n == null) {
            return false;
        }
        return tab.equals(n.tab);
    }

    //cria um nó com o movimento para cima
    public Node moveUp(){
        Tabuleiro tab1=tab.clone();
        if (tab1.moveUp()){
            return new Node(tab1, this, profundidade+1, this.movimento+" cima");
        }
        return null;
    }

    //cria um nó com o movimento para baixo
    public Node moveDown(){
        Tabuleiro tab1=tab.clone();
        if (tab1.moveDown()){
            return new Node(tab1, this, profundidade+1, this.movimento+" baixo");
        }
        return null;
    }

    //cria um nó com o movimento para a esquerda
    public Node moveLeft(){
        Tabuleiro tab1=tab.clone();
        if (tab1.moveLeft()){
            return new Node(tab1, this, profundidade+1, this.movimento+" esquerda");
        }
        return null;
    }

    //cria um nó com o movimento para a direita
    public Node moveRight(){
        Tabuleiro tab1=tab.clone();
        if (tab1.moveRight()){
            return new Node(tab1, this, profundidade+1, this.movimento+" direita");
        }
        return null;
    }

    //verifica se o nó é null
    public boolean isNull(){
        System.out.println(this.equals(null));
        return this.equals(null);
    }

    

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class DFS { //depth first search

    //criar um map como visitados
    //static HashMap<Tabuleiro, Integer> visitados;

    //complexidade um pouco ma, nao resolve o puzzle com profundidade 12
    static ArrayList<Tabuleiro> visitados;
    static Stack<Node> pilha;
    
    public static void solve (Tabuleiro atual, Tabuleiro objetivo) {
        long startTime = System.currentTimeMillis();

        Node no = new Node(atual, null, 0, "");
        int MAX_DEPTH = atual.countMisplaced(objetivo) + atual.countManhattan(objetivo);
        pilha = new Stack<>();
        visitados = new ArrayList<>();
        int nos=0; //nos criados total
        pilha.push(no);
        int i=0;
        while(!pilha.isEmpty()){
            Node n = pilha.pop();
            //System.out.println(n.tab);
            if (n.tab.equals(objetivo)){
                System.out.println("Solucao encontrada!");
                System.out.println(n);
                //imprime o tempo de execução do programa
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Tempo de execução: " + totalTime + "ms");
                System.out.println( "                  " + totalTime/1000 + "s");
                System.out.println("Nos criados: " + nos);
                break;
            }
            if (!contains1(n.tab,visitados)){
                visitados.add(n.tab);

                Node up = n.moveUp();nos++;
                Node left = n.moveLeft();nos++;
                Node down = n.moveDown();nos++;
                Node right = n.moveRight();nos++;
           
                if (up!=null && n.profundidade<=MAX_DEPTH) pilha.push(up); 
                if (down!=null && n.profundidade<=MAX_DEPTH) pilha.push(down);
                if (left!=null && n.profundidade<=MAX_DEPTH) pilha.push(left); 
                if (right!=null && n.profundidade<=MAX_DEPTH) pilha.push(right);
            }
        }
    }
    
    //tentei fzr com o hashmap mas continuo a ter problemas com o contains do hasjmap



    /* public static void solve (Tabuleiro atual, Tabuleiro objetivo) {
        int MAX_DEPTH = atual.countMisplaced(objetivo) + atual.countManhattan(objetivo);
        Tabuleiro root=atual.clone();
        long startTime = System.currentTimeMillis(); //inicia o tempo de execução do programa
        Node no = new Node(atual, null, 0, ""); //no de partida
        visitados = new HashMap<>(); //mapa de visitados
        pilha = new Stack<>(); //pilha de nos
        int nos=1; //nos criados total
        pilha.push(no); //adiciona o no de partida a pilha

        System.out.println("Profundidade máxima utilizada " + MAX_DEPTH);
        while(!pilha.isEmpty()){ 
            Node n = pilha.pop(); //retira o no do topo da pilha para ser analisado
            //System.out.println(n.tab);

            if (n.tab.equals(objetivo)){ //verifica se o no é o objetivo
                System.out.println("Solucao encontrada!");
                System.out.println(root);
                System.out.println(n);
                //imprime o tempo de execução do programa
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Tempo de execução: " + totalTime + "ms");
                System.out.println( "                    " + totalTime/1000 + "s");
                System.out.println("Nos criados: " + nos);
                System.out.println("Profundidade máxima utilizada " + MAX_DEPTH);
                break;
            }

            if (!visitados.containsKey(n.tab)){ //verifica se o no já foi visitado
                visitados.put(n.tab,n.tab.hashCode()); //adiciona o no ao mapa de visitados

                Node up = n.moveUp();nos++; //cria um no com o movimento para cima
                Node left = n.moveLeft();nos++; //cria um no com o movimento para a esquerda
                Node down = n.moveDown();nos++; //cria um no com o movimento para baixo
                Node right = n.moveRight();nos++; //cria um no com o movimento para a direita
                
                //adicionar os nos criados a pilha se nao forem null
                //sao null apenas se o movimento nao for possivel
                if (up!=null && n.profundidade<=MAX_DEPTH) pilha.push(up); 
                if (down!=null && n.profundidade<=MAX_DEPTH) pilha.push(down);
                if (left!=null && n.profundidade<=MAX_DEPTH) pilha.push(left); 
                if (right!=null && n.profundidade<=MAX_DEPTH) pilha.push(right);
            }
        }
    } */
    
    static boolean contains1(Tabuleiro atual, ArrayList<Tabuleiro> visitados){
        int flag=0;
        for (Tabuleiro x : visitados){
            for (int i=0; i<16; i++){
                if (x.equals(atual)) flag=1;
            }
        }
        if (flag==1) return true;
        return false;
    }


    
}
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
class BFS { //breadth first search (busca em largura)
    static ArrayList<Tabuleiro> visitados;
    static Queue<Node> fila;
    
    public static void solve(Tabuleiro atual, Tabuleiro objetivo) {
        long startTime = System.currentTimeMillis(); //inicia o tempo de execução do programa

        Node no = new Node(atual, null, 0, ""); //no de partida
        
        fila = new LinkedList<>(); //fila de nos
        visitados = new ArrayList<>(); //lista de visitados

        fila.add(no); //adiciona o no de partida a fila
        int nos=1; //nos criados total
        while(!fila.isEmpty()){
            Node n = fila.remove();
            //System.out.println("Profundidade atual a pesquisar: "+n.profundidade);
            if (n.tab.equals(objetivo)){ //verifica se o no é o objetivo
                System.out.println("Solucao encontrada!");
                System.out.println(n);
                //imprime o tempo de execução do programa
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Tempo de execução: " + totalTime + "ms");
                System.out.println( "                  " + totalTime/1000 + "s");
                System.out.println("Nos criados: " + nos);
                break;
            }

            if (!contains1(n.tab)){ //verifica se o no já foi visitado
                visitados.add(n.tab); //adiciona o no a lista de visitados

                //cria um no com o movimento para cima, baixo, esquerda e direita
                Node up = n.moveUp();nos++; 
                Node down = n.moveDown();nos++;
                Node left = n.moveLeft();nos++;
                Node right = n.moveRight();nos++;

                //adicionar os nos criados a fila se nao forem null
                //sao null apenas se o movimento nao for possivel
                if (up!=null) fila.add(up);
                if (down!=null) fila.add(down);
                if (left!=null) fila.add(left); 
                if (right!=null) fila.add(right);
            }
        }
        
    }

    static boolean contains1(Tabuleiro atual){
        int flag=0;
        for (Tabuleiro x : visitados){
            for (int i=0; i<16; i++){
                if (x.equals(atual)) flag=1;
            }
        }
        if (flag==1) return true;
        return false;
    }

}

class Greedy{
    HashMap<Tabuleiro, Integer> visitados;
    PriorityQueue<Node> fila;


    //escolher o proximo no consoante a distancia de manhattan do tabuleiro atual ao objetivo
    public void solve(Tabuleiro atual,Tabuleiro objetivo){
        long startTime = System.currentTimeMillis(); //inicia o tempo de execução do programa
        Node no = new Node(atual, null, 0, ""); //no de partida
        fila = new PriorityQueue<>(new Comparator<Node>() { //fila de prioridade a comparar distancias atraves da funcao countManhattan
            @Override
            public int compare(Node o1, Node o2) {
                return o1.tab.countManhattan(objetivo) - o2.tab.countManhattan(objetivo); //comparar as distancias de manhattan
            }
        });

        visitados = new HashMap<>(); //mapa de visitados
        fila.add(no); //adiciona o no de partida a fila
        int nos=1; //nos criados total

        while(!fila.isEmpty()){
            Node n = fila.remove(); //retira o no do topo da fila para ser analisado

            if (n.tab.equals(objetivo)){ //verifica se o no é o objetivo
                System.out.println("Solucao encontrada!");
                System.out.println(n);
                //imprime o tempo de execução do programa
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Tempo de execução: " + totalTime + "ms");
                System.out.println( "                  " + totalTime/1000 + "s");
                System.out.println("Nos criados: " + nos);
                break;
            }

            if (!visitados.containsKey(n.tab)){
                visitados.put(n.tab, n.tab.hashCode());
                Node up = n.moveUp();nos++;
                Node left = n.moveLeft();nos++;
                Node down = n.moveDown();nos++;
                Node right = n.moveRight();nos++;
           
                if (up!=null) fila.add(up);
                if (down!=null) fila.add(down);
                if (left!=null) fila.add(left); 
                if (right!=null) fila.add(right);
            }
        }
    }
}


   
class AStar { //a*
    //mesmo que o greedy mas a preferir analisar caminhos em q a soma da distancia de manhattan ate ao proximo no e do proximo no ate ao objetivo seja menor
    HashMap<Tabuleiro, Integer> visitados;
    PriorityQueue<Node> fila;

    public void solve(Tabuleiro atual,Tabuleiro objetivo){
        long startTime = System.currentTimeMillis(); //inicia o tempo de execução do programa
        Node no = new Node(atual, null, 0, ""); //no de partida
        fila = new PriorityQueue<>(new Comparator<Node>() { //fila de prioridade a comparar distancias atraves da funcao countManhattan
            @Override
            public int compare(Node o1, Node o2) {
                return (o1.tab.countManhattan(objetivo) + o1.tab.countMisplaced(objetivo)) - (o2.tab.countManhattan(objetivo) + o2.tab.countMisplaced(objetivo)); //comparar as distancias de manhattan com as distancias de misplaced
            }
        });

        visitados = new HashMap<>(); //mapa de visitados
        fila.add(no); //adiciona o no de partida a fila
        int nos=1; //nos criados total

        while(!fila.isEmpty()){
            Node n = fila.remove(); //retira o no do topo da fila para ser analisado

            if (n.tab.equals(objetivo)){ //verifica se o no é o objetivo
                System.out.println("Solucao encontrada!");
                System.out.println(n);
                //imprime o tempo de execução do programa
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Tempo de execução: " + totalTime + "ms");
                System.out.println( "                  " + totalTime/1000 + "s");
                System.out.println("Nos criados: " + nos);
                break;
            }

            if (!visitados.containsKey(n.tab)){
                visitados.put(n.tab, n.tab.hashCode());
                Node up = n.moveUp();nos++;
                Node left = n.moveLeft();nos++;
                Node down = n.moveDown();nos++;
                Node right = n.moveRight();nos++;
           
                if (up!=null) fila.add(up);
                if (down!=null) fila.add(down);
                if (left!=null) fila.add(left); 
                if (right!=null) fila.add(right);
            }
        }
    }

}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Puzzle15{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tabuleiro atual = new Tabuleiro();
        Tabuleiro objetivo = new Tabuleiro();
        clear();
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                            15 PUZZLE                        |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                            Feito por:                       |");
        System.out.println("|       Francisco             Matheus              Sérgio     |");
        System.out.println("+----------------------+-------------------+------------------+");
        
        System.out.println("Insira o tabuleiro inicial: ");
        atual.read(in);
        System.out.println("Insira o tabuleiro objetivo: ");
        objetivo.read(in);
        atraso();

        if (!atual.hasSolution(objetivo)){ 
            System.out.println("O tabuleiro inicial nao tem solucao para o tabuleiro objetivo!");
            return;
        }
        //esperar que o utilizador escolha o algoritmo
        clear();
        System.out.println("+---------------------------------------------+");
        System.out.println("|Conjunto de tabuleiros válidos e com solução:| ");
        System.out.println("|Escolha o algoritmo a utilizar:              |");
        System.out.println("+---------------------------------------------+");
        System.out.println("|1 - DFS                                      |");
        System.out.println("|2 - BFS                                      |");
        System.out.println("|3 - Greedy                                   |");
        System.out.println("|4 - A*                                       |");
        System.out.println("|5 - Interagir com tabuleiro                  |");
        System.out.println("+---------------------------------------------+");
        int algoritmo = in.nextInt();
        atraso();
        clear();
        switch(algoritmo){
            case 1:
                DFS.solve(atual, objetivo);
                break;
            case 2:
                BFS.solve(atual, objetivo);
                break;
            case 3:
                Greedy g = new Greedy();
                g.solve(atual, objetivo);
                break;
            case 4:
                AStar aStar = new AStar();
                aStar.solve(atual, objetivo);
                break;
            case 5:
                interact(in, atual, objetivo);
                break;
            default:
                System.out.println("Opcao invalida");
                break;
        }


        
        
    }

    //interagir com o tabuleiro
    public static void interact(Scanner in, Tabuleiro atual, Tabuleiro objetivo){ 
        clear();
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                 Interacao para a proxima jogada             |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|Introduzir uma direcao| 'u' para cima     | 'd' para baixo   |");
        System.out.println("|                      | 'l' para esquerda | 'r' para direita |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                      'q' ou 'Q' para sair                   |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("Tabuleiro atual: ");
        System.out.print(atual);
        System.out.println("Tabuleiro objetivo: ");
        System.out.print(objetivo);
        atraso();
        clear();
        //loop para a interacao ate estar resolvido ou q para sair
        while(!atual.equals(objetivo)){
            System.out.println("Introduza uma direcao: ");
            char direcao = in.next().charAt(0);
            if (direcao=='q' || direcao=='Q') break;
            if (direcao=='u'){
                atual.moveUp();
            }
            else if (direcao=='d'){
                atual.moveDown();
            }
            else if (direcao=='l'){
                atual.moveLeft();
            }
            else if (direcao=='r'){
                atual.moveRight();
            }
            else{
                System.out.println("Direcao invalida");
            }
            clear();
            System.out.println("Tabuleiro atual: ");
            System.out.print(atual);
            System.out.println("Tabuleiro objetivo: ");
            System.out.print(objetivo);
        }
    }

    //limpa a tela
    public static void clear() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  
    // Atrasa ms
    public static void atraso(){
      try {
         Thread.sleep(1000); //numero a atrasar de milisegundos
      }
      catch (Exception e) {
         e.printStackTrace();
      }

    }
} 