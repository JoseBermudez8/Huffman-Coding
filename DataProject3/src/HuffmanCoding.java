import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import ciic4020.sortedlist.*;
import ciic4020.bst.*;
import ciic4020.hashtable.*;

public class HuffmanCoding<K, V> {
	
	public static void main (String[] args) throws FileNotFoundException {
		
		File file = new File("C:\\Users\\pankaj\\Desktop\\test.txt");
		load_data(file);
		
	}
	
	public static String load_data(File file) throws FileNotFoundException {
			    Scanner sc = new Scanner(file); 
			  
			    while (sc.hasNextLine()) 
			     return sc.nextLine();
				return null;
	}
	
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
	
	public String encode(HashTableSC<Integer,String> map, String input) {
		
		
		return null;
	}
	
	public void process_results(Map<K,V> fd, Map<K,V> code, String input, String output) {
		
	}
	
	public BTNode<Integer,String> minFreqRemove(SortedList<BTNode<Integer,String>> SL){
		
		BTNode<Integer,String> min = null;
		
		for (int i = 0; i < SL.size(); i++) {
			if(min.compareTo(SL.get(i))>0) {
				min = SL.get(i);
			}
		}
		return min;
	}
}
