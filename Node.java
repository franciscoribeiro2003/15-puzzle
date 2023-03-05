// -----------------------------------------------------------
// Inspired by the implementation of Pedro Ribeiro
// All the content used here can be found in: http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2223/
// -----------------------------------------------------------
// Node of a table
// -----------------------------------------------------------

public class Node<T> {
    private T value;       // Value of the Table
    private Node<T> up;    // Table with the up movement
    private Node<T> down;  // Table with the down movement
    private Node<T> left;  // Table with the left movement
    private Node<T> right; // Table with the right movement

   // Construtor
   Node(T v, Node<T> u, Node<T> d, Node<T> l, Node<T> r) {
      value = v;
      up = u;
      down = d;
      left = l;
      right = r;
   }

   // Getters e Setters
   public T getValue() {return value;}
    public Node<T> getUp() {return left;}
   public Node<T> getDown() {return right;}
   public Node<T> getLeft() {return left;}
   public Node<T> getRight() {return right;}

   public void setValue(T v) {value = v;}
    public void setUp(Node<T> l) {left = l;}
   public void setDown(Node<T> r) {right = r;}   
   public void setLeft(Node<T> l) {left = l;}
   public void setRight(Node<T> r) {right = r;}   
}
