package day13_multiplethreads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Resource {

  private String name;
  private int id;
  private boolean flag;
}

class Producer2 implements Runnable {

  private Resource resource;

  public Producer2(Resource resource) {
    this.resource = resource;
  }

  public void run() {
    try {
      createResource();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void createResource() throws InterruptedException {
    synchronized (resource) {
      while (!resource.isFlag()) { // 当flag为false时进行生产
        resource.setName("robot");
        resource.setId(resource.getId() + 1);
        System.out.println(
            Thread.currentThread().getName() + "... create ... " + resource.getName() + resource
                .getId());
        resource.setFlag(true); // 生产后更改flag为可消费
        resource.notifyAll();
        resource.wait();
      }

    }
  }
}

class Consumer2 implements Runnable {

  private Resource resource;

  public Consumer2(Resource resource) {
    this.resource = resource;
  }

  public void run() {
    try {
      getResource();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void getResource() throws InterruptedException {
    synchronized (resource) {
      while (resource.isFlag()) {// flag为true时可以消费
        System.out
            .println(
                Thread.currentThread().getName() + "... get ... " + resource.getName() + resource
                    .getId());
        resource.setFlag(false); // 消费后更改flag为可生产
        resource.notifyAll();
        resource.wait();
      }
    }
  }
}

public class MultipleThreadsCommunicateWaitNotify {

  public static void main(String[] args) {
    Resource resource = new Resource();
    Producer2 producer1 = new Producer2(resource);
    Producer2 producer2 = new Producer2(resource);
    Consumer2 consumer1 = new Consumer2(resource);
    Consumer2 consumer2 = new Consumer2(resource);
    Thread t0 = new Thread(producer1);
    Thread t1 = new Thread(producer2);
    Thread t2 = new Thread(consumer1);
    Thread t3 = new Thread(consumer2);
    t0.start();
    t1.start();
    t2.start();
    t3.start();
  }
}
