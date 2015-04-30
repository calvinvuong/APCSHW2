import java.io.*;
import java.util.*;

public class BSTree <T extends Comparable> {

  private class BSTreeNode<T extends Comparable> {

    private T data;
    private int tally;
    private BSTreeNode<T> left;
    private BSTreeNode<T> right;

    public BSTreeNode(T d) {
      data = d;
      tally = 1;
      left = right = null;
    }

    public T getData() {
      return data;
    }
    @SuppressWarnings("unchecked")
    public int compareData(BSTreeNode<T> d) {
      return data.compareTo(d.getData());
    }
    public BSTreeNode<T> getLeft() {
      return left;
    }
    public boolean hasLeft() {
      return left != null;
    }
    public BSTreeNode<T> getRight() {
      return right;
    }
    public boolean hasRight() {
      return right != null;
    }

    public void setData(T d) {
      data = d;
    }
    public void setLeft(BSTreeNode<T> l) {
      left = l;
    }
    public void setRight(BSTreeNode<T> r) {
      right = r;
    }
    public boolean isLeaf() {
      return (left == null && right == null);
    }

    public int getTally() {
      return tally;
    }
    public void incrementCount() {
      tally++;
    }
    public void decrementCount() {
      tally--;
    }
    
    public String toString() {
      return data + "(" + tally + ")";
    }
  }
  
  private BSTreeNode<T> root_;

  public BSTree() {
    root_ = null;
  }

  public boolean isEmpty() {
    return root_ == null;
  }
  /*======== public void add() ==========
    Inputs:   T c  
    Returns: 

    Wrapper for the recursive add method
    ====================*/
  public void add(T c) {
    root_ = add(root_, new BSTreeNode<T>(c));
  }

  /*======== public BSTreeNode<T> add() ==========
    Inputs:  BSTreeNode<T> curr
    BSTreeNode<T> t 
    Returns: 

    Add t to the correct place in the tree root_ed at curr.
    ====================*/
  private BSTreeNode<T> add(BSTreeNode<T> curr, BSTreeNode<T> t) {
    if (curr == null) {
      return t;
    } else if (curr.compareData(t) < 0) {
      curr.setLeft(add(curr.getLeft(), t));
    } else if (curr.compareData(t) > 0) {
      curr.setRight(add(curr.getRight(), t));
    } else {
      curr.setRight(add(curr.getRight(), t));
      // curr.incrementCount();
    }
    return curr;
  }

  /*======== public void remove() ==========
    Inputs:   T c  
    Returns: 
      
    Wrapper for the recursive remove method
    ====================*/
  public void remove( T c ) {
    root_ = remove(root_, c);
  }

  /*======== public BSTreeNode<T> remove() ==========
    Inputs:   BSTreeNode<T> curr
    T c
    Returns: 

    Should remove the value c from the tree root_ed at
    curr, if it exists.
    ====================*/
  private BSTreeNode<T> remove(BSTreeNode<T> curr, T c) {
    return null;
  }

  public void inOrder() {
    inOrderHelper(root_);
    System.out.println();
  }

  public void inOrderHelper(BSTreeNode<T> current) {
    if (current == null) {
      return;
    } else if (current.isLeaf()) {
      System.out.println(current);
      return;
    }
    inOrderHelper(current.getLeft());
    System.out.println(current);
    inOrderHelper(current.getRight());
  }
   
  public static void main( String[] args ) {
    BSTree<Integer> t = new BSTree<Integer>();
    t.add(1);
    t.add(2);
    t.add(3);
    t.add(5);
    t.add(4);
    t.inOrder();
  }
}
