package InputLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Database.Database;
import Dictionary.Constants;
import ProcessingLayer.Trie;
	
public class ChatBot {

	//function to Train the Bot
	public Trie trie;
	public Database answerDatabase;
	
	public ChatBot(){
		answerDatabase = new Database();
	}
	
	public void trainBot(){
		
		Scanner scan = new Scanner(System.in);
		String ans;
		int noOfKeywords;
		String keyword;
		String trainEnd = "stop training";
		while(true){			
			ans = scan.nextLine();
			
			//checking termination command
			if(ans.equals(trainEnd)){
				break;
			}
			noOfKeywords = Integer.parseInt(scan.nextLine());
			
			int index = answerDatabase.addAnswer(ans);
			while(noOfKeywords-- > 0){
				keyword = scan.nextLine();
				trie.insert(keyword, index);
			}			
		}
		
	}
	
	
	public static void main(String arg[]){
		
		//Initialising constants
		Constants constants = new Constants();
		ChatBot chatbot = new ChatBot();
		chatbot.deserializeTrie();
		
		//if trie is not initialised
		if(chatbot.trie == null){
			chatbot.trie = new Trie();
		}
		
		Scanner scan = new Scanner(System.in);
		String botResponse, userResponse;
		InputProcessor processInput;
		
		botResponse="Hi There i am Chat Bot.\n Please feel free to ask me any query :)";
		while(true){			
			
			//Bot Response
			System.out.print("> ");
			System.out.println(botResponse);
			System.out.println("");
			
			botResponse = "Sorry!! Please be more Clear ";
			
			//User Response
			System.out.print("> ");
			userResponse = scan.nextLine();
			userResponse.trim();
			if(userResponse.equals("TrainBot")){
				System.out.println("> Bot is in training mode \n");
				chatbot.trainBot();
			}else if(userResponse.equals("end")){
				break;
			}
			processInput = new InputProcessor(userResponse);
			processInput.splitStringToTokens();
			System.out.println("");
		}
		chatbot.serializeTrie();
		
		
	}
	
	public void serializeTrie(){
		try{
			FileOutputStream fout = new FileOutputStream("/trie.ser");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(trie);
			out.close();
			fout.close();
		}catch(Exception e){
			System.out.println("Exception in serialization \n"+e);
		}
	}
	
	public void deserializeTrie(){
		try{
			FileInputStream fin = new FileInputStream("/trie.ser");
			ObjectInputStream in = new ObjectInputStream(fin);
			trie = (Trie)in.readObject();
			in.close();
			fin.close();
		}catch(Exception e){
			System.out.println("Exception in deserialization \n"+e);
		}
	}
	
}
