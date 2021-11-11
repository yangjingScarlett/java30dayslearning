package day02to05_javabasic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Testing {

  public static void main(String[] args) {
    int[] arr = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 6, 6, 7, 8, 8, 8};
    recordFrequency(arr);
    recordFrequency2(arr);
    recordFrequency3(arr);
    // uniqueNum(arr);
    // uniqueNum2(arr);
  }

  /**
   * record the unique num and its frequency in the array
   *
   * @param num given array with ascending numbers
   */
  static void recordFrequency(int[] num) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
      if (freq.containsKey(num[i])) {
        freq.put(num[i], freq.get(num[i]) + 1);
      } else {
        freq.put(num[i], 1);
      }
    }
    System.out.println(freq);
  }


  /**
   * record the unique num and its frequency in the array.
   * <p>
   * 1. create an array to record the frequency of each num 2. use the index as the num, and the
   * value as its frequency
   *
   * @param num given array with ascending num
   */
  static void recordFrequency2(int[] num) {
    int[] freq = new int[num.length];
    int current = 0, n = 0;
    for (int i = 0; i < num.length; i++) {
      if (num[i] == current) {
        n++;
      } else {
        freq[current] = n;
        while (num[i] != current) {
          current++;
        }
        n = 1;
      }
    }
    freq[current] = n;
    System.out.println(Arrays.toString(freq));
  }

  /**
   * record the frequency of each num in the array
   * <p>
   * 1. create an array to record the frequency of each num 2. use the index as the num, and the
   * value as its frequency
   *
   * @param num given array with ascending numbers
   */
  static void recordFrequency3(int[] num) {
    int[] freq = new int[num.length];
    for (int i = 0; i < num.length; i++) {
      freq[num[i]] = freq[num[i]] + 1;
    }
    System.out.println(Arrays.toString(freq));
  }


  /**
   * get the unique number in the array
   *
   * @param nums given array with ascending numbers
   */
  static void uniqueNum(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      set.add(nums[i]);
    }
    System.out.println(set);
  }

  /**
   * get the unique number in the array, without using any data structure
   * <p>
   * 1. use another array to record 2. check if current num is in new array, if so, go to next num,
   * if not, record it int the new array
   *
   * @param nums given array with ascending numbers
   */
  static void uniqueNum2(int[] nums) {
    int[] uniqueArr = new int[nums.length];
    uniqueArr[0] = nums[0];
    int index = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != uniqueArr[index]) {
        uniqueArr[++index] = nums[i];
      }
    }
    System.out.println(Arrays.toString(uniqueArr));
  }

}
