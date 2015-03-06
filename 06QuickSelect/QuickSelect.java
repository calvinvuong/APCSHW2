import java.util.*;

public class QuickSelect {

  // s and e are inclusive
  public static void partition(int[] a, int s, int e) {
    int[] partition = Arrays.copyOf(a, a.length);
    
    Random rand = new Random();
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    System.out.println(pivotIndex);

    int ss = s, ee = e;
    for (int i = s; i < e + 1; ++i) {
      System.out.println(Arrays.toString(a) + " " + ss + " " + ee + " ");
      if (i == pivotIndex) {
        continue;
      }
      if (partition[i] < partition[pivotIndex]) {
        a[ss] = partition[i];
        ss++;
      } else if (partition[i] > partition[pivotIndex]) {
        a[ee] = partition[i];
        ee--;
      } else {
        throw new Error("Boom");
      }
    }
    a[ss] = partition[pivotIndex];
  }

  public static int kthLowest(int[] a) {
    int kthLowest = 0;
    return kthLowest;
  }

  public static void main(String[] args) {
    int[] a = new int[] {
      4, 2, 5, 7, 9, 3, 6, 8, 1
    };

    partition(a, 0, a.length - 1);
    System.out.println(Arrays.toString(a));
  }
}
