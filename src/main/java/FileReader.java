import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader implements Runnable {

    private final static File folder = new File("PhpTester/data");
    private final static String filePath = "PhpTester/data/";
    private Scanner input;
    private Dynamo db;

    FileReader(Dynamo db) {
        this.db = db;
    }

    public void run() {
        while (true) {
            ArrayList<String> files = listFilesForFolder(folder);
            try {
                for (String fname : files) {
                    String fPath = filePath + fname;
                    System.out.println(fPath);
                    ArrayList<String> userDeatils = new ArrayList<String>();
                    input = new Scanner(new FileInputStream(fPath));
                    while (input.hasNextLine()) {
                        userDeatils.add(input.nextLine());
                    }
                    File ftemp = new File(fPath);
                    System.out.println(userDeatils);
                    db.save(userDeatils);
                    input.close();
                    ftemp.delete();
                }
            } catch (Exception exception) {
                System.err.println("Error: Bad File - " + filePath);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> files = new ArrayList<String>();
        try {
            for (final File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                    files.add(fileEntry.getName());
                }
            }

        }catch (Exception e){
            e.getStackTrace();
        }
        return files;
    }
}
