package day13_multiplethreads.threadscommunicate.locks;

import static java.lang.Thread.currentThread;

import day13_multiplethreads.threadscommunicate.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;

public class MultipleCreatorsAndConsumers3 {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Lock lock = new ReentrantLock();
    Creator_6 creator = new Creator_6(products, lock);
    Consumer_6 consumer = new Consumer_6(products, lock);

    Thread t11 = new Thread(creator);
    Thread t12 = new Thread(creator);
    Thread t21 = new Thread(consumer);
    Thread t22 = new Thread(consumer);
    t11.start();
    t12.start();
    t21.start();
    t22.start();
  }
}


class Creator_6 implements Runnable {

  private int num = 1;
  private final List<Product> products;
  private final Lock lock;

  public Creator_6(List<Product> products, Lock lock) {
    this.products = products;
    this.lock = lock;
  }

  @Override
  public void run() {
    createProduct();
  }

  private void createProduct() {
    lock.lock();
    try {
      while (true) {
        if (products.size() < 1) {
          System.out.println(currentThread().getName() + " - Creates id: " + num);
          products.add(new Product(num++, "bread"));
        } else {
          products.notifyAll();
          products.wait();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

@AllArgsConstructor
class Consumer_6 implements Runnable {

  private final List<Product> products;
  private final Lock lock;

  @Override
  public void run() {
    consume();
  }

  private void consume() {
    lock.lock();
    try {
      while (true) {
        if (products.size() > 0) {
          System.out.println(
              currentThread().getName() + " - Consumes id: " + products.get(0).getId());
          System.out.println();
          products.remove(0);
        } else {
          products.notifyAll();
          products.wait();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

  }
}