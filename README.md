QuadTree
========

A QuadTree Implementation written in Java.

Usage
-----
This implementation of a QuadTree allows you to define a QuadTree object that covers a certain 2D space. 
You can then insert QuadTree Nodes, each having a location and an (optional) value. 

The insert method will insert these nodes according to the QuadTree convention. The first insertion becomes the root, and any insertion that comes after is recursively added to the tree based on whether the node is NE, NW, SE, or SW of the other nodes. 

The queryRange method will return a list of all nodes that are within a given 2D Range, defined by a Rectangle object. 

There are other useful methods as well and they are all documented in the JavaDocs. The implementation files are found in the main/ directory while the test cases are found in the test/ directory. 

Build
-----
Although the QuadTree is mostly intended to be used in other Java applications as a data structure, both the main QuadTree.java and the QuadTreeJavaTest.java files have demonstrations (the QuadTree.java demonstration code is below). 

The application was developed in Eclipse but can be run from the command line as well (You will need the Java SDK and JUnit installed). 

```
$ git clone https://github.com/FrancescoA/QuadTree.git
$ cd QuadTree
$ javac -d bin -classpath .: src/*/*.java
```
From here you can run the QuadTree class file and see the output. 
```
$ cd bin
$ java main.QuadTree

```
Or you can also see the QuadTreeTest output results (there should not be any failures). 

```
$ java test.QuadTree
```


Example
-------

```java
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

```
