package ProcessingLayer;

import java.io.Serializable;
import java.util.ArrayList;

public class Trie implements Serializable{

	private TrieNode root;
	
	public Trie(){
		root = new TrieNode(' ');
	}
	
	
	/*TODO : ADD THE INDEX TO THE ARRAYLIST OF THE LEAF NODE*/
	public void insert(String word, int index){
		TrieNode current = root;
		
		if(search(word, index)==true){
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
		if(current.indexList == null){
			current.indexList = new ArrayList<>();
		}
		current.indexList.add(index);
	}
	
	//Search whether the word exists in the trie or not
	public boolean search(String word){
		return search(word,-1);
	}
	public boolean search(String word,int index){
		TrieNode current = root;
		
		for(char ch : word.toCharArray()){
			
			if(current.subNode(ch)==null){
				return false;
			}else{
				current=current.subNode(ch);
			}
			
		}
		
		if(current.isEnd == true){
			if(index != -1){
				if(current.indexList==null){
					current.indexList = new ArrayList<Integer>();
				}
				current.indexList.add(index);
			}
			return true;
		}
		return false;
	}
	
	
	//get Array list corresponding to word
	public ArrayList<Integer> getList(String word){
		
		TrieNode current = root;
		
		for(char ch : word.toCharArray()){
			
			if(current.subNode(ch)==null){
				return null;
			}else{
				current = current.subNode(ch);
			}	
		}
		if(current.isEnd == true){
			return current.indexList;
		}	
		return null;
	}
}
