package day11_exception;


import java.sql.SQLException;

class NoValueException extends Exception {

  public NoValueException() {

  }

  public NoValueException(String message) {
    super(message);
  }
}

class DBTool {

  public String getData(String sql) throws NoValueException {
    System.out.println("连接数据库...");
    try {
      System.out.println("开始拿数据...");
      if (sql == null || sql.isEmpty()) {
        throw new SQLException("发生异常, sql 语句是空的");
      } else {
        return "got data";
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      throw new NoValueException("没拿到数据， 原因： " + e.getMessage());
    } finally {
      System.out.println("关闭数据库连接。");
    }
  }

}

public class DBToolDemo {

  public static void main(String[] args) {
    DBTool dbTool = new DBTool();
    try {
      String data = dbTool.getData("");
    } catch (NoValueException e) {
      System.out.println(e.getMessage());
    }
  }

}
