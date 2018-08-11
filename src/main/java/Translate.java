import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

public class Translate {
    private static final String REGION = "us-east-1";
    AmazonTranslate translate;

    Translate(AWSCredentialsProvider creds) {
        translate = AmazonTranslateClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds.getCredentials()))
                .withRegion(REGION)
                .build();
    }

    public String translateMessage(String msg, String sLang, String dLang, boolean hasTranslated) {
        if (hasTranslated) {
            String temp = dLang;
            dLang = sLang;
            sLang = temp;
        }

        TranslateTextRequest request = new TranslateTextRequest()
                .withText(msg)
                .withSourceLanguageCode(sLang)
                .withTargetLanguageCode(dLang);
        TranslateTextResult result = translate.translateText(request);

        return result.getTranslatedText();
    }
}
