import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services. translate.model.TranslateTextResult;

import java.util.Scanner;

public class Main {
    private static final String REGION = "us-east-1";
    public static void main(String args[]){
        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();

        AmazonTranslate translate = AmazonTranslateClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
                .withRegion(REGION)
                .build();
Scanner in = new Scanner(System.in);

while(true) {
    System.out.print("Enter text: ");

    TranslateTextRequest request = new TranslateTextRequest()
            .withText(in.nextLine())
            .withSourceLanguageCode("en")
            .withTargetLanguageCode("zh");
    TranslateTextResult result = translate.translateText(request);
    System.out.println(result.getTranslatedText());

}}
}
