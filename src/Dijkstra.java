/**
 * ������˵���У�sΪԴ��w[u,v]Ϊ��u��v֮��ıߵĳ��ȣ����������dist[]
 * ��ʼ����Դ�ľ���dist[s]��Ϊ0�������ĵ������Ϊ�����ͬʱ�����еĵ��״̬��Ϊû����չ���� ѭ��n-1�Σ�
 * ��û����չ���ĵ���ȡһ������С�ĵ�u��������״̬��Ϊ����չ��
 * ����ÿ����u���ڵĵ�v��ִ��Relax(u,v)��Ҳ����˵�����dist[u]+w[u,v]<
 * dist[v]����ô��dist[v]���³ɸ��̵ľ���dist[u]+w[u,v]����ʱ����v�����·���ϣ�ǰһ���ڵ㼴Ϊu��
 * ��������ʱ���������u��dist[u]����s��u�ľ��롣
 * 
 * ��Ȩ�ġ�Ȩֵ�޸�ָ�����·��,�����ڽӱ��ʾ��
 * ʱ�临�Ӷ�O(|E|+|V|^2)������߳�����ô���У������ϡ���̫���ˡ�
 * ����������ʵ�֣����ʵ��Ҫ�ö���ʵ�֡�
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

	//���������ԭʼ����˳����ң���Ҫ�Ľ�
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
