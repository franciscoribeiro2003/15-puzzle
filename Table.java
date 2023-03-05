import java.util.Scanner;

//Table of the game
class Table {
    int[] initialTable;
    int[] finalTable;

    Auxiliary aux = new Auxiliary();

    Table() {
        initialTable = new int[16];
        finalTable = new int[16];
    }

    void read(Scanner in){
        for(int i = 0; i < 16; i++) initialTable[i] = in.nextInt();
        for(int j = 0; j < 16; j++) finalTable[j] = in.nextInt();
    }

    //Movements of the game
    private boolean movements(int[] table, char direction) {
        int pos = aux.findIndex(table, 0);
        int temp = table[pos];

        switch(direction) {
            case 'u':
            if(!(pos >= 0 && pos < 4)) {
                table[pos] = table[pos - 4];
                table[pos - 4] = temp;
                return true;
            }
            break;

            case 'd':
            if(!(pos >= 12 && pos < 15)) {
                table[pos] = table[pos + 4];
                table[pos + 4] = temp;
                return true;
            }
            break;

            case 'l':
            if(!(pos == 0 || pos == 4 || pos == 8 || pos == 12)) {
                table[pos] = table[pos - 1];
                table[pos - 1] = temp;
                return true;
            }
            break;

            case 'r':
            if(!(pos == 3 || pos == 7 || pos == 11 || pos == 15)) {
                table[pos] = table[pos + 1];
                table[pos + 1] = temp;
                return true;
            }
            break;
        }

        return false;
    }

    private int blankCount(int[] initialTable, int[] finalTable) {
        int pos = aux.findIndex(initialTable, 0);
        int posFinal = aux.findIndex(finalTable, 0);

        //The Lines are formed by: (0-3)(4-7)(8-11)(12-15)
        int vertDist = Math.abs((posFinal/4) - (pos/4));
        int horiDist = Math.abs((posFinal%4) - (pos%4));

        int finalDist = vertDist + horiDist;

        return finalDist;
    }

    //Solvability of the Puzzle
    public boolean isSolvable() {
        int inv = 0; //Permutations
        int blankRow = blankCount(initialTable, finalTable); //Distance between empty spaces
        int[] iTable = aux.copyArray(initialTable);
        
        //Calculate the permutations
        for(int i = 0; i < 16; i++) {
            if(iTable[i] != finalTable[i]) {
                for(int j = aux.findIndex(iTable, finalTable[i]); j > i; j--) {
                    int temp = iTable[j - 1];
                    iTable[j - 1] = iTable[j];
                    iTable[j] = temp;
                    inv++;
                }
            }
        }

        return (blankRow%2 == 0) == (inv%2 == 0);
    }

    //print the table
    public String toString(int[] itable) {
        String res = "";
        for(int i = 0; i < 16; i++) {
            if(i%4 == 0) {
                if (i==0) res = "\n";
                else res += "|\n";
                res += "+---------------+\n";
            }
            if (itable[i]<10){
                res += "|"+ itable[i] + "  ";
            }
            else{
                res += "|"+itable[i] + " ";
            }
        }
        res+="|"+"\n+---------------+";
        res += "\n";
        return res;
    }

    public void printTables() {
        System.out.println("Tabela Inicial: " + toString(initialTable) + "\n" + "Tabela Final: " + toString(finalTable));
    }

}