package day13to15_multiplethreads.threadscommunicate;

import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * This is for solving problem of CreatorAndConsumer3: deadlock. because t1 thread doesn't check
 * while condition so it cannot notify t2.
 * <p>
 * Solution: make the t1 while condition always true. then use if...else to do create or
 * notify...wait
 * <p>
 * Now, everything is right
 */
public class CreatorAndConsumer4 {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Creator4 creator = new Creator4(products);
    Consumer4 consumer = new Consumer4(products);

    Thread t1 = new Thread(creator);
    Thread t2 = new Thread(consumer);
    t1.start();
    t2.start();
  }

}

class Creator4 implements Runnable {

  private int num = 1;
  private final List<Product> products;

  public Creator4(List<Product> products) {
    this.products = products;
  }

  @Override
  public void run() {
    createProduct();
  }

  private void createProduct() {
    synchronized (products) {
      try {
        while (true) {
          if (products.size() < 10) {
            System.out.println(currentThread().getName() + " - Create id: " + num);
            products.add(new Product(num++, "bread"));
          } else {
            products.notify();
            products.wait();
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

@AllArgsConstructor
class Consumer4 implements Runnable {

  private final List<Product> products;

  @Override
  public void run() {
    consume();
  }

  private void consume() {
    synchronized (products) {
      try {
        while (true) {
          if (products.size() > 0) {
            System.out.println(
                currentThread().getName() + " - Consume id: " + products.get(0).getId());
            products.remove(0);
          } else {
            products.notify();
            products.wait();
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

