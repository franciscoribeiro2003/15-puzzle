import java.util.Scanner;

public class Puzzle {
    //Auxiliar method
    private static int findIndex(int[] table, int number) {
        int res = -1;
        for(int i = 0; i < table.length; i++) {
            if(table[i] == number) {
                res = i;
                break;
            }
        }

        return res;
    }

    //Movements of the game
    public static boolean movements(int[] table, char direction) {
        int pos = findIndex(table, 0);
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

    //Solvability of the Puzzle
    public static boolean isSolvable(int[] initialTable, int[] finalTable) {
        int inv = 0; //Permutations
        int blankRow = 0; //Distance between empty spaces

        for(int i = 0; i < 16; i++) {
            if(initialTable[i] != finalTable[i]) {
                for(int j = findIndex(initialTable, finalTable[i]); j > i; j--) {
                    int temp = initialTable[j - 1];
                    initialTable[j - 1] = initialTable[j];
                    initialTable[j] = temp;
                    inv++;
                }
            }
        }

        return (blankRow%2 == 0) == (inv%2 == 0);
    }

    public static void main(String[] args) {
        //Input Lines
        Scanner in = new Scanner(System.in);

        int[] initialTable = new int[16];
        int[] finalTable = new int[16];

        for(int i = 0; i < 16; i++) initialTable[i] = in.nextInt();
        for(int j = 0; j < 16; j++) finalTable[j] = in.nextInt();

        isSolvable(initialTable, finalTable);
    }
}
