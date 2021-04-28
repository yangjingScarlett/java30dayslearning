package day02to05_javabasic;

public class ArrayExam3 {

  public static void main(String[] args) {
    int[] arr = {23, 34, 56, 78, 79, 80, 83, 85, 91, 99};
    int index = binarySearch(arr, 91);
    System.out.println(index);
  }

  /**
   * 二分查找逻辑： 每次都从中间位置查找，如果找到返回索引，如果没找到更新中间位置。
   *
   * @param arr 有序的数组
   * @param key 要查找的数值
   */
  public static int binarySearch(int[] arr, int key) {
    // 定义起始位置，结尾位置，和中间位置
    int begin = 0;
    int end = arr.length;

    while (begin <= end) {
      int mid = (begin + end) / 2;
      if (arr[mid] > key) {
        end = mid - 1;
      } else if (arr[mid] < key) {
        begin = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

}
