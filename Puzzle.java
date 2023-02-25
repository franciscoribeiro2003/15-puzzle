import java.util.Scanner;

////////////////////////////////////////////////////////////////////////////////////////

//classe para funcoes auxiliares
class Auxiliares{
    public static int findIndex(int[] table, int number) {
        int res = -1;
        for(int i = 0; i < table.length; i++) {
            if(table[i] == number) {
                res = i;
                break;
            }
        }

        return res;
    }
    //metodo para copiar duas listas de inteiros
    public static int[] copyList(int[] list) {
        int[] res = new int[list.length];
        for(int i = 0; i < list.length; i++) {
            res[i] = list[i];
        }

        return res;
    }

    //compara duas listas de inteiros
    public static boolean compare(int[] list1, int[] list2) {
        boolean res = true;
        for(int i = 0; i < list1.length; i++) {
            if(list1[i] != list2[i]) {
                res = false;
                break;
            }
        }

        return res;
    }

}


////////////////////////////////////////////////////////////////////////////////////////

class TABULEIRO {
    int[] initialTable;
    int[] finalTable;
    int[] itable; //interactive table

    TABULEIRO(){
        initialTable = new int[16];
        finalTable = new int[16];
        itable=new int[16]; //interactive table
    }

    void read(Scanner in){
        for(int i = 0; i < 16; i++) initialTable[i] = in.nextInt();
        for(int j = 0; j < 16; j++) finalTable[j] = in.nextInt();
        itable= Auxiliares.copyList(initialTable);
    }


    //limites
    public boolean limites(int pos, char direcao) {
        switch(direcao) {
            case 'u': //Up
                if(pos >= 0 && pos < 4) {
                    return false;
                }
                break;
            case 'd': //Down
                if(pos >= 12 && pos < 15) {
                    return false;
                }
                break;
            case 'l': //Left
                if(pos == 0 || pos == 4 || pos == 8 || pos == 12) {
                    return false;
                }
                break;
            case 'r': //Right
                if(pos == 3 || pos == 7 || pos == 11 || pos == 15) {
                    return false;
                }
                break;
        }

        return true;
    }

    
    //modificar com os movimentos, "u" para cima, "d" para baixo, "l" para esquerda e "r" para direita
    public boolean movements(char direction) {
        int pos = Auxiliares.findIndex(itable, 0);
        int temp = itable[pos];
        
        switch(direction) {
            case 'u': //Up
                if(limites(pos, 'u')) {
                    itable[pos] = itable[pos - 4];
                    itable[pos - 4] = temp;
                    return true;
                }
            break;
            case 'd': //Down
                if(limites(pos, 'd')) {
                    itable[pos] = itable[pos + 4];
                    itable[pos + 4] = temp;
                    return true;
                }
            break;
            case 'l': //Left
                if(limites(pos, 'l')) {
                    itable[pos] = itable[pos - 1];
                    itable[pos - 1] = temp;
                    return true;
                }
            break;
            case 'r': //Right
                if(limites(pos, 'r')) {
                    itable[pos] = itable[pos + 1];
                    itable[pos + 1] = temp;
                    return true;
                }
            break;
        }

        return false;
    }


    //print the table
    public String toString() {
        String res = "";
        for(int i = 0; i < 16; i++) {
            if(i%4 == 0) {
                res += "\n";
            }
            res += itable[i] + " ";
        }
        res += "\n";
        return res;
    }


    int SaltosBranco(){
        int pos = Auxiliares.findIndex(initialTable, 0);
        int pos_final = Auxiliares.findIndex(finalTable, 0);
        //as linhas sao os grupos (0-3)(4-7)(8-11)(12-15)
        int dist_vertical = (pos_final/4) - (pos/4);
        int dist_horizontal = (pos_final%4) - (pos%4);
        //distancia tem de ser positiva
        if(dist_vertical < 0) {
            dist_vertical = dist_vertical * -1;
        }    
        if(dist_horizontal < 0) {
            dist_horizontal = dist_horizontal * -1;
        }
        int distancia_final = dist_vertical + dist_horizontal;
        return distancia_final;
    }

    //Solvability of the Puzzle
    public boolean isSolvable() {
        int[] temp = Auxiliares.copyList(initialTable);
        int inv = 0; //Permutations
        int distancia_brancos = SaltosBranco(); //Distancia dos brancos

        for(int i = 0; i < 16; i++) {
            if(temp[i] != finalTable[i]) {
                for(int j = Auxiliares.findIndex(temp, finalTable[i]); j > i; j--) {
                    int temporario = temp[j - 1];
                    temp[j - 1] = temp[j];
                    temp[j] = temporario;
                    inv++;
                }
            }
        }

        return (distancia_brancos%2 == 0) == (inv%2 == 0);
    }

}

////////////////////////////////////////////////////////////////////////////////////////

class Puzzle{
    static TABULEIRO teste= new TABULEIRO();
    
    public static void main(String[] args) {
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                            15 PUZZLE                        |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                            Feito por:                       |");
        System.out.println("|       Francisco             Matheus              Sérgio     |");
        System.out.println("+----------------------+-------------------+------------------+");
        //Input Lines
        Scanner in = new Scanner(System.in);
        teste.read(in);

        if (teste.isSolvable()) {
            System.out.println("Tem solucao");
            interact(in);
        } else {
            System.out.println("Nao tem solucao");
            System.out.println("A sair...");
        }
    }

    //interagir com o puzzle
    public static void interact(Scanner in){
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                 Interacao para a proxima jogada             |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|Introduzir uma direcao| 'u' para cima     | 'd' para baixo   |");
        System.out.println("|                      | 'l' para esquerda | 'r' para direita |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println("|                      'q' ou 'Q' para sair                   |");
        System.out.println("+----------------------+-------------------+------------------+");
        System.out.println(teste);
        in.nextLine();

        //enquanto o charater nao for "q" ou "Q" continua a pedir input
        //o intpu é um carater de cada vez separado por mudanca de linha
        //se a table.itable for igual á finaltable, o puzzle esta resolvido e sai do loop
        while(in.hasNext()){
            char input = in.next().charAt(0);
            if(input == 'q' || input == 'Q') {
                System.out.println("A sair... :(");
                break;
            }
            if(teste.movements(input)) {
                System.out.println(teste);
            }
            else{
                System.out.println("Movimento invalido, tente novamente");
            }

            if(Auxiliares.compare(teste.itable, teste.finalTable)) {
                System.out.println("Puzzle Resolvido");
                break;
            }
        }
    }
}