package day13_multiplethreads;

class TicketSale extends Thread {

  private static int ticketNum = 100;

  // 卖票的代码需要被多个线程执行，所以要将这些代码定义在线程任务中，即run()方法中。
  public void run() {
    while (ticketNum > 0) {
      System.out.println(Thread.currentThread().getName() + "......" + ticketNum--);
    }
  }

}

public class ThreadTicketSampleDemo {

  public static void main(String[] args) {
    TicketSale t1 = new TicketSale();
    TicketSale t2 = new TicketSale();
    TicketSale t3 = new TicketSale();
    TicketSale t4 = new TicketSale();
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }

}
