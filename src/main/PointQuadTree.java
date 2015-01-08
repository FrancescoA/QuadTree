package main;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple point QuadTree implementation.
 * @see <a href="http://en.wikipedia.org/wiki/Quadtree">QuadTree</a>
 * @author FrancescoA
 *
 */
public class PointQuadTree<T> extends AbstractQuadTree<T> {
  
  private int visited = 0;
  
  public PointQuadTree (Rectangle bounds) {
    super(bounds);
  }
  
  public PointQuadTree(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  public PointQuadTree(Rectangle bounds, AbstractNode<T> root) {
    super(bounds, root);
  }

  /**
   * Constructs a new Node with the given parameters and inserts it into the QuadTree instance. 
   * @param location the location of the Node
   * @param value the value of the Node
   */
  public void insert(Point location, T value) {
    insert(new PointNode<T>(location, value));
  }

  @Override
  public void insert(AbstractNode<T> node) {
    if (!bounds.contains(node.getLocation())) {
      throw new QuadTreeException("Cannot insert node " + node + " because it is outside QuadTree bounds.");
    }
    size++;
    root = insert(root, node);
  }
  
  /**
   * Recursive helper function to insert a Node into the QuadTree. 
   * @param root the node in this recursive call (initially root)
   * @param newNode the node to be inserted
   * @return
   */
  private AbstractNode<T> insert(AbstractNode<T> root, AbstractNode<T> newNode) {
    if (root == null) {
      return newNode;
    } else {
      if (newNode.equals(root)){
        size--;
        System.out.println("Attempted to insert a duplicate point ("+ newNode +"): was not inserted");
      } else if (newNode.isNorthEastOf(root)) {
        root.northEast = insert(root.northEast, newNode);
      } else if (newNode.isSouthEastOf(root)) {
        root.southEast = insert(root.southEast, newNode);
      } else if (newNode.isNorthWestOf(root)) {
        root.northWest = insert(root.northWest, newNode);
      } else if (newNode.isSouthWestOf(root)) {
        root.southWest = insert(root.southWest, newNode);
      }
    }
    return root;
  }
  

  @Override
  public List<AbstractNode<T>> queryRange(Shape range) {
    queryResult.clear();
    visited = 0;
    queryRange(root, range);
    return queryResult;
  }
  
  /**
   * Recursive helper function to find all points contained within a range. 
   * @param root the node in the current recursive call (initially root).
   * @param range the range (Shape) that encompasses the locations of desired nodes. 
   */
  private void queryRange(AbstractNode<T> root, Shape range) {
    if (root == null) {
      return;
    }
    visited++;
    if (range.contains(root.getLocation())) {
      queryResult.add(root);
    } 
    if (root.isNorthEastOf(range)) {
      queryRange(root.southWest, range);
    }
    if (root.isNorthWestOf(range)) {
      queryRange(root.southEast, range);
    } 
    if (root.isSouthEastOf(range)) {
      queryRange(root.northWest, range);
    }
    if (root.isSouthWestOf(range)) {
      queryRange(root.northEast, range);
    }
      
  }
  
  public int getVisitedOnLastSearch() {
    return visited;
  }
  
  @Override
  public void clear () {
    root.clear();
  }
  
  /**
   * Like the queryRange method but visits every single node and does not take advantage of QuadTree structure. 
   * @param range
   * @return
   */
  public List<AbstractNode<T>> innefficientQueryRange(Shape range) {
    queryResult.clear();
    visited = 0;
    innefficientQueryRange(root, range);
    return queryResult;
  }
  
  private void innefficientQueryRange(AbstractNode<T> root, Shape range) {
    if (root == null) {
      return;
    }
    visited++;
    if (range.contains(root.getLocation())) {
      queryResult.add(root);
    }
    innefficientQueryRange(root.northEast, range);
    innefficientQueryRange(root.northWest, range);
    innefficientQueryRange(root.southEast, range);
    innefficientQueryRange(root.southWest, range);
  }
  
  /**
   * Get the total amount of nodes in the QuadTree. 
   * @return the number of nodes in the QuadTree. 
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Print out all the nodes in the QuadTree.
   */
  public void print() {
    print(root);
  }
  
  /**
   * Recursive helper function to print out all nodes in the QuadTree. 
   * @param root
   */
  private void print(AbstractNode<T> root){
    if (root != null){
      System.out.println(root);
      print(root.northEast);
      print(root.northWest);
      print(root.southEast);
      print(root.southWest);
    }
  }
 
  public static void main (String[] args) {
    //Demonstration
    PointQuadTree st = new PointQuadTree(new Rectangle(0,0,100,100));
    int N = 10000;
    Rectangle queryRect = new Rectangle(50,50,50,50);
    for (int i = 0; i < N; i++) {
        Integer x = (int) (100 * Math.random());
        Integer y = (int) (100 * Math.random());
        Point loc = new Point(x,y);
        PointNode n = new PointNode(loc, "P"+i);
        System.out.println("Inserting node: "+n);
        st.insert(n);
    }
    System.out.println("Done preprocessing " + N + " points");
    List<PointNode> results = st.queryRange(queryRect);
    System.out.println("QT has " + st.size + " unique nodes");
    System.out.println("Queried a range: "+ queryRect);
    System.out.println("And found: "+ results.size() + " results:");
    System.out.println(results);
    Set<PointNode> set1 = new HashSet<PointNode>();
    set1.addAll(results);
    results = st.innefficientQueryRange(queryRect);
    System.out.println(results);
    Set<PointNode> set2 = new HashSet<PointNode>();
    set2.addAll(results);
    System.out.println(set2.removeAll(set1));
    System.out.println(set2);
  }



}