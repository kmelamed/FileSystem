import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteToFile {

	public static void main(String[] args) throws FileNotFoundException {
		File fromFile = new File("Demo.txt");
		File toFile = new File("Temp");
		
		Scanner in = new Scanner(fromFile);
		PrintWriter out = new PrintWriter(toFile); //creates a file
		//rewrite file to have each word on a new line
		
		while (in.hasNext()) {
			out.println(in.next());
		}
		fromFile.renameTo(toFile);
		
//		out.print(in.nextInt() + in.nextInt());
//		out.print("aaaaa");
		
		out.close();
		in.close();
	}

}
