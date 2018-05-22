/******************************************************************************
 *
 *  A directed graph, implemented using a Map of sets.
 *  Self-loops allowed.  Based on the general Graph class provided by
 *  cs.princeton.edu under the GNU General Public License, version 3 (GPLv3)
 *  available at http://www.gnu.org/copyleft/gpl.html
 *
 *  The <tt>DiGraph</tt> class represents a directed graph of vertices,
 *  represented as String values.
 *  It supports the following operations:
 *  - add a vertex to the graph,
 *  - add an edge to the graph,
 *  - obtain a set of all of the vertices adjacent to a vertex. 
 *  It also provides methods for returning the number of vertices <em>V</em>,
 *  the number of edges <em>E</em>, and a String representation of the Graph.
 * 
 */
 
import java.util.*;
 
public class DiGraph {
   private static final String NEWLINE = System.getProperty("line.separator");
   private int V;
   private int E;
   private Map<String, Set<String>> adj;
   
   /**
    * Initializes an empty graph
    */
   public DiGraph() {
      this.V = 0;
      this.E = 0;
      adj =  new HashMap<>();
   }

    /**
    * Returns the number of vertices in this graph.
    * @return the number of vertices in this graph
    */
   public int vertices() {
      return V;
   }

   /**
    * Returns the number of edges in this graph.
    * @return the number of edges in this graph
    */
   public int edges() {
      return E;
   }

   /**
    * Determines if the argument is a valid vertex in the graph
	 * @param  v one vertex in the graph
    * @return true if the argument is a valid vertex, false otherwise
    */
   public boolean validVertex(String v) {
      return (adj.containsKey(v));
   }

  /**
       * Adds the vertex v to this graph
       * @param  v one vertex in the graph
       * @return true if v was added, false otherwise
   */
   public boolean addVertex(String v) {
      if (validVertex(v))
         return false;
      V++;
      Set<String> neighbors = new HashSet<>();
      adj.put(v, neighbors);
      return true;
   }

   /**
    * Adds the directed edge v-w to this graph.
    * The arguments must be valid vertices in the graph.
    * @param  v one vertex in the edge
    * @param  w the other vertex in the edge
    * @return true if edge was added, false otherwise
    */
   public boolean addEdge(String v, String w) {
      if (!validVertex(v) || !validVertex(w))
         return false;
      if (adj.get(v).contains(w))
         return false;
      E++;
      adj.get(v).add(w);
      return true;
   }

   /**
    * Returns the vertices adjacent to vertex <tt>v</tt>.
    *
    * @param  v the vertex
    * @return a set containing the vertices adjacent to vertex <tt>v</tt>
    * @throws IllegalArgumentException if v is not a valid vertex
    */
   public Set<String> getAdjacent(String v) {
      if (!validVertex(v))
         throw new IllegalArgumentException("Invalid Vertex: " + v);
      return new HashSet<String>(adj.get(v));
   }

   /**
    * Returns a string representation of this graph.
    *
    * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
    *         followed by the <em>V</em> adjacency lists
    */
   public String toString() {
      StringBuilder s = new StringBuilder();
      s.append(V + " vertices, " + E + " edges " + NEWLINE);
      for (String v: adj.keySet()) {
         s.append(v + ": ");
         for (String w : adj.get(v)) {
            s.append(w + " ");
         }
         s.append(NEWLINE);
      }
      return s.toString();
   }

/*
   Creates a sample graph with 3 vertices and 2 edges.
*/   
   public static void main(String[] args)
   {
      DiGraph g = new DiGraph();
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.addVertex("d");
      g.addEdge("a", "b");
      g.addEdge("a", "d");
      g.addEdge("a", "c");
      g.addEdge("d", "b");
      g.addEdge("d", "c");
      g.addEdge("d", "a");
      System.out.println(g); 
   }
}