package day13_multiplethreads.threadscommunicate.locks;

import static java.lang.Thread.currentThread;

import day13_multiplethreads.threadscommunicate.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;

/**
 * A great benefit of Condition objects is it is a middle layer of Lock and Monitor functions.
 * <p>
 * A lock can have several condition objects, we can use each to do specific monitor.
 */
public class MultipleCreatorsAndConsumersLock2 {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Lock lock = new ReentrantLock();
    Condition con1 = lock.newCondition();
    Condition con2 = lock.newCondition();
    Creator8 creator = new Creator8(products, lock, con1, con2);
    Consumer8 consumer = new Consumer8(products, lock, con1, con2);

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


class Creator8 implements Runnable {

  private int num = 1;
  private final List<Product> products;
  private final Lock lock;
  private final Condition creatorCondition;
  private final Condition consumerCondition;

  public Creator8(List<Product> products, Lock lock, Condition creatorCondition,
      Condition consumerCondition) {
    this.products = products;
    this.lock = lock;
    this.creatorCondition = creatorCondition;
    this.consumerCondition = consumerCondition;
  }

  @Override
  public void run() {
    createProduct();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void createProduct() {
    try {
      lock.lock();
      while (true) {
        if (products.size() < 1) {
          System.out.println(currentThread().getName() + " - Creates id: " + num);
          products.add(new Product(num++, "bread"));
          consumerCondition.signal();
        } else {
          creatorCondition.await();
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
class Consumer8 implements Runnable {

  private final List<Product> products;
  private Lock lock;
  private Condition creatorCondition;
  private Condition consumerCondition;

  @Override
  public void run() {
    consume();
  }

  private void consume() {
    try {
      lock.lock();
      while (true) {
        if (products.size() > 0) {
          System.out.println(
              currentThread().getName() + " - Consumes id: " + products.get(0).getId());
          System.out.println();
          products.remove(0);
          creatorCondition.signal();
        } else {
          consumerCondition.await();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

