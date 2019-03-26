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
//		System.out.println(lsFiles());
//		System.out.println(ls());
//		System.out.println(lsFolders());
//		System.out.println(currentDir());
//		System.out.println(cleanDir());
//		System.out.println(cleanDirFiles());
//		rm("This text is parseable");
//		rm("Hello World");
//		rm("Hello World");
//		System.out.println(dir);
		System.out.println(currentDir());
		cd("Deeper");
//		System.out.println(p);
//		System.out.println(dir.charAt(p));
		System.out.println(currentDir());
		cd("Deeper2");
		System.out.println(currentDir());
		cd("..");
		System.out.println(p);
		System.out.println(currentDir());
		cd("..");
		System.out.println(p);
		System.out.println(currentDir());
		cd("..");
		System.out.println(p);
		System.out.println(currentDir());
//		rm("Hello World");
//		rm("Hello World");
//		rm("Test1");
//		System.out.println(ls());
//		System.out.println(lsFolders());
//		rmDir("Deeper2");
//		System.out.println(currentDir());
//		System.out.println(dir);
//		cd("Deeper2");
//		rm("Test1");
//		System.out.println(currentDir());
//		System.out.println(ls());
//		System.out.println(lsFolders());
		
	}

	// Done
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
	
	// Done
	public static String lsFiles() {
		int i = p;
		int pTemp = i;
		String temp = "";
		String out = "";
		while (i < dir.length()) {
			if (dir.charAt(i) == ':' && dir.substring(i - 4, i).equals("file")) {
				pTemp = i + 1;
				while (dir.charAt(pTemp) != '[' && dir.charAt(pTemp) != ']') {
					temp += "" + dir.charAt(pTemp);
					pTemp++;
				}
				out += temp + "   ";
				temp = "";
			} else if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
				i = folderCheck(i);
			}
			i++;
		}
		return out;
	}

	// Done
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

	// Done
	public static void cd(String newDir) {
		String folders = lsFolders();
//		String currentDir = currentDir();
		String cleanDir;
		if (newDir.equals("..")) {
			int brackets = 1;
			int i = p;
			while (i > 0) {
				if (dir.charAt(i) == '[')
					brackets--;
				else if (dir.charAt(i) == ']')
					brackets++;
				
				if (brackets == -1) {
					break;
				}
				i--;
			}
			p = Math.min(dir.indexOf("[", i + 1) - 1, dir.indexOf("]", i + 1) - 1);
		} else if (folders.contains(newDir)) {
			cleanDir = cleanDir();
			int x = cleanDir.indexOf(newDir);
			p += x + newDir.length();
		} else {
			System.out.println("Directory does not exist.");
		}
	}
	
	// Done
	public static void rm(String file) {
		String currentDir = currentDir();
		String cleanDirFiles = cleanDirFiles();
		if (lsFiles().contains(file)) {
			int x = cleanDirFiles.indexOf(file);
//			currentDir = currentDir.substring(x - 6, x + file.length() + 1);
//			System.out.println(currentDir.substring(x + file.length() + 1));
//			System.out.println();
			currentDir = currentDir.substring(0, x - 6) + currentDir.substring(x + file.length() + 1);
//			System.out.println(currentDir);
			dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
		} else {
			System.out.println("File does not exist!");
		}
	}
	
	
	// Done
	public static void rmDir(String folder) {
		String currentDir = currentDir();
		String cleanDir = cleanDir();
		if(lsFolders().contains(folder)) {
			if (cleanDir.contains(folder + "]")) {
				int x = cleanDir.indexOf(folder);
				currentDir = currentDir.substring(0, x - 8) + currentDir.substring(x + folder.length() + 1);
				dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
			} else {
				System.out.println("Directory must be empty!");
			}
		} else {
			System.out.println("Directory does not exist!");
		}
	}
	
	
	// Done
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
	
	// Probably necessary for touch and makeDir
	public static String plusEquals(String str) {
		return str;
	}

	// Done
	public static String cleanDir() {
//		System.out.println(currentDir);
		String currentDir = currentDir();
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
				while (currentDir.charAt(i) != '[' && currentDir.charAt(i) != ']') {
//					System.out.println(currentDir.charAt(i));
					cleanDir += currentDir.charAt(i);
					i++;
				}
				int brackets = 0;
				while (brackets != -1) {
//					System.out.println(brackets);
//					System.out.println(cleanDir);
//					System.out.println(currentDir.charAt(i));
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
	
	// Done
	public static String cleanDirFiles() {
//		System.out.println(currentDir);
		String currentDir = currentDir();
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

	
	// Done
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

	
	// Done
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

	
	// Done
	public static String currentDir() {
		return dir.substring(p + 1, endOfDir());
	}
	
	
	// WIP
	public static void touch() {
		
	}
	
	// WIP
	public static void makeDir() {
		
	}
	
	// WIP
	// Not sure if this should be a method or part of main
	public static void userInput() {
		
	}
	
	// Might want to make this
	public static void writeToFile() {
		
	}

}
