import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Terminal {

//	public static String[] commands = { "cd", "edit", "exit", "ls", "mkdir", "rm", "rmdir", "touch" };

	public static void main(String[] args) throws FileNotFoundException {
		String path  = "src/doritOS";
		File doritOS = new File(path);
		Scanner inFile = new Scanner(doritOS);
		String dir = inFile.nextLine();
		inFile.close();
		
		FileSystem fs = new FileSystem(dir);
		Scanner inUser = new Scanner(System.in);
		System.out.print("computer:" + fs.currentDirName() + " user$ ");
		
		String commandLine = inUser.nextLine();
		commandLine = commandLine.replace("\\ ", "\\");
		StringTokenizer st = new StringTokenizer(commandLine);
		String command = st.nextToken();
		while (!command.equals("exit")) {
			switch (command) {
				case "cd":
					if (st.countTokens() >= 1) {
						fs.cd(st.nextToken().replace("\\", " "));
					}
					break;
				case "edit":
					if (st.countTokens() >= 2) {
						fs.edit(st.nextToken().replace("\\", " "), st.nextToken().replace("\\", " "));					
					}
					break;
				case "ls":
					fs.ls();
					break;
				case "mkdir":
					if (st.countTokens() >= 1) {
						fs.mkdir(st.nextToken().replace("\\", " "));
					}
					break;
				case "rm":
					if (st.countTokens() >= 1) {
						fs.rm(st.nextToken().replace("\\", " "));
					}
					break;
				case "rmdir":
					if (st.countTokens() >= 1) {
						fs.rmdir(st.nextToken().replace("\\", " "));
					}
					break;
				case "touch":
					if (st.countTokens() >= 1) {
						fs.touch(st.nextToken().replace("\\", " "));
					}
					break;
				default:
					System.out.println("-doritOS: " + command + ": command not found");
			}
			System.out.print("computer:" + fs.currentDirName() + " user$ ");
			commandLine = inUser.nextLine();
			commandLine = commandLine.replace("\\ ", "\\");
			st = new StringTokenizer(commandLine);
			command = st.nextToken();
		}
		doritOS.delete();
		PrintWriter pw = new PrintWriter(path);
		pw.println(fs.dir);
		pw.close();
		inUser.close();
	}

}
