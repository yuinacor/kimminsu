import java.io.*;
import java.util.*;

public class ReplaceText {
	public static void main(String[] args) throws Exception {

		if (args.length == 4) {// targetFile�� ������� ����
			// Check if source file exists
			if (!args[2].endsWith("txt")) {
				System.out.println("�ؽ�Ʈ ���ϸ� ��밡���մϴ�.");
				System.exit(0);
			}

			File sourceFile = new File(args[2]);
			if (!sourceFile.exists()) {
				System.out
						.println("Source file " + args[2] + " does not exist");
				System.exit(0);
			}

			// Check if target file exists
			File targetFile = new File(args[3]);
			if (targetFile.exists()) {
				System.out
						.println("Target file " + args[3] + " already exists");
				System.exit(0);
			}

			// Create input and output files
			Scanner input = new Scanner(sourceFile);
			PrintWriter output = new PrintWriter(targetFile);
			int counts[] = null;
			System.out.println("�ؽ�Ʈ ���� �� �ึ���� ���ĺ� ����");

			while (input.hasNext()) {

				String s1 = input.nextLine();
				String s2 = s1.replaceAll(args[0], args[1]);
				output.println(s2);
				counts = countString(s2);
				displayCounts(counts);
				System.out.println("");

			}

			input.close();
			output.close();
		}

		else if (args.length == 3) {// �Է°��� 3���϶�, �� targerFile������ �ȵǾ�����.
			if (!args[2].endsWith("txt")) {
				System.out.println("�ؽ�Ʈ ���ϸ� ��밡���մϴ�.");
				System.exit(0);
			}

			File sourceFile = new File(args[2]);
			if (!sourceFile.exists()) {
				System.out
						.println("Source file " + args[2] + " does not exist");
				System.exit(0);
			}
			// �ӽ� ���� ���� �� JVM����� ���� ����.
			File targetFile = null;
			targetFile.deleteOnExit();

			// Create input and output files
			Scanner input = new Scanner(sourceFile);
			PrintWriter output = new PrintWriter(targetFile);

			while (input.hasNext()) {

				String s1 = input.nextLine();
				String s2 = s1.replaceAll(args[0], args[1]);
				output.println(s2);

			}

			Scanner input2 = new Scanner(targetFile);
			PrintWriter output2 = new PrintWriter(sourceFile);

			while (input.hasNext()) {
				String s1 = input2.nextLine();
				String s2 = s1;
				output2.println(s2);
			}

			input.close();
			output.close();
		}

		else {// ���� �Է°��� �߸��Ǿ����� ��� ����� �ϴ��� ���!
			System.out
					.println("Usage: java ReplaceText oldStr newStr sourceFile targetFile"
							+ "or java Replacementoldstr newStr sourceFile ");
			System.exit(0);

		}

	}

	public static int[] countString(String string) {
		int[] counts = new int[52];

		for (int i = 0; i < string.length(); i++) {

			if (string.charAt(i) >= 65 && string.charAt(i) <= 90) {
				counts[string.charAt(i) - 'A']++;
			} else if (string.charAt(i) >= 97 && string.charAt(i) <= 122) {
				counts[string.charAt(i) - 'a' + 26]++;
			}
		}
		return counts;
	}

	public static void displayCounts(int[] counts) {
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] == 0)
				continue;
			if ((i + 1) % 10 == 0)
				if (i < 26) {
					System.out.println((char) (i + 'A') + " : " + counts[i]);
				} else {
					System.out.println((char) (i + 'a' - 26) + " : "
							+ counts[i]);
				}
			else {
				if (i < 26) {
					System.out.print((char) (i + 'A') + " : " + counts[i]
							+ "\t");
				} else {
					System.out.print((char) (i + 'a' - 26) + " : " + counts[i]
							+ "\t");
				}
			}
		}

	}
}
