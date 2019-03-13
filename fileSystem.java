import java.io.FileNotFoundException;

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
		p = 13;
//		PrintWriter pw = new PrintWriter(doritOS);
		ls();
		System.out.println();
		lsFolders();
		System.out.println();
		System.out.println(currentDir());
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

	public static String lsFolders() {
		int i = p;
		int pTemp = i;
		String temp = "";
		String out = "";
		while (i < dir.length()) {
			if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
				pTemp = i + 1;
				while (dir.charAt(pTemp) != '[' && dir.charAt(pTemp) != ']') {
					temp += "" + dir.charAt(pTemp);
					pTemp++;
				}
				System.out.print(temp + "   ");
				out += temp + "   ";
				temp = "";
				i = folderCheck(i);

			}
			i++;
		}
		return out;
	}

	public static int cd(String newDir) {
		String folders = lsFolders();
		String currentDir = currentDir();
		String cleanDir;
		if (folders.contains(newDir)) {
			cleanDir = cleanDir(currentDir);
//			int i = p;
//			while (i < currentDir.length()) {
//				if (dir.charAt(i) == ':' && dir.substring(i - 4, i).equals("file")) {
//					cleanDir += ':';
//					i++;
//					while (currentDir.charAt(i) != ']') {
//						if (currentDir.charAt(i) == '[')
//							cleanDir += '[';
//						else
//							cleanDir += ' ';
//						i++;
//					}
//					cleanDir += ']';
//				} else if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
//					cleanDir += ':';
//					int brackets = 1;
//					for (int i = p + 1; i < dir.length(); i++) {
//						if (dir.charAt(i) == '[')
//							brackets++;
//						if (dir.charAt(i) == ']')
//							brackets--;
//						if (brackets == 0)
//							return i;
//					}
//					i++;
//				} else {
//					
//				}
//			}
		}
		System.out.println("Directory does not exist.");
		return -1;
	}

	public static String cleanDir(String currentDir) {
		int i = 0;
		String cleanDir = "";
		while (i < currentDir.length()) {
			if (currentDir.charAt(i) == ':' && currentDir.substring(i - 4, i).equals("file")) {
				System.out.println("test1");
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != ']') {
					if (currentDir.charAt(i) == '[')
						cleanDir += '[';
					else
						cleanDir += ' ';
					i++;
				}
				cleanDir += ']';
			} else if (currentDir.charAt(i) == ':' && currentDir.substring(i - 6, i).equals("folder")) {
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != '[') {
					cleanDir += currentDir.charAt(i);
					i++;
				}
		
				int brackets = 1;
				while (brackets != 0) {
					if (currentDir.charAt(i) == '[') {
						brackets++;
						cleanDir += '[';
					} else if (currentDir.charAt(i) == ']') {
						brackets--;
						cleanDir += ']';
					} else {
						cleanDir += ' ';
					}
					i++;
				}
			} else {
				cleanDir += currentDir.charAt(i);
			}
			i++;
		}
		return cleanDir;
	}

	public static int folderCheck(int i) {
		while (!dir.substring(i, i + 2).equals("]]")) {
			if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
//				System.out.println(i);
				folderCheck(++i);
			}
			i++;
		}
		return i;
	}

	public static int endOfDir() {
		int brackets = 1;
		for (int i = p + 1; i < dir.length(); i++) {
			if (dir.charAt(i) == '[')
				brackets++;
			if (dir.charAt(i) == ']')
				brackets--;
			if (brackets == 0)
				return i;
		}
		return dir.length() - 1;
	}

	public static String currentDir() {
		return dir.substring(p, endOfDir());
	}

}
