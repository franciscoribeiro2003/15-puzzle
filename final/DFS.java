import java.util.*;
class DFS{
    static Stack<Node> fila;
    static HashMap<Tabuleiro, Integer> visitados;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tabuleiro atual=new Tabuleiro();
        Tabuleiro objetivo=new Tabuleiro();

        atual.read(in);
        objetivo.read(in);

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
        Node no = new Node(atual, null,0,0,""); // cria o no inicial
        fila.add(no); // adiciona o no inicial na fila
        System.out.println("Iniciando a busca com MAX_DEPTH a " + MAX_DEPTH + " ...");
        while(!fila.isEmpty()){
            no = fila.pop(); // retira o primeiro no da fila
            if(no.tab.equals(objetivo)){ // verifica se o no atual é o objetivo
                // gravar o tempo final do programa
                long tempoFinal = (long) (System.currentTimeMillis());
                // gravar a quantidade de memoria usada
                long finalMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                // imprimir os resultados
                System.out.println("Encontrou a solução!");
                System.out.println(no);
                System.out.printf("Tempo de execução: %.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
                System.out.println("Espaço de memória: " + ((finalMemory-usedMemory)/1000000) + "  MB");
                System.out.println("Nós gerados: " + nos);
                return;
            }
            if (!visitados.containsKey(no.tab) && no.profundidade<MAX_DEPTH){
                visitados.put(no.tab, no.tab.hashCode());
                Node up = no.moveUp(objetivo,0);nos++;
                Node left = no.moveLeft(objetivo,0);nos++;
                Node down = no.moveDown(objetivo,0);nos++;
                Node right = no.moveRight(objetivo,0);nos++;

                if (up!=null)   fila.add(up);
                if (down!=null) fila.add(down);
                if (left!=null) fila.add(left);
                if (right!=null) fila.add(right);
            }
        }
        System.out.println("Não encontrou solução :(");
    }


}