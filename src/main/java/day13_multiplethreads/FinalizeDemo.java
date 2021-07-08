package day13_multiplethreads;

class Sample {

  public void finalize() { // 重写了Object的方法
    System.out.println("finalize sample");
  }
}

public class FinalizeDemo {

  public static void main(String[] args) {
    new Sample();
    new Sample();
    new Sample();
    new Sample();
    new Sample();
    System.gc(); // 启动垃圾回收器
    System.out.println("main function finish");
  }
}
