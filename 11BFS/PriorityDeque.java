import java.util.*;

/**
 * Deque with priority removal.
 */
public class PriorityDeque<T> {

  private final int DEFAULT_SIZE = 10;

  private T[] items_;
  private int[] priorities_;
  private int head_;
  private int tail_;
  private int size_;

  @SuppressWarnings("unchecked")
  public PriorityDeque() {
    items_ = (T[]) (new Object[DEFAULT_SIZE]);
    priorities_ = new int[DEFAULT_SIZE];
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

  @SuppressWarnings("unchecked")
  private void resize() {
    int newSize = size_;
    if (size_ == items_.length) {
      newSize = size_ * 2;
    } else {
      return;
    }
    
    T[] newItems = (T[]) (new Object[newSize]);
    int[] newPriorities = new int[newSize];
    int copyCounter = 0;
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        newItems[copyCounter] = items_[i];
        newPriorities[copyCounter] = priorities_[i];
        copyCounter++;
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        newItems[copyCounter] = items_[i];
        newPriorities[copyCounter] = priorities_[i];
        copyCounter++;
      }
      for (int i = 0; i <= tail_; ++i) {
        newItems[copyCounter] = items_[i];
        newPriorities[copyCounter] = priorities_[i];
        copyCounter++;
      }
    }
    head_ = 0;
    tail_ = size_ - 1;
    items_ = newItems;
    priorities_ = newPriorities;
  }

  public boolean add(T item) {
    addLast(item);
    return true;
  }

  public void add(T item, int priority) {
    addLast(item);
    priorities_[tail_] = priority;
  }

  public void addFirst(T item) {
    resize();
    head_ = normalize(head_ - 1);
    items_[head_] = item;
    priorities_[head_] = 1;
    size_++;
  }

  public void addLast(T item) {
    resize();
    tail_ = normalize(tail_ + 1);
    items_[tail_] = item;
    priorities_[tail_] = 1;
    size_++;
  }

  public T getFirst() {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    return items_[head_];
  }

  public T getLast() {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    return items_[tail_];
  }

  private T priorityRemove(boolean gt) {
    if (size_ == 0) {
      throw new NoSuchElementException();
    }
    size_--;
    int priorityIndex = head_;
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        if (gt) {
          if (priorities_[i] > priorities_[priorityIndex]) {
            priorityIndex = i;
          }
        } else {
          if (priorities_[i] < priorities_[priorityIndex]) {
            priorityIndex = i;
          }
        }
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        if (gt) {
          if (priorities_[i] > priorities_[priorityIndex]) {
            priorityIndex = i;
          }
        } else {
          if (priorities_[i] < priorities_[priorityIndex]) {
            priorityIndex = i;
          }
        }
      }
      for (int i = 0; i <= tail_; ++i) {
        if (gt) {
          if (priorities_[i] > priorities_[priorityIndex]) {
            priorityIndex = i;
          }
        } else {
          if (priorities_[i] < priorities_[priorityIndex]) {
            priorityIndex = i;
          }
        }
      }
    }

    T toReturn = items_[priorityIndex];
    items_[priorityIndex] = items_[head_];
    items_[head_] = null;
    priorities_[priorityIndex] = priorities_[head_];
    priorities_[head_] = 0;
    head_ = normalize(head_ + 1);
    return toReturn;
  }

  public T removeSmallest() {
    return priorityRemove(false);
  }

  public T removeLargest() {
    return priorityRemove(true);
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
    String dataOut = "[ ";
    String priorityOut = "[ ";
    if (head_ <= tail_) {
      for (int i = head_; i <= tail_; ++i) {
        dataOut += items_[i] + " ";
        priorityOut += priorities_[i] + " ";
      }
    } else {
      for (int i = head_; i < items_.length; ++i) {
        dataOut += items_[i] + " ";
        priorityOut += priorities_[i] + " ";
      }
      for (int i = 0; i <= tail_; ++i) {
        dataOut += items_[i] + " ";
        priorityOut += priorities_[i] + " ";
      }
    }
    return dataOut + "]" + " " + priorityOut + "]";
  }

  public static void main(String[] args) {
    PriorityDeque<Integer> q = new PriorityDeque<Integer>();
    q.add(1, 11);
    q.add(2, 10);
    q.add(3, 5);
    q.add(4, 2);
    q.add(5, 3);
    q.add(6, 4);
    q.add(7, 55);
    System.out.println(q);
    System.out.println(q.removeSmallest());
    System.out.println(q);
    System.out.println(q.removeSmallest());
    System.out.println(q);
    System.out.println(q.removeSmallest());
    System.out.println(q);
    System.out.println(q.removeSmallest());
  }
}
