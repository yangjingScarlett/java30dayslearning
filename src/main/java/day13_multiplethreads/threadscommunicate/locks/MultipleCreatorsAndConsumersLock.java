package day13_multiplethreads.threadscommunicate.locks;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import day13_multiplethreads.threadscommunicate.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;


/**
 * java1.4: concurrent related: synchronized, wait...notify
 * <p>
 * java 1.5: concurrent related: Lock, Condition(await...signal)
 * <p>
 * Lock can totally replace synchronized, but need to pay attention to the condition. This class can
 * work successfully, but still has efficiency problem. signalAll() will signal all threads, but we
 * only need to signal one opposite thread
 */
public class MultipleCreatorsAndConsumersLock {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Creator7 creator = new Creator7(products, lock, condition);
    Consumer7 consumer = new Consumer7(products, lock, condition);

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


class Creator7 implements Runnable {

  private int num = 1;
  private final List<Product> products;
  private final Lock lock;
  private final Condition condition;

  public Creator7(List<Product> products, Lock lock, Condition condition) {
    this.products = products;
    this.lock = lock;
    this.condition = condition;
  }

  @Override
  public void run() {
    try {
      sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    createProduct();
  }

  private void createProduct() {
    try {
      lock.lock();
      while (true) {
        if (products.size() < 1) { // assume the list can have 100 breads most
          System.out.println(currentThread().getName() + " - Creates id: " + num);
          products.add(new Product(num++, "bread"));
          condition.signalAll(); // after creating then signal
        } else {
          condition.await();
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
class Consumer7 implements Runnable {

  private final List<Product> products;
  private Lock lock;
  private Condition condition;

  @Override
  public void run() {
    try {
      sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
          condition.signalAll();
        } else {
          condition.await();
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
