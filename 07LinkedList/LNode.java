public class LNode {

  private int value_;
  private LNode next_;

  public LNode(int value) {
    value_ = value;
  }

  public String toString() {
    return "" + value_;
  }

  public LNode getNext() {
    return next_;
  }

  public void setNext(LNode next) {
    next_ = next;
  }

  public int getValue() {
    return value_;
  }

  public void setValue(int value) {
    value_ = value;
  }
}
