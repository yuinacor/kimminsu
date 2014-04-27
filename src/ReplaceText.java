import java.io.*;
import java.util.*;

class Chker {

	public void isTxt(String[] args) {
		if (!args[2].endsWith("txt")) {
			System.out.println("텍스트 파일만 사용가능합니다.");
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
	
	public int[] countString(String string) {

		for (int i = 0; i < string.length(); i++) {

			if (string.charAt(i) >= 65 && string.charAt(i) <= 90) {
				counts[string.charAt(i) - 'A']++;
			} else if (string.charAt(i) >= 97 && string.charAt(i) <= 122) {
				counts[string.charAt(i) - 'a' + 26]++;
			}
		}
		return counts;
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

		if (args.length == 4) {// targetFile이 있을경우 진행
			// Check if source file exists

			chker.isTxt(args);

			File sourceFile = new File(args[2]);
			chker.isSourceExist(args, sourceFile);

			// Check if target file exists
			File targetFile = new File(args[3]);
			chker.isTargetExist(args, targetFile);

			// Create input and output files
			Scanner input = new Scanner(sourceFile);
			PrintWriter output = new PrintWriter(targetFile);
			int counts[] = null;
			System.out.println("텍스트 파일 각 행마다의 알파벳 갯수");

			while (input.hasNext()) {

				String s1 = input.nextLine();
				String s2 = s1.replaceAll(args[0], args[1]);
				output.println(s2);
				count.countString(s2);
				count.displayCounts();
				System.out.println("");

			}

			input.close();
			output.close();
		}

		else if (args.length == 3) {// 입력값이 3개일때, 즉 targerFile설정이 안되었을때.
			chker.isTxt(args);
			File sourceFile = new File(args[2]);
			chker.isSourceExist(args, sourceFile);
			// 임시 파일 생성 후 JVM종료시 파일 삭제.
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

		else {// 뭔가 입력값이 잘못되었을때 어떻게 적어야 하는지 출력!
			System.out
					.println("Usage: java ReplaceText oldStr newStr sourceFile targetFile"
							+ "or java Replacementoldstr newStr sourceFile ");
			System.exit(0);

		}

	}


}
