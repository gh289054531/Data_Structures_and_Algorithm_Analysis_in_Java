import java.util.Arrays;
import java.util.Comparator;

/**
 * 最近点问题, 分治, 时间复杂度O(N*logN)
 * 
 * 
 */
public class MinDistPointPair {

	private double calDist(Point A, Point B) {
		return Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}

	public Pair cal(Point[] points, int left, int right) {
		if (points == null || left >= right) {
			return null;
		}
		if (right - left == 1) {
			return new Pair(points[left], points[right]);
		}
		int middle = (right + left) / 2;
		Pair leftresult = cal(points, left, middle - 1);
		Pair rightresult = cal(points, middle, right);
		double min = 0.0;
		Pair result = null;
		if (leftresult == null) {
			min = rightresult.dist;
			result = rightresult;
		} else {
			if (leftresult.dist < rightresult.dist) {
				result = leftresult;
				min = leftresult.dist;
			} else {
				result = rightresult;
				min = rightresult.dist;
			}
		}
		double delt = min;
		for (int i = left; i <= right; i++) {
			if (points[i].x < (points[middle].x - delt) || points[i].x > (points[middle].x + delt)) {
				continue;
			}
			Point X = points[i];
			for (int j = left; j < right && j != i; j++) {
				Point Y = points[j];
				if (Math.abs(Y.y - X.y) > delt) {
					continue;
				}
				double temp = calDist(X, Y);
				if (temp < min) {
					min = temp;
					result = new Pair(X, Y);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(-5, 10);
		Point p2 = new Point(-1, 4);
		Point p3 = new Point(-1, 30);
		Point p4 = new Point(5, 23);
		Point p5 = new Point(-4, 32);
		Point p6 = new Point(3, 102);
		Point p7 = new Point(-4, 2);
		Point p8 = new Point(-51, 2);
		Point p9 = new Point(-51, 11);
		Point p10 = new Point(-54, 10);
		Point p11 = new Point(7, 11);
		Point[] points = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11 };
		Arrays.sort(points, new ComparatorX());
		MinDistPointPair m = new MinDistPointPair();
		Pair pair = m.cal(points, 0, 10);
		System.out.println(pair.toString());
	}

}

class Pair {
	Point A = null;
	Point B = null;
	double dist = 0.0;

	public Pair(Point A, Point B) {
		this.A = A;
		this.B = B;
		this.dist = Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}

	public String toString() {
		return "(" + A.x + "," + A.y + ")<->(" + B.x + "," + B.y + ")";
	}
}

class Point {
	double x;
	double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class ComparatorX implements Comparator<Point> {

	@Override
	public int compare(Point o1, Point o2) {
		if (o1.x > o2.x) {
			return 1;
		} else if (o1.x < o2.x) {
			return -1;
		} else {
			return 0;
		}
	}

}

class ComparatorY implements Comparator<Point> {

	@Override
	public int compare(Point o1, Point o2) {
		if (o1.y > o2.y) {
			return 1;
		} else if (o1.y < o2.y) {
			return -1;
		} else {
			return 0;
		}
	}

}