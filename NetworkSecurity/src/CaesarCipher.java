import java.util.Scanner;

public class CaesarCipher {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the message");
		String msg = s.next();
		System.out.println("Enter the key(1,25)");
		int key = s.nextInt();
		System.out.println("Cipher Text: " + encrypt(msg, key));
		System.out.println("Plain Text: " + decrypt(encrypt(msg, key), key));
	}

	public static StringBuilder encrypt(String msg, int key) {
		StringBuilder encr_msg = new StringBuilder();
		int[] ascii = new int[msg.length()];
		for (int i = 0; i < msg.length(); i++) {
			ascii[i] = msg.charAt(i);
			ascii[i] -= 65;
		}
		int[] encrypted = new int[ascii.length];
		for (int i = 0; i < ascii.length; i++)
			encrypted[i] = ((ascii[i] + key) % 26) + 65;
		for (int i = 0; i < encrypted.length; i++) {
			char c = (char) encrypted[i];
			encr_msg.append(c);
		}
		return encr_msg;
	}

	public static StringBuilder decrypt(StringBuilder encr_msg, int k) {
		StringBuilder decrypted = new StringBuilder();
		int[] ascii = new int[encr_msg.length()];
		for (int i = 0; i < encr_msg.length(); i++)
			ascii[i] = (int) encr_msg.charAt(i) - 65;
		int[] decrypt = new int[ascii.length];
		for (int i = 0; i < ascii.length; i++) {
			if (ascii[i] - k >= 0)
				decrypt[i] = ((ascii[i] - k) % 26) + 65;
			else {
				decrypt[i] = (26 - Math.abs((ascii[i] - k))) % 26 + 65;
			}
		}
		for (int i = 0; i < decrypt.length; i++) {
			char c = (char) decrypt[i];
			decrypted.append(c); 
		}
		return decrypted;
	}

}
