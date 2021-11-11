package day06to10_javaclass;

public class Heritance2 {

  public static void main(String[] args) {
    Child2 child2 = new Child2();
    child2.show();
  }
}

class Father2 {

  int num = 4;
}

class Child2 extends Father2 {

  private int num = 5;

  public void show() {
    int num = 6;
    System.out.println(num);
    System.out.println(this.num);
    System.out.println(super.num);
  }
}
