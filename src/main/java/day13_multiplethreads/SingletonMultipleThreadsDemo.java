package day13_multiplethreads;


class SingletonHungry {

  private static final SingletonHungry singleton = new SingletonHungry();

  private SingletonHungry() {
  }

  public static SingletonHungry getInstance() {
    return singleton;
  }
}

class SingletonLazy {

  private static SingletonLazy singleton = null;

  private SingletonLazy() {
  }

  public static SingletonLazy getInstance() {
    if (singleton == null) {
      synchronized (SingletonLazy.class) {
        if (singleton == null) {
          singleton = new SingletonLazy();
        }
      }
    }
    return singleton;
  }
}

class ExecuteSingleton implements Runnable {

  public void run() {
    for (int i = 0; i < 5; i++) {
      SingletonLazy s = SingletonLazy.getInstance();
      System.out.println(Thread.currentThread().getName() + "....." + s.hashCode());
    }

  }
}

public class SingletonMultipleThreadsDemo {

  public static void main(String[] args) {
    ExecuteSingleton e = new ExecuteSingleton();
    Thread t0 = new Thread(e);
    Thread t1 = new Thread(e);
    Thread t2 = new Thread(e);
    t0.start();
    t1.start();
    t2.start();
  }
}
