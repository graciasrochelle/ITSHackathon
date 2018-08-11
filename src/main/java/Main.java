import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

public class Main {

    public static void main(String args[]) {
        // AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
        //  Translate tl = new Translate(awsCreds);
        Dynamo db = new Dynamo();
        FileReader myRunnable = new FileReader(db);
        Thread t = new Thread(myRunnable);
        t.start();
        //System.out.println(tl.translateMessage("Test","en","es",false));
    }
}
