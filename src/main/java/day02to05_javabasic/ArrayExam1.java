package day02to05_javabasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayExam1 {

  private static Logger logger = LoggerFactory.getLogger(ArrayExam1.class);

  public static void main(String[] args) {
    //String hex1 = toHexWithWholeBits(60);
    //String hex2 = toHexWithoutUselessZero(60);
    //String hex3 = toHexWithoutUselessZero2(60);
    //String hex4 = toHexWithoutUselessZero3(60);
    //logger.info(hex1);
    //logger.info(hex2);
    //logger.info(hex3);
    //logger.info(hex4);

    String binary = trans(60, 1, 1);
    String octal = trans(60, 7, 3);
    String hex = trans(60, 15, 4);

    logger.info("binary = " + binary);
    logger.info("octal = " + octal);
    logger.info("hex = " + hex);
  }

  /**
   * 将一个十进制的整数转换为十六进制形式，并且返回这个形式 如： 60 返回 0x0000003c
   *
   * @param num 要转换形式的整数
   * @return 返回的十六进制数值的字符串
   */
  public static String toHexWithWholeBits(int num) {
    char[] arr = new char[8];
    int index = arr.length - 1;
    for (int i = 0; index >= 0 && i < 8; i++) {
      int bit = num & 15;
      if (bit > 9) {
        arr[index] = (char) (bit - 10 + 'a');
      } else {
        arr[index] = (char) (bit + '0');
      }
      num = num >>> 4;
      index--;
    }

    return "0x" + arr2String(arr);

  }

  public static String arr2String(char[] arr) {
    String str = "";
    for (int i = 0; i < arr.length; i++) {
      str += arr[i];
    }
    return str;
  }

  /**
   * 将一个十进制的整数转换为十六进制形式，并且不要多余的0， 然后返回这个形式 如： 60 返回 0x3c
   *
   * @param num 要转换形式的十进制整数
   * @return 返回的十六进制的字符串
   */
  public static String toHexWithoutUselessZero(int num) {
    char[] arr = new char[8];
    int index = arr.length;
    for (int i = 0; index > 0 && i < 8; i++) {
      int bit = num & 15;
      if (bit > 9) {
        arr[--index] = (char) (bit - 10 + 'a');
      } else {
        arr[--index] = (char) (bit + '0');
      }

      num = num >>> 4;
      if (num == 0) { // 如果移位后的二进制数全部为零，就没必要再进行运算了
        break;
      }
    }

    return "0x" + arr2StringWithIndex(arr, index); // 为了去除多余的0，必须给定数组里有效位的索引
  }

  /**
   * 将一个十进制的整数转换为十六进制形式，并且不要多余的0， 然后返回这个形式 如： 60 返回 0x3c
   * <p>
   * 改进后的函数
   *
   * @param num 要转换形式的十进制整数
   * @return 返回的十六进制的字符串
   */
  public static String toHexWithoutUselessZero2(int num) {
    char[] arr = new char[8];
    int index = arr.length;
    while (num != 0) { // 在这里判断循环是否继续
      int bit = num & 15;
      if (bit > 9) {
        arr[--index] = (char) (bit - 10 + 'a');
      } else {
        arr[--index] = (char) (bit + '0');
      }

      num = num >>> 4;
    }

    return "0x" + arr2StringWithIndex(arr, index); // 为了去除多余的0，必须给定数组里有效位的索引
  }

  public static String arr2StringWithIndex(char[] arr, int index) {
    String str = "";
    for (int i = index; i < arr.length; i++) {
      str += arr[i];
    }
    return str;
  }

  /**
   * 查表法获取十进制位对应的十六进制数值
   */
  public static String toHexWithoutUselessZero3(int num) {
    // 首先定义一个表，存储十进制和十六进制每一位的转换
    char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    char[] arr = new char[8];
    int index = arr.length;
    while (num != 0) { // 在这里判断循环是否继续
      int bit = num & 15;
      arr[--index] = chs[bit]; // 直接用查表法去获取对应的十六进制值
      num = num >>> 4;
    }

    return "0x" + arr2StringWithIndex(arr, index); // 为了去除多余的0，必须给定数组里有效位的索引
  }

  /**
   * 十进制转其他进制的通用方法
   *
   * @param num    给定的需要进行进制转换的十进制数
   * @param base   想转的进制需要 & 的数
   * @param offset 想转的进制对应的需要移位的位数
   * @return 返回转换后的字符串
   */
  public static String trans(int num, int base, int offset) {
    // 首先定义一个表，存储十进制和其他进制每一位的转换
    char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    // 定义存储转换后的每一位的数组，长度是32是因为如果转二进制最多有32位
    char[] arr = new char[32];
    int index = arr.length;
    while (num != 0) { // 在这里判断循环是否继续
      int bit = num & base;
      arr[--index] = chs[bit]; // 直接用查表法去获取对应的十六进制值
      num = num >>> offset;
    }

    return arr2StringWithIndex(arr, index); // 为了去除多余的0，必须给定数组里有效位的索引

  }


}
