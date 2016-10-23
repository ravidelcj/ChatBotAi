package ProcessingLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class TrieNode implements Serializable{
	
	char letter;
	boolean isEnd;
	int noOfLetters = 0;
	LinkedList<TrieNode> childList;
	ArrayList<Integer> indexList;
	
	public TrieNode(char c){
		childList = new LinkedList<TrieNode>();
		letter = c;
		noOfLetters = 0;
	}
	

	//method to return subtree with the character c as root
	public TrieNode subNode(char c){
		if( childList != null){
			
			for(TrieNode node : childList){
				
				if(node.letter ==  c){
					return node;
				}
				
			}
			
		}
		return null;
	}

}
