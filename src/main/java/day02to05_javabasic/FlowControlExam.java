package day02to05_javabasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlowControlExam {

  private static Logger logger = LoggerFactory.getLogger(FlowControlExam.class);

  public static void main(String[] args) throws IOException {
    /*
    1.	根据用户输入的月份，显示月份对应的季节。3/4/5春季，6/7/8夏季，9/10/11秋季，12/1/2 冬季，
    别的数字显示不属于月份。分别用if和switch实现。
     */

    /*
    2.	对给定的整数，获取其十六进制的表现形式。
    思路： 1. 给定的整数不确定，用变量表示。
          2. 整数对应的二进制数在内存中是32位： 00000000 00000000 00000000 00000000
             二进制转换为十六进制就是每4位合并成1位，最后得到一个8位的数
             如何获取二进制中的四位？ 可以用 （& 00000000 00000000 00000000 00001111）获取，也就是 &15。
             注意得到的这个数字是十六进制的一位，那么如果它大于等于10，需要转换为十六进制字符abcedf，如下：
             十进制：   0 - 9,   10,   11,   12,   13,   14,   15
             十六进制： '0'-'9', 'a',  'b',  'c',  'd',  'e',  'f'
             如何转换？ 大于等于10的时候， 十六进制=（十进制 - 10）+'a'

          3. 获取了最后四位后，要移除这四位，再把它之前的四位移到最后四位上，可以用 >>> 4 实现
          4. 移位移几次呢？每次已4位，共有32位，那么就要移8次。
          5. 重复做移位动作，使用循环即可。
     */

    System.out.println("请输入你想转换为十六进制的一个整数：");
    int a = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
    String a16 = "";
    for (int i = 0; i < 8; i++) {
      int b = a & 15;
      if (b < 10) {
        a16 = b + a16; // 注意，因为是从最后一位开始取的，所以组合的时候注意顺序
      } else {
        b = (b - 10) + 'a';
        a16 = (char) b + a16;
      }
      a = a >>> 4;
    }

    logger.info(a + "的十六进制表现是： " + a16);



  /*
    3.	三个数，显示最大的数。
   */

  /*
   4.	在屏幕上显示如下内容：
    54321
    5432
    543
    54
    5

    思路： 1. 循环打印，用循环语句，打印5行。
    2. 每一行打印不确定的次数用变量n表示，第一行打印5次，所以n初始值为5，每次递减1。
    3. 每一行每一次打印的值不确定，用value表示，value从5开始递减
   */
    int n = 5;
    for (int i = 0; i < 5; i++) {
      int value = 5;
      for (int j = 0; j < n; j++) {
        System.out.print(value--);
      }
      System.out.println();
      n--;
    }

    /*
    上述第4个问题的思路有没有更好的？ 有
    1. 循环打印，每次打印5行。
    2. 每一行打印的次数不确定，但是有规律，上面定义的行数从0开始，所以规律是：
       第0行打印5个，第1行4个，第2行3个， 次数 = 5 - 行数，如果从0次开始，那么总次数就 < 5 - 行数。
    3. 每一行打印的值都是从5开始，依次递减。这个值和打印次序也有关系啊：
       第0次是5，第1次是4，第2次是3，第3次是2，第4次是1. 值 = 5 - 次数。
     */
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5 - i; j++) {
        System.out.print(5 - j);
      }
      System.out.println();
    }

    /*
    5.	在屏幕上打印九九乘法表：
      1*1=1
      1*2=2 2*2=4
      1*3=3 2*3=6 3*3=9
      ......

      思路：1.循环打印9行
      2. 第1行打印1次，第2行打印2次，第3行打印3次.......第i行打印i次
         分析发现，每一行每一次打印的都是 n*行数，至于n则取决于次数，第1次打印是1*行数，第2次则是2*行数.....
     */
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + "*" + i + "=" + j * i + "\t");
      }
      System.out.println();
    }

    /*
    6.	在屏幕上打印出 “Hello World!”。注意，屏幕上也要有双引号
     */
    System.out.println("\"Hello World!\"");

  }
}
