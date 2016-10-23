package ProcessingLayer;

import java.io.Serializable;

public class Trie implements Serializable{

	private TrieNode root;
	
	public Trie(){
		root = new TrieNode(' ');
	}
	
	public void insert(String word){
		TrieNode current = root;
		
		if(search(word)==true){
			return;
		}else{
			
			for(char ch : word.toCharArray()){
				
				TrieNode child = current.subNode(ch);
				if(child != null){
					current = child;
				}else{
					current.childList.add(new TrieNode(ch));
					current = current.subNode(ch);
					
				}
				current.noOfLetters++;
			}
			
		}
		current.isEnd = true;
	}
	
	//Search whether the word exists in the trie or not
	public boolean search(String word){
		TrieNode current = root;
		
		for(char ch : word.toCharArray()){
			
			if(current.subNode(ch)==null){
				return false;
			}else{
				current=current.subNode(ch);
			}
			
		}
		
		if(current.isEnd == true){
			return true;
		}
		return false;
	}
	
}
