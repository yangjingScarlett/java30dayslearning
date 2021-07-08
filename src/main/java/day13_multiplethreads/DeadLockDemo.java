package day13_multiplethreads;

class Task implements Runnable {

  private boolean flag;

  public Task(boolean flag) {
    this.flag = flag;
  }

  public void run() {
    if (flag) {
      while (true) {
        synchronized (MyLock.LOCK_A) {
          System.out.println("if......LOCK_A");
          synchronized (MyLock.LOCK_B) {
            System.out.println("if......LOCK_A");
          }
        }
      }
    } else {
      while (true) {
        synchronized (MyLock.LOCK_B) {
          System.out.println("else......LOCK_B");
          synchronized (MyLock.LOCK_A) {
            System.out.println("else......LOCK_A");
          }
        }
      }
    }
  }
}

class MyLock {

  public static final Object LOCK_A = new Object();
  public static final Object LOCK_B = new Object();
}

public class DeadLockDemo {

  public static void main(String[] args) {
    Task t1 = new Task(true);
    Task t2 = new Task(false);
    new Thread(t1).start();
    new Thread(t2).start();
  }
}
