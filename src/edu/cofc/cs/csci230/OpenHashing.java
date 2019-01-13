package edu.cofc.cs.csci230;

public class OpenHashing {
	
	private ArrayList<String>[] array;
	private int size;
	
	public OpenHashing(int size) {
		this.size = size;
		array = new ArrayList[this.size];
		for (int i = 0; i < this.size; i ++) {
			array[i] = new ArrayList<String>();
		}
	}
	
	public void add(String key) {
		if (!search(key)) {
			int hashVal = hashFunction(key);
			ArrayList<String> elementArray = array[hashVal];
			elementArray.add(key);
		}
	}
	
	public void delete(String key) {
		if (search(key)) {
			int hashVal = hashFunction(key);
			ArrayList<String> elementArray = array[hashVal];
			elementArray.remove(getIndex(elementArray, key));
		}
	}
	public boolean search(String key) {
		int hashVal = hashFunction(key);
		ArrayList<String> elementArray = array[hashVal];
		for (int i = 0; i < elementArray.size(); i ++) {
			if (elementArray.get(i).equalsIgnoreCase(key)) {
				return true;
			}
		}
		return false;
	}
	
	public int getM() {
		int rtnVal = 0;
		for (int i = 0; i < array.length; i ++) {
			if (!array[i].isEmpty()) {
				rtnVal ++;
			}
		}
		return rtnVal;
	}
	private int getIndex(ArrayList<String> elementArray, String key) {
		for (int i = 0; i < elementArray.size(); i ++) {
			if (elementArray.get(i).equalsIgnoreCase(key)) {
				return i;
			}
		}
		return -1;
	}
	
	private int hashFunction(String key) {
		
		char[] charArray = key.toCharArray();
		int rtnVal = 0;
		
		for (int i = 0; i < key.length(); i ++) {
			rtnVal += charArray[i];
		}
		
		return rtnVal % size;
	}
}