package day13_multiplethreads;

class TicketSale2 implements Runnable {

  private int ticketNum = 100;
  Object obj = new Object();

  public void run() {
    synchronized (obj) {
      while (ticketNum > 0) {
        try {
          Thread.sleep(10);
        } catch (InterruptedException err) {
        }

        System.out.println(Thread.currentThread().getName() + "......" + ticketNum--);
      }
    }
  }
}

public class RunnableTicketSampleDemo {

  public static void main(String[] args) {
    TicketSale2 t = new TicketSale2();
    Thread t1 = new Thread(t);
    Thread t2 = new Thread(t);
    Thread t3 = new Thread(t);
    Thread t4 = new Thread(t);
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }
}
