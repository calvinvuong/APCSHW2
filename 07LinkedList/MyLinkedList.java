public class MyLinkedList {
  
  private int size_;
  private LNode head_;

  public MyLinkedList() {
    size_ = 0;
  }

  public int get(int index) {
    if (index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return 0;
  }

  public void set(int index, int value) {
  }

  public int remove(int index) {
    return 0;
  }

  public void add(int value) {
    if (size_ == 0) {
      head_ = new LNode(value);
      size_++;
    } else {
      LNode current = head_;
      while (current.getNext() != null) {
        current.setNext(new LNode(value));
        current = current.getNext();
        size_++;
      }
    }
  }

  public void add(int index, int value) {
    if (index >= size_) {
      throw new ArrayIndexOutOfBoundsException();
    }
    LNode current = head_;
    for (int i = 0; i < index; ++i) {
      current = current.getNext();
    }
    
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
    String out = "[ ";
    LNode current = head_;
    while (current.getNext() != null) {
      out += current.toString() + ", ";
      current = current.getNext();
    }
    return out + " ]";
  }

  public static void main(String[] args) {
    MyLinkedList l = new MyLinkedList();
    System.out.println(l.size());
    System.out.println(l);
    l.add(3);
    System.out.println(l.size());
    System.out.println(l);
    l.add(4);
    System.out.println(l.size());
    System.out.println(l);
  }
}
