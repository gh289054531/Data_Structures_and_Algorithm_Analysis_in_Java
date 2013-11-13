import java.util.Random;

/**
 * �Թ����ɣ�������DisjointSets�ࡣ�㷨����ʱ����find����������ʱ�临�Ӷ�O(N*logN)
 * 
 * @author root
 * 
 */
public class GenerateMaze {

	public boolean[][] maze = null;
	private Wall[] walls = null;
	private int wallNum = 0;
	private DisjointSets sets = null;

	public GenerateMaze(int row, int col) {
		try {
			this.sets = new DisjointSets(row * col);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.wallNum = (row - 1) * col + (col - 1) * row;
		maze = new boolean[row][col];
		walls = new Wall[this.wallNum];
		int index = 0;
		for (int i = 0; i < row * col - 1; i++) {
			if ((i + 1) % col != 0) {
				walls[index++] = new Wall(i, i + 1, true);
			}
			if (i < (row - 1) * col) {
				walls[index++] = new Wall(i, i + col, true);
			}
		}
	}

	/**
	 * ������n����Ԫ����ô���ջ���n-1��ǽ��
	 * ÿ��ѡ��1��ǽ������ѡ2�����ӣ�������2��find��������������1��union�����ԣ���������Ҫ��find��������ʱ�临�Ӷȣ�
	 * ��ǽ�������϶�С��4n�����������4n*2��find��find����ʱ�临�Ӷ���logN��������ʱ�临�Ӷ���O(N*logN)
	 */
	private void randomPickWall() {
		Random r = new Random();
		while (true) {
			int i = r.nextInt(wallNum);
			Wall result = walls[i];
			if (result.hasWall == false) {
				continue;
			} else {
				int root1 = sets.find(result.cell1);
				int root2 = sets.find(result.cell2);
				if (root1 == root2) {
					continue;
				} else {
					result.hasWall = false;
					// System.out.println(result.cell1 + " " + result.cell2);
					sets.union(root1, root2);
					/*
					 * for (int i1 = 0; i1 < sets.s.length; i1++) {
					 * System.out.print(sets.s[i1] + " "); }
					 * System.out.println();
					 */
					if (check() == true) {
						return;
					}
				}
			}
		}
	}

	private boolean check() {
		for (int i = 0; i < this.sets.s.length; i++) {
			if (Math.abs(this.sets.s[i]) == this.sets.s.length) {
				return true;
			}
		}
		return false;
	}

	public void print() {
		System.out.println("���¸���֮���ǽ�����飺");
		for (int i = 0; i < walls.length; i++) {
			if (walls[i].hasWall == false) {
				System.out.println(walls[i].cell1 + " " + walls[i].cell2);
			}
		}
	}

	public static void main(String[] args) {
		GenerateMaze maze = new GenerateMaze(5, 5);
		maze.randomPickWall();
		maze.print();
	}
}

class Wall {
	int cell1;
	int cell2;
	boolean hasWall;

	public Wall(int cell1, int cell2, boolean hasWall) {
		this.cell1 = cell1;
		this.cell2 = cell2;
		this.hasWall = hasWall;
	}

	public String toString() {
		return cell1 + " " + cell2 + " " + hasWall;
	}
}