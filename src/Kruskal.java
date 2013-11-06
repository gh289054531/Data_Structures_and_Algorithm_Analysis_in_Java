import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *  ±º‰∏¥‘”∂»O(|E|*log|E|)
 * 
 * @author root
 * 
 */
public class Kruskal {

	public ArrayList<Edge<Integer>> kruskal(PriorityQueue<Edge<Integer>> pq, int vertexNum) {
		HashMap<Vertex<Integer>, HashSet<Vertex<Integer>>> known = new HashMap<Vertex<Integer>, HashSet<Vertex<Integer>>>();
		ArrayList<Edge<Integer>> selectEdge = new ArrayList<Edge<Integer>>();
		while(selectEdge.size()!=vertexNum-1) {
			Edge<Integer> e = pq.remove();
			if (e.makeCycle(known) == false) {
				selectEdge.add(e);
			}
		}
		return selectEdge;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertex<Integer>[] graph = new Vertex[7];
		graph[0] = new Vertex<Integer>(1);
		graph[1] = new Vertex<Integer>(2);
		graph[2] = new Vertex<Integer>(3);
		graph[3] = new Vertex<Integer>(4);
		graph[4] = new Vertex<Integer>(5);
		graph[5] = new Vertex<Integer>(6);
		graph[6] = new Vertex<Integer>(7);
		PriorityQueue<Edge<Integer>> pq = new PriorityQueue<Edge<Integer>>();
		pq.add(new Edge<Integer>(graph[0], graph[1], 2));
		pq.add(new Edge<Integer>(graph[0], graph[2], 4));
		pq.add(new Edge<Integer>(graph[0], graph[3], 1));
		pq.add(new Edge<Integer>(graph[1], graph[3], 3));
		pq.add(new Edge<Integer>(graph[1], graph[4], 10));
		pq.add(new Edge<Integer>(graph[2], graph[3], 2));
		pq.add(new Edge<Integer>(graph[3], graph[4], 7));
		pq.add(new Edge<Integer>(graph[2], graph[5], 5));
		pq.add(new Edge<Integer>(graph[3], graph[5], 8));
		pq.add(new Edge<Integer>(graph[3], graph[6], 4));
		pq.add(new Edge<Integer>(graph[4], graph[6], 6));
		pq.add(new Edge<Integer>(graph[5], graph[6], 1));
		Kruskal k = new Kruskal();
		ArrayList<Edge<Integer>> result = k.kruskal(pq, 7);
		for (Edge<Integer> e : result) {
			System.out.println(e.toString());
		}
	}
}

class Edge<T> implements Comparable<Edge<T>> {
	Vertex<T> v1 = null;
	Vertex<T> v2 = null;
	int weight = Integer.MAX_VALUE;

	public Edge(Vertex<T> v1, Vertex<T> v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	public boolean makeCycle(HashMap<Vertex<T>, HashSet<Vertex<T>>> knownVertexs) {
		if (knownVertexs.containsKey(v1) == false) {
			HashSet<Vertex<T>> v1Set = new HashSet<Vertex<T>>();
			v1Set.add(v1);
			knownVertexs.put(v1, v1Set);
		}
		if (knownVertexs.containsKey(v2) == false) {
			HashSet<Vertex<T>> v2Set = new HashSet<Vertex<T>>();
			v2Set.add(v2);
			knownVertexs.put(v2, v2Set);
		}
		if (knownVertexs.get(v1) == knownVertexs.get(v2)) {
			return true;
		}
		HashSet<Vertex<T>> mergeSet = knownVertexs.get(v1);
		mergeSet.addAll(knownVertexs.get(v2));
		for (Vertex<T> v : mergeSet) {
			knownVertexs.put(v, mergeSet);
		}
		return false;
	}

	public int compareTo(Edge<T> o) {
		// TODO Auto-generated method stub
		if (this.weight > o.weight) {
			return 1;
		} else if (this.weight == o.weight) {
			return 0;
		} else {
			return -1;
		}
	}

	public String toString() {
		return v1 + " " + v2 + " " + weight;
	}
}