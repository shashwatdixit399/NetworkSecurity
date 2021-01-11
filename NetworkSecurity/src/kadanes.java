
public class kadanes {

	public static void main(String[] args) {
		int[] a = { -1, 3, -0, -2 };
		System.out.println(sum(a));
	}

	public static int sum(int[] a) {
		int max_so_far = Integer.MIN_VALUE, max_ending = 0;
		for (int i = 0; i < a.length; i++) {
			max_ending += a[i];
			if (max_so_far < max_ending)
				max_so_far = max_ending;
			if (max_ending < 0)
				max_ending = 0;
		}
		return max_so_far;
	}

}
