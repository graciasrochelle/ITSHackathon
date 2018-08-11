import java.util.ArrayList;
import java.util.Iterator;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;

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

            System.out.println("PutItem succeeded");

        } catch (Exception e) {
            System.err.println("Unable to add item");
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<String> load(){
        String dLang;
        boolean isTranslated;
        String sLang;
        String ID;
        String message;

        ArrayList<String> translateValue = new ArrayList<>();

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("ID,dLang,message,sLang,isTranslated");

        try {
            ItemCollection<ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            while (iter.hasNext()){
                Item item = iter.next();

                dLang = item.getString("dLang");
                isTranslated = item.getBOOL("isTranslated");
                sLang = item.getString("sLang");
                ID = item.getString("ID");
                message = item.getString("message");

                translateValue.add(dLang);
                translateValue.add(String.valueOf(isTranslated));
                translateValue.add(sLang);
                translateValue.add(ID);
                translateValue.add(message);
            }

        }catch (Exception e){
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }

        System.out.println(translateValue);
        return translateValue;
    }


}

