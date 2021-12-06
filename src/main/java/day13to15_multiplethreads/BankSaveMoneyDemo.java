package day13to15_multiplethreads;

class BankAccount {

  private int amount;

  public synchronized void add(int num) {
    amount += num;
    System.out.println(amount);
  }
}

class Customer implements Runnable {

  private BankAccount bankAccount = new BankAccount();

  public void run() {
    for (int i = 0; i < 3; i++) {
      bankAccount.add(100);
    }
  }
}

public class BankSaveMoneyDemo {

  public static void main(String[] args) {
    Customer customer1 = new Customer();
    Customer customer2 = new Customer();
    Thread t1 = new Thread(customer1);
    Thread t2 = new Thread(customer2);
    t1.start();
    t2.start();
  }
}
