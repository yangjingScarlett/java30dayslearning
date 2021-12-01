package day13_multiplethreads.threadscommunicate;

import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * This if for solving problem of CreatorAndConsumer2: main thread finishes before consumer thread
 * executes.
 * <p>
 * Solution: let creator wait after creating, let consumer wait after consuming. but only wait will
 * cause deadlock, so must have notify. and notify must be called before wait, otherwise notify may
 * notify that thread just waited. if you use notify then you must have synchronized function or
 * synchronized code
 * <p>
 * Problems: deadlock. Why? t1 creates then wait, then t2 consumes then notify t1 then t2 wait. t1
 * is notified, but now t1's while checking has finished, so t1 next step is printing. then t1
 * process finish without notifying t2. so t2 always hang, and main thread cannot stop.
 */
public class CreatorAndConsumer3 {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Creator3 creator = new Creator3(products);
    Consumer3 consumer = new Consumer3(products);

    Thread t1 = new Thread(creator);
    Thread t2 = new Thread(consumer);
    t1.start();
    t2.start();
  }

}

class Creator3 implements Runnable {

  private int num = 1;
  private final List<Product> products;

  public Creator3(List<Product> products) {
    this.products = products;
  }

  @Override
  public void run() {
    createProduct();
  }

  private void createProduct() {
    synchronized (products) {
      try {
        while (products.size() < 10) {
          System.out.println(currentThread().getName() + " - Create id: " + num);
          products.add(new Product(num++, "bread"));
        }
        products.notify();
        products.wait();
        System.out.println("Creator wait..." + products.size());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

@AllArgsConstructor
class Consumer3 implements Runnable {

  private final List<Product> products;

  @Override
  public void run() {
    consume();
  }

  private void consume() {
    synchronized (products) {
      try {
        while (products.size() > 0) {
          System.out.println(
              currentThread().getName() + " - Consume id: " + products.get(0).getId());
          products.remove(0);
        }
        products.notify();
        products.wait();
        System.out.println("Consumer wait...");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
