import java.util.ArrayList;

public class Prim {

	public ArrayList<Vertex<Integer>> prim(Vertex<Integer>[] graph) {
		ArrayList<Vertex<Integer>> choosed = new ArrayList<Vertex<Integer>>();
		choosed.add(graph[0]);
		graph[0].known = true;
		for (int iter = 0; iter < graph.length - 1; iter++) {
			Vertex<Integer> curNode = choosed.get(choosed.size() - 1);
			for (int i = 0; i < graph.length; i++) {
				if (graph[i].known == false) {
					int index = curNode.adjacent.indexOf(graph[i]);
					if (index >= 0) {
						if (graph[i].primDist > curNode.weights.get(index)) {
							graph[i].primDist = curNode.weights.get(index);
							graph[i].path = curNode;
						}
					}
				}
			}
			int minDisBetweenChoosedAndUnchoosed = Integer.MAX_VALUE;
			Vertex<Integer> minVertex = null;
			for (int i = 0; i < graph.length; i++) {
				if (graph[i].known == false) {
					if (graph[i].primDist < minDisBetweenChoosedAndUnchoosed) {
						minDisBetweenChoosedAndUnchoosed = graph[i].primDist;
						minVertex = graph[i];
					}
				}
			}
			choosed.add(minVertex);
			minVertex.known = true;
		}
		return choosed;
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
		graph[0].addAdjacent(graph[1], 2);
		graph[0].addAdjacent(graph[2], 4);
		graph[0].addAdjacent(graph[3], 1);
		graph[1].addAdjacent(graph[0], 2);
		graph[1].addAdjacent(graph[3], 3);
		graph[1].addAdjacent(graph[4], 10);
		graph[2].addAdjacent(graph[0], 4);
		graph[2].addAdjacent(graph[3], 2);
		graph[2].addAdjacent(graph[5], 5);
		graph[3].addAdjacent(graph[0], 1);
		graph[3].addAdjacent(graph[1], 3);
		graph[3].addAdjacent(graph[2], 2);
		graph[3].addAdjacent(graph[4], 7);
		graph[3].addAdjacent(graph[5], 8);
		graph[3].addAdjacent(graph[6], 4);
		graph[4].addAdjacent(graph[1], 10);
		graph[4].addAdjacent(graph[3], 7);
		graph[4].addAdjacent(graph[6], 6);
		graph[5].addAdjacent(graph[2], 5);
		graph[5].addAdjacent(graph[3], 8);
		graph[5].addAdjacent(graph[6], 1);
		graph[6].addAdjacent(graph[3], 4);
		graph[6].addAdjacent(graph[4], 6);
		graph[6].addAdjacent(graph[5], 1);
		Prim p = new Prim();
		ArrayList<Vertex<Integer>> result = p.prim(graph);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).value + " " + result.get(i).path);
		}
	}
}