import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ciic4020.sortedlist.*;
import ciic4020.bst.*;
import ciic4020.hashtable.*;
import ciic4020.list.List;

public class HuffmanCoding<K, V> {
	
	/* The main method will use other methods provided to form said Huffman tree. 
	 * Firstly we load the selected string file using the load data method. We then input said file into the
	 * compute_fd method that will calculate the frequency distribution of the individual characters in
	 * the loaded string. A HashTable will be constructed for frequency distribution and this HashTable will be taken
	 * to create a Huffman tree through the huffman_tree method. After constructing the tree we will return 
	 * a mapping of every symbol to its corresponding Huffman code. After encoding the input string
	 * by using the code Hashtable we use all these in process_results which will print into the console the results 
	 * of the huffman encoding onto the inputed string along with the frequency distribution Hashtable,
	 * the huffman code hashtable. 
	 */
	public static void main (String[] args) throws FileNotFoundException {
	
		
		File file = new File("inputData/stringData.txt");
		String load = load_data(file);
		
		HashTableSC<String, Integer> fd = compute_fd(load);
		HashTableSC<String, String> code = huffman_code(huffman_tree(fd));
		String output = encode(code, load);
		process_results(fd, code, load, output);
		
	}
	/*By inputing a file by using a directory the method scans the text file to create a string 
	* and looks over the rest of the file to continue returning strings. 
	*/
	public static String load_data(File file) throws FileNotFoundException {
			    Scanner sc = new Scanner(file); 
			  
			    while (sc.hasNextLine()) 
			     return sc.nextLine();
				return null;
	}
	/* This method will receive a string use a loop to add into the created map each element
	 * while also reporting the frequency of any aforementioned symbol as it continues it's search.
	 * We create a string variable to use more efficiently and equal it to the singular selected string/symbol
	 * currently targeted in the loop. We use the containsKey method to check whether the selected symbol is already
	 * the map and if so add to its frequency count, overwriting what was by using the put method and adding from there.
	 * If the element is not found in the map we simply add it into the map with a count of 1. The code here was 
	 * largely inspired by the compute_fd in project 2's MapFD.
	 */
	@SuppressWarnings("rawtypes")
	public static HashTableSC<String,Integer> compute_fd(String string){
		@SuppressWarnings("unchecked")
		HashTableSC<String,Integer> map = 
				new HashTableSC<String,Integer>(11, new SimpleHashFunction());
		
	for (int i = 0; i < string.length(); i++) {
			String e = string.substring(i, i+1);
			if(map.containsKey(string.substring(i, i+1))) {
				map.put(e, map.get(e) + 1);
			}
			else {
				map.put(e, 1);
				}
			}
		return map;
	}
	/*By receiving the frequency distribution hashtable this method will create the huffman tree used to encode
	 * our string. We create a sorted list which will help us find the minimum values easily. Using a loop we add
	 * BTNodes into the sorted list which are nodes of our binary tree that will use the frequency as the key 
	 * and the symbol(s) as their value. After setting and adding them into SL we begin creating the tree. We create
	 * a new blank node and two other notes with the two lowest values in our sorted list. We use a auxiliary method
	 *  called minFreqRemove to search for these and when found remove them from the sorted list. We use these two 
	 *  values to set ts children and by combining their key values, the key. For the blank value we add the two 
	 *  variable node values together and once done add the no longer blank node into the list again. 
	 */
	public static BTNode<Integer, String> huffman_tree(HashTableSC<String, Integer> fd) {
	SortedList<BTNode<Integer,String>> SL = new SortedArrayList<BTNode<Integer,String>>(fd.size());
	List<String> list = fd.getKeys();
	for (int i = 0; i < fd.size(); i++) {
		BTNode<Integer,String> s = new BTNode<Integer,String>();
		s.setKey(fd.get(list.get(i)));
		s.setValue(list.get(i));
		SL.add(s);
	}
	
	
	for (int i = 0; i < fd.size()-1; i++) {
		BTNode<Integer,String> N = new BTNode<Integer,String>();
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
	/*
	 * By using a huffman tree this method will create a hashtable with a mapping of every symbol to their
	 * corresponding huffman code. First we check if root is null which would stop the method, and if that condition
	 * is not meant than the auxiliary method seek will be used with the parameters of the received root, the created 
	 * hashtable and an empty string. 
	 */
	public static HashTableSC<String,String> huffman_code(BTNode<Integer, String> root){
		HashTableSC<String,String> append = new HashTableSC<String,String>(11, new SimpleHashFunction<String>());
	
		if(root==null) return append;
		
		else {
		seek(root, append, "");
		}
		return append;
	}
	/*
	 * The seek method will traverse the tree and by inspecting the children will
	 * create the codes for the elements included. We use recursion and begin with the stop condition which would 
	 * be the lack of children, if not met we re-call the method onto its children and depending on whether we travel
	 * left or right we add 0 or 1 to the string which represents the symbol's code. 
	 */
	public static void seek(BTNode<Integer, String> root, HashTableSC<String,String> append, String code) {
		
		if(root.getLeftChild()==null && root.getRightChild()==null) append.put(root.getValue(), code);
		
		else {	
		seek(root.getLeftChild(), append, code+0);
		seek(root.getRightChild(), append, code+1);
		}
	}
	/*
	 * Here we received the loaded string along with a huffman code map which should include every unique symbol
	 * within the inputed string along with their unique binary code. With these we will create a new and 
	 * better compressed version of the original string. First we create an empty string. Then we cycle through the 
	 * inputed string and as we do that we search for the binary code associated with the individual character and we
	 * add the code to our empty string, which will fill up as the loop iterates over every symbol in the input and
	 * results in our completely encoded output. 
	 */
	public static String encode(HashTableSC<String,String> map, String input) {
		String output="";
		for (int i = 0; i < input.length(); i++) {
			output = output + map.get(input.substring(i, i+1));
		}
		return output;
	}
	
	
	/*
	 * This method serves to demonstrate the fruits of the method's labor and receives a frequency distribution map,
	 * a huffman code map, the inputed string and its encoded output. This method largely only function to print these
	 * contents into the console as long as some necessary statics stating the benefits of having encoded the original
	 * string this way. We first create a list composed of all the keys in the frequency distribution list. 
	 * We then iterate through the frequency map and print every unique symbol from the input string along with
	 * their frequency within the string and the huffman code of each symbol. After this we demonstrate the
	 * inputed string and the encoded result for comparison. We then seek the amount of bytes within both codes
	 * and after demonstrating how many bytes both string include we present the amount of space saved between
	 * both version, putting into words the improvement in huffman encoding a string for the sake of space. 
	 */
	public static void process_results(HashTableSC<String, Integer> fd,HashTableSC<String, String> code, String input, String output) {
		List<String> list = fd.getKeys();
		SortedList<BTNode<Integer,String>> sort = new SortedArrayList<BTNode<Integer,String>>(11);
		for(String key : fd.getKeys()) {
			BTNode<Integer, String> symbol = new BTNode<Integer,String>(fd.get(key),key);
			sort.add(symbol);
		}
		System.out.println("Symbol: 	Frequency: 	Code:");
		for (int i = fd.size()-1; i >= 0; i--) {
			System.out.println(sort.get(i).getValue() +"		" 
		+fd.get(sort.get(i).getValue()) +"		" +code.get(sort.get(i).getValue()));
		}
		System.out.println("---------------------");
		System.out.println("Input string: " +input);
		System.out.println("---------------------");
		System.out.println("Output string: " +output);
		System.out.println("---------------------");
		System.out.println("The original string requires " +input.length() +" bytes.");
		double out = Math.ceil(output.length()/8.0); 
		System.out.println("The encoded string requires " +out +" bytes.");
		double save = (out/input.length())*100;
		System.out.println("Difference in space required is " +(100-save)+"%");
	}
	/*
	 * This is the auxiliary method used in huffman tree creator which serves to find
	 * the smallest member in the sorted list which is as easy as accessing the first element.
	 * By equaling it to a variable we are able to delete the smallest member while 
	 * retaining its value and return it for use in the huffman tree creator. 
	 */
	public static BTNode<Integer,String> minFreqRemove(SortedList<BTNode<Integer,String>> SL){
		
		BTNode<Integer,String> min = SL.get(0);
		SL.removeIndex(0);
		return min;
	}
}
