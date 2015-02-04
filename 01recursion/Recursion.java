public class Recursion {

  public String name() {
    return "Lin,Alvin";
  }

  public int fact(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n <= 1) {
      return n;
    }
    return n * fact(n - 1);
  }

  public int fib(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  private double newGuess(double n, double guess) {
    return ((n / guess) + guess) / 2.0;
  }

  private double squirt(double n, double guess) {
    if (Math.abs(newGuess(n, guess) - guess) < 0.01) {
      return newGuess(n, guess);
    }
    return squirt(n, newGuess(n, guess));
  }

  public double sqrt(double n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    return squirt(n, n / 2.0);
  }

  public static void main(String[] args) {
    Recursion r = new Recursion();

    System.out.println(r.sqrt(81));
    System.out.println(r.sqrt(121));
  }
}
