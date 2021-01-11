import java.util.Scanner;

public class AffineCipher {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the message");
		String msg = s.next();
		System.out.println("Enter a(should be co prime of 26)");
		int a = s.nextInt();
		System.out.println("Enter b([1,25])");
		int b = s.nextInt();
		if (gcd(a, 26) != 1)
			throw new Exception("a is not co prime of 26");
		System.out.println("Cipher Text: " + encrypt(msg, a, b));
		System.out.println("Plain Text: " + decrypt(encrypt(msg, a, b), a, b));
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

	public static int mult_inv(int a) {
		for (int i = 0; i < 26; i++) {
			int f = (a * i) % 26;
			if (f == 1)
				return i;
		}
		return -1;
	}

	public static StringBuilder encrypt(String msg, int a, int b) {
		StringBuilder encr_msg = new StringBuilder();
		int[] ascii = new int[msg.length()];
		for (int i = 0; i < msg.length(); i++) {
			ascii[i] = msg.charAt(i);
			ascii[i] -= 65;
		}
		int[] encrypted = new int[ascii.length];
		for (int i = 0; i < ascii.length; i++)
			encrypted[i] = (((ascii[i] * a) + b) % 26) + 65;
		for (int i = 0; i < encrypted.length; i++) {
			char c = (char) encrypted[i];
			encr_msg.append(c);
		}
		return encr_msg;
	}

	public static StringBuilder decrypt(StringBuilder encr_msg, int a, int b) {
		StringBuilder decrypted = new StringBuilder();
		int[] ascii = new int[encr_msg.length()];
		for (int i = 0; i < encr_msg.length(); i++)
			ascii[i] = (int) encr_msg.charAt(i) - 65;
		int[] decrypt = new int[ascii.length];
		for (int i = 0; i < ascii.length; i++) {
			if (ascii[i] - b >= 0)
				decrypt[i] = ((mult_inv(a) * (ascii[i] - b)) % 26) + 65;
			else {
				decrypt[i] = (mult_inv(a) * (26 - Math.abs((ascii[i] - b)))) % 26 + 65;
			}
		}
		for (int i = 0; i < decrypt.length; i++) {
			char c = (char) decrypt[i];
			decrypted.append(c);
		}
		return decrypted;

	}

}
