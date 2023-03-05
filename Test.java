import java.util.Scanner;
import java.lang.Math;

public class Test {
    public static void main(String[] args) {
         Puzzle puzzle = new Puzzle();
         Scanner in = new Scanner(System.in);
         puzzle.read(in);

         if(puzzle.isSolvable()) System.out.println("Tem solução");
         else System.out.println("Não tem solução");

         puzzle.DFS();
    }
}