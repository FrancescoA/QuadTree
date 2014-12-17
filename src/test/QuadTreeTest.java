package test;

import static org.junit.Assert.*;
import java.awt.Rectangle;
import java.util.List;
import main.Node;
import main.QuadTree;
import main.QuadTreeException;
import org.junit.Test;

/**
 * Some QuadTree test cases. 
 * @author FrancescoA
 *
 */
public class QuadTreeTest {

  private QuadTree getQuadTree() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node[] nodes = {
                    new Node(1,2),
                    new Node(0,0),
                    new Node(100,100),
                    new Node(17,8),
                    new Node(21,55),
                    new Node(9,35),
                    new Node(39,54),
                    new Node(86,70),
                    new Node(48,47),
                    new Node(12,3),
                    new Node(96,5),
                    new Node(53,35),
                    new Node(9,32),
                    new Node(39,30),
                    new Node(85,0),
                    new Node(70,83),
                    new Node(57,44),
                    new Node(7,32),
                    new Node(23,89),
                    new Node(53,81),
                    };
    qt.insertAll(nodes);
    return qt;
  }
  
  
  @Test(expected = QuadTreeException.class)
  public void testOutOfBoundEntry() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node n = new Node(101,101);
    qt.insert(n);
  }
  
  @Test
  public void shouldNotInsertDuplicates() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node n1 = new Node(1,1);
    Node n2 = new Node(1,1);
    qt.insert(n1);
    qt.insert(n2);
    assertEquals(1,qt.getSize());
  }
  
  @Test
  public void shouldNotGiveOutOfBoundException() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node n = new Node(0,0);
    qt.insert(n);
  }
  
  @Test
  public void shouldNotGiveOutOfBoundException2() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node n = new Node(100,100);
    qt.insert(n);
  }
  
  @Test 
  public void shouldNotGiveOutOfBoundException3() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node n = new Node(0,100);
    qt.insert(n);
  }
  
  @Test 
  public void shouldNotGiveOutOfBoundException4() {
    QuadTree qt = new QuadTree(0,0,100,100);
    Node n = new Node(100,0);
    qt.insert(n);
  }
  
  @Test
  public void testQuery() {
    QuadTree qt = getQuadTree();
    Rectangle query = new Rectangle(10,10);
    List<Node> results = qt.queryRange(query);
    assertEquals("[(1, 2), (0, 0)]",results.toString());
  }
  
  @Test
  public void testQuery2() {
    QuadTree qt = getQuadTree();
    Rectangle query = new Rectangle(50,70);
    List<Node> results = qt.queryRange(query);
    assertEquals("[(1, 2), (17, 8), (21, 55), (39, 54), (48, 47), (39, 30), (9, 35), (9, 32), (7, 32), (12, 3), (0, 0)]",results.toString());
  }
  
  @Test
  public void testQueryAll() {
    QuadTree qt = getQuadTree();
    List<Node> allNodes = qt.getAllNodes();
    assertEquals(qt.getSize(), allNodes.size());
  }
  

}
