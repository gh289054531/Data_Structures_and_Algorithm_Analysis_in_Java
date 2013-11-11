/**
 * �շѹ�·�ؽ�����
 * 
 * @author root
 * 
 */
public class TurnpikeReconstruction {

	/**
	 * ʱ�临�Ӷ�O(N*N*LogN)
	 * 
	 * @param x
	 *            ��������С��������
	 * @param d
	 *            AVL����ʾ
	 * @return
	 */
	public boolean turnpike(int[] x, AVLTree<Integer> d) {
		if (x == null || (x.length * (x.length - 1) / 2 != d.size())) {
			return false;
		}
		if (x.length < 3) {
			return true;
		}
		int n = x.length - 1;
		x[0] = 0;
		x[n] = d.findMax().theElement;
		d.remove(x[n]);
		x[n - 1] = d.findMax().theElement;
		d.remove(x[n - 1]);
		if (d.contains(x[n] - x[n - 1])) {
			d.remove(x[n] - x[n - 1]);
			return place(x, d, 1, n - 2);
		} else {
			return false;
		}
	}

	private boolean place(int[] x, AVLTree<Integer> d, int left, int right) {
		if (left > right && d.size() == 0) {
			return true;
		}
		if ((left > right && d.size() != 0) || (left <= right && d.size() == 0)) {
			return false;
		}
		int cur = d.findMax().theElement;
		boolean flag = true;
		int i = 0;
		for (i = 0; i < left; i++) {// �ٶ�curλ��right,�����в���
			if (d.contains(cur - x[i]) == false) {
				flag = false;
				break;
			} else {
				d.remove(cur - x[i]);
			}
		}
		if (flag == false) {
			for (int j = 0; j < i; j++) {
				d.insert(cur - x[j]);
			}
		} else {
			for (i = right + 1; i < x.length; i++) {
				if (d.contains(x[i] - cur) == false) {
					flag = false;
					break;
				} else {
					d.remove(x[i] - cur);
				}
			}
			if (flag == false) {
				for (int j = 0; j < left; j++) {
					d.insert(cur - x[j]);
				}
				for (int j = right + 1; j < i; j++) {
					d.insert(x[j] - cur);
				}
			}
		}
		if (flag == true) {//���flag==true˵��curλ��right���У���ô�ݹ�����С�ķ�Χleft��right-1
			x[right] = cur;
			flag = place(x, d, left, right - 1);
			if (flag == false) {//����˵��С��Χ���Խ��Ϊfalse����ô���ǵð�����ɾ���ľ��벹��ȥ
				for (int j = 0; j < left; j++) {
					d.insert(cur - x[j]);
				}
				for (int j = right + 1; j < x.length; j++) {
					d.insert(x[j] - cur);
				}
			}
		}
		//----------------------------------�ָ���------------------------------------------
		if (flag == false) {// �ٶ�curλ��left�������в��У��߼�ͬ����
			flag = true;
			for (i = 0; i < left; i++) {
				if (d.contains(x[x.length - 1] - cur - x[i]) == false) {
					flag = false;
					break;
				} else {
					d.remove(x[x.length - 1] - cur - x[i]);
				}
			}
			if (flag == false) {
				for (int j = 0; j < i; j++) {
					d.insert(x[x.length - 1] - cur - x[j]);
				}
			} else {
				for (i = right + 1; i < x.length; i++) {
					if (d.contains(x[i] - (x[x.length - 1] - cur)) == false) {
						flag = false;
						break;
					} else {
						d.remove(x[i] - (x[x.length - 1] - cur));
					}
				}
				if (flag == false) {
					for (int j = 0; j < left; j++) {
						d.insert(x[x.length - 1] - cur - x[j]);
					}
					for (int j = right + 1; j < i; j++) {
						d.insert(x[j] - (x[x.length - 1] - cur));
					}
				}
			}		
			if (flag == true) {
				x[left] = x[x.length - 1] - cur;
				flag = place(x, d, left + 1, right);
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		int[] d = new int[] { 1, 2, 2, 2, 3, 3, 3, 4, 5, 5, 5, 6, 7, 8, 10 };
		int[] x = new int[6];
		AVLTree<Integer> dtree = new AVLTree<Integer>();
		for (int i = 0; i < d.length; i++) {
			dtree.insert(d[i]);
		}
		TurnpikeReconstruction t = new TurnpikeReconstruction();
		System.out.println(t.turnpike(x, dtree));
	}
}
