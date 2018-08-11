import java.util.ArrayList;

public class PingDynamoDB implements Runnable{

	private Dynamo db;

	PingDynamoDB(Dynamo db) {
		this.db = db;
	}

	//  dlang 0
	//  istrans 1
	//  slang 2
	//  id 3
	//  message 4
	// utype 5

	public void run() {
		ArrayList<String> tArray= db.load();
		System.out.println(tArray);
		for(int x=0;x<tArray.size()/5;x++)
		{
			if(Boolean.parseBoolean(tArray.get(x*5+1))){
				//tArray.set(x*5+4, translateMessage(tArray.get(x*5+4), tArray.get(x*5+2),tArray.get(x*5), Boolean.parseBoolean(tArray.get(x*5+1))));
				// 0 - Q; 1 - A
				LogCommunicator lgCom = new LogCommunicator();
				if(Boolean.parseBoolean(tArray.get(x*5+5))){
					lgCom.postL(tArray.get(x*5+4),tArray.get(x*5+3));
				}else{
					lgCom.postS(tArray.get(x*5+4));
				}
			}else{
				tArray.set(x*5+1, "1");
			}
		}
	}
}
