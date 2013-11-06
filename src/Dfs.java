/**
 * O(|E|+|V|)
 */
public class Dfs<T> {
	public void dfs(Vertex<T> node) {
		node.known = true;
		for (Vertex<T> v : node.adjacent) {
			if (v.known == false) {
				v.path = node;
				dfs(v);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertex<String>[] graph = new Vertex[5];
		graph[0] = new Vertex<String>("A");
		graph[1] = new Vertex<String>("B");
		graph[2] = new Vertex<String>("C");
		graph[3] = new Vertex<String>("D");
		graph[4] = new Vertex<String>("E");
		graph[0].addAdjacent(graph[1]);
		graph[0].addAdjacent(graph[3]);
		graph[0].addAdjacent(graph[4]);
		graph[1].addAdjacent(graph[0]);
		graph[1].addAdjacent(graph[2]);
		graph[1].addAdjacent(graph[3]);
		graph[2].addAdjacent(graph[1]);
		graph[2].addAdjacent(graph[3]);
		graph[2].addAdjacent(graph[4]);
		graph[3].addAdjacent(graph[0]);
		graph[3].addAdjacent(graph[1]);
		graph[3].addAdjacent(graph[2]);
		graph[4].addAdjacent(graph[0]);
		graph[4].addAdjacent(graph[2]);
		Dfs d = new Dfs<String>();
		d.dfs(graph[0]);
		System.out.println();
	}

}
