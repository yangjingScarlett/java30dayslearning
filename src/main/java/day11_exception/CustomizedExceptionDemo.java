package day11_exception;


/**
 * 自定义的异常类
 */
class NegativeNumberException extends RuntimeException {

  public NegativeNumberException() {
  }

  public NegativeNumberException(String message) {
    super(message);
  }
}

/**
 * 除法功能类
 */
class DivideDemo {

  public int divide(int a, int b) {
    if (b < 0) {
      throw new NegativeNumberException("除数不能为负数。");
    }
    if (b == 0) {
      throw new ArithmeticException("除数不能为0");
    }
    return a / b;
  }
}

/**
 * 主函数类
 */
public class CustomizedExceptionDemo {

  public static void main(String[] args) {
    DivideDemo divideDemo = new DivideDemo();
    int result = divideDemo.divide(10, -2);
    System.out.println(result);
  }
}
