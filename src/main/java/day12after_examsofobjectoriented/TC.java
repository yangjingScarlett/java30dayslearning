package day12after_examsofobjectoriented;

class Circle {

  private double radius;

  public Circle(double r) {
    radius = r;
  }

  public static double compare(Circle[] cir) {
    double maxRadius = cir[0].radius;
    for (int i = 1; i < cir.length; i++) {
      if (cir[i].radius > maxRadius) {
        maxRadius = cir[i].radius;
      }
    }
    return maxRadius;
  }
}

public class TC {

  public static void main(String[] args) {
    Circle[] cir = new Circle[3];
    cir[0] = new Circle(1.0);
    cir[1] = new Circle(2.0);
    cir[2] = new Circle(4.0);
    System.out.println("最大的半径值是： " + Circle.compare(cir));
  }
}
