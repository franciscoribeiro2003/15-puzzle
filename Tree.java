// -----------------------------------------------------------
// Inspired by the implementation of Pedro Ribeiro
// All the content used here can be found in: http://www.dcc.fc.up.pt/~pribeiro/aulas/edados2223/
// -----------------------------------------------------------
// Tree of the movements of the Table
// -----------------------------------------------------------

public class Tree<T> {   
   private Node<T> root; // raiz da arvore

   // Construtor
   Tree() {
      root = null;
   }

   // Getter e Setter para a raiz
   public Node<T> getRoot() {return root;}
   public void setRoot(Node<T> r) {root = r;}

   // Verificar se arvore esta vazia
   public boolean isEmpty() {
      return root == null;
   }

   // --------------------------------------------------------

   // Numero de nos da arvore   
   public int numberNodes() {
      return numberNodes(root);
   }

   private int numberNodes(Node<T> n) {
      if (n == null) return 0;
      return 1 + numberNodes(n.getUp()) + numberNodes(n.getDown()) + numberNodes(n.getLeft()) + numberNodes(n.getRight());
   }

   // --------------------------------------------------------

   // Altura da arvore
   public int depth() {
      return depth(root);
   }

   private int depth(Node<T> n) {
      if (n == null) return -1;
      return 1 + Math.max(Math.max(depth(n.getUp()), depth(n.getDown())), Math.max(depth(n.getLeft()), depth(n.getRight())));
   }

   // --------------------------------------------------------
   
   // O elemento value esta contido na arvore?
   public boolean contains(T value) {
      return contains(root, value);
   }

   private boolean contains(Node<T> n, T value) {
      if (n==null) return false;
      if (n.getValue().equals(value)) return true;
      return contains(n.getUp(), value) || contains(n.getDown(), value) || contains(n.getLeft(), value) || contains(n.getRight(), value);
   }
}