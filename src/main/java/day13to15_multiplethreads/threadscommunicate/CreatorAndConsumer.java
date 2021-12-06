package day13to15_multiplethreads.threadscommunicate;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Requirement: creator to create products, consumer to consume the created products
 * <p>
 * Analyze: Product, Creator, Consumer. creator and consumer operates on same products, so they both
 * should have a list of products(property), and they can share the same list of products.
 * <p>
 * How to identify different products? product should have id
 */
public class CreatorAndConsumer {

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    Creator c1 = new Creator(products);
    c1.createProduct("bread");
    c1.createProduct("bread");

    Creator c2 = new Creator(products);
    c1.createProduct("bread");
    c1.createProduct("bread");

    Consumer consume = new Consumer(products);
    consume.consume();
    consume.consume();
    consume.consume();

    new Consumer(products).consume();
    new Consumer(products).consume();
    new Consumer(products).consume();
    new Consumer(products).consume();
    new Consumer(products).consume();
    new Consumer(products).consume();

  }

}


class Creator {

  private static int num = 1;
  private final List<Product> products;

  public Creator(List<Product> products) {
    this.products = products;
  }

  public void createProduct(String name) {
    System.out.println("Create new product. id: " + num + ", name: " + name);
    products.add(new Product(num++, name));
  }
}

@AllArgsConstructor
class Consumer {

  private List<Product> products;

  public void consume() {
    if (products != null && products.size() > 0) {
      System.out.println(
          "Consume a product, id: " + products.get(0).getId() + ", name: " + products.get(0)
              .getName());
      products.remove(0);
    } else {
      System.out.println("No product to consume");
    }
  }
}