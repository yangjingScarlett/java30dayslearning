package day12after_examsofobjectoriented;

public class ArrayFindItemExam {

  private static int find(char[] arr, char c) {
    if (arr == null) {
      throw new IllegalArgumentException("传入的数组为空！");
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == c) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    char[] arr = {'a', 'b', 'r', 't', 'n', 'm', 'r', 'y', 'r', 'b'};
    int index = find(arr, 'u');
    System.out.println(index);
  }

}
