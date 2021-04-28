package day02to05_javabasic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorExam {

  private static Logger logger = LoggerFactory.getLogger(OperatorExam.class);
  /*
  1. 最有效的方式计算 2 乘以 8
  2. 对两个整数变量的值进行互换（不能使用第三方变量）
  */

  public static void main(String[] args) {
    // 1. 2 * 8这个运算在计算机中，首先将2和8转换为二进制，然后对每一位进行乘法运算。有没有更高效的方式？
    logger.info("2 << 3 = {}", 2 << 3);

    // 2. 使用求和计算，比较容易想到，但是有个问题，如果两个数字很大，那么求和的结果可能超出int的范围
    int m = 13, n = 14;
    logger.info("m = {}, n = {}", m, n);
    m = m + n;
    n = m - n;
    m = m - n;
    logger.info("m = {}, n = {}", m, n);

    // 2. 使用异或，最有效率
    int a = 3, b = 4;
    logger.info("a = {}, b = {}", a, b);
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    logger.info("a = {}, b = {}", a, b);
  }

}
