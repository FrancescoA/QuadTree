package main;
import java.awt.Point;

/**
 * A QuadTree Node class. 
 * Each Node has 4 children (NE, SE, NW, SW), a point location, and a value. 
 * @author FrancescoA
 *
 */
public class Node {
  
  private Point location;
  private Object value;
    
  public Node northEast, southEast, southWest, northWest;
  
  /**
   * A Node constructor.
   * @param location a Point representing the location of the node
   * @param value the value (any Object instance) of the node
   */
  public Node(Point location, Object value) {
    this.location = location;
    this.value = value;
  } 
  
  /**
   * A Node constructor that creates a new Point. 
   * @param x the x-coordinate of the node location
   * @param y the y-coordinate of the node location
   * @param value the value of the node
   */
  public Node(int x, int y, Object value) {
    this(new Point(x, y), value);
  }
  
  /**
   * A Node constructor that creates a new Point and gives the node a value of null. 
   * @param x the x-coordinate of the node location
   * @param y the y-coordinate of the node location
   */
  public Node(int x, int y) {
    this(x, y, null);
  }
  
  /**
   * A Node constructor that creates a node at the given location with a value of null. 
   * @param location
   */
  public Node(Point location){
    this(location, null);
  }
  /**
   * Return the Point location instance variable. 
   * @return 
   */
  public Point getLocation() {
    return location;
  }

  /**
   * Get the nodes value. 
   * @return
   */
  public Object getValue(){
    return value;
  }
  
  /**
   * Set the nodes value. 
   * @param val
   */
  public void setValue(Object val) {
    value = val;
  }
  
  /**
   * Determines if this node is NE of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isNorthEastOf(Node other) {
    return this.location.x >= other.getLocation().x && this.location.y > other.getLocation().y;
  }
  
  /**
   * Determines if this node is SE of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isSouthEastOf(Node other) {
    return this.location.x > other.getLocation().x && this.location.y <= other.getLocation().y;
  }
  
  /**
   * Determines if this node is NW of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isNorthWestOf(Node other) {
    return this.location.x < other.getLocation().x && this.location.y >= other.getLocation().y;
  }
  
  /**
   * Determines if this node is SW of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isSouthWestOf(Node other) {
    return this.location.x <= other.getLocation().x && this.location.y < other.getLocation().y;
  }

  /**
   * Determine whether this node is equal to another node (based on location). 
   */
  @Override
  public boolean equals(Object other){
    if (other == null) {
      return false;
    }
    if (other instanceof Node) {
      Node o = (Node) other;
      return location.equals(o.getLocation());
    }
    return false;
  }
  
  /**
   * Return the string representation of this node. 
   */
  @Override
  public String toString(){
    String representation = "("+location.x + ", " + location.y + ")";
    if (value != null) {
      return value + "= " + representation;
    }
    return representation; 
  }
  
  
}