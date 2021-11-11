package day06to10_javaclass;

public class InternalClass3 {

  public static void main(String[] args) {
    Outer3 outer3 = new Outer3(10);
    outer3.method();
  }

}

class Outer3 {

  private int num;

  public Outer3(int num) {
    this.num = num;
  }

  public void method() {
    new Outer3(6) { // anonymous and partial internal class
      public void show() {
        System.out.println("show: " + num); // guess what is the value?
      }
    }.show();
  }
}
