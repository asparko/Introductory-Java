package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		
		//Save input into variable s
		Scanner s = new Scanner(System.in);
		
		//Run method to count letters
		process(s);
	}
	
	public static void process(Scanner s) {
		
		//Put first user entry into variable dna
		String strand = s.next();
		
		//Initialize counts
		int aCount=0;
		int cCount=0;
		int gCount=0;
		int tCount=0;
		
		//Loop on each strand, so long as it is not "end"
		while (!strand.equals("end")) {
			
			//Loop through each character in the current strand
			for (int i=0; i<strand.length(); i++) {
				
				//Switch on possible characters to add to counts
				switch (strand.charAt(i)) {
					case 'A': 
						aCount++;
						break;
					case 'C':
						cCount++;
						break;
					case 'G':
						gCount++;
						break;
					case 'T':
						tCount++;
						break;
						
					//Error if anything other than A,C,G,T entered	
					default: System.out.println("Invalid Character!");
						break;
				}
				
			}
			strand = s.next(); //Go to next strand and start again
		}
		
		//Close scanner
		
		s.close();
		
		//Display counts
		System.out.println("A count: "+ aCount);
		System.out.println("C count: "+ cCount);
		System.out.println("G count: "+ gCount);
		System.out.println("T count: "+ tCount);
		
    }
	
}
