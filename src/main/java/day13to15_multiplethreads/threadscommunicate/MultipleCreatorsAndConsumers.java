package day13to15_multiplethreads.threadscommunicate;

import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * CreatorAndConsumer4 works fine, it has 1 creator thread and 1 consumer thread. Now we want to
 * create multiple creator threads and multiple consumer threads. Simply creating multiple threads
 * to have a try
 * <p>
 * Problem: It will cause deadlock, because notify() only notify one single thread, for example,
 * creator creates one product then it calls notify, wants to notify consumer, but since creator has
 * multiple threads, it may notify another creator, in this case deadlock happens
 */
public class MultipleCreatorsAndConsumers {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Creator5 creator = new Creator5(products);
    Consumer5 consumer = new Consumer5(products);

    Thread t11 = new Thread(creator);
    Thread t12 = new Thread(creator);
    Thread t13 = new Thread(creator);
    Thread t14 = new Thread(creator);
    Thread t21 = new Thread(consumer);
    Thread t22 = new Thread(consumer);
    Thread t23 = new Thread(consumer);
    Thread t24 = new Thread(consumer);
    t11.start();
    t12.start();
    t13.start();
    t14.start();
    t21.start();
    t22.start();
    t23.start();
    t24.start();
  }

}

class Creator5 implements Runnable {

  private int num = 1;
  private final List<Product> products;

  public Creator5(List<Product> products) {
    this.products = products;
  }

  @Override
  public void run() {
    createProduct();
  }

  private void createProduct() {
    synchronized (products) {
      while (true) {
        try {
          if (products.size() < 1) {
            System.out.println(currentThread().getName() + " - Creates id: " + num);
            products.add(new Product(num++, "bread"));
          } else {
            products.notify();
            products.wait();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

@AllArgsConstructor
class Consumer5 implements Runnable {

  private final List<Product> products;

  @Override
  public void run() {
    consume();
  }

  private void consume() {
    synchronized (products) {
      while (true) {
        try {
          if (products.size() > 0) {
            System.out.println(
                currentThread().getName() + " - Consumes id: " + products.get(0).getId());
            System.out.println();
            products.remove(0);
          } else {
            products.notify();
            products.wait();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
