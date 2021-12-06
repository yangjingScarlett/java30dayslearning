package day13to15_multiplethreads;

class MyThread extends Thread {

  private String name;

  public MyThread(String name) {
    this.name = name;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(Thread.currentThread().getName() + "......" + name + "......" + i);
    }
  }
}

public class ThreadDemo {

  public static void main(String[] args) {
    MyThread t1 = new MyThread("张三");
    MyThread t2 = new MyThread("李四");
    t1.start();
    t2.start();

    for (int i = 0; i < 10; i++) {
      System.out.println(Thread.currentThread().getName() + "......" + i);
    }
  }
}
