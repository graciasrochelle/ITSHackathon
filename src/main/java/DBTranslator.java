import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public class DBTranslator implements Runnable{

    private DynamoDB db;

    DBTranslator(DynamoDB db){
        this.db = db;
    }

    @Override
    public void run() {

    }
}
