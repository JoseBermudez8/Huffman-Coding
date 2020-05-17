package ciic4020.bst;

/**
 * This class represents a binary tree node with key, value,
 * left child, right child, and parent.
 * @author Juan O. Lopez
 *
 * @param <K> Generic type for the key of the values to be stored in the nodes
 * @param <V> Generic type for the values to be stored in the nodes
 */
@SuppressWarnings("rawtypes")
public class BTNode<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<BTNode<K,V>>{
	private K key;
	private V value;
	private BTNode<K, V> leftChild, rightChild, parent;

	public BTNode() {
		this(null, null, null);
	}
	public BTNode(K key, V value) {
		this(key, value, null);
	}

	public BTNode(K key, V value, BTNode<K, V> parent) {
		this.key = key;
		this.value = value;
		this.parent = parent;
		this.leftChild = this.rightChild = null;
	}

	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public BTNode<K, V> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BTNode<K, V> leftChild) {
		this.leftChild = leftChild;
	}
	public BTNode<K, V> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BTNode<K, V> rightChild) {
		this.rightChild = rightChild;
	}
	public BTNode<K, V> getParent() {
		return parent;
	}
	public void setParent(BTNode<K, V> newParent) {
		parent = newParent;
	}
	public void clear() {
		key = null;
		value = null;
		leftChild = rightChild = parent = null;
	}

	@Override
	public int compareTo(BTNode<K, V> key) {
		// TODO Auto-generated method stub
		 int result=0;
	        if(this.getKey().compareTo(key.getKey())>0)
	        {
	            result=1;
	        }else if(this.getKey().compareTo(key.getKey())<0){
	            result=-1;
	        }else if(this.getKey()==key)
	        {
	            result=0;
	        }
	        return result;
	}
}