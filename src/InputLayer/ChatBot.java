package InputLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
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
	
	/*function to be called to reset the trie, database 
	 * and train the bot from start
	 */
	public void resetHard(){
		//delete all the rows from the table
		answerDatabase.truncateTable();
		
		//delete trie.ser file
		try{
    		File file = new File("./trie.ser");
    		if(file.delete()){
    			//System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	}catch(Exception e){
             System.out.println("Exception while deleting train.ser in resetHard" + e);
    	}
		
		/*
		 * Train the bot after reset using previousdata.txt file 
		 */
		String ans;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("./previousdata.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fin, Charset.forName("UTF-8")));
			while((ans = br.readLine()) != null){			
				//System.out.println(ans);
				int index = answerDatabase.addAnswer(ans.trim());
				while(!(ans = br.readLine()).equals("/")){
					trie.insert(ans.trim(), index);
					//System.out.println(ans);
				}	
			}
			br.close();
			fin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in resetHard : " + e);
		}
	}
	
	/*
	 * Function to be called to append new knowledge in the program
	 */
	public void trainBot(){
		
		String ans;
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream("./train.txt");
			fout = new FileOutputStream("./previousdata.txt", true);
			BufferedReader br = new BufferedReader(new InputStreamReader(fin, Charset.forName("UTF-8")));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout, Charset.forName("UTF-8")));
			while((ans = br.readLine()) != null){			
				//System.out.println(ans);
				bw.append(ans + "\n");
				int index = answerDatabase.addAnswer(ans);
				while(!(ans = br.readLine()).equals("/")){
					trie.insert(ans, index);
					//System.out.println(ans);
					bw.append(ans + "\n");
				}	
				bw.append("/\n");
			}
			br.close();
			bw.close();
			fin.close();
			fout.close();
			new FileWriter("./train.txt").write("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in trainBot : " + e);
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
			
			//botResponse = "Sorry!! Please be more Clear ";
			
			//User Response
			System.out.print("> ");
			userResponse = scan.nextLine();
			userResponse.trim();
			if(userResponse.equals("TrainBot")){
				System.out.println("> Bot is in training mode \n");
				chatbot.trainBot();
				chatbot.serializeTrie();
				continue;
			}else if(userResponse.equals("HardReset")){
				chatbot.resetHard();
				chatbot.serializeTrie();
				botResponse="Hi There i am Chat Bot.\n Please feel free to ask me any query :)";
				continue;
			}else if(userResponse.equals("end")){
				break;
			}
			processInput = new InputProcessor(userResponse);
			processInput.splitStringToTokens();
			
			try{

				int index = processInput.getIndexOfAnswer(chatbot.trie);
				
			botResponse=chatbot.answerDatabase.getAnswer(index);
			}catch(Exception e){
				botResponse = "Sorry i could not understand";
			}
			
			System.out.println("");
		}	
		
	}
	
	public void serializeTrie(){
		try{
			FileOutputStream fout = new FileOutputStream("./trie.ser");
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
			FileInputStream fin = new FileInputStream("./trie.ser");
			ObjectInputStream in = new ObjectInputStream(fin);
			trie = (Trie)in.readObject();
			in.close();
			fin.close();
		}catch(Exception e){
			System.out.println("Exception in deserialization \n"+e);
		}
	}
	
}
