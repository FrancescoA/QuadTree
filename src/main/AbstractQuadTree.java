package main;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractQuadTree<T> {
  
  protected AbstractNode<T> root;
  protected Rectangle bounds;
  protected List<AbstractNode<T>> queryResult;
  protected int size;
  
  /**
   * A QuadTree class constructor.
   * 
   * @param bounds the dimension and location of the QuadTree.
   */
  public AbstractQuadTree(Rectangle bounds) {
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
  public AbstractQuadTree(int x, int y, int width, int height) {
    this(new Rectangle(x, y, width, height));
  }

  /**
   * A QuadTree class constructor.
   * 
   * @param bounds the dimension and location of the QuadTree
   * @param root the first node of the QuadTree
   */
  public AbstractQuadTree(Rectangle bounds, AbstractNode<T> root) {
    bounds.grow(1,1); //rectangle.contains method is not inclusive. 
    this.bounds = bounds;
    this.root = root;
    this.queryResult = new ArrayList<AbstractNode<T>>();
    this.size = 0;
  }
  
  /**
   * Return a List<Node> of all the nodes in the tree.
   * @return all the nodes in the QuadTree instance. 
   */
  public List<AbstractNode<T>> getAllNodes() {
    return queryRange(bounds);
  }
  
  /**
   * Calls the insert method once for every Node element in the list
   * @param nodes an Iterable<Node> object. 
   */
  public void insertAll(Iterable<AbstractNode<T>> nodes) {
    for (AbstractNode<T> n: nodes) {
      insert(n);
    }
  }
  
  /**
   * Calls the insert method once for every Node element in the list (inserts all nodes)
   * @param nodes an array of Node objects
   */
  public void insertAll(AbstractNode<T>[] nodes) {
    for (AbstractNode<T> n: nodes) {
      insert(n);
    }
  }
  
  /**
   * 
   * @return root the root node of this quadtree
   */
  public AbstractNode<T> getRoot () {
    return root;
  }
  
  /**
   * Inserts a node into the QuadTree instance. 
   * Should not insert duplicates (determined based on location). 
   * @param node a Node object
   */
  public abstract void insert(AbstractNode<T> newNode);
  
  /**
   * Find all points in the QuadTree contained within a range. 
   * @param range the range (Shape) that encompasses the locations of desired nodes. 
   * @return
   */
  public abstract List<AbstractNode<T>> queryRange(Shape range);
  
  /**
   * Clear the QuadTree and any desired data that is contained by subclasses. 
   */
  public abstract void clear();
  

}
