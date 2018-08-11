import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

import java.util.ArrayList;

public class Main {

    public static void main(String args[]) {
         AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
         Translate tl = new Translate(awsCreds);
         Dynamo db = new Dynamo();
         FileReader FileDB = new FileReader(db);
         PingDynamoDB myRunnable = new PingDynamoDB(db,awsCreds);
         Thread t = new Thread(myRunnable);
        Thread fDB = new Thread(FileDB);

         t.start();
        fDB.start();








//       System.out.println(tl.translateList(db.load()));
//        ArrayList<String> test = new ArrayList<>();
//        test.add("1534011351456");
//        test.add("我该如何解决这个问题");
//        test.add("zh");
//        test.add("EN");
//        test.add(String.valueOf(true));
//        test.add(String.valueOf(true));

       // db.update(test);
        //System.out.println(tl.translateMessage("Test","en","es",false));
    }
}
