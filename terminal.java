import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Terminal {
	
	public static String[] commands = {"cd", "edit", "exit", "ls", "mkdir", "rm", "rmdir", "touch"};

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print(FileSystem.currentDirName() + " user$ ");
		String commandLine = in.nextLine();
		StringTokenizer st = new StringTokenizer(commandLine);
		String command = st.nextToken();
//		System.out.println(command);
		while (!command.equals("exit")) {
			if (Arrays.asList(commands).contains(command)) {
				switch(command) {
					
				}
			} else {
				System.out.println("-doritOS: " + command + ": command not found");
			}
			System.out.print(FileSystem.currentDirName() + " user$ ");
			commandLine = in.nextLine();
			st = new StringTokenizer(commandLine);
			command = st.nextToken();
		}
		in.close();
	}

}
