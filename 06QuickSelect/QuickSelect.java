import java.util.*;

public class QuickSelect {

  // s and e are inclusive
  public static int partition(int[] a, int s, int e) {
    int[] partition = Arrays.copyOf(a, a.length);
    
    Random rand = new Random();
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    int ss = s, ee = e;
    for (int i = s; i < e + 1; ++i) {
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
    return ss;
  }

  public static int kthLowest(int[] a, int k) {
    if (k >= a.length) {
      throw new ArrayIndexOutOfBoundsException();
    }

    int[] tmp = Arrays.copyOf(a, a.length);

    int testK = partition(tmp, 0, tmp.length - 1);
    while (testK != k) {
      if (testK > k) {
        testK = partition(tmp, 0, testK - 1);
      } else {
        testK = partition(tmp, testK, tmp.length - 1);
      }
    }
    return tmp[testK];
  }

  private static void quicksortH(int[] a, int s, int e) {
    if (s < e) {
      int pivot = partition(a, s, e);
      quicksortH(a, s, pivot - 1);
      quicksortH(a, pivot + 1, e);
    }
  }

  public static void quicksort(int[] a) {
    quicksortH(a, 0, a.length - 1);
  }

  public static void main(String[] args) {
    int[] a = new int[] {
      4, 3, 1, 2, 7, 9, 8, 6, 5
    };

    quicksort(a);
    System.out.println(Arrays.toString(a));
  }
}
