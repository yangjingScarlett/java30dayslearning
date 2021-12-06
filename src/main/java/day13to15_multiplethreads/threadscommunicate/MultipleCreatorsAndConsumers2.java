package day13to15_multiplethreads.threadscommunicate;

import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * This class if for fixing the issue of MultipleCreatorsAndConsumers: deadlock
 * <p>
 * Solution: instead of notify(), using notifyAll()
 */
public class MultipleCreatorsAndConsumers2 {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Creator6 creator = new Creator6(products);
    Consumer6 consumer = new Consumer6(products);

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


class Creator6 implements Runnable {

  private int num = 1;
  private final List<Product> products;

  public Creator6(List<Product> products) {
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
            products.notifyAll();
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
class Consumer6 implements Runnable {

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
            products.notifyAll();
            products.wait();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

