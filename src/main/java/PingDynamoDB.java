import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

public class PingDynamoDB implements Runnable{

	private Dynamo db;
	private AWSCredentialsProvider creds;

	PingDynamoDB(Dynamo db, AWSCredentialsProvider creds) {
		this.db = db;
		this.creds = creds;
	}

	//  dlang 0
	//  istrans 1
	//  slang 2
	//  id 3
	//  message 4
	// utype 5

	public void run() {
		while(true){
		ArrayList<String> tArray= db.load();
		Translate tl = new Translate(creds);
		for(int x=0;x<tArray.size()/5;x++)
		{
			if(Boolean.parseBoolean(tArray.get(x*5+1))){
				//tArray.set(x*5+4, translateMessage(tArray.get(x*5+4), tArray.get(x*5+2),tArray.get(x*5), Boolean.parseBoolean(tArray.get(x*5+1))));
				// 0 - Q; 1 - A
				LogCommunicator lgCom = new LogCommunicator();
				if(Boolean.parseBoolean(tArray.get(x*5+5))){
                    lgCom.postS(tArray.get(x*5+4));
                    db.delete(tArray.get(x*5+3));
				}else{
                    lgCom.postL(tArray.get(x*5+4),tArray.get(x*5+3));
                    db.delete(tArray.get(x*5+3));
				}
			}
			else{
				//  dlang 0
				//  istrans 1
				//  slang 2
				//  id 3
				//  message 4
				// utype 5

				//(String msg, String sLang, String dLang, boolean isTranslat
                if(!Boolean.parseBoolean(tArray.get(x*5+1)) == true){
                    System.out.println(tArray.get(x*5));
				tArray.set(x*5+4, tl.translateMessage(tArray.get(x*5+4), tArray.get(x*5+2),tArray.get(x*5), Boolean.parseBoolean(tArray.get(x*5+1))));
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(tArray.get(x*5)); // Dlang
				temp.add(tArray.get(x*5+1));
				temp.add(tArray.get(x*5+2));
				temp.add(tArray.get(x*5+3));
				temp.add(tArray.get(x*5+4));
				temp.add(tArray.get(x*5+5));

                db.update(temp);
			}}
		}
		try {
            Thread.sleep(1000);
        }catch (Exception e){
		    e.printStackTrace();
        }
	}
}}
