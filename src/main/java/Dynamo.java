import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dynamo {

    AmazonDynamoDB client;
    DynamoDB dynamoDB;
    Table table;

    Dynamo() {
        client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://dynamodb.us-east-1.amazonaws.com", "us-east-1"))
                .build();

        dynamoDB = new DynamoDB(client);

        table = dynamoDB.getTable("SQA");
    }

    public void save(ArrayList<String> userDetails) {

        String studentID = userDetails.get(0);
        String message = userDetails.get(1);
        String dLang = userDetails.get(2);
        String sLang = userDetails.get(3);
        Boolean type = Boolean.parseBoolean(userDetails.get(4));
        Boolean isTranslated = Boolean.parseBoolean(userDetails.get(5));


        try {
            System.out.println("Adding a new item...");
            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("StudentID", studentID)
                            .with("SLang", sLang).with("Dlang", dLang).with("Type", type)
                            .with("TText", "FIX ME"));

            System.out.println("PutItem succeeded:\n" + outcome.toString());

        } catch (Exception e) {
            System.err.println("Unable to add item: " + dLang + " " + sLang);
            System.err.println(e.getMessage());
        }


    }
}

