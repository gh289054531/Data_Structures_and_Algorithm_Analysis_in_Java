/**
 * —∞’“∏Óµ„ O(|E|+|V|)
 * 
 */
public class FindArticulationPoint<T> {
	public void findArt(Vertex<T> node, int counter, Vertex<T> root) {
		node.known = true;
		node.num = counter;
		node.low = node.num;
		for (Vertex<T> v : node.adjacent) {
			if (v.known == false) {// forward edge
				v.path = node;
				findArt(v, counter + 1, root);
				if (node != root && v.low >= node.num) {// root's judge rule is
														// different
					System.out.println(node.value);
				}
				node.low = node.low > v.low ? v.low : node.low;
			} else if (node.path != v) {// back edge
				node.low = node.low > v.num ? v.num : node.low;
			}
		}
		if (node == root) {// judge root
			int count = 0;
			for (Vertex<T> v : node.adjacent) {
				if (v.path == node) {
					count++;
				}
				if (count > 1) {
					System.out.println(node.value);
					return;
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertex<String>[] graph = new Vertex[7];
		graph[0] = new Vertex<String>("A");
		graph[1] = new Vertex<String>("B");
		graph[2] = new Vertex<String>("C");
		graph[3] = new Vertex<String>("D");
		graph[4] = new Vertex<String>("E");
		graph[5] = new Vertex<String>("F");
		graph[6] = new Vertex<String>("G");
		graph[0].addAdjacent(graph[1]);
		graph[0].addAdjacent(graph[3]);
		graph[1].addAdjacent(graph[0]);
		graph[1].addAdjacent(graph[2]);
		graph[2].addAdjacent(graph[1]);
		graph[2].addAdjacent(graph[3]);
		graph[2].addAdjacent(graph[6]);
		graph[3].addAdjacent(graph[0]);
		graph[3].addAdjacent(graph[2]);
		graph[3].addAdjacent(graph[4]);
		graph[3].addAdjacent(graph[5]);
		graph[4].addAdjacent(graph[3]);
		graph[4].addAdjacent(graph[5]);
		graph[5].addAdjacent(graph[3]);
		graph[5].addAdjacent(graph[4]);
		graph[6].addAdjacent(graph[2]);
		FindArticulationPoint<String> f = new FindArticulationPoint<String>();
		f.findArt(graph[0], 1, graph[0]);
		System.out.println();
	}

}
