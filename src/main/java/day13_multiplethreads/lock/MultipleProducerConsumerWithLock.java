package day13_multiplethreads.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Resource {

  private String name;
  private int id;
  private boolean flag;
}

class Producer implements Runnable {

  private Resource resource;
  private Lock lock;
  private Condition condition;

  public Producer(Resource resource, Lock lock, Condition condition) {
    this.resource = resource;
    this.lock = lock;
    this.condition = condition;
  }

  public void run() {
    createResource();
  }

  public void createResource() {
    try {
      lock.lock();
      while (!resource.isFlag()) {
        resource.setName("toy");
        resource.setId(resource.getId() + 1);
        resource.setFlag(true);
        System.out
            .println(Thread.currentThread().getName() + "... create toy ..." + resource.getId());
        condition.signal();
        condition.await();
      }
    } catch (InterruptedException e) {
      System.out.println("create error occurs.");
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

class Consumer implements Runnable {

  private Resource resource;
  private Lock lock;
  private Condition condition;

  public Consumer(Resource resource, Lock lock, Condition condition) {
    this.resource = resource;
    this.lock = lock;
    this.condition = condition;
  }

  public void run() {
    getResource();
  }

  private void getResource() {
    try {
      lock.lock();
      while (resource.isFlag()) {
        System.out.println(Thread.currentThread().getName() + "... get toy ..." + resource.getId());
        resource.setFlag(false);
        condition.signal();
        condition.await();
      }
    } catch (InterruptedException e) {
      System.out.println("get error occurs");
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

public class MultipleProducerConsumerWithLock {

  public static void main(String[] args) {
    Resource resource = new Resource();
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Producer p1 = new Producer(resource, lock, condition);
    Producer p2 = new Producer(resource, lock, condition);
    Consumer c1 = new Consumer(resource, lock, condition);
    Consumer c2 = new Consumer(resource, lock, condition);
    Thread t0 = new Thread(p1);
    Thread t1 = new Thread(p2);
    Thread t2 = new Thread(c1);
    Thread t3 = new Thread(c2);
    t0.start();
    t1.start();
    t2.start();
    t3.start();
  }
}
