import java.util.Scanner;

public class PlayFairCipher {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Eneter the Key");
		String key = s.next();
		if (key.length() > 25)
			throw new Exception("Key too large !");
		System.out.println("Enter the Message");
		String msg = s.next();
		if (chk_dupl(msg))
			msg = tackle_duplicates(msg);
		else if (msg.length() % 2 != 0)
			msg = msg + "X";
		System.out.println("Message changed to:" + msg);
		System.out.println("Cipher Text: " + encrypt(msg, key));
	}

	public static String tackle_duplicates(String msg) {
		StringBuilder new_msg = new StringBuilder();
		if (msg.length() % 2 != 0) {
			for (int i = 0; i < msg.length(); i += 2) {
				if (i == msg.length() - 1) {
					new_msg.append(msg.charAt(i) + "X");
					break;
				}
				if (msg.charAt(i) != msg.charAt(i + 1)) {
					new_msg.append(msg.charAt(i));
					new_msg.append(msg.charAt(i + 1));
				} else {
					new_msg.append(msg.charAt(i) + "X");
					i--;
				}
			}
		} else {
			for (int i = 0; i < msg.length(); i += 2) {
				if (i == msg.length() - 1) {
					new_msg.append(msg.charAt(i) + "X");
					break;
				}
				if (msg.charAt(i) != msg.charAt(i + 1)) {
					new_msg.append(msg.charAt(i));
					new_msg.append(msg.charAt(i + 1));
				} else {
					new_msg.append(msg.charAt(i) + "X");
					i--;
				}
			}
		}
		return new_msg.toString();
	}

	public static boolean chk_dupl(String msg) {
		if (msg.length() % 2 == 0) {
			for (int i = 0; i < msg.length(); i += 2) {
				if (msg.charAt(i) == msg.charAt(i + 1))
					return true;
			}
		} else {
			for (int i = 0; i < msg.length() - 1; i += 2) {
				if (msg.charAt(i) == msg.charAt(i + 1))
					return true;
			}
		}
		return false;
	}

	public static StringBuilder encrypt(String msg, String key) {
		StringBuilder encrypted = new StringBuilder();
		char[][] m = new char[5][5];
		int ctr = 0, i = 0, j = 0;
		for (i = 0; i < 5 && ctr < key.length(); i++)
			for (j = 0; j < 5 && ctr < key.length(); j++) {
				m[i][j] = key.charAt(ctr);
				ctr++;
			}
		i--;
		// display2d(m);
		ctr = 65;
		for (; i < 5; i++) {
			do {
				char c = (char) ctr;
				if (c == 'J') {
					ctr++;
					continue;
				}
				if (!chk_presence(m, c)) {
					m[i][j] = c;
					ctr++;
				} else {
					ctr++;
					j--;
				}
				j++;
			} while (j < 5);
			j = 0;
		}
		display2d(m);

		char[] pair = new char[2];
		for (i = 0; i < msg.length(); i += 2) {
			pair[0] = msg.charAt(i);
			pair[1] = msg.charAt(i + 1);
			int[] pos1, pos2;
			pos1 = find_pos(m, pair[0]);
			pos2 = find_pos(m, pair[1]);
			String str = "";
			if (pos1[0] == pos2[0]) {
				if (pos1[1] != 4 && pos2[1] != 4) {
					str = str + m[pos1[0]][pos1[1] + 1] + m[pos2[0]][pos2[1] + 1];
					System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
				} else {
					if (pos1[1] == 4) {
						str = str + m[pos1[0]][0] + m[pos2[0]][pos2[1] + 1];
						System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
					} else {
						str = str + m[pos1[0]][pos1[1]+1] + m[pos2[0]][0];
						System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
					}
				}
				encrypted.append(str);
			} else if (pos1[1] == pos2[1]) {
				if (pos1[0] != 4 && pos2[0] != 4) {
					str = str + m[pos1[0] + 1][pos1[1]] + m[pos2[0] + 1][pos2[1]];
					System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
				} else {
					if (pos1[0] == 4) {
						str = str + m[0][pos1[1]] + m[pos2[0] + 1][pos2[1]];
						System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
					} else {
						str = str + m[pos1[0] + 1][pos1[1]] + m[0][pos2[1]];
						System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
					}
				}
				encrypted.append(str);
			} else {
				str = str + m[pos1[0]][pos2[1]] + m[pos2[0]][pos1[1]];
				System.out.println((char) pair[0] + "" + (char) pair[1] + " becomes " + str);
				encrypted.append(str);
			}
		}
		return encrypted;

	}

	public static int[] find_pos(char[][] m, char c) {
		if (c == 'J')
			return find_pos(m, 'I');
		int[] pos = new int[2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				if (m[i][j] == c) {
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
		}
		return pos;
	}

	public static void display2d(char[][] m) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				System.out.print(m[i][j] + " ");
			System.out.println();
		}
	}

	public static boolean chk_presence(char[][] m, char c) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (m[i][j] == c)
					return true;
			}
		}
		return false;
	}

}
