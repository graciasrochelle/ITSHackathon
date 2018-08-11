import java.util.ArrayList;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

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
                    .putItem(new Item().withPrimaryKey("ID", String.valueOf(System.currentTimeMillis()))
                            .with("studentID", studentID)
                            .with("message", message)
                            .with("dLang", dLang)
                            .with("sLang", sLang)
                            .with("type", type)
                            .with("isTranslated", isTranslated)
                    );

            System.out.println("PutItem succeeded:\n" + outcome.toString());

        } catch (Exception e) {
            System.err.println("Unable to add item: " + dLang + " " + sLang);
            System.err.println(e.getMessage());
        }


    }
}

