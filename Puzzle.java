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
}

////////////////////////////////////////////////////////////////////////////////////////

class TABULEIRO {
    int[] initialTable;
    int[] finalTable;

    TABULEIRO(){
        initialTable = new int[16];
        finalTable = new int[16];
    }

    void read(Scanner in){
        for(int i = 0; i < 16; i++) initialTable[i] = in.nextInt();
        for(int j = 0; j < 16; j++) finalTable[j] = in.nextInt();
    }


    //Movements of the game
    public static boolean movements(int[] table, char direction) {
        int pos = Auxiliares.findIndex(table, 0);
        int temp = table[pos];
        
        //The cases in the switch represents the illegal moves
        switch(direction) {
            case 'u': //Up
            if(!(pos >= 0 && pos < 4)) {
                table[pos] = table[pos - 4];
                table[pos - 4] = temp;
                return true;
            }
            case 'd': //Down
            if(!(pos >= 12 && pos < 15)) {
                table[pos] = table[pos + 4];
                table[pos + 4] = temp;
                return true;
            }
            case 'l': //Left
            if(!(pos == 0 || pos == 4 || pos == 8 || pos == 12)) {
                table[pos] = table[pos - 1];
                table[pos - 1] = temp;
                return true;
            }
            case 'r': //Right
            if(!(pos == 3 || pos == 7 || pos == 11 || pos == 15)) {
                table[pos] = table[pos + 1];
                table[pos + 1] = temp;
                return true;
            }
        }

        return false;
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
        int[] temp = initialTable;
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

class PUZZLE{

    public static void main(String[] args) {
        //Input Lines
        Scanner in = new Scanner(System.in);
        TABULEIRO teste= new TABULEIRO();
        teste.read(in);

        if (teste.isSolvable()) {
            System.out.println("Solvable");
        } else {
            System.out.println("Not Solvable");
        }
    }
}