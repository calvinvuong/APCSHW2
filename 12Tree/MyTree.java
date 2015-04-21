import java.util.*;

public class MyTree<T> {

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

    public void setLeft(TreeNode<T> left) {
      left_ = left;
    }

    public TreeNode<T> getRight() {
      return right_;
    }

    public void setRight(TreeNode<T> right) {
      right_ = right;
    }
    
    public boolean isLeaf() {
      return left_ == null && right_ == null;
    }
  }
  
  private TreeNode<T> root_;
  // nooz
  private int size_;

  public MyTree() {
    size_ = 0;
  }

  private TreeNode<T> getNextOpenNode(TreeNode<T> root, int size) {
    if (size < 3) {
      return root;
    } else if (size < 6) {
      return getNextOpenNode(root.getLeft(), size / 2);
    } else {
      return getNextOpenNode(root.getRight(), size / 2);
    }
  }

  public void add(T value) {
    if (root_ == null) {
      root_ = new TreeNode<T>(value);
    } else {
      if (size_ % 2 == 0) {
        getNextOpenNode(root_, size_).setRight(new TreeNode<T>(value));
      } else {
        getNextOpenNode(root_, size_).setLeft(new TreeNode<T>(value));
      }
    }
    size_++;
  }

  private String toString(TreeNode<T> root, String out) {
    if (root.isLeaf()) {
      return out + root;
    } else {
      return root + "\n\t" +
        toString(root.getLeft(), out) + "\n\t" +
        toString(root.getRight(), out);
    }
  }

  public String toString() {
    return toString(root_, "");
  }

  public static void main(String[] args) {
    MyTree<Integer> t = new MyTree<Integer>();
    t.add(1);
    System.out.println(t);
    t.add(2);
    t.add(3);
    System.out.println(t);
    t.add(4);
    System.out.println(t);
  }
}
