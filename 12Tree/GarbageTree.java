import java.util.*;
import java.io.*;

public class GarbageTree<T> {

  private class TreeNode<T> {
    private T data_;
    private TreeNode<T> left_;
    private TreeNode<T> right_;

    public TreeNode(T data) {
      this(data, null, null);
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
      data_ = data;
      left_ = left;
      right_ = right;
    }

    public String toString() {
      return data_.toString();
    }

    public T getData() {
      return data_;
    }

    public void setData(T data) {
      data_ = data;
    }

    public TreeNode<T> getLeft() {
      return left_;
    }

    public boolean hasLeft() {
      return left_ == null;
    }

    public void setLeft(TreeNode<T> left) {
      left_ = left;
    }

    public TreeNode<T> getRight() {
      return right_;
    }

    public boolean hasRight() {
      return right_ == null;
    }

    public void setRight(TreeNode<T> right) {
      right_ = right;
    }
    
    public boolean isLeaf() {
      return left_ == null && right_ == null;
    }
  }
  
  private static final int RANDOM_SEED = 1337;
  public static final int PRE_ORDER = 0;
  public static final int IN_ORDER = 1;
  public static final int POST_ORDER = 2;

  private TreeNode<T> root_;
  private Random rand_;

  public GarbageTree() {
    root_ = null;
    rand_ = new Random(RANDOM_SEED);
  }

  public void add (T value) {
    add(root_, value);
  }

  private void add(TreeNode<T> branch, TreeNode<T> newBranch) {
    if (branch == null) {
      branch = new TreeNode<T>(value);
    } else if (branch.isLeaf()) {
      if (rand_.nextInt() % 2 == 0) {
        branch.setLeft(new TreeNode<T>(value));
      } else {
        branch.setRight(new TreeNode<T>(value));
      }
    } else if (branch.hasLeft()) {
      branch.setRight(new TreeNode<T>(value));
    } else if (branch.hasRight()) {
      branch.setLeft(new TreeNode<T>(value));
    } else {
      if (rand_.nextInt() % 2 == 0) {
        add(branch.getLeft(), value);
      } else {
        add(branch.getRight(), value);
      }
    }
  }

  public void traverse(int mode) {
    if (mode == PRE_ORDER) {
      System.out.println("PRE_ORDER");
      preOrder(root_);
    } else if (mode == IN_ORDER) {
      inOrder(root_);
    } else {
      postOrder(root_);
    }
  }

  public void preOrder(TreeNode<T> current) {
    System.out.println(root_.getData());
    if (current == null) {
      return;
    } else if (current.isLeaf()) {
      System.out.println(current.getData());
      return;
    }
    preOrder(current.getLeft());
    preOrder(current.getRight());
    System.out.println(current.getData());
  }

  public void inOrder(TreeNode<T> current) {
  }

  public void postOrder(TreeNode<T> current) {
  }

  private String toString(TreeNode<T> current, String out) {
    if (current == null) {
      return "";
    }
    if (current.isLeaf()) {
      return out + current;
    } else {
      return current + "\n\t" +
        toString(current.getLeft(), out) + "\n\t" +
        toString(current.getRight(), out);
    }
  }

  public String toString() {
    return toString(root_, "");
  }

  public static void main(String[] args) {
    GarbageTree<Integer> t = new GarbageTree<Integer>();
    t.add(1);
    t.add(2);
    t.add(3);
    t.traverse(GarbageTree.PRE_ORDER);
    System.out.println(t);
    t.add(4);
    System.out.println(t);
  }
}
