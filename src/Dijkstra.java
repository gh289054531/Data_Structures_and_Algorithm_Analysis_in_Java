/**
 * 在以下说明中，s为源，w[u,v]为点u和v之间的边的长度，结果保存在dist[]
 * 初始化：源的距离dist[s]设为0，其他的点距离设为无穷大，同时把所有的点的状态设为没有扩展过。 循环n-1次：
 * 在没有扩展过的点中取一距离最小的点u，并将其状态设为已扩展。
 * 对于每个与u相邻的点v，执行Relax(u,v)，也就是说，如果dist[u]+w[u,v]<
 * dist[v]，那么把dist[v]更新成更短的距离dist[u]+w[u,v]。此时到点v的最短路径上，前一个节点即为u。
 * 结束。此时对于任意的u，dist[u]就是s到u的距离。
 * 
 * 带权的、权值无负指的最短路径,采用邻接表表示，
 * 时间复杂度O(|E|+|V|^2)，如果边稠密那么还行，如果边稀疏就太慢了。
 * 这个不是最佳实现，最佳实现要用堆来实现。
 */

public class Dijkstra<T> {
	public void printPath(Vertex<T>[] graph, Vertex<T> dest) {
		if (dest.path != null) {
			printPath(graph, dest.path);
		}
		System.out.println(dest.value + " ");
	}

	public void dijkstra(Vertex<T>[] graph, Vertex<T> source) {
		source.dist = 0;
		while (true) {
			Vertex<T> cur = findMinInUnknown(graph);
			if (cur != null) {
				cur.known = true;
				for (int i = 0; i < cur.adjacent.size(); i++) {
					Vertex<T> adj = cur.adjacent.get(i);
					int weight = cur.weights.get(i);
					if (adj.dist > cur.dist + weight) {
						adj.dist = cur.dist + weight;
						adj.path = cur;
					}
				}
			} else {
				return;
			}
		}
	}

	//这里采用最原始落后的顺序查找，需要改进
	private Vertex<T> findMinInUnknown(Vertex<T>[] graph) {
		Vertex<T> minV = null;
		int mindist = Integer.MAX_VALUE;
		for (int i = 0; i < graph.length; i++) {
			if (graph[i].known == false) {
				if (graph[i].dist < mindist) {
					minV = graph[i];
					mindist = graph[i].dist;
				}
			}
		}
		return minV;
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
		graph[0].addAdjacent(graph[1], 2);
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
		Dijkstra<Integer> t = new Dijkstra<Integer>();
		t.dijkstra(graph, graph[0]);
		t.printPath(graph, graph[6]);
	}

}
