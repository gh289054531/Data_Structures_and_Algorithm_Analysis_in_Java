import java.util.Random;

/**
 * 迷宫生成，利用了DisjointSets类。算法运行时间由find操作决定。时间复杂度O(N*logN)
 * 
 */
public class GenerateMaze {

	public boolean[][] maze = null;// 迷宫单元格
	private Wall[] walls = null;// 所有的墙
	private int wallNum = 0;// 墙的总数
	private DisjointSets sets = null;// 不相交集类，记录单元格之间的连通性

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
	 * 假设有n个单元格，那么最终会拆掉n-1个墙（原因：如果两个单元格之间没联通就把墙拆了，如果联通了就继续寻找下一面墙）；
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
				// 如果两个单元格之间没联通就把墙拆了，如果联通了就继续寻找下一面墙，因此一共只拆单元格数目-1的墙就可以保证全部联通
				if (root1 == root2) {
					continue;
				} else {
					result.hasWall = false;// 把墙拆掉
					sets.union(root1, root2);
					if (check() == true) {
						return;
					}
				}
			}
		}
	}

	/**
	 * 检查是否所有的单元都联通了，判断条件根中记录的树大小==单元总数。因为不相交集类树的深度不超过logN，所以最差时间复杂度O(logN)
	 */
	private boolean check() {
		if (Math.abs(sets.find(this.sets.s[0])) == this.sets.s.length) {
			return true;
		}
		return false;
	}

	/**
	 * 打印结果
	 */
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

/**
 * 墙类，墙的表示方法是：夹在单元格cell1和cell2之间，因此用cell1和cell2可以唯一标识
 */
class Wall {
	int cell1;
	int cell2;
	boolean hasWall;// 墙是否被拆

	public Wall(int cell1, int cell2, boolean hasWall) {
		this.cell1 = cell1;
		this.cell2 = cell2;
		this.hasWall = hasWall;
	}

	public String toString() {
		return cell1 + " " + cell2 + " " + hasWall;
	}
}