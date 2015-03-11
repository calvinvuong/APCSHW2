public class LNode {

  private int value_;
  private LNode next_;

  public LNode(int value) {
    this(value, null);
  }

  public LNode(int value, LNode next) {
    value_ = value;
    next_ = next;
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
