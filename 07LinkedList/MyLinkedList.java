import java.util.*;

public class MyLinkedList<T> {
  
  private int size_;
  private LNode<T> head_;

  public MyLinkedList() {
    size_ = 0;
  }

  public T get(int index) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode<T> current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    return current.getData();
  }

  public void set(int index, T data) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode<T> current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    current.setData(data);
  }

  public T remove(int index) {
    if (index < 0 || index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    T toReturn;
    if (index == 0) {
      toReturn = head_.getData();
      head_ = head_.getNext();
    } else {
      LNode<T> current = head_;
      while (index != 1) {
        current = current.getNext();
        index--;
      }
      toReturn = current.getNext().getData();
      current.setNext(current.getNext().getNext());
    }
    size_--;
    return toReturn;
  }

  public boolean add(T data) {
    if (size_ == 0) {
      head_ = new LNode<T>(data);
    } else {
      LNode<T> current = head_;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(new LNode<T>(data));
    }
    size_++;
    return true;
  }

  public void add(int index, T data) {
    if (index < 0 || index > size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    if (index == size_) {
      add(data);
      return;
    }
    LNode<T> current = head_;
    while (index != 0) {
      current = current.getNext();
      index--;
    }
    T tmp = current.getData();
    current.setData(data);
    current.setNext(new LNode<T>(tmp, current.getNext()));
    size_++;
  }

  public void swap(int index1, int index2) {
    T tmp = get(index1);
    set(index1, get(index2));
    set(index2, tmp);
  }

  public int indexOf(T value) {
    LNode<T> current = head_;
    int counter = 0;
    while (current != null) {
      if (current.getData().equals(value)) {
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

  public T[] toArray() {
    T[] array = new T[size_];
    int counter = 0;
    LNode<T> current = head_;
    while (current != null) {
      T[counter] = current.getData();
      current = current.getNext();
      counter++;
    }
    return array;
  }

  public String toString() {
    if (size_ == 0) {
      return "[ ]";
    }
    String out = "[";
    LNode<T> current = head_;
    while (current.getNext() != null) {
      out += current.toString() + ", ";
      current = current.getNext();
    }
    out += current.toString();
    return out + "]";
  }

  public static void main(String[] args) {
    MyLinkedList<Integer> l = new MyLinkedList<Integer>();
    l.add(5);
    l.add(6);
    System.out.println(l);
  }
}
