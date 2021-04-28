package day02to05_javabasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfExam {

  private static Logger logger = LoggerFactory.getLogger(IfExam.class);
  /*
  根据用户给定的数字，显示该数值对应的星期，如用户输入2，输出星期二。
  思路：
  1. 如何获取用户输入的数值呢？ 来源有很多，不确定，只要是不确定的数值，都用变量存储。
  2. 数值不确定，怎么知道输出星期几呢？那就需要对数值进行判断。
  3. 如何输出呢？用输出语句就可以
  */

  public static void main(String[] args) throws IOException {
    // 1.定义变量，存储用户输入的数值
    logger.info("请输入您想打印的数值：");
    int day = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
    String weekday;

    // 2.判断语句来判断输出星期几
    if (day == 1) {
      weekday = "星期一";
    } else if (day == 2) {
      weekday = "星期二";
    } else if (day == 3) {
      weekday = "星期三";
    } else if (day == 4) {
      weekday = "星期四";
    } else if (day == 5) {
      weekday = "星期五";
    } else if (day == 6) {
      weekday = "星期六";
    } else if (day == 7) {
      weekday = "星期日";
    } else {
      weekday = "数据错误！ 一周中没有这一天！";
    }

    // 3.输出语句打印
    logger.info(weekday);

    // 将上面的判断语句改成选择语句，做相同的事情
    switch (day) {
      case 1:
        logger.info("星期一");
        break;
      case 2:
        logger.info("星期二");
        break;
      case 3:
        logger.info("星期三");
        break;
      case 4:
        logger.info("星期四");
        //break; // 如果这里没有break语句会发生什么？
      default:
        logger.info("数据错误！ 一周中没有这一天！");
        //break;
      case 5:
        logger.info("星期五");
        //break;
      case 6:
        logger.info("星期六");
        //break;
      case 7:
        logger.info("星期七");
        //break;
    }
  }

}
