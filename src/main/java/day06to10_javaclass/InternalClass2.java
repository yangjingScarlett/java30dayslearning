package day06to10_javaclass;

import day06to10_javaclass.Outer2.Inner2;

public class InternalClass2 {

  public static void main(String[] args) {
    // new Outer2("Outer", "This is Outer");
    // Inner2.show2(); // static internal class can be used like an outernal class
    //
    // // if you want to call show(), you must create an instance for Inner2, because show() is not static
    // Inner2 inner2 = new Inner2();
    // inner2.show();

    Outer2.Inner2 inner21 = new Outer2.Inner2(); // this is the same with Inner2 inner2 = new Inner2();
    inner21.show();
    inner21.show2();
  }

}

class Outer2 {

  private static String name;
  private String desc; // Inner2 class cannot access desc because the whole Inner2 is static

  public Outer2(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }

  static class Inner2 {

    void show() {
      System.out.println("Inner show: " + name);
    }

    static void show2() {
      System.out.println("Inner show: " + name);
    }
  }

}
