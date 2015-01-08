package test;

import static org.junit.Assert.*;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;
import main.PointNode;
import main.PointQuadTree;
import main.QuadTreeException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Some QuadTree test cases. 
 * @author FrancescoA
 *
 */
public class QuadTreeTest {

  private PointQuadTree getQuadTree() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode[] nodes = {
                    new PointNode(1,2),
                    new PointNode(0,0),
                    new PointNode(100,100),
                    new PointNode(17,8),
                    new PointNode(21,55),
                    new PointNode(9,35),
                    new PointNode(39,54),
                    new PointNode(86,70),
                    new PointNode(48,47),
                    new PointNode(12,3),
                    new PointNode(96,5),
                    new PointNode(53,35),
                    new PointNode(9,32),
                    new PointNode(39,30),
                    new PointNode(85,0),
                    new PointNode(70,83),
                    new PointNode(57,44),
                    new PointNode(7,32),
                    new PointNode(23,89),
                    new PointNode(53,81),
                    };
    qt.insertAll(nodes);
    return qt;
  }
  
  
  @Test(expected = QuadTreeException.class)
  public void testOutOfBoundEntry() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode n = new PointNode(101,101);
    qt.insert(n);
  }
  
  @Test
  public void shouldNotInsertDuplicates() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode n1 = new PointNode(1,1);
    PointNode n2 = new PointNode(1,1);
    qt.insert(n1);
    qt.insert(n2);
    assertEquals(1,qt.getSize());
  }
  
  @Test
  public void shouldNotGiveOutOfBoundException() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode n = new PointNode(0,0);
    qt.insert(n);
  }
  
  @Test
  public void shouldNotGiveOutOfBoundException2() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode n = new PointNode(100,100);
    qt.insert(n);
  }
  
  @Test 
  public void shouldNotGiveOutOfBoundException3() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode n = new PointNode(0,100);
    qt.insert(n);
  }
  
  @Test 
  public void shouldNotGiveOutOfBoundException4() {
    PointQuadTree qt = new PointQuadTree(0,0,100,100);
    PointNode n = new PointNode(100,0);
    qt.insert(n);
  }
  
  @Test
  public void testQuery() {
    PointQuadTree qt = getQuadTree();
    Rectangle query = new Rectangle(10,10);
    List<PointNode> results = qt.queryRange(query);
    assertEquals("[(1, 2), (0, 0)]",results.toString());
  }
  
  @Test
  public void testQuery2() {
    PointQuadTree qt = getQuadTree();
    Rectangle query = new Rectangle(50,70);
    List<PointNode> results = qt.queryRange(query);
    assertEquals("[(1, 2), (0, 0), (17, 8), (12, 3), (9, 35), (9, 32), (7, 32), (21, 55), (39, 54), (39, 30), (48, 47)]",results.toString());
  }
  
  @Test 
  public void comapareQueries() {
    PointQuadTree qt = getQuadTree();
    Rectangle query = new Rectangle(50,70);
    List<PointNode> results = qt.queryRange(query);
    List<PointNode> results2 = qt.innefficientQueryRange(query);
    Collections.sort(results);
    Collections.sort(results2);
    assertEquals(results.toString(), results2.toString());
  }
  
  @Test
  public void testEfficiency() {
    PointQuadTree qt = getQuadTree();
    Rectangle query = new Rectangle(30,30);
    qt.queryRange(query);
    int queryOneVisits = qt.getVisitedOnLastSearch();
    qt.innefficientQueryRange(query);
    int queryTwoVisits = qt.getVisitedOnLastSearch();
    assertEquals(true, queryOneVisits < queryTwoVisits);
  }
  
  @Test
  public void testQueryAll() {
    PointQuadTree qt = getQuadTree();
    List<PointNode> allNodes = qt.getAllNodes();
    assertEquals(qt.getSize(), allNodes.size());
  }
  
  
  public static void main(String[] args) {
    System.out.println("Starting tests");
    Result result = JUnitCore.runClasses(QuadTreeTest.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println("Tests ended");
  }
  

}
