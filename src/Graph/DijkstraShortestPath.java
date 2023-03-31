/*
* https://en.wikipedia.org/wiki/Dijkstra's_algorithm
*
The following pseudocode algorithm uses a Priority Queue. The dist is an array that contains the current distances from the source to other vertices, i.e. dist[u] is the current distance from the source to the vertex u. The prev array contains pointers to previous-hop nodes on the shortest path from source to the given vertex.

1  function Dijkstra(Graph, source):
2      dist[source] ← 0                           // Initialization
3
4      create vertex priority queue Q
5
6      for each vertex v in Graph:           
7          if v ≠ source
8              dist[v] ← INFINITY                 // Unknown distance from source to v
9              prev[v] ← UNDEFINED                // Predecessor of v
10
11         Q.add_with_priority(v, dist[v])
12
13
14     while Q is not empty:                      // The main loop
15         u ← Q.extract_min()                    // Remove and return best vertex
16         for each neighbor v of u:              // only v that are still in Q
17             alt ← dist[u] + length(u, v) 
18             if alt < dist[v]
19                 dist[v] ← alt
20                 prev[v] ← u
21                 Q.decrease_priority(v, alt)
22
23     return dist, prev
*/
import java.util.*;

public class DijkstraShortestPath {
  public class Vertex {
    public String id;
    public List<Edge> neighbors;
  }
  
  public class Edge {
    //public Vertex from;
    public Vertex to;
    public float weight;
  }
  
  public class Graph {
    public Set<Vertex> vertices;
  }
  
  // For each vertex, keep its so far the minimum distance to the source vertex
  static Map<Vertex, Float> dist = new HashMap<>(); 

  public class VertexComparator implements Comparator<Vertex> {
    public int compare(Vertex v1, Vertex v2) {
      float d1 = (dist.containsKey(v1))? dist.get(v1) : Float.MAX_VALUE;
      float d2 = (dist.containsKey(v2))? dist.get(v2) : Float.MAX_VALUE;
      return (d1 < d2)? -1 : (d1>d2) ? 1 : 0; // compare distance to the source vertex
    }
  };

  public List<Vertex> solve(Graph g, Vertex source, Vertex target) {
    
    dist.put(source, 0f);
    
    // for each vertex, keep its previous vertex on a path
    Map<Vertex, Vertex> prev = new HashMap<>(); 
    
    PriorityQueue<Vertex> queue = new PriorityQueue<>(new VertexComparator());
    
    for(Vertex v : g.vertices) {
      if (v != source) {
        dist.put(v, Float.MAX_VALUE);
        
        queue.offer(v); // add the v to the queue (use dist as priority)
      }
    }
    
    while(!queue.isEmpty()) {
      Vertex u = queue.poll(); // remove min from the queue
      if (u.neighbors != null) {
        for(Edge e : u.neighbors) {
          Vertex v = e.to;
          // distance from source to v = 
          // (distance from source to u) + (distance from u to v)
          float alt = dist.get(u) + e.weight;
          if (alt < dist.get(v)) {
            dist.put(v, alt);
            prev.put(v, u);
            
            // to decrease the priority of v
            // by adding or updating v in the queue
            if (queue.contains(v)) {
              queue.remove(v);
            }
            queue.offer(v); // add v to end of the queue
          }
        }
      }
    } // end-while-loop
    
    /* construct the shortest path from dist and prev
    1  S ← empty sequence
    2  u ← target
    3  if prev[u] is defined or u = source:          // Do something only if the vertex is reachable
    4      while u is defined:                       // Construct the shortest path with a stack S
    5          insert u at the beginning of S        // Push the vertex onto the stack
    6          u ← prev[u]                           // Traverse from target to source
    */
    
    List<Vertex> path = new ArrayList<>();
    if (target != source && prev.containsKey(target)) { // found a path from soure to target
      Vertex u = target;
      while(u != null) {
        path.add(u);
        u = prev.get(u);
      }
    }
    return path;
  }
}
