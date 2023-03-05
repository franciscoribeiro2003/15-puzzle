import java.util.Scanner;
import java.lang.Math;

public class Puzzle {
    public static void main(String[] args) {
         Table table = new Table();
         Scanner in = new Scanner(System.in);

         table.read(in);

         table.printTables();

         if(table.isSolvable()) System.out.println("Tem solução");
         else System.out.println("Não tem solução");
    }
}