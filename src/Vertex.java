import java.util.ArrayList;

class Vertex<T> {
	int indegree = 0;
	int outdegree = 0;
	int topNum = 0;
	T value = null;
	int dist = Integer.MAX_VALUE;
	boolean known = false;
	Vertex<T> path = null;
	ArrayList<Vertex<T>> adjacent = new ArrayList<Vertex<T>>();
	ArrayList<Integer> weights = new ArrayList<Integer>();
	int primDist = Integer.MAX_VALUE;
	int low = Integer.MAX_VALUE;
	int num = Integer.MAX_VALUE;

	public Vertex(T value) {
		this.value = value;
	}

	public void addAdjacent(Vertex<T> v) {
		if (v == null) {
			return;
		}
		if (adjacent.contains(v)) {
			return;
		}
		adjacent.add(v);
		this.outdegree++;
		v.indegree++;
	}

	public void addAdjacent(Vertex<T> v, int weight) {
		if (v == null) {
			return;
		}
		if (adjacent.contains(v)) {
			return;
		}
		adjacent.add(v);
		weights.add(weight);
		this.outdegree++;
		v.indegree++;
	}

	public String toString() {
		return String.valueOf(value);
	}

}