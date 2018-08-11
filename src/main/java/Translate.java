import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

import java.util.ArrayList;

public class Translate {
    private static final String REGION = "us-east-1";
    AmazonTranslate translate;

    Translate(AWSCredentialsProvider creds) {
        translate = AmazonTranslateClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds.getCredentials()))
                .withRegion(REGION)
                .build();
    }

    public String translateMessage(String msg, String sLang, String dLang, boolean isTranslated) {
        if (isTranslated) {
            String temp = dLang;
            dLang = sLang;
            sLang = temp;
        }
        TranslateTextRequest request = new TranslateTextRequest()
                .withText(msg)
                .withSourceLanguageCode(sLang)
                .withTargetLanguageCode(dLang);
        System.out.println(request);
        TranslateTextResult result = translate.translateText(request);

        return result.getTranslatedText();
    }

//    dlang 0
//    istrans 1
//    slang 2
//    id 3
//    message 4

    public ArrayList<String> translateList(ArrayList<String> data){
        for(int x = 0; x < data.size()/5; x++){
            data.set(x*5+4, translateMessage(data.get(x*5+4), data.get(x*5+2),data.get(x*5), Boolean.parseBoolean(data.get(x*5+1))));
                    if(Boolean.parseBoolean(data.get(x*5+1))){
                        data.set(x*5+1, "0");
            }else{
                data.set(x*5+1, "1");
            }


        }
        return data;

    }
}
