import java.util.*;

public class Sorts  {

  public static void randomize(int[] a) {
    Random rand = new Random();
    for (int i = 0; i < a.length; ++i) {
      int randIndex = rand.nextInt(a.length - i) + i;
      int tmp = a[i];
      a[i] = a[randIndex];
      a[randIndex] = tmp;
    }
  }

  // s and e are inclusive
  public static int partition(int[] a, int s, int e) {
    int[] partition = Arrays.copyOf(a, a.length);
    
    Random rand = new Random();
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    int ss = s, ee = e;
    int dupCount = 0;
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
        dupCount++;
      }
    }

    for (int i = ss; i < ss + dupCount; ++i) {
      a[i] = partition[pivotIndex];
    }

    return ss;
  }

  public static int quickselect(int[] a, int k) {
    int[] partition = Arrays.copyOf(a, a.length);
    
    Random rand = new Random();
    int s = 0, e = a.length - 1;
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    int ss = s, ee = e;
    int dupCount = 0;
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
        dupCount++;
      }
    }

    for (int i = ss; i < ss + dupCount; ++i) {
      a[i] = partition[pivotIndex];
    }

    if (k < ss) {
      return quickselect(Arrays.copyOfRange(a, 0, ss + 1), k);
    } else if (k > ee) {
      return quickselect(Arrays.copyOfRange(a, ee, a.length), k);
    } else {
      return partition[pivotIndex];
    }
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
    int[] a = new int[Integer.parseInt(args[0])];
    for (int i = 0; i < a.length; ++i) {
      a[i] = i;
    }
    int[] b = new int[] {
      1, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4
    };
    System.out.println(quickselect(b, 2));
  }
}
