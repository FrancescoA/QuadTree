package main;
import java.awt.Point;

/**
 * A QuadTree Node class. 
 * Each Node has 4 children (NE, SE, NW, SW), a point location, and a value. 
 * @author FrancescoA
 * @param <T>
 *
 */
public class PointNode<T> extends AbstractNode<T> {
  
  private T value;
  
  /**
   * A Node constructor.
   * @param location a Point representing the location of the node
   * @param value the value (any Object instance) of the node
   */
  public PointNode(Point location, T value) {
    super(location);
    this.value = value;
  } 
  
  /**
   * A Node constructor that creates a new Point. 
   * @param x the x-coordinate of the node location
   * @param y the y-coordinate of the node location
   * @param value the value of the node
   */
  public PointNode(int x, int y, T value) {
    this(new Point(x, y), value);
  }
  
  /**
   * A Node constructor that creates a new Point and gives the node a value of null. 
   * @param x the x-coordinate of the node location
   * @param y the y-coordinate of the node location
   */
  public PointNode(int x, int y) {
    this(x, y, null);
  }
  
  /**
   * A Node constructor that creates a node at the given location with a value of null. 
   * @param location
   */
  public PointNode(Point location){
    this(location, null);
  }
  
  
  /**
   * Get the nodes value. 
   * @return
   */
  public T getValue(){
    return value;
  }
  
  /**
   * Set the nodes value. 
   * @param val
   */
  public void setValue(T val) {
    value = val;
  }
  
  @Override
  public void clear() {
    if (northEast != null) {
      northEast.clear(); 
      northEast = null;
    }
    if (southEast != null) {
      southEast.clear();
      southEast = null;
    }
    if (northWest != null) {
      northWest.clear();
      northWest = null;
    }
    if (southWest != null) {
      southWest.clear();
      southWest = null;
    }
  }
  
  /**
   * Return the string representation of this node. 
   */
  @Override
  public String toString() {
    String representation = "("+location.x + ", " + location.y + ")";
    if (value != null) {
      return value + "= " + representation;
    }
    return representation; 
  }

  
  
}