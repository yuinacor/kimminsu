import java.io.*;
import java.util.*;

class Chker {

	public void isTxt(String[] args) {
		if (!args[2].endsWith("txt")) {
			System.out.println("�ؽ�Ʈ ���ϸ� ��밡���մϴ�.");
			System.exit(0);
		}
	}

	public void isSourceExist(String[] args, File sourceFile) {
		if (!sourceFile.exists()) {
			System.out.println("Source file " + args[2] + " does not exist");
			System.exit(0);
		}
	}

	public void isTargetExist(String[] args, File targetFile) {
		if (targetFile.exists()) {
			System.out.println("Target file " + args[3] + " already exists");
			System.exit(0);
		}
	}
}

class Count {

	private int[] counts = new int[52];

	public void countString(String string) {

		for (int i = 0; i < string.length(); i++) {

			if (string.charAt(i) >= 65 && string.charAt(i) <= 90) {
				counts[string.charAt(i) - 'A']++;
			} else if (string.charAt(i) >= 97 && string.charAt(i) <= 122) {
				counts[string.charAt(i) - 'a' + 26]++;
			}
		}
	}

	public void displayCounts() {
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

public class ReplaceText {

	public static void main(String[] args) throws Exception {

		Chker chker = new Chker();
		Count count = new Count();
		chker.isTxt(args);
		// Check if source file exists
		File sourceFile = new File(args[2]);
		chker.isSourceExist(args, sourceFile);

		if (args.length == 4) {// targetFile�� ������� ����

			// Check if target file exists
			File targetFile = new File(args[3]);
			chker.isTargetExist(args, targetFile);

			// Create input and output files
			Scanner input = new Scanner(sourceFile);
			PrintWriter output = new PrintWriter(targetFile);
			System.out.println("�ؽ�Ʈ ���� �� �ึ���� ���ĺ� ����");

			while (input.hasNext()) {

				String s1 = input.nextLine();
				String s2 = s1.replaceAll(args[0], args[1]);
				output.println(s2);
				count.countString(s2);
			}
			count.displayCounts();
			System.out.println("");

			input.close();
			output.close();
		}

		else if (args.length == 3) {// �Է°��� 3���϶�, �� targerFile������ �ȵǾ�����.

			// �ӽ� ���� ���� �� JVM����� ���� ����.
			File targetFile = new File("target.txt");
			targetFile.deleteOnExit();

			// Create input and output files
			Scanner input = new Scanner(sourceFile);
			PrintWriter output = new PrintWriter(targetFile);

			while (input.hasNext()) {

				String s1 = input.nextLine();
				String s2 = s1.replaceAll(args[0], args[1]);
				output.println(s2);

			}
			input.close();
			output.close();

			Scanner input2 = new Scanner(targetFile);
			PrintWriter output2 = new PrintWriter(sourceFile);

			while (input2.hasNext()) {
				String s1 = input2.nextLine();
				String s2 = new String(s1);
				output2.println(s2);
			}
			input2.close();
			output2.close();

		}

		else {// ���� �Է°��� �߸��Ǿ����� ��� ����� �ϴ��� ���!
			System.out
					.println("Usage: java ReplaceText oldStr newStr sourceFile targetFile"
							+ "or java Replacementoldstr newStr sourceFile ");
			System.exit(0);

		}

	}

}
