import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogCommunicator {

    LogCommunicator(){

    }

    public void postL(String message, String ID){
        try {

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("././PhpTester/lLog.txt", true));
            out.write(ID+": " + message);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }

    }
    public void postS(String message){
        try {

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter("././PhpTester/sLog.txt", true));
            out.write("Lecturer: " + message);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }

    }
}
