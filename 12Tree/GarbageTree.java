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
      return left_ != null;
    }

    public void setLeft(TreeNode<T> left) {
      left_ = left;
    }

    public TreeNode<T> getRight() {
      return right_;
    }

    public boolean hasRight() {
      return right_ != null;
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
    if (root_ == null) {
      root_ = new TreeNode<T>(value);
    } else {
      add(root_, new TreeNode<T>(value));
    }
  }

  private void add(TreeNode<T> branch, TreeNode<T> newBranch) {
    if (branch.isLeaf()) {
      if (rand_.nextInt() % 2 == 0) {
        branch.setLeft(newBranch);;
      } else {
        branch.setRight(newBranch);
      }
    } else if (!branch.hasLeft()) {
      branch.setLeft(newBranch);
    } else if (!branch.hasRight()) {
      branch.setRight(newBranch);
    } else {
      if (rand_.nextInt() % 2 == 0) {
        add(branch.getLeft(), newBranch);
      } else {
        add(branch.getRight(), newBranch);
      }
    }
  }

  public void traverse(int mode) {
    if (mode == PRE_ORDER) {
      preOrder(root_);
    } else if (mode == IN_ORDER) {
      inOrder(root_);
    } else {
      postOrder(root_);
    }
    System.out.println("");
  }

  public void preOrder(TreeNode<T> current) {
    if (current == null) {
      return;
    } else if (current.isLeaf()) {
      System.out.println(current.getData());
      return;
    }
    System.out.println(current);
    preOrder(current.getLeft());
    preOrder(current.getRight());
  }

  public void inOrder(TreeNode<T> current) {
    if (current == null) {
      return;
    } else if (current.isLeaf()) {
      System.out.println(current.getData());
      return;
    }
    inOrder(current.getLeft());
    System.out.println(current);
    inOrder(current.getRight());
  }

  public void postOrder(TreeNode<T> current) {
    if (current == null) {
      return;
    } else if (current.isLeaf()) {
      System.out.println(current.getData());
      return;
    }
    postOrder(current.getLeft());
    postOrder(current.getRight());
    System.out.println(current);
  }

  public int getHeight() {
    return getHeight(root_);
  }

  public int getHeight(TreeNode<T> current) {
    if (current.isLeaf()) {
      return 1;
    }
    return 1 + Math.max(getHeight(current.getLeft()),
                        getHeight(current.getRight()));
  }

  private String toString(TreeNode<T> current, String out) {
    if (current == null) {
      return "";
    }
    return current + "\n" +
      toString(current.getLeft(), out) + " " +
      toString(current.getRight(), out);
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
    t.add(4);
    t.traverse(GarbageTree.POST_ORDER);
    t.add(5);
    t.traverse(GarbageTree.IN_ORDER);
  }
}
