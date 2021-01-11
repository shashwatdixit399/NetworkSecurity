import java.util.Arrays;

public class MinMaxDiff {

	public static void main(String[] args) {
		int[] a = { 1, 4, 2, 4, 5 };
		System.out.println(min(a, 10));
	}

	public static int min(int[] a, int k) {
		if (a.length == 1)
			return 0;
		Arrays.sort(a);
		int ans = a[a.length - 1] - a[0];
		int small = a[0] + k;
		int big = a[a.length - 1] - k;
		if (big < small) {
			int t = big;
			big = small;
			small = t;
		}
		for (int i = 1; i < a.length - 1; i++) {
			int add = a[i] + k;
			int sub = a[i] - k;
			if (sub >= small || add <= big)
				continue;
			if (big - sub <= add - small)
				small = sub;
			else
				big = add;
		}
		return Math.min(big - small, ans);
	}

}
