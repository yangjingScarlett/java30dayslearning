package day13_multiplethreads.threadscommunicate;


import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Based on CreatorAndConsumer, we want to create a thread for creator and a thread for consumer.
 * creator creates 10 then consumers consumes 10.
 * <p>
 * Problems: creator creates 10, but main finishes before consuming
 */
public class CreatorAndConsumer2 {

  public static void main(String[] args) throws InterruptedException {
    List<Product> pros = new ArrayList<>();
    Creator2 creator = new Creator2(pros);
    Consumer2 consumer = new Consumer2(pros);

    Thread t1 = new Thread(creator);
    Thread t2 = new Thread(consumer);
    t1.start();
    t2.start();
  }

}


class Creator2 implements Runnable {

  private int num = 1;
  private final List<Product> products;

  public Creator2(List<Product> pros) {
    this.products = pros;
  }

  @Override
  public void run() {
    createProduct();
  }

  private void createProduct() {
    while (products.size() < 10) {
      System.out.println(currentThread().getName() + " - Create new product. id: " + num);
      products.add(new Product(num++, "bread"));
    }
  }


}

@AllArgsConstructor
class Consumer2 implements Runnable {

  private final List<Product> products;

  @Override
  public void run() {
    consume();
  }

  private void consume() {
    while (products.size() > 0) {
      System.out.println(
          "Consume a product, id: " + products.get(0).getId() + ", name: " + products.get(0)
              .getName());
      products.remove(0);
    }
  }

}
