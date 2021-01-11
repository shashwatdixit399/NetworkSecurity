
public class DucthNF {

	public static void main(String[] args) {
		int[] a = { 2, 1, 2, 1, 0, 1, 2 };
		sort(a);
		display(a);
	}

	public static void sort(int[] a) {
		int lo = 0, mid = 0, hi = a.length - 1;
		while (mid <= hi) {
			if (a[mid] == 0) {
				swap(a, lo, mid);
				lo++;
				mid++;
			} else if (a[mid] == 1)
				mid++;
			else {
				swap(a, mid, hi);
				hi--;
			}
		}
	}

	public static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void display(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

}
