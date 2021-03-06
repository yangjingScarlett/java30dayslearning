package day02to05_javabasic;

public class ForLoopExam {

  public static void main(String[] args) {
    /*
    需求： 在屏幕上打印以下图形：
            *********
             *******
              *****
               ***
                *
    思路一  1. 观察图形发现有5行，每一行有多个*。
           2. 先不要考虑那么复杂，首先5行我们就可以用循环实现，一个循环来控制行数，行数是确定的为5.
           3. 观察每一行发现总是先打印空格，然后再打印*，每一行打印不同的空格就有一个循环控制空格数，而每一行空格数不确定，就用变量表示空格数。
           4. 每一行也要打印多个*就需要另一个循环控制*数，而每一行*数也不确定，再用一个变量表示*数。
           5. 总结需要三个循环，一个控制行数共5行，一个控制每一行空格数共x个，另一个控制每一行*数共y个。
           6. 三个循环里其中后两个循环都取决于第一个循环，那么就知道共两层。
    */
    int x = 0, y = 9;
    for (int i = 1; i <= 5; i++) {// 控制行数，从1到5
      for (int j = 1; j <= x; j++) { // 控制空格数，从1到x，当行数为1时，x为0，那么x初始值就是0，后面每加一行，空格数就加1
        System.out.print(" ");
      }

      for (int k = 1; k <= y; k++) { // 控制*数，从1到y，当行数为1时，y为9，那么y初始值为9，后面每加一行，*数量就减2
        System.out.print("*");
      }
      System.out.println(); // 记得每一行打印完成后要换行
      x++;
      y -= 2;
    }

    /*
    思路二 1. 观察图形发现有5行，每一行有多个*。
          2. 先不要考虑那么复杂，首先5行我们就可以用循环实现，一个循环来控制行数，行数是确定的为5.
          3. 每一行先打印空格，空格数虽然不确定，但是却有规律可循，第一行空格为0个，第二行空格为1个，依次递增1。
             分析发现，空格数 = 行数 - 1，那么每一行都要循环打印空格（行数-1）次。
          4. 每一行打印完空格后再打印*，第一行有9个，第二行7个，分析发现，（*的个数 + 空格数 x 2） = 9，
             也就是说 * 的个数 = 9 - 空格数x2
     */

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < i; j++) {
        System.out.print(" ");
      }
      for (int k = 0; k < 9 - 2 * i; k++) {
        System.out.print("*");
      }
      System.out.println();
    }
  }

}
