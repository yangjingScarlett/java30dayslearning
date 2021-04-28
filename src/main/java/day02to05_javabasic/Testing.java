package day02to05_javabasic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Testing {

  public static void main(String[] args) {
    int[] nums = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 6, 6, 7, 8, 8, 8};
    //check(nums);
    //uniqueNum(nums);
    //uniqueNum2(nums);
    recordFrequency(nums);
  }

  /**
   * record the unique num and its frequency in the array
   *
   * @param nums given array with ascending numbers
   */
  static void check(int[] nums) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (freq.get(nums[i]) != null) {
        freq.put(nums[i], freq.get(nums[i]) + 1);
      } else {
        freq.put(nums[i], 1);
      }
    }
    System.out.println(freq);
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

  /**
   * record the frequency of each num in the array
   * <p>
   * 1. create an array to record the frequency of each num 2. use the index as the num, and the
   * value as its frequency
   *
   * @param nums given array with ascending numbers
   */
  static void recordFrequency(int[] nums) {
    int[] freq = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      freq[nums[i]] = freq[nums[i]] + 1;
    }
    System.out.println(Arrays.toString(freq));
  }
}
