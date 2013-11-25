/**
 * ��������������Ѿ�����O(N)��ƽ���������O(N*logN)���ȶ�
 * ��Ҫ����ռ�O(N)
 * �鲢�����ǳ��������㷨����Ҫ�Ƚϴ������ٵģ�����˼��
 * ��JAVA�У����ͽ��бȽϲ����������󣬶�����Ԫ���ƶ��ܿ죨��Ϊ�ƶ��������ã����ʺϹ鲢����
 * ��˷��͵�Collections.sort()������һ�ֹ鲢����ĸĽ��汾
 * ���ǻ���Ԫ�ص�����Arrays.sort()�����ǸĽ��Ŀ�������
 */
public class MergeSort {
	public static <T extends Comparable<? super T>> void Merge(T[] input, T[] temp, int left, int middle, int right) {
		int tempPos = left;
		int i = left, j = middle + 1;
		for (; i <= middle && j <= right;) {
			if (input[i].compareTo(input[j]) <= 0) {
				temp[tempPos++] = input[i++];
			}
			if (input[i].compareTo(input[j]) > 0) {
				temp[tempPos++] = input[j++];
			}
		}
		while (i <= middle) {
			temp[tempPos++] = input[i++];
		}
		while (j <= right) {
			temp[tempPos++] = input[j++];
		}
		System.arraycopy(temp, left, input, left, right - left + 1);
	}

	public static <T extends Comparable<? super T>> void MergeSort(T[] input, T[] temp, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			MergeSort(input, temp, left, middle);
			MergeSort(input, temp, middle + 1, right);
			Merge(input, temp, left, middle, right);
		}
	}

	public static <T extends Comparable<? super T>> void MergeSort(T[] input) {
		if (input == null) {
			return;
		}
		T[] temp = (T[]) new Comparable[input.length];
		MergeSort(input, temp, 0, input.length - 1);
	}


	public static void main(String[] args) {
		Integer[] test1 = new Integer[] { 213, 123, 432, 54, 56765, 5324, 8768, 234 };
		MergeSort.MergeSort(test1);
		for (int i = 0; i < test1.length; i++) {
			System.out.println(test1[i]);
		}
	}

}
