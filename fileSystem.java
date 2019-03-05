import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSystem {
	
	public static String dir;
	public static int p;
	
	public static void main(String[] args) throws FileNotFoundException {
//		File doritOS = new File("/Users/pfelix/Documents/test.txt");
//		Scanner in = new Scanner(doritOS);
//		String dir = in.nextLine();
		dir = "[folder:dorit[file:Hello World][file:This text is parseable][folder:Deeper[file:Hello World][folder:Deeper2[file:Test1]]][file:Doom]]";
//		dir = "[folder:dorit[file:a][folder:b[file:c]]]";
		System.out.println(dir);
		p = 12;
//		PrintWriter pw = new PrintWriter(doritOS);
		ls();

	}
	
	public static String ls() {
		int i = p;
		int pTemp = i;
		String temp = "";
		String out = "";
		while (i < dir.length()) {
			if (dir.charAt(i) == ':') {
				pTemp = i + 1;
				while (dir.charAt(pTemp) != '[' && dir.charAt(pTemp) != ']') {
					temp += "" + dir.charAt(pTemp);
					pTemp++;
				}
				System.out.print(temp + "   ");
				out += temp + "   ";
				temp = "";
				if (dir.substring(i - 6, i).equals("folder")) {
					i = folderCheck(i);
				}
			}
			i++;
		}
		return out;
	}
	
	public static int cd(String newDir) {
		String currentDir = ls();
		if (currentDir.contains(newDir)) {
			dir.in
		}
		System.out.println("Directory does not exist.");
		return -1;
	}
	
	public static int folderCheck(int i) {
		while (!dir.substring(i, i + 2).equals("]]")) {
			if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
				System.out.println(i);
				folderCheck(++i);
			}
			i++;
		}
		return i;
	}

}
