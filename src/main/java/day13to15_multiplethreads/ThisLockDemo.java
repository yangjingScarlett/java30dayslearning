package day13to15_multiplethreads;

class TicketSeller implements Runnable {

  private int ticketNum = 10000;
  private final Object obj = new Object();
  private boolean flag = true;

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public void run() {
    if (flag) {
      while (true) {
        synchronized (this) {
          if (ticketNum > 0) {
            System.out.println(Thread.currentThread().getName() + "... 同步代码块..." + ticketNum--);
          }
        }
      }
    } else {
      while (true) {
        sale();
      }
    }

  }

  private synchronized void sale() {
    if (ticketNum > 0) {
      System.out.println(Thread.currentThread().getName() + "... 同步函数..." + ticketNum--);
    }
  }
}

public class ThisLockDemo {

  public static void main(String[] args) throws InterruptedException {
    TicketSeller seller = new TicketSeller();
    Thread t0 = new Thread(seller);
    Thread t1 = new Thread(seller);
    t0.start();
    Thread.sleep(10);
    seller.setFlag(false);
    t1.start();
  }
}
