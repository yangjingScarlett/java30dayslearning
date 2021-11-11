package day02to05_javabasic;

import java.util.Arrays;

public class ArrayExam5 {

  public static void main(String[] args) {
    int[] arr = new int[10];
    for (int i = 0; i < arr.length; i++) {
      if (i == 0) {
        arr[i] = (int) (Math.random() * 100);
      } else {
        arr[i] = (int) (arr[i - 1] + Math.random() * 10);
      }
    }

    printArray(arr);
    int index = getInsertPoint(arr, 119);
    System.out.println(index);

    // Arrays.binarySearch 是java做二分查找的内部方法，如果找到返回所在位置，没找到的话返回 -(min + 1); 表示插入点的位置是第 min + 1个。
    // - 号是为了表示没找到，(low + 1) 是因为如果插入点的位置是0的话，那么 -0 还是0 会有歧义，所以干脆写成 -1，表示插入点是第一个位置。
    int index2 = Arrays.binarySearch(arr, 119);
    System.out.println("index2 = " + index2);
  }

  /**
   * 在有序数组中插入一个数并继续保持有序，问应该插入的位置。
   * <p>
   * 思路： 1. 有序数组，找位置，首先想到跟二分查找有点像。
   * <p>
   * 2. 写一下二分查找的函数。 分析发现如果找到key，那么返回key的位置，如果找不到key，返回-1.
   * <p>
   * 3. 对于找key的插入点，如果找到key，key的位置就是插入点。如果找不到key，那么插入点应该是第一个比key大的值。 在二分查找法中，如果没找到key，min 和
   * max分别是什么？此时 min 应该大于 max，且min位置的值比key大，max的值比key小。那么min刚好就是我们要找的插入点。
   */
  public static int getInsertPoint(int[] arr, int key) {

    int min = 0, max = arr.length, mid;

    while (min <= max) {
      mid = (min + max) >> 1;
      if (key > arr[mid]) {
        min = mid + 1;
      } else if (key < arr[mid]) {
        max = mid - 1;
      } else {
        return mid;
      }
    }
    return min;
  }

  public static void printArray(int[] arr) {
    System.out.print("[");
    for (int i = 0; i < arr.length; i++) {
      if (i != arr.length - 1) {
        System.out.print(arr[i] + ", ");
      } else {
        System.out.println(arr[i] + "]");
      }
    }
  }
}
