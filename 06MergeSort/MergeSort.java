import java.util.*;

public class MergeSort {

  private static void insert(int[] target, int index, int[] array) {
    for (int i = 0; i < array.length; ++i) {
      target[index + i] = array[i];
    }
  }

  public static int[] merge(int[] a1, int[] a2) {
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
  
  public static void main(String[] args) {
    int[] a = new int[] {
      1, 1, 2, 6, 8, 10, 19, 20, 100
    };
    int[] b = new int[] {
      2, 4, 7, 9, 10, 11, 19
    };
    System.out.println(Arrays.toString(MergeSort.merge(a, b)));
  }
}
