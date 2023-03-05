import java.util.Scanner;

//Table of the game
class Puzzle {
    int[] initialTable;
    int[] finalTable;

    Auxiliary aux = new Auxiliary();

    Puzzle() {
        initialTable = new int[16];
        finalTable = new int[16];
    }

    void read(Scanner in){
        for(int i = 0; i < 16; i++) initialTable[i] = in.nextInt();
        for(int j = 0; j < 16; j++) finalTable[j] = in.nextInt();
    }

    //Movements of the game
    private int[] movements(int[] table, char direction) {
        int[] res = aux.copyArray(table);
        int pos = aux.findIndex(table, 0);
        int temp = table[pos];

        switch(direction) {
            case 'u':
            if(!(pos >= 0 && pos < 4)) {
                res[pos] = res[pos - 4];
                res[pos - 4] = temp;
            }
            break;

            case 'd':
            if(!(pos >= 12 && pos < 15)) {
                res[pos] = res[pos + 4];
                res[pos + 4] = temp;
            }
            break;

            case 'l':
            if(!(pos == 0 || pos == 4 || pos == 8 || pos == 12)) {
                res[pos] = res[pos - 1];
                res[pos - 1] = temp;
            }
            break;

            case 'r':
            if(!(pos == 3 || pos == 7 || pos == 11 || pos == 15)) {
                res[pos] = res[pos + 1];
                res[pos + 1] = temp;
            }
            break;
        }

        return res;
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

    //Search algorithms
    public void DFS() {

        Node<int[]> root = new Node<int[]>(initialTable, null, null, null, null);
        Tree<int[]> path = new Tree<int[]>();
        path.setRoot(root);

        System.out.print("DFS:");
      
        Stack<Node<int[]>> q = new LinkedListStack<Node<int[]>>();
        
        q.push(root);
        while (!q.isEmpty()) {
            Node<int[]> cur = q.pop();
            if (cur != null) {
                System.out.print(" " + toString(cur.getValue()));
                q.push(cur.getUp());
                q.push(cur.getDown());
                q.push(cur.getLeft());
                q.push(cur.getRight()); 
            }
        }
        System.out.println();
    }

}