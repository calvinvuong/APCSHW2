import java.util.*;

public class Sorts {

  public static String name() {
    return "lin.alvin";
  }
  
  /**
   * Swaps two elements in an array.
   * @param a The array of elements.
   * @param i1 The index of the first element to swap.
   * @param i2 The index of the second element to swap.
   */
  private static void swap(int[] a, int i1, int i2) {
    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
  
  /**
   * Randomizes all the elements in an array.
   * @param a An array of elements to randomize.
   */
  public static void randomize(int[] a) {
    Random rand = new Random();
    for (int i = 0; i < a.length; ++i) {
      int randIndex = rand.nextInt(a.length - i) + i;
      swap(a, i, randIndex);
    }
  }

  /**
   * Selection sorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void selection(int[] a) {
    for (int i = 0; i < a.length - 1; ++i) {
      int minIndex = i;
      for (int j = i + 1; j < a.length; ++j) {
        if (a[j] < a[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        swap(a, i, minIndex);
      }
    }
  }

  /**
   * Insertion sorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void insertion(int[] a) {
    int tmp, track;
    for (int i = 1; i < a.length; ++i) {
      tmp = a[i];
      track = i;
      while (track > 0 && tmp < a[track - 1]) {
        a[track] = a[track - 1];
        track--;
      }
      a[track] = tmp;
    }
  }

  /**
   * Partition function for the quicksort
   * @param a The array to partition.
   * @param s The starting index (inclusive).
   * @param e The end index (inclusive).
   * @return The index of the pivot.
   */
  private static int partition(int[] a, int s, int e) {
    Random rand = new Random();
    int pivotIndex = rand.nextInt(e - s + 1) + s;

    swap(a, e, pivotIndex);
    for (int i = s; i < e; ++i) {
      if (a[i] < a[e]) {
        swap(a, s, i);
        s++;
      }
    }
    swap(a, e, s);
    return s;
  }

  /**
   * Returns the kth smallest integer in given array of ints.
   * @param a The array to search through
   * @param k The kth smallest number to find.
   * @return The kth smallest integer in array a.
   */
  public static int quickselect(int[] a, int k) {
    return quickselect(a, 0, a.length - 1, k - 1);
  }

  /**
   * Private recursive helper method for the quickselect.
   * @param a The array to search through.
   * @param s The starting index (inclusive).
   * @param e The ending index (inclusive).
   * @param k The kth smallest number to find.
   */
  private static int quickselect(int[] a, int s, int e, int k) {
    int testPivot = partition(a, s, e);

    if (k < testPivot) {
      return quickselect(a, s, testPivot - 1, k);
    } else if (k > testPivot) {
      return quickselect(a, testPivot + 1, e, k);
    } else {
      return a[testPivot];
    }
  }

  /**
   * Quicksorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void quicksort(int[] a) {
    quicksortH(a, 0, a.length - 1);
  }

  /**
   * Private helper method for the quicksort.
   * @param a The array of integers to sort.
   * @param s The starting index (inclusive).
   * @param e The ending index (inclusive).
   */
  private static void quicksortH(int[] a, int s, int e) {
    if (s >= e) {
      return;
    }
    int pivot = partition(a, s, e);
    quicksortH(a, s, pivot - 1);
    quicksortH(a, pivot + 1, e);
  }

  /**
   * Merges two sorted arrays.
   * @param a1 The first sorted array.
   * @param a2 The second sorted array.
   * @return An array contained all the elements of a1 and a2, sorted.
   */
  private static int[] merge(int[] a1, int[] a2) {
    int[] mergedList = new int[a1.length + a2.length];
    int c1 = 0, c2 = 0;
    while (c1 <= a1.length && c2 <= a2.length) {
      if (c1 == a1.length) {
        for (; c2 < a2.length; ++c2) {
          mergedList[c1 + c2] = a2[c2];
        }
        return mergedList;
      }
      if (c2 == a2.length) {
        for (; c1 < a1.length; ++c1) {
          mergedList[c1 + c2] = a1[c1];
        }
        return mergedList;
      }
      if (a1[c1] < a2[c2]) {
        mergedList[c1 + c2] = a1[c1];
        c1++;
      } else {
        mergedList[c1 + c2] = a2[c2];
        c2++;
      }
    }
    return mergedList;
  }

  /**
   * Private helper method for the merge sort.
   * @param a The array to merge sort.
   * @return Returns the sorted version of array a.
   */
  private static int[] mergesortH(int[] a) {
    if (a.length == 1) {
      return a;
    }

    int[] sub1 = Arrays.copyOfRange(a, 0, a.length / 2);
    int[] sub2 = Arrays.copyOfRange(a, a.length / 2, a.length);

    return merge(sort(sub1), sort(sub2));
  }

  /**
   * Mergesorts a given array of integers.
   * @param a The array of integers to sort.
   */
  public static void mergesort(int[] a) {
    int[] q = Sorts.sort(a);
    for (int i = 0; i < q.length; ++i) {
      a[i] = q[i];
    }
  }

  public static void heapify(int[] a) {
  }

  public static void heapsort(int[] a) {
  }

  
  public static void main(String[] args) {
  }
}
