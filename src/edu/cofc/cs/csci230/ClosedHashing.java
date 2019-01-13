package edu.cofc.cs.csci230;

public class ClosedHashing {
	
	private String[] array;
	private int size;
	
	public ClosedHashing(int size) {
		this.size = size * 2;
		array = new String[this.size];
	}
	
	public void add(String key) {
		if (!search(key)) {
			int hashVal = hashFunction(key);
			String element = array[hashVal];
			boolean hasRoom = true;
			int i = hashVal;
			while (element != null) {
				i ++;
				element = array[i];
				if (i == hashVal) {
					hasRoom = false;
					break;
				}
				if (i >= size) {
					i = 0;
				}
			}
			if (hasRoom) {
				array[i] = key;
			}
		}
	}
	
	public void delete(String key) {
		
	}
	
	public boolean search(String key) {
		int hashVal = hashFunction(key);
		String element = array[hashVal];
		int i = hashVal;
		while (element != null) {
			if (element.equalsIgnoreCase(key)) {
				return true;
			}
			i ++;
			element = array[i];
			if (i >= size) {
				i = 0;
			}
			
		}
		return false;
	}
	
	public int getM() {
		return array.length;
	}
	private int hashFunction(String key) {
		
		char[] charArray = key.toCharArray();
		int rtnVal = 0;
		
		for (int i = 0; i < key.length(); i ++) {
			rtnVal += charArray[i];
		}
		
		return (rtnVal * charArray[0]) % size;
	}
}

