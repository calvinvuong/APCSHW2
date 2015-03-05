import java.util.*;

public class QuickSelect {
  
  private static void repartition(int[] a, int s, int e) {
    int[] partition = Arrays.copyOfRange(a, s, e);

    Random rand = new Random();
    int pivot = rand.nextInt(e - s) + s;

    System.out.println(pivot);

    int[] tmp = new int[partition.length];
    int ss = 0, ee = partition.length - 1;
    for (int i = 0; i < partition.length; ++i) {
      if (i == pivot) {
        continue;
      }
      if (partition[i] < partition[pivot]) {
        tmp[ss] = partition[i];
        ss++;
      } else {
        tmp[ee] = partition[i];
        ee--;
      }
    }
    tmp[ss] = partition[pivot];

    for (int i = s; i < e; ++i) {
      a[i] = tmp[i];
    }
  }

  public static int kthLowest(int[] a) {
    int kthLowest = 0;
    return kthLowest;
  }

  public static void main(String[] args) {
    int[] a = new int[] {
      4, 2, 5, 7, 9, 3, 6, 8, 1
    };

    repartition(a, 0, a.length);
    System.out.println(Arrays.toString(a));
  }
}
