package a1;

import java.util.Scanner;

public class A1Jedi {

public static void main(String[] args) {
		
		//Save input into variable s
		Scanner s = new Scanner(System.in);
		
		//Run method to process input
		process(s);
	}
	
	public static void process(Scanner s) {
		
		//Put number of search patterns into var N
		int N = s.nextInt();
		
		//Make array of search patterns with N entries
		String[] patterns = new String[N];
		
		//Make array of pattern counts with N entries
		int[] pattCounts = new int[N];
		
		//Fill pattern search array with next N entries of s
		for (int i=0; i<N; i++){
			patterns[i]=s.next();
		}
		
		//Put first user strand entry into variable strand
		String strand = s.next();
		
		//Loop on each strand, so long as it is not "end"
		while (!strand.equals("end")) {
			
			//Loop through each letter of current strand to look for search string
			for (int i=0; i<strand.length(); i++){
				
				//Search for jth pattern in pattern array
				for(int j=0; j<N; j++){
					
					//Length of jth search pattern
					int pattLength = patterns[j].length();
					
					//If there are at least pattLength characters left in strand
					if (strand.substring(i).length() >= pattLength){
						
						//If find jth pattern, add one to pattCounts[j]
						if (strand.substring(i,i+pattLength).equals(patterns[j])){
							pattCounts[j]++;
						}
					}
					
				}			
			
			}
			//proceed to next strand
			strand = s.next(); 
		}
		
		//Close scanner
		s.close();
		
		//Loop through pattern array to print each count
		for (int i=0; i<patterns.length; i++){
			System.out.println("Pattern: " + patterns[i] + " Count: " + pattCounts[i]);
		}
	}
}
