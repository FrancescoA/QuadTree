package test;

import static org.junit.Assert.*;
import main.PointNode;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class NodeTest {

  @Test
  public void testCompareTo() {
    PointNode a = new PointNode(1,1);
    PointNode b = new PointNode(2,1);
    PointNode c = new PointNode(2,2);
    assertEquals(true,a.compareTo(b) < 0);
    assertEquals(false,a.compareTo(b) > 0);
    assertEquals(true, b.compareTo(c) < 0);
    assertEquals(Integer.MAX_VALUE,a.compareTo(null));
  }

  
  public static void main(String[] args) {
    System.out.println("Starting tests");
    Result result = JUnitCore.runClasses(NodeTest.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println("Tests ended");
  }
}
