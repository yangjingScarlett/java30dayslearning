package day02to05_javabasic;

public class ArrayExam2 {

  public static void main(String[] args) {
    int[] arr = new int[10];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * 10);
    }
    System.out.print("初始的数组： ");
    printArray(arr);
    // sortSelect(arr);
    // sortBubble(arr);
    sortInsert(arr);
    System.out.print("排序后数组： ");
    printArray(arr);
  }

  /**
   * 选择排序逻辑：从0开始，每次记录这个位置上应该放的最小值的位置。
   */
  public static void sortSelect(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      int min = i; // min 代表最小值所在的位置，初始时假定就是当前的 i
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[min]) {
          min = j; // 记录最小值的位置
        }
      }
      // 当前 i 上的值和我们拿到的最小值的位置，两个位置上的值进行交换，就能保证最后i位置上是最小的
      swap(arr, i, min);
    }
  }

  /**
   * 冒泡排序逻辑：依次比较左右两个位置的值，每次都将最大值放最后。
   */
//  public static void sortBubble(int[] arr) {
//    for (int i = 0; i < arr.length - 1; i++) {
//      for (int j = 0; j < arr.length - 1 - i; j++) { // j < arr.length - i 会报错，因为下面有 arr[j+1]
//        if (arr[j] > arr[j + 1]) {
//          swap(arr, j, j + 1);
//        }
//      }
//    }
//
//  }
  public static void sortBubble(int[] arr) {
    for (int i = arr.length; i > 0; i--) {
      for (int j = 0; j < i - 1; j++) {
        int before = arr[j];
        int after = arr[j + 1];
        if (before > after) {
          arr[j] = after;
          arr[j + 1] = before;
        }
      }
    }
  }


  public static void sortInsert(int[] arr) {

    for (int i = 1; i < arr.length; i++) {
      int selected = arr[i];
      int j;
      for (j = i; j > 0; j--) {
        if (arr[j - 1] > selected) {
          arr[j] = arr[j - 1];
        } else {
          break;
        }
      }
      arr[j] = selected;
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
