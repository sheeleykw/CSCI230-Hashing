package edu.cofc.cs.csci230;

import java.io.File;
import java.util.Scanner;

public class TestHashing {
	
	public static void main(String args[]) {
		
		File file = new File("Assembly Instructions.txt");
		ArrayList<String> input = new ArrayList<String>();
		
		try {
			Scanner inFile = new Scanner(file);
			while (inFile.hasNext()) {
				String nextElement = inFile.next();
				if (!hasElement(input, nextElement)) {
					input.add(nextElement.trim());
				}
			}
				
		}
		catch (Exception e) {
			System.out.println(e);
		}

		ArrayList<String> successfulHash = new ArrayList<String>();
		ArrayList<String> unsuccessfulHash = new ArrayList<String>();
		
		for (int i = 0; i < input.size() / 2; i ++) {
			successfulHash.add(input.get(i));
			unsuccessfulHash.add(input.get(input.size() / 2 + i));
		}

		OpenHashing openHash = new OpenHashing(successfulHash.size());
		ClosedHashing closedHash = new ClosedHashing(successfulHash.size());
		
		for (int i = 0; i < successfulHash.size(); i ++) {
			openHash.add(successfulHash.get(i));
			closedHash.add(successfulHash.get(i));
		}
		
		long startTime;
		long endTime;
		long totalTime;
		
		System.out.println("OpenHash load factor = " + successfulHash.size() + "/" + openHash.getM());
		System.out.println("ClosedHash load factor = " + successfulHash.size() + "/" + closedHash.getM());
		
		System.out.println("\nSuccessful Searches: OpenHashing.");
		totalTime = 0;
		for (int i = 0; i < successfulHash.size(); i ++) {
			startTime = System.nanoTime();
			openHash.search(successfulHash.get(i));
			endTime = System.nanoTime();
			totalTime += endTime - startTime;
			//System.out.println(endTime - startTime);
			
		}
		System.out.println("Average successful searches: " + Math.floorDiv(totalTime, (long)successfulHash.size()));
		totalTime = 0;
		
		System.out.println("\nSuccessful Searches: ClosedHashing.");
		for (int i = 0; i < successfulHash.size(); i ++) {
			startTime = System.nanoTime();
			closedHash.search(successfulHash.get(i));
			endTime = System.nanoTime();
			totalTime += endTime - startTime;
			//System.out.println(endTime - startTime);
		}
		System.out.println("Average successful searches: " + Math.floorDiv(totalTime, (long)successfulHash.size()));
		totalTime = 0;
		
		System.out.println("\nUnsuccessful Searches: OpenHashing.");
		for (int i = 0; i < unsuccessfulHash.size(); i ++) {
			startTime = System.nanoTime();
			openHash.search(unsuccessfulHash.get(i));
			endTime = System.nanoTime();
			totalTime += endTime - startTime;
			//System.out.println(endTime - startTime);
		}
		System.out.println("Average unsuccessful searches: " + Math.floorDiv(totalTime, (long)unsuccessfulHash.size()));
		totalTime = 0;
		
		System.out.println("\nUnsuccessful Searches: ClosedHashing.");
		for (int i = 0; i < unsuccessfulHash.size(); i ++) {
			startTime = System.nanoTime();
			closedHash.search(unsuccessfulHash.get(i));
			endTime = System.nanoTime();
			totalTime += endTime - startTime;
			//System.out.println(endTime - startTime);
		}
		System.out.println("Average unsuccessful searches: " + Math.floorDiv(totalTime, (long)unsuccessfulHash.size()));
		totalTime = 0;
	}
	
	
	private static boolean hasElement(ArrayList<String> array, String element) {
		for (int i = 0; i < array.size(); i ++) {
			if (array.get(i).equalsIgnoreCase(element)) {
				return true;
			}
		}
		return false;
	}
}
