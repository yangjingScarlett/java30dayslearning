package day06to10_javaclass;

public class Heritance {

  public static void main(String[] args) {
    Child child = new Child();
    child.show();
  }
}

class Father {

  int num = 4;
}

class Child extends Father {

  private int num2 = 5;

  public void show() {
    int num3 = 6;
    System.out.println(num);
    System.out.println(num2);
    System.out.println(num3);
  }
}