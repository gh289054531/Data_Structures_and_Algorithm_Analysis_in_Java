import java.util.LinkedList;

/**
 * 带权的、带负值的最短路径 时间复杂度O(|E|*|V|) 如果遇到负值圈，算法不会结束
 */
public class WeightedNegativeShortestPath<T> {
	public void printPath(Vertex<T>[] graph, Vertex<T> dest) {
		if (dest.path != null) {
			printPath(graph, dest.path);
		}
		System.out.print(dest.value + " ");
	}

	public void weightedNegativeShortestPath(Vertex<T>[] graph, Vertex<T> source) {
		LinkedList<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		source.dist = 0;
		queue.add(source);
		while (queue.isEmpty() == false) {
			Vertex<T> cur = queue.poll();
			for (int i = 0; i < cur.adjacent.size(); i++) {
				Vertex<T> adj = cur.adjacent.get(i);
				int weight = cur.weights.get(i);
				if (adj.dist >= cur.dist + weight) {
					adj.dist = cur.dist + weight;
					adj.path = cur;
					if (queue.contains(adj) == false) {
						queue.add(adj);
					}
				}
			}
		}
	}

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
		graph[0].addAdjacent(graph[1], -8);
		graph[0].addAdjacent(graph[3], 1);
		graph[1].addAdjacent(graph[3], 3);
		graph[1].addAdjacent(graph[4], 10);
		graph[2].addAdjacent(graph[0], 4);
		graph[2].addAdjacent(graph[5], 5);
		graph[3].addAdjacent(graph[2], 2);
		graph[3].addAdjacent(graph[4], 2);
		graph[3].addAdjacent(graph[5], 8);
		graph[3].addAdjacent(graph[6], 4);
		graph[4].addAdjacent(graph[6], 6);
		graph[6].addAdjacent(graph[5], 1);
		WeightedNegativeShortestPath w = new WeightedNegativeShortestPath();
		w.weightedNegativeShortestPath(graph, graph[0]);
		for (int i = 0; i < graph.length; i++) {
			w.printPath(graph, graph[i]);
			System.out.println();
		}
	}
}
