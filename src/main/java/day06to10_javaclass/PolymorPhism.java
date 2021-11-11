package day06to10_javaclass;

public class PolymorPhism {

  public static void main(String[] args) {
    Animal a = new Dog();
    System.out.println(a.age);
    a.show();
    a.run();
  }
}


class Animal {

  int age = 3;

  void show() {
    System.out.println("Animal show.");
  }

  static void run() {
    System.out.println("Animal run.");
  }
}

class Dog extends Animal {

  int age = 4;

  void show() {
    System.out.println("Dog show.");
  }

  static void run() {
    System.out.println("Dog run.");
  }
}
