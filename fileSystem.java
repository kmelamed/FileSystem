
public class FileSystem {

	String dir;
	int p;
	
	public FileSystem(String d) {
		dir = d;
		int brackets = 0;
		int x = 0;
		while (brackets < 2) {
			if (d.charAt(x) == '[')
				brackets++;
			x++;
		}
		p = x - 2;
	}

	// Done
	public void ls() {
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
				out += temp + "   ";
				temp = "";
				if (dir.substring(i - 6, i).equals("folder")) {
					i = folderCheck(i);
				}
			}
			i++;
		}
		System.out.println(out);
	}

	// Done
	public String lsFiles() {
		int i = p;
		int pTemp = i;
		String temp = "";
		String out = ".";
		while (i < dir.length()) {
			if (dir.charAt(i) == ':' && dir.substring(i - 4, i).equals("file")) {
				pTemp = i + 1;
				while (dir.charAt(pTemp) != '[' && dir.charAt(pTemp) != ']') {
					temp += "" + dir.charAt(pTemp);
					pTemp++;
				}
				out += temp + ".   .";
				temp = "";
			} else if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
				i = folderCheck(i);
			}
			i++;
		}
		return out;
	}

	// Done
	public String lsFolders() {
		int i = p;
		int pTemp = i;
		String temp = "";
		String out = ".";
		while (i < dir.length()) {
			if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
				pTemp = i + 1;
				while (dir.charAt(pTemp) != '[' && dir.charAt(pTemp) != ']') {
					temp += "" + dir.charAt(pTemp);
					pTemp++;
				}
				out += temp + ".   .";
				temp = "";
				i = folderCheck(i);

			}
			i++;
		}
		return out;
	}

	// Done
	public void cd(String newDir) {
		String folders = lsFolders();
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
			System.out.println("-doritOS: cd: " + newDir + ": Directory does not exist!");
		}
	}

	// Done
	public void rm(String file) {
		String currentDir = currentDir();
		String cleanDirFiles = cleanDirFiles();
		if (lsFiles().contains(file)) {
			int x = cleanDirFiles.indexOf(file);
			currentDir = currentDir.substring(0, x - 6) + currentDir.substring(x + file.length() + 1);
			dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
		} else {
			System.out.println("-doritOS: rm: " + file + ": File does not exist!");
		}
	}

	// Done
	public void rmdir(String folder) {
		String currentDir = currentDir();
		String cleanDir = cleanDir();
		if (lsFolders().contains(folder)) {
			if (cleanDir.contains(folder + "]")) {
				int x = cleanDir.indexOf(folder);
				currentDir = currentDir.substring(0, x - 8) + currentDir.substring(x + folder.length() + 1);
				dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
			} else {
				System.out.println("-doritOS: rmdir: " + folder + ": Directory must be empty!");
			}
		} else {
			System.out.println("-doritOS: rmdir: " + folder + ": Directory does not exist!");
		}
	}

	// Done
	public String minusEquals(String str) {
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

	// Done
	public String cleanDir() {
		String currentDir = currentDir();
		int i = 0;
		String cleanDir = "";
		while (i < currentDir.length()) {
			if (currentDir.charAt(i) == ':' && currentDir.substring(i - 5, i).equals("[file")) {
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
				cleanDir = minusEquals(cleanDir);
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != '[' && currentDir.charAt(i) != ']') {
					cleanDir += currentDir.charAt(i);
					i++;
				}
				int brackets = 0;
				while (brackets != -1) {
					if (currentDir.charAt(i) == '[') {
						brackets++;
						cleanDir += '[';
					} else if (currentDir.charAt(i) == ']') {
						brackets--;
						cleanDir += ']';
					} else if (currentDir.charAt(i) == ':') {
						cleanDir += ':';
					} else {
						cleanDir += ' ';
					}
					i++;
				}
			} else {
				cleanDir += currentDir.charAt(i);
				i++;
			}
		}
		return cleanDir;
	}

	// Done
	private String cleanDirFiles() {
		String currentDir = currentDir();
		int i = 0;
		String cleanDir = "";
		while (i < currentDir.length()) {
			if (currentDir.charAt(i) == ':' && currentDir.substring(i - 5, i).equals("[file")) {
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
				cleanDir = minusEquals(cleanDir);
				cleanDir += ':';
				i++;
				while (currentDir.charAt(i) != '[') {
					cleanDir += ' ';
					i++;
				}
				int brackets = 0;
				while (brackets != -1) {
					if (currentDir.charAt(i) == '[') {
						brackets++;
						cleanDir += '[';
					} else if (currentDir.charAt(i) == ']') {
						brackets--;
						cleanDir += ']';
					} else if (currentDir.charAt(i) == ':') {
						cleanDir += ':';
					} else {
						cleanDir += ' ';
					}
					i++;
				}
			} else {
				cleanDir += currentDir.charAt(i);
				i++;
			}
		}
		return cleanDir;
	}

	// Done
	private int folderCheck(int i) {
		while (!dir.substring(i, i + 2).equals("]]")) {
			if (dir.charAt(i) == ':' && dir.substring(i - 6, i).equals("folder")) {
				folderCheck(++i);
			}
			i++;
		}
		return i;
	}

	// Done
	private int endOfDir() {
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

	// Done
	private String currentDir() {
		return dir.substring(p + 1, endOfDir());
	}
	
	// Done
	public String currentDirName() {
		int x = p;
		while (dir.charAt(x) != ':') {
			x--;
		}
		return dir.substring(x + 1, p + 1);
	}

	// Done
	public void touch(String file) {
		if (!validName(file)) {
			System.out.println("-doritOS: touch: " + file + ": Not a valid file name!");
				return;
		} else if (!lsFiles().contains(file)) {
			String currentDir = currentDir();
			currentDir += "[file:" + file + "]";
			dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
		}
	}
	
	// Done
	private boolean validName(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)) && !Character.isLetter(str.charAt(i)) && !(str.charAt(i) == ' ')) {
				return false;
			}
		}
		return true;
	}
	
	// Done
	public void edit(String oldFile, String newFile) {
		if (newFile.equals(oldFile)) 
			return;
		
		if (!lsFiles().contains("." + oldFile + ".")) {
			System.out.println("-doritOS: edit: " + oldFile + ": Target file does not exist!");
		} else if (lsFiles().contains("." + newFile + ".")) {
			System.out.println("-doritOS: edit: " + newFile + ": Cannot create a duplicate file!");
		} else {
			if (validName(newFile)) {
				String cleanDirFiles = cleanDirFiles();
				int x = cleanDirFiles.indexOf(oldFile);
				String currentDir = currentDir();
				currentDir = currentDir.substring(0, x) + newFile + currentDir.substring(x + oldFile.length());
				dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
			} else {
				System.out.println("-doritOS: edit: " + newFile + ": Not a valid file name!");
			}
		} 
	}

	// Done
	public void mkdir(String folder) {
		for (int i = 0; i < folder.length(); i++) {
			if (!Character.isDigit(folder.charAt(i)) && !Character.isLetter(folder.charAt(i))) {
				System.out.println("-doritOS: mkdir: " + folder + ": Not a valid folder name!");
				return;
			}
		}
		if (!lsFolders().contains(folder)) {
			String currentDir = currentDir();
			currentDir += "[folder:" + folder + "]";
			dir = dir.substring(0, p + 1) + currentDir + dir.substring(endOfDir());
		} else {
			System.out.println("-doritOS: mkdir: " + folder + ": Folder already exists!");
		}
	}

}
