import java.util.*;

public class MyLinkedList {
  
  private int size_;
  private LNode head_;

  public MyLinkedList() {
    size_ = 0;
  }

  public int get(int index) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    return current.getValue();
  }

  public void set(int index, int value) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    current.setValue(value);
  }

  public int remove(int index) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int toReturn;
    if (index == 0) {
      toReturn = head_.getValue();
      head_ = head_.getNext();
    } else {
      LNode current = head_;
      while (index != 1) {
        current = current.getNext();
        index--;
      }
      toReturn = current.getNext().getValue();
      current.setNext(current.getNext().getNext());
    }
    size_--;
    return toReturn;
  }

  public void add(int value) {
    if (size_ == 0) {
      head_ = new LNode(value);
    } else {
      LNode current = head_;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(new LNode(value));
    }
    size_++;
  }

  public void add(int index, int value) {
    if (index < 0 || index > size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    if (index == size_) {
      add(value);
      return;
    }
    LNode current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    int tmp = current.getValue();
    current.setValue(value);
    current.setNext(new LNode(tmp, current.getNext()));
    size_++;
  }

  public void swap(int index1, int index2) {
    int tmp = get(index1);
    set(index1, get(index2));
    set(index2, tmp);
  }

  public int indexOf(int value) {
    LNode current = head_;
    int counter = 0;
    while (current != null) {
      if (current.getValue() == value) {
        return counter;
      }
      current = current.getNext();
      counter++;
    }
    return -1;
  }

  public int size() {
    return size_;
  }

  public void clear() {
    size_ = 0;
    head_ = null;
  }

  public String toString() {
    if (size_ == 0) {
      return "[ ]";
    }
    String out = "[";
    LNode current = head_;
    while (current.getNext() != null) {
      out += current.toString() + ", ";
      current = current.getNext();
    }
    out += current.toString();
    return out + "] " + size_;
  }

  public static void main(String[] args) {
    MyLinkedList l = new MyLinkedList();
    l.add(3);
    l.add(4);
    l.add(56);
    l.add(565);
    l.add(536);
    System.out.println(l.size());
    System.out.println(l);
    for (int i = 0; i < l.size(); ++i) {
      System.out.println(l.get(i));
    }
    l.set(0, 100);
    l.set(4, 200);
    System.out.println(l);
    l.remove(3);
    System.out.println(l);
    l.add(4, 999);
    System.out.println(l);
    System.out.println(l.indexOf(999));
  }
}
