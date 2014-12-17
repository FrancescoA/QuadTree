package main;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple point QuadTree implementation.
 * @see <a href="http://en.wikipedia.org/wiki/Quadtree">QuadTree</a>
 * @author FrancescoA
 *
 */
public class QuadTree {
  
  private Node root;
  private Rectangle bounds;
  private List<Node> queryResult;
  private int size;
  
  /**
   * A QuadTree class constructor.
   * 
   * @param bounds the dimension and location of the QuadTree.
   */
  public QuadTree(Rectangle bounds) {
    this(bounds, null);
  }
  
  /**
   * A QuadTree class constructor. 
   * 
   * @param x the left-most x coordinate of the QuadTree bounds 
   * @param y the upper-most y coordinate of the QuadTree bounds
   * @param width the width of the QuadTree bounds
   * @param height the height of the QuadTree bounds. 
   */
  public QuadTree(int x, int y, int width, int height) {
    this(new Rectangle(x, y, width, height));
  }

  /**
   * A QuadTree class constructor.
   * 
   * @param bounds the dimension and location of the QuadTree
   * @param root the first node of the QuadTree
   */
  public QuadTree(Rectangle bounds, Node root) {
    bounds.grow(1,1); //rectangle.contains method is not inclusive. 
    this.bounds = bounds;
    this.root = root;
    queryResult = new ArrayList<Node>();
    size = 0;
  }
  
  /**
   * Return a List<Node> of all the nodes in the tree.
   * @return all the nodes in the QuadTree instance. 
   */
  public List<Node> getAllNodes() {
    return queryRange(bounds);
  }
  
  /**
   * Calls the insert method once for every Node element in the list
   * @param nodes an Iterable<Node> object. 
   */
  public void insertAll(Iterable<Node> nodes) {
    for (Node n: nodes) {
      insert(n);
    }
  }
  
  /**
   * Calls the insert method once for every Node element in the list (inserts all nodes)
   * @param nodes an array of Node objects
   */
  public void insertAll(Node[] nodes) {
    for (Node n: nodes) {
      insert(n);
    }
  }
  
  /**
   * Constructs a new Node with the given parameters and inserts it into the QuadTree instance. 
   * @param location the location of the Node
   * @param value the value of the Node
   */
  public void insert(Point location, Object value) {
    insert(new Node(location, value));
  }

  /**
   * Inserts a node into the QuadTree instance. 
   * Does not insert duplicates (determined based on location). 
   * @param node a Node object
   */
  public void insert(Node node) {
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
  private Node insert(Node root, Node newNode) {
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
  
  /**
   * Find all points in the QuadTree contained within a range. 
   * @param range the range (Shape) that encompasses the locations of desired nodes. 
   * @return
   */
  public List<Node> queryRange(Shape range) {
    queryResult.clear();
    queryRange(root, range);
    return queryResult;
  }
  
  /**
   * Recursive helper function to find all points contained within a range. 
   * @param root the node in the current recursive call (initially root).
   * @param range the range (Shape) that encompasses the locations of desired nodes. 
   */
  private void queryRange(Node root, Shape range) {
    if (root == null) {
      return;
    }
    if (range.contains(root.getLocation())) {
      queryResult.add(root);
    }
    if (root.northEast != null) {
      queryRange(root.northEast, range);
    }
    if (root.northWest != null) {
      queryRange(root.northWest, range);
    }
    if (root.southEast != null) {
      queryRange(root.southEast, range);
    }
    if (root.southWest != null) {
      queryRange(root.southWest, range);
    } 
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
  private void print(Node root){
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
    QuadTree st = new QuadTree(new Rectangle(0,0,100,100));
    int N = 200;
    Rectangle queryRect = new Rectangle(50,50,100,100);
    for (int i = 0; i < N; i++) {
        Integer x = (int) (100 * Math.random());
        Integer y = (int) (100 * Math.random());
        Point loc = new Point(x,y);
        Node n = new Node(loc, "P"+i);
        System.out.println("Inserting node: "+n);
        st.insert(n);
    }
    System.out.println("Done preprocessing " + N + " points");
    List<Node> results = st.queryRange(queryRect);
    System.out.println("QT has " + st.size + " unique nodes");
    System.out.println("Queried a range: "+ queryRect);
    System.out.println("And found: "+ results.size() + " results:");
    System.out.println(results);
  }

}