import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
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
        String dLang = "EN";
        String sLang = "ZH";
        String type = "Q";
        Boolean TText = true;
        

        try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("StudentID", studentID)
                           .with("SLang",sLang).with("Dlang",dLang).with("Type",type)
                            .with("TText",TText));

            System.out.println("PutItem succeeded:\n" + outcome.toString());

        }
        catch (Exception e) {
            System.err.println("Unable to add item: " + dLang + " " + sLang);
            System.err.println(e.getMessage());
        }



    }
}
