import java.util.LinkedList;
/**
 * 无权的最短路径。基于广度优先搜索BFS,时间复杂度O(|V|+|E|)
 *
 */
public class NoWeightShortestPath<T> {

	public void noWeightShortestPath(Vertex<T>[] graph, Vertex<T> source) {
		for (int i = 0; i < graph.length; i++) {
			graph[i].dist = Integer.MAX_VALUE;
		}
		LinkedList<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		source.dist = 0;
		queue.add(source);
		while (queue.isEmpty() == false) {
			Vertex<T> cur = queue.poll();
			for (Vertex<T> adj : cur.adjacent) {
				if (adj.dist == Integer.MAX_VALUE) {
					adj.dist = cur.dist + 1;
					adj.path = cur;
					queue.add(adj);
				}
			}
		}
	}

	public static void main(String[] args) {
		Vertex<Integer>[] graph = new Vertex[7];
		graph[0] = new Vertex<Integer>(1);
		graph[1] = new Vertex<Integer>(2);
		graph[2] = new Vertex<Integer>(3);
		graph[3] = new Vertex<Integer>(4);
		graph[4] = new Vertex<Integer>(5);
		graph[5] = new Vertex<Integer>(6);
		graph[6] = new Vertex<Integer>(7);
		graph[0].addAdjacent(graph[1]);
		graph[0].addAdjacent(graph[2]);
		graph[0].addAdjacent(graph[3]);
		graph[1].addAdjacent(graph[3]);
		graph[1].addAdjacent(graph[4]);
		graph[2].addAdjacent(graph[5]);
		graph[3].addAdjacent(graph[2]);
		graph[3].addAdjacent(graph[5]);
		graph[3].addAdjacent(graph[6]);
		graph[4].addAdjacent(graph[3]);
		graph[4].addAdjacent(graph[6]);
		graph[6].addAdjacent(graph[5]);
		NoWeightShortestPath<Integer> t = new NoWeightShortestPath<Integer>();
		t.noWeightShortestPath(graph, graph[0]);
		for (int i = 0; i < graph.length; i++) {
			System.out.println(graph[i].dist);
		}
	}

}
