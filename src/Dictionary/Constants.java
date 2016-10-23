package Dictionary;

import java.util.ArrayList;

public class Constants {

	public static ArrayList<String> irrelevantTokens;
	
	public Constants(){
		
		irrelevantTokens = new ArrayList<String>();
		initIrrelevantTokens();
	}
	
	
	
	
	private void initIrrelevantTokens(){
		
		irrelevantTokens.add("is");
		irrelevantTokens.add("am");
		irrelevantTokens.add("are");
		irrelevantTokens.add("the");
		irrelevantTokens.add("has");
		irrelevantTokens.add("have");
		irrelevantTokens.add("had");
		irrelevantTokens.add("shall");
		irrelevantTokens.add("should");
		irrelevantTokens.add("can");
		irrelevantTokens.add("could");
		irrelevantTokens.add("well");
		irrelevantTokens.add("wont");
	}
	
}
