public class MyStack<T> {
  private MyLinkedList<T> linkedList_;

  public MyStack() {
    linkedList_ = new MyLinkedList<T>();
  }

  public void push(T element) {
    linkedList_.add(element);
  }

  public T peek() {
    return linkedList_.get(0);
  }

  public T pop() {
    return linkedList_.remove(0);
  }

  public int size() {
    return linkedList_.size();
  }

  public static void main(String[] args) {
    MyStack<Integer> q = new MyStack<Integer>();
    for (int i = 0; i < 10; ++i) {
      q.push(i);
    }
    while (q.size() != 0) {
      System.out.println(q.pop());
    }
  }
}
