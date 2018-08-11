import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.HashMap;
import java.util.Map;

public class DynamoTest {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.us-east-1.amazonaws.com", "us-east-1"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("SQA");

        String studentID = "S124";
        String dLang = "ZH";
        String sLang = "EN";
        String type = "Q";
        String TText = null;

        final Map<String,Object> infoMap = new HashMap<String,Object>();
        infoMap.put("studentID", studentID);
        infoMap.put("SLang", sLang);
        infoMap.put("Dlang",dLang);
        infoMap.put("Type",type);

        try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("StudentID", studentID, "Dlang", dLang)
                            .withMap("info", infoMap));

            System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + dLang + " " + sLang);
            System.err.println(e.getMessage());
        }



    }
}
