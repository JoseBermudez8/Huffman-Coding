import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import ciic4020.sortedlist.*;
import ciic4020.bst.*;
import ciic4020.hashtable.*;

public class HuffmanCoding<K, V> {
	
	public static void main (String[] args) throws FileNotFoundException {
	
		
		File file = new File("C:\\Users\\sirsh\\git\\DataProject3\\DataProject3\\stringData.txt");
		String load = load_data(file);
		
		//HashTableSC<Integer, String> fd = compute_fd(load);
		//HashTableSC<Integer, String> code = huffman_code(huffman_tree(fd));
		//String output = encode(code, load);
		//process_results(fd, code, load, output);
		
	}
	//Receives the string to be encoded
	public static String load_data(File file) throws FileNotFoundException {
			    Scanner sc = new Scanner(file); 
			  
			    while (sc.hasNextLine()) 
			     return sc.nextLine();
				return null;
	}
	//Computes the frequency of a character in the string
	public HashTableSC<Integer,String> compute_fd(String string){
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	HashTableSC<Integer, String> fd = new HashTableSC<>(11, new SimpleHashFunction());
		
	for (int i = 0; i < string.length(); i++) {
			for (int j = i+1; j < string.length(); j++) {
				int freq = 0;
				if(string.charAt(i)==string.charAt(j)) {
					freq++;
					fd.put(freq, string.substring(i, i+1));
				}
			}
		}
		return fd;
	}
	
	@SuppressWarnings("null")
	public BTNode<Integer, String> huffman_tree(HashTableSC<Integer,String> map) {
	
	SortedList<BTNode<Integer,String>> SL = new SortedArrayList<BTNode<Integer,String>>(map.size());
	
	for (int i = 0; i < map.size(); i++) {
		BTNode<Integer,String> N = null;
		BTNode<Integer, String> x = minFreqRemove(SL);
		BTNode<Integer, String> y = minFreqRemove(SL);
		N.setLeftChild(x);
		N.setRightChild(y);
		N.setKey(x.getKey() + y.getKey()); 
		N.setValue(x.getValue() + y.getValue());
		SL.add(N);
		}
	
		return minFreqRemove(SL);
	}
	//Attaches a string along to its code in a map
	public HashTableSC<Integer,String> huffman_code(BTNode<Integer, String> root){
		HashTableSC<Integer,String> appendix = new HashTableSC<Integer,String>(11, new SimpleHashFunction<Integer>());
	
		if(root==null) return appendix;
		
		else {
		huffman_code(root.getRightChild());
		huffman_code(root.getLeftChild());
		appendix.put(root.getKey(), seek(root, root.getValue()));
		}
		return appendix;
	}
	//Traverses through huffman tree to get the code of the selected string
	public String seek(BTNode<Integer, String> root, String string) {
		
		String word="";
		if(root.getLeftChild().getValue().equals(string)) return word+0;
		
		if(root.getValue().equals(string)) return word;
		
		else {seek(root.getRightChild(), string);
		word = word+"1";
		}
		
		return word;
	}
	
	public String encode(HashTableSC<Integer,String> map, String input) {
		
		return input;
	}
	
	
	
	public void process_results(Map<K,V> fd, Map<K,V> code, String input, String output) {
		
	}
	
	public BTNode<Integer,String> minFreqRemove(SortedList<BTNode<Integer,String>> SL){
		
		BTNode<Integer,String> min = null;
		if(!SL.isEmpty()) {
		for (int i = 0; i < SL.size(); i++) {
			if(min.compareTo(SL.get(i))>0) {
				min = SL.get(i);
				SL.remove(min);
			}
		}
		return min;
		}
		else return null;
	}
}
