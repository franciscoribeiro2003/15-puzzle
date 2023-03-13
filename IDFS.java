import java.util.*;
class IDFS{
    static Stack<Node> fila;
    static HashMap<Tabuleiro, Integer> visitados;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tabuleiro atual=new Tabuleiro();
        Tabuleiro objetivo=new Tabuleiro();

        atual.read(in);
        objetivo.read(in);

        if (!atual.hasSolution(objetivo)){ 
            System.out.println("O tabuleiro inicial nao tem solucao para o tabuleiro objetivo!");
            return;
        }
        System.out.println("O tabuleiro inicial tem solucao para o tabuleiro objetivo!");
        System.out.println();
        System.out.println("----------------IDFS----------------");
        solve(atual,objetivo);

    }

    public static void solve(Tabuleiro atual, Tabuleiro objetivo){
        int MAX_DEPTH=atual.countMisplaced(objetivo)+ atual.countManhattan(objetivo);

        // gravar o tempo inicial do programa
        long tempoInicial = System.currentTimeMillis(); 

        // gravar a quantidade de memoria usada
        long usedMemory = Runtime.getRuntime().totalMemory() -Runtime.getRuntime().freeMemory();

        //contar o numero de nos gerados
        int nos=0;

        //criar o hashmap para armazenar os nos visitados
        visitados = new HashMap<>();
        

        fila = new Stack<Node>(); // fila de nos a serem visitados
        Node inicio = new Node(atual, null,0,0,""); // cria o no inicial
        for (int j=1; j<=MAX_DEPTH;j++){
            //System.out.println("Iniciando a busca com profundidade "+ j + " e MAX_DEPTH a " + MAX_DEPTH + " ...");
            fila.add(inicio); // adiciona o no inicial na fila
            nos++;

            while(!fila.isEmpty()){
                Node no = fila.pop().clone(); // retira o primeiro no da fila

                if(no.tab.equals(objetivo)){ // verifica se o no atual é o objetivo
                    // gravar o tempo final do programa
                    long tempoFinal = (long) (System.currentTimeMillis());
                    // gravar a quantidade de memoria usada
                    long finalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                    // imprimir os resultados
                    System.out.println("Encontrou a solução!");
                    System.out.println(no);
                    System.out.printf("Tempo de execução: %.3f s%n", (tempoFinal - tempoInicial) / 1000d);
                    System.out.println("Espaço de memória: " + ((finalMemory-usedMemory)/1000000) + "  MB");
                    System.out.println("Nós gerados: " + nos);
                    return;
                }

                if (!visitados.containsKey(no.tab) && no.profundidade<j){
                    visitados.put(no.tab, no.tab.hashCode());
                    Node up = no.moveUp(objetivo,0,null);nos++;
                    Node left = no.moveLeft(objetivo,0,null);nos++;
                    Node down = no.moveDown(objetivo,0,null);nos++;
                    Node right = no.moveRight(objetivo,0,null);nos++;

                    if (up!=null)   fila.add(up);
                    if (down!=null) fila.add(down);
                    if (left!=null) fila.add(left);
                    if (right!=null) fila.add(right);
                }
            }
            visitados.clear();
     }
        System.out.println("Não encontrou solução :(");
    }


}