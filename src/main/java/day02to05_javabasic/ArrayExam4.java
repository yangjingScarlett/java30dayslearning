package day02to05_javabasic;

public class ArrayExam4 {

  public static void main(String[] args) {
    int[] arr = {3, 55, 8, 44};
    printArray(arr);
    reverse(arr);
    printArray(arr);
  }

  /**
   * 对一个给定的数组进行反转，如： {3, 55, 8, 44} - {44, 8, 55 ,3}
   * <p>
   * 思路： 1. 反转意味着每次都有两个变量交换位置，这两个位置不确定，所以定义变量i, j。
   * <p>
   * 2. i 从头开始， j 从尾开始， i每次加1， j每次减一。
   * <p>
   * 3. i永远要小于j
   */
  public static void reverse(int[] arr) {
    for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
      swap(arr, i, j);
    }
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

  public static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

}
