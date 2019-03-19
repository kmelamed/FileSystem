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
		p = 12;
//		PrintWriter pw = new PrintWriter(doritOS);
		System.out.println(ls());
		System.out.println(lsFolders());
		System.out.println(currentDir());
		System.out.println(cleanDir(currentDir()));
		cd("Deeper");
		System.out.println(currentDir());
		System.out.println(ls());
		System.out.println(lsFolders());
		cd("Deeper2");
		System.out.println(currentDir());
		System.out.println(ls());
		System.out.println(lsFolders());
		
	}

	public static String ls() {
		int i = p;
		int pTemp = i;
		String temp = "";
		String out = "";
		int x = endOfDir();
		while (i < x) {
			if (dir.charAt(i) == ':') {
				pTemp = i + 1;
				while (dir.charAt(pTemp) != '[' && dir.charAt(pTemp) != ']') {
					temp += "" + dir.charAt(pTemp);
					pTemp++;
				}
//				System.out.print(temp + "   ");
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
//				System.out.print(temp + "   ");
				out += temp + "   ";
				temp = "";
				i = folderCheck(i);

			}
			i++;
		}
		return out;
	}

	public static void cd(String newDir) {
		String folders = lsFolders();
		String currentDir = currentDir();
		String cleanDir;
		String cleanDirFiles;
		if (folders.contains(newDir)) {
			cleanDir = cleanDir(currentDir);
			cleanDirFiles = cleanDirFiles(currentDir);
			System.out.println(cleanDirFiles);
			int x = cleanDir.indexOf(newDir);
			p += x + newDir.length();
		} else {
			System.out.println("Directory does not exist.");
		}
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
	
	public static String minusEquals(String str) {
		int x = 0;
		
		while (str.charAt(str.length() - 1 - x) != '[') {
			x++;
		}
		str = str.substring(0, str.length() - x);
		for (int i = 0; i < x; i++) {
			str += ' ';
		}
		return str;
	}

	public static String cleanDir(String currentDir) {
//		System.out.println(currentDir);
		int i = 0;
		String cleanDir = "";
		while (i < currentDir.length()) {
//			System.out.println(currentDir.charAt(i));
			if (currentDir.charAt(i) == ':' && currentDir.substring(i - 5, i).equals("[file")) {
//				System.out.println("test1");
				cleanDir = minusEquals(cleanDir);
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != ']') {
					cleanDir += ' ';
					i++;
				}
				cleanDir += "]";
				i++;
			} else if (currentDir.charAt(i) == ':' && currentDir.substring(i - 7, i).equals("[folder")) {
//				System.out.println("test2");
				cleanDir = minusEquals(cleanDir);
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != '[') {
					cleanDir += currentDir.charAt(i);
					i++;
				}
				int brackets = 0;
				while (brackets != -1) {
//					System.out.println(brackets);
//					System.out.println(cleanDir);
					if (currentDir.charAt(i) == '[') {
						brackets++;
						cleanDir += '[';
					} else if (currentDir.charAt(i) == ']') {
						brackets--;
						cleanDir += ']';
					} else if (currentDir.charAt(i) == ':'){
						cleanDir += ':';
					} else {
						cleanDir += ' ';
					}
					i++;
				}
//				i++;
			} else {
				cleanDir += currentDir.charAt(i);
				i++;
			}
//			i++;
		}
		return cleanDir;
	}
	
	public static String cleanDirFiles(String currentDir) {
//		System.out.println(currentDir);
		int i = 0;
		String cleanDir = "";
		while (i < currentDir.length()) {
//			System.out.println(currentDir.charAt(i));
			if (currentDir.charAt(i) == ':' && currentDir.substring(i - 5, i).equals("[file")) {
//				System.out.println("test1");
				cleanDir = minusEquals(cleanDir);
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != ']') {
					cleanDir += currentDir.charAt(i);
					i++;
				}
				cleanDir += "]";
				i++;
			} else if (currentDir.charAt(i) == ':' && currentDir.substring(i - 7, i).equals("[folder")) {
//				System.out.println("test2");
				cleanDir = minusEquals(cleanDir);
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != '[') {
					cleanDir += ' ';
					i++;
				}
				int brackets = 0;
				while (brackets != -1) {
//					System.out.println(brackets);
//					System.out.println(cleanDir);
					if (currentDir.charAt(i) == '[') {
						brackets++;
						cleanDir += '[';
					} else if (currentDir.charAt(i) == ']') {
						brackets--;
						cleanDir += ']';
					} else if (currentDir.charAt(i) == ':'){
						cleanDir += ':';
					} else {
						cleanDir += ' ';
					}
					i++;
				}
//				i++;
			} else {
				cleanDir += currentDir.charAt(i);
				i++;
			}
//			i++;
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
//				return i + 1;
		}
		return dir.length() - 1;
	}

	public static String currentDir() {
		return dir.substring(p + 1, endOfDir());
	}

}
