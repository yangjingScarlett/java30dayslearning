package day13_multiplethreads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Product {

  private String name;
  private int id;
  private boolean flag;

}


class Producer implements Runnable {

  private final Product product;

  public Producer(Product product) {
    this.product = product;
  }

  public void run() {
    while (true) {
      createProduct();
    }
  }

  public void createProduct() {
    synchronized (product) {
      if (!product.isFlag()) { //flag为false时可生产
        product.setName("bread");
        product.setId(product.getId() + 1);
        System.out
            .println(Thread.currentThread().getName() + "... create ...bread" + product.getId());

        product.setFlag(true); // 生产完之后，更改flag
      }
    }
  }
}

class Consumer implements Runnable {

  private final Product product;

  public Consumer(Product product) {
    this.product = product;
  }

  public void run() {
    while (true) {
      getProduct();
    }
  }

  public void getProduct() {
    synchronized (product) {
      if (product.isFlag()) { // flag为true可消费
        System.out.println(
            Thread.currentThread().getName() + "... get ..." + product.getName() + product.getId());
        product.setFlag(false); //消费后更改标记
      }
    }
  }
}


public class MultipleThreadsCommunicate {

  public static void main(String[] args) {
    Product product = new Product();
    Producer producer = new Producer(product);
    Consumer consumer = new Consumer(product);
    Thread t0 = new Thread(producer);
    Thread t1 = new Thread(consumer);
    t0.start();
    t1.start();
  }
}
