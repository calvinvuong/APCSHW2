import java.util.*;

public class MyHeap {

  // data_[0] is the size of the heap
  private int[] data_;
  private boolean isMaxHeap_;

  public MyHeap(boolean isMaxHeap) {
    data_ = new int[10];
    data_[0] = 0;
    isMaxHeap_ = isMaxHeap;
  }

  public MyHeap() {
    this(true);
  }

  private void swap(int i1, int i2) {
    int tmp = data_[i1];
    data_[i1] = data_[i2];
    data_[i2] = tmp;
  }
  
  private int getLeft(int node) {
    return node * 2;
  }

  private int getRight(int node) {
    return node * 2 + 1;
  }

  private int getParent(int node) {
    return node / 2;
  }

  private boolean compare(int v1, int v2) {
    return isMaxHeap_ ? data_[v1] > data_[v2] : data_[v1] < data_[v2];
  }
  
  private void doSwappyThingy(int node) {
    if (node == 1) {
      return;
    } else if (compare(node, getParent(node))) {
      System.out.println(this);
      swap(node, getParent(node));
      doSwappyThingy(getParent(node));
    } else if (compare(node, getLeft(getParent(node)))) {
      swap(node, getLeft(getParent(node)));
    }
  }

  private void pushTheThingy(int node) {
    // WIP doesn't completely work
    if (compare(getLeft(node), getRight(node))) {
      data_[node] = data_[getLeft(node)];
      pushTheThingy(getLeft(node));
    } else if (compare(getRight(node), getLeft(node))) {
      data_[node] = data_[getRight(node)];
      pushTheThingy(getRight(node));
    }
  }
  
  private void resize() {
    if (data_[0] == data_.length - 1) {
      data_ = Arrays.copyOf(data_, data_[0] * 2);
    } else if (data_[0] < data_.length / 2 && data_[0] > 10) {
      data_ = Arrays.copyOf(data_, data_.length / 2);
    }
  }
  
  public void add(int value) {
    data_[data_[0] + 1] = value;
    doSwappyThingy(data_[0] + 1);
    data_[0]++;
    resize();
  }

  public void remove() {
    if (data_[0] == 0) {
      throw new NoSuchElementException();
    }
    int tmp = data_[1];
  }

  public int peek() {
    if (data_[0] == 0) {
      throw new NoSuchElementException();
    }
    return data_[1];
  }
  
  public String toString() {
    return Arrays.toString(data_);
  }

  public static void main(String[] args) {
    MyHeap h = new MyHeap(false);
    h.add(4);
    h.add(3);
    h.add(5);
    h.add(10);
    h.add(12);
    h.add(1);
    System.out.println(h);
  }
}
