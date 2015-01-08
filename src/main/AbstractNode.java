package main;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;



public abstract class AbstractNode<T> implements Comparable<AbstractNode<T>> {
  
  protected Point location;
  protected AbstractNode<T> northEast, southEast, southWest, northWest;
 
  /**
   * Basic constructor for a node with a location value. 
   * @param location
   */
  public AbstractNode(Point location) {
    this.location = location;
  }
  
  /**
   * Clear all data related to this node and children nodes. 
   */
  public abstract void clear();
  
  /**
   * Return the Point location instance variable. 
   * @return 
   */
  public Point getLocation() {
    return location;
  }
  
  /**
   * Determines if this node is NE of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isNorthEastOf(AbstractNode<T> other) {
    return this.location.x >= other.getLocation().x && this.location.y > other.getLocation().y;
  }
  
  /**
   * Determines if this node is NE of a query shape. 
   * If the node is within the query shape, it is considered NE of it. 
   * @param query the query shape
   * @return
   */
  public boolean isNorthEastOf(Shape query) {
    Rectangle bounds = query.getBounds();
    return this.location.x >= bounds.getMinX() && this.location.y > bounds.getMinY();
  }
  
  /**
   * Determines if this node is SE of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isSouthEastOf(AbstractNode<T> other) {
    return this.location.x > other.getLocation().x && this.location.y <= other.getLocation().y;
  }
  
  /**
   * Determines if this node is SE of a query shape. 
   * If the node is within the query shape, it is considered SE of it. 
   * @param query the query shape
   * @return
   */
  public boolean isSouthEastOf(Shape query) {
    Rectangle bounds = query.getBounds();
    return this.location.x > bounds.getMinX() && this.location.y <= bounds.getMaxY();
  }
  
  /**
   * Determines if this node is NW of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isNorthWestOf(AbstractNode<T> other) {
    return this.location.x < other.getLocation().x && this.location.y >= other.getLocation().y;
  }
  
  /**
   * Determines if this node is NW of a query shape. 
   * If the node is within the query shape, it is considered NW of it. 
   * @param query the query shape
   * @return
   */
  public boolean isNorthWestOf(Shape query) {
    Rectangle bounds = query.getBounds();
    return this.location.x < bounds.getMaxX() && this.location.y >= bounds.getMinY();
  }
  
  /**
   * Determines if this node is SW of other. 
   * @param other the other node instance
   * @return
   */
  public boolean isSouthWestOf(AbstractNode<T> other) {
    return this.location.x <= other.getLocation().x && this.location.y < other.getLocation().y;
  }
  
  /**
   * Determines if this node is SW of a query shape. 
   * If the node is within the query shape, it is considered SW of it. 
   * @param query the query shape
   * @return
   */
  public boolean isSouthWestOf(Shape query) {
    Rectangle bounds = query.getBounds();
    return this.location.x <= bounds.getMaxX() && this.location.y < bounds.getMaxY();
  }
  
  /**
   * Determine whether this node is equal to another node (based on location). 
   */
  @Override
  public boolean equals(Object other){
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }
    if (other instanceof AbstractNode<?>) {
      AbstractNode<?> other2 = (AbstractNode<?>) other;
      return location.equals(other2.getLocation());
    }
    return false;
  }
  
  /**
   * Return the string representation of this node. 
   */
  @Override
  public String toString(){
    String representation = "("+location.x + ", " + location.y + ")";
    return representation; 
  }

  @Override
  public int compareTo (AbstractNode<T> other) {
   if (other == null) {
     return Integer.MAX_VALUE;
   }
   int deltax = this.location.x - other.getLocation().x;
   if (deltax == 0) {
     return this.location.y - other.getLocation().y;
   } else {
     return deltax;
   }
  }

}
