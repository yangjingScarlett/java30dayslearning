package day06to10_javaclass;

public class Heritance3 {

  public static void main(String[] args) {
    Manager manager = new Manager("Machael", "1001", 45, 6000d, 1000d);
    Programmer programmer = new Programmer("Sany", "2001", 31, 3000d);
    manager.work();
    programmer.work();
  }

}

abstract class Employee {

  private String name;
  private String id;
  private int age;
  private double salary;

  public abstract void work();

  public Employee(String name, String id, int age, double salary) {
    this.name = name;
    this.id = id;
    this.age = age;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }
}

class Manager extends Employee {

  private double bonus;

  public Manager(String name, String id, int age, double salary, double bonus) {
    super(name, id, age, salary);
    this.bonus = bonus;
  }

  @Override
  public void work() {
    System.out.println("Manager: " + getName() + " is working...");
  }
}

class Programmer extends Employee {

  public Programmer(String name, String id, int age, double salary) {
    super(name, id, age, salary);
  }

  public void work() {
    System.out.println("Programmer: " + getName() + " is working...");
  }
}

