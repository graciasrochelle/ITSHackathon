import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;

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
    //  dlang 0
    //  istrans 1
    //  slang 2
    //  id 3
    //  message 4
    // utype 5
    public void save(ArrayList<String> userDetails) {
        String message = userDetails.get(1);
        String dLang = userDetails.get(2);
        String sLang = userDetails.get(3);
        Boolean type = Boolean.parseBoolean(userDetails.get(4));
        Boolean isTranslated = Boolean.parseBoolean(userDetails.get(5));

        System.out.println(isTranslated);
        try {
            System.out.println("Adding a new item...");
            //    dlang 0
//    istrans 1
//    slang 2
//    id 3
//    message 4
            String id = String.valueOf(System.currentTimeMillis());
            PutItemOutcome outcome = table
                    .putItem(new Item().withPrimaryKey("ID", id )
                            .with("dLang", dLang)
                            .with("isTranslated", isTranslated)
                            .with("sLang", sLang)
                            .with("studentID", id)
                            .with("message", message)
                            .with("utype", type)

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
        boolean utype;

        ArrayList<String> translateValue = new ArrayList<String>();

        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("ID,dLang,message,sLang,isTranslated,utype");

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
                utype=item.getBOOL("utype");
                translateValue.add(dLang);
                translateValue.add(String.valueOf(isTranslated));
                translateValue.add(sLang);
                translateValue.add(ID);
                translateValue.add(message);
                translateValue.add(String.valueOf(utype));
            }

        }catch (Exception e){
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }


        return translateValue;
    }

    public void update(ArrayList<String> updatedData){
        String ID = "";
        String studentID = updatedData.get(3);
        String message = updatedData.get(4);
        String dLang = updatedData.get(0);
        String sLang = updatedData.get(2);
        Boolean type = Boolean.parseBoolean(updatedData.get(5));
        Boolean isTranslated = true;

        System.out.println(studentID);

        for (int i = 0; i < updatedData.size()/5; i++) {
            ID = updatedData.get(i*5+3);
        }

        UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                .withPrimaryKey("ID", ID)
                .withUpdateExpression("set dLang= :d ,isTranslated = :val, message= :m,sLang= :s,studentID= :sI,utype=:t")
                .withValueMap(new ValueMap()
                .withString(":d",dLang)
                .withBoolean(":val", isTranslated)
                .withString(":m",message)
                .withString(":s",sLang)
                .withString(":sI","s31234567")
                .withBoolean(":t",type));

        try {
            System.out.println("Updating the item...");
            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
        }catch (Exception e){
            System.err.println("Unable to update item: " + dLang + " " + sLang + " " + message + " " + isTranslated + " "
                    + isTranslated + " " + type);
            System.err.println(e.getMessage());
        }
    }

    public void delete(String id){
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
                .withPrimaryKey(new PrimaryKey("ID", id));


        try {
            System.out.println("Attempting a conditional delete...");
            table.deleteItem(deleteItemSpec);
            System.out.println("DeleteItem succeeded");
        }
        catch (Exception e) {
            System.err.println("Unable to delete item: " + id);
            System.err.println(e.getMessage());
        }
    }



}

