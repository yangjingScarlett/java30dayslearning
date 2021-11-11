package day06to10_javaclass;

import day06to10_javaclass.Outer.Inner;

public class InternalClass {

  public static void main(String[] args) {
    Outer outer = new Outer("out");
    outer.show();

    Inner inner = outer.new Inner(13);
    inner.show();
  }
}

class Outer {

  private String name;

  public Outer(String name) {
    this.name = name;
  }

  public void show() {
    System.out.println("Outer show: " + name);
  }

  class Inner {

    private int age;

    public Inner(int age) {
      this.age = age;
    }

    public void show() {
      System.out.println("Inner show: " + name);
      System.out.println("Inner show: " + age);
    }
  }
}
