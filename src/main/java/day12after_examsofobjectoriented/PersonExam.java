package day12after_examsofobjectoriented;

class Person {

  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void introduce() {
    System.out.println("我是" + name + "， 我的年龄是" + age);
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("传入的对象为空");
    }
    if (this == obj) {
      return true;
    }
    if (obj instanceof Person) {
      Person p = (Person) obj;
      return name.equals(p.getName()) && age == p.getAge();
    }
    return false;
  }

  public boolean isSamePerson(Person p){
    return p!= null && name.equals(p.getName()) && age == p.getAge();
  }

}

public class PersonExam {

  public static void main(String[] args) {
    Person p1 = new Person("张三", 21);
    p1.introduce();
    Person p2 = new Person("张三", 21);
    System.out.println(p1.equals(p2));
    boolean ifSamePerson = p1.isSamePerson(p2);
    System.out.println(ifSamePerson);
  }
}
