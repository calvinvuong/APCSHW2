import java.util.*;

public class Deque<T> {

  private final int DEFAULT_SIZE = 10;

  private T[] items_;
  private int head_;
  private int tail_;
  private int size_;

  public Deque() {
    items_ = (T[]) (new Object[DEFAULT_SIZE]);
    head_ = 0;
    tail_ = DEFAULT_SIZE - 1;
    size_ = 0;
  }

  private int normalize(int n) {
    while (n < items_.length) {
      n += items_.length;
    }
    return n % items_.length;
  }

  private void resize() {
    int newSize = size_;
    if (size_ == items_.length) {
      newSize = size_ * 2;
    } else {
      return;
    }
    
    T[] newArray = (T[]) (new Object[newSize]);
    int copyCounter = 0;
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        newArray[copyCounter] = items_[i];
        copyCounter++;
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        newArray[copyCounter] = items_[i];
        copyCounter++;
      }
      for (int i = 0; i <= tail_; ++i) {
        newArray[copyCounter] = items_[i];
        copyCounter++;
      }
    }
    head_ = 0;
    tail_ = size_ - 1;
    items_ = newArray;
  }

  public boolean add(T item) {
    addLast(item);
    return true;
  }

  public void addFirst(T item) {
    resize();
    head_ = normalize(head_ - 1);
    items_[head_] = item;
    size_++;
  }

  public void addLast(T item) {
    resize();
    tail_ = normalize(tail_ + 1);
    items_[tail_] = item;
    size_++;
  }

  public T peekFirst() {
    if (size_ == 0) {
      return null;
    }
    return items_[head_];
  }

  public T peekLast() {
    if (size_ == 0) {
      return null;
    }
    return items_[tail_];
  }

  public T removeFirst() {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int index = head_;
    head_ = normalize(head_ + 1);
    return items_[index];
  }

  public T removeLast() {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int index = tail_;
    tail_ = normalize(tail_ - 1);
    return items_[index];
  }

  public int size() {
    return size_;
  }

  public String toString() {
    if (size_ == 0) {
      return "[ ]";
    }
    String out = "[ ";
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        out += items_[i] + " ";
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        out += items_[i] + " ";
      }
      for (int i = 0; i <= tail_; ++i) {
        out += items_[i] + " ";
      }
    }
    return out + "]";
  }

  public static void main(String[] args) {
    Deque<Integer> q = new Deque<Integer>();
    q.add(1);
    System.out.println(q + " " + q.size());
    q.add(2);
    System.out.println(q);
    q.addFirst(1);
    System.out.println(q);
    for (int i = 0; i < 10; ++i) {
      q.addFirst(i);
    System.out.println(q);
    }
    System.out.println(q.removeFirst() + " " + q);
    System.out.println(q.removeFirst() + " " + q);
    System.out.println(q.removeLast() + " " + q);
    System.out.println(q.peekFirst() + " " + q);
    System.out.println(q.peekLast() + " " + q);
  }
}
