import java.util.LinkedList;

/**
 * 使用邻接图表示，拓补排序实现
 * 算法会使邻接图的入度指与实际不符，因为中间过程修改了它，如果没有环那么最终入度都是0
 * 时间复杂度O(|E|+|V|)
 */
public class TopSort<T> {

	public void TopSort(Vertex<T>[] graph) throws Exception {
		LinkedList<Vertex<T>> queue = new LinkedList<Vertex<T>>();

		for (int i = 0; i < graph.length; i++) {
			if (graph[i].indegree == 0) {
				queue.add(graph[i]);
			}
		}

		int counter = 0;
		while (queue.isEmpty() == false) {
			Vertex<T> cur = queue.poll();
			cur.topNum = counter++;
			for (int i = 0; i < cur.adjacent.size(); i++) {
				Vertex curAdjacent = cur.adjacent.get(i);
				if (--curAdjacent.indegree == 0) {
					queue.add(curAdjacent);
				}
			}
		}
		if (counter != graph.length) {
			throw new Exception("graph has circle");
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
		TopSort<Integer> t=new TopSort<Integer>();
		try {
			t.TopSort(graph);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<graph.length;i++){
			System.out.println(graph[i].topNum);
		}
	}

}


