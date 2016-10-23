package InputLayer;

import java.util.ArrayList;

import Dictionary.Constants;

public class InputProcessor {
	
	//String to represent User Input
	private String userInput;
	private int inputLength;
	private ArrayList<String> inputTokens;

	
	public InputProcessor(String userInput){
		this.userInput = userInput;
		inputLength = userInput.length();
		inputTokens =  new ArrayList<String>();
	}
	

	//Splits the User Input To tokens
	public void splitStringToTokens(){
		String[] temporaryTokens = userInput.split(" ");
		
		for(int i = 0; i < temporaryTokens.length; i++){
			
			if(checkWord(temporaryTokens[i])&& !isIrrelevantToken(temporaryTokens[i])){
				
				//change to lower case and add
				inputTokens.add(temporaryTokens[i].toLowerCase());
				
			}
	
		}
	}
	
	//check Whether the token is relevant or not
	private boolean isIrrelevantToken(String word){
		
		if(Constants.irrelevantTokens.contains(word)){
			return true;
		}else{
			return false;
		}
	}
	
	//Values Passed from splitStringToTokens to check the word is valid or not
	private boolean checkWord(String word){
		
		if(word.equals(" ") || word == null)
			return false;
		
		//String word divided to character to check character by character
		char[] wordChar = word.toCharArray();
		for(int i = 0; i < wordChar.length; i++){
			
				if(!Character.isLetter(wordChar[i]))
					return false;
		}		
		return true;
	}

}
