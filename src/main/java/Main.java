import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

public class Main {

    public static void main(String args[]){
        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
        Translate tl = new Translate(awsCreds);


        System.out.println(tl.translateMessage("Test","en","zh",false));

    }
}
