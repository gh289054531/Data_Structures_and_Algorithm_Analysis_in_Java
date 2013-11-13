import java.util.Random;

/**
 * 迷宫生成，利用了DisjointSets类。算法运行时间由find操作决定。时间复杂度O(N*logN)
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
	 * 假设有n个单元格，那么最终会拆掉n-1个墙；
	 * 每次选择1面墙（等于选2个格子）都带有2次find操作，还可能有1次union（忽略），所以主要由find操作决定时间复杂度，
	 * 而墙的总数肯定小于4n，所以最多有4n*2次find，find操作时间复杂度是logN，所以总时间复杂度是O(N*logN)
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
		System.out.println("以下格子之间的墙被打碎：");
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