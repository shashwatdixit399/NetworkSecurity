import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class RSA {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter p");
		int p = s.nextInt();
		System.out.println("Enter q");
		int q = s.nextInt();
		if (p == q)
			throw new Exception("p and q cannot be equal !!!");
		if (!chk_prime(p) || !chk_prime(q))
			throw new Exception("p and q should be prime !");
		int n = p * q;
		int phi = (p - 1) * (q - 1);
		int e = get_e(phi);
		System.out.println("Enter message");
		String msg = s.next();
		int d = mult_inv(e, phi);
		System.out.println("Cipher Text: "+encrypt(msg, e, n));
		System.out.println("Plain Text: "+decrypt(encrypt(msg, e, n), d, n));
	}

	public static ArrayList<Integer> encrypt(String msg, int e, int n) {
		StringBuilder encrypted = new StringBuilder();
		ArrayList<Integer> encr = new ArrayList<>();
		int[] ascii = new int[msg.length()];
		for (int i = 0; i < msg.length(); i++)
			ascii[i] = msg.charAt(i);
		for (int i = 0; i < ascii.length; i++)
			encr.add(exp_mod(ascii[i], e, n));
		for (int i = 0; i < encr.size(); i++) {
			int a = encr.get(i);
			char c = (char) a;
			encrypted.append(c);
		}
		return encr;
	}

	public static StringBuilder decrypt(ArrayList<Integer> encr, int d, int n) {
		int[] decr = new int[encr.size()];
		for (int i = 0; i < encr.size(); i++)
			decr[i] = exp_mod(encr.get(i), d, n);
		StringBuilder decrypted = new StringBuilder();
		for (int i = 0; i < decr.length; i++) {
			char c = (char) decr[i];
			decrypted.append(c);
		}
		return decrypted;
	}

	public static int mult_inv(int a, int p) {
		for (int i = 0; i < p; i++) {
			int f = (a * i) % p;
			if (f == 1)
				return i;
		}
		return -1;
	}

	public static boolean chk_prime(int n) {
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public static int gcd(int a, int b) {
		if (a == 0)
			return b;
		if (b == 0)
			return a;
		if (a == b)
			return a;
		if (a > b)
			return gcd(a - b, b);
		return gcd(a, b - a);
	}

	public static int get_e(int phi) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 2; i < phi; i++)
			if (gcd(i, phi) == 1)
				list.add(i);
		Random rand = new Random();
		return list.get(rand.nextInt(list.size()));
	}

	public static int exp_mod(int M, int e, int n) {
		int no = 1;
		for (int i = 1; i <= e; i++) {
			no *= M;
			no = no % n;
		}
		return (no % n);
	}
}
