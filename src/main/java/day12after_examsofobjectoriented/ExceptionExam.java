package day12after_examsofobjectoriented;

interface Graph {

  double calculateAcreage();
}

class Rectangle implements Graph {

  private double length;
  private double width;

  public Rectangle(double length, double width) {
    if (length <= 0) {
      throw new IllegalArgumentException("这个数值是非法的： " + length);
    }
    if (width <= 0) {
      throw new IllegalArgumentException("这个数值是非法的： " + width);
    }
    this.length = length;
    this.width = width;
  }

  public double calculateAcreage() {
    return length * width;
  }
}

class Cycle implements Graph {

  private double radius;

  public Cycle(double radius) {
    if (radius <= 0) {
      throw new IllegalArgumentException("这个数值是非法的： " + radius);
    }
    this.radius = radius;
  }

  public double calculateAcreage() {
    return 3.14 * radius * radius;
  }
}

public class ExceptionExam {

  public static void main(String[] args) {
    Graph graph1 = new Rectangle(-3, 4);
    Graph graph2 = new Cycle(1);
    System.out.println(graph1.calculateAcreage());
    System.out.println(graph2.calculateAcreage());
  }

}
