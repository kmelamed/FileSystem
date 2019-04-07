import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ZeroByte {

	public static void main(String[] args) {
		File folder = new File("/Users/kaylenmelamed/ZeroByte");
		System.out.println(folder.exists());

		File[] listOfFiles = folder.listFiles();

		String[] lines = new String[listOfFiles.length];

		int k = 0;
		for (File f: listOfFiles) {
			if (f.isFile()) {
				System.out.println(f.getName());
				lines[k] = f.getName();
				k++;
			}
		}

		Arrays.sort(lines);

		for (int i = 0; i < lines.length; i++) {
			lines[i] = lines[i].replace("DOT", ".");
			System.out.println(lines[i]);
			String result = "";
			for (int j = 0; j < lines[i].length(); j++) {
				if (Character.isDigit(lines[i].charAt(j)))
					continue;
			}
		}

		System.out.println(Arrays.toString(lines));
		
		try (FileWriter fw = new FileWriter("/Users/kaylenmelamed/Documents/workspace/Test/src.Test.java", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter("/Users/kaylenmelamed/Documents/workspace/Test/src/Test.java");
			PrintWriter out = new PrintWriter(bw)){
			for(String line: lines) out.println(line);
		}
		catch (IOException e) {
			System.out.println("Failed");
		}

	}

	static String parse (String s) {
		String result = "";
		return result;
	}

}
