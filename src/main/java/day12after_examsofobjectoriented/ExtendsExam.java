package day12after_examsofobjectoriented;

class A {

  void func1() {
    System.out.println(func2());
  }

  int func2() {
    return 123;
  }
}

public class ExtendsExam extends A {

  int func2() {
    return 456;
  }

  public static void main(String[] args) {
    A a;
    ExtendsExam b = new ExtendsExam();
    b.func1();

    a = b;
    a.func1();
  }
}
