import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader implements Runnable{

	private final static File folder = new File("PhpTester/data");
	private final static String filePath = "PhpTester/data/";
	private Scanner input;
	private Dynamo db;	
	
	FileReader(Dynamo db){
		this.db = db;	
	}
	
	public void run(){
		ArrayList<String> files = listFilesForFolder(folder);
		try {
			for (String fname : files){
				String fPath = filePath + fname;
				System.out.println(fPath);
				ArrayList<String> userDeatils = new ArrayList<String>();
				input = new Scanner(new FileInputStream(fPath));
				while (input.hasNextLine()) {
					userDeatils.add(input.nextLine());
				}
				File ftemp = new File(fPath);
				db.save(userDeatils);
				input.close();
				ftemp.delete();
			}
		}
		catch (Exception exception) {
			System.err.println("Error: Bad File - " + filePath);
		}

	}

	public ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> files = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				files.add(fileEntry.getName());
			}
		}
		return files;
	}
}
