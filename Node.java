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
   BTNode(T v, BTNode<T> u, BTNode<T> d, BTNode<T> l, BTNode<T> r) {
      value = v;
      up = u;
      down = d;
      left = l;
      right = r;
   }

   // Getters e Setters
   public T getValue() {return value;}
    public BTNode<T> getUp() {return left;}
   public BTNode<T> getDown() {return right;}
   public BTNode<T> getLeft() {return left;}
   public BTNode<T> getRight() {return right;}
   public void setValue(T v) {value = v;}
    public void setUp(BTNode<T> l) {left = l;}
   public void setDown(BTNode<T> r) {right = r;}   
   public void setLeft(BTNode<T> l) {left = l;}
   public void setRight(BTNode<T> r) {right = r;}   
}
