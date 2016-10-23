package InputLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Dictionary.Constants;
import ProcessingLayer.Trie;
	
public class ChatBot {

	public static void main(String arg[]){
		
		//Initialising constants
		Constants constants = new Constants();
		Trie trie = new Trie();
		trie.insert("ravi");
		trie.insert("himanshu");
		trie.insert("karan");
		try{
			FileOutputStream fout = new FileOutputStream("./trie.ser");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(trie);
			out.close();
			fout.close();
			System.out.println("Serialized");
		}catch(Exception e){
			System.out.println("Exception while Serialization\n"+e);
		}
		
		Trie t = null;
		try{
			FileInputStream fin = new FileInputStream("./trie.ser");
			ObjectInputStream in = new ObjectInputStream(fin);
			
			t = (Trie) in.readObject();
			in.close();
			fin.close();
			System.out.println("Deserialization complete");
		}catch(Exception e){
			System.out.println("Exception in Deserialization\n"+e);
		}
		
		if(t!=null){
			
			if(t.search("asdasdas")){
				System.out.println("Found");
			}else{
				System.out.println("fuck");
			}
			
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
			processInput = new InputProcessor(userResponse);
			processInput.splitStringToTokens();
			System.out.println("");
		}
		
	}
	
}
