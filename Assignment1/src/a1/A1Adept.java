package a1;

import java.util.Scanner;

public class A1Adept {
	
	public static void main(String[] args) {
		
		//Save input into variable s
		Scanner s = new Scanner(System.in);
		
		//Run method to count letters
		process(s);
	}
	
	public static void process(Scanner s) {
		
		//Put first user entry into variable strand
		String strand = s.next();
		
		//Initialize counts
		int aCount=0;
		int cCount=0;
		int gCount=0;
		int tCount=0;
		
		//Initialize high and low char counts
		int lowACount = 0;
		int highACount = 0;
		int lowCCount = 0;
		int highCCount = 0;
		int lowGCount = 0;
		int highGCount = 0;
		int lowTCount = 0;
		int highTCount = 0;
		
		//Initialize high and low strands to first input
		String lowA = strand;
		String highA = strand;
		String lowC = strand;
		String highC = strand;
		String lowG = strand;
		String highG = strand;
		String lowT = strand;
		String highT = strand;
		
		/*
		//Initialize if a given char has been found yet
		boolean aFound = false;
		boolean cFound = false;
		boolean gFound = false;
		boolean tFound = false;
		*/
		
		//Loop on each strand, so long as it is not "end"
		while (!strand.equals("end")) {
			
			//Initialize local strand counts
			int strandA =0;
			int strandC =0;
			int strandG =0;
			int strandT =0;
			
			//Loop through each character in the current strand
			for (int i=0; i<strand.length(); i++) {
			
				//Switch on possible characters to add to counts
				switch (strand.charAt(i)) {
					case 'A': 
						strandA++;
						break;
					case 'C':
						strandC++;
						break;
					case 'G':
						strandG++;
						break;
					case 'T':
						strandT++;
						break;
						
					//Error if anything other than A,C,G,T entered	
					default: System.out.println("Invalid Character!");
						break;
				}	
				
			}
			
			//Add strand char counts to total counts
			aCount += strandA;
			cCount += strandC;
			gCount += strandG;
			tCount += strandT;
			
			//Replace high/low strings and counts if needed
			
			if(strandA <= lowACount){
				lowA = strand;
				lowACount = strandA;
			} if (strandA >= highACount) {
				highA = strand;
				highACount = strandA;
			}
			
			if(strandC <= lowCCount){
				lowC = strand;
				lowCCount = strandC;
			} if (strandC >= highCCount) {
				highC = strand;
				highCCount = strandC;
			}
			
			if(strandG <= lowGCount){
				lowG = strand;
				lowGCount = strandG;
			} if (strandG >= highGCount) {
				highG = strand;
				highGCount = strandG;
			}
			
			if(strandT <= lowTCount){
				lowT = strand;
				lowTCount = strandT;
			} if (strandT >= highTCount) {
				highT = strand;
				highTCount = strandT;
			}
				
			/*
			//Replace high/low strings and counts if needed
			if (strandA > 0) { //if there are A's in the current strand
				if (aFound == false) { //if you haven't found A's yet
					lowA = strand;
					highA = strand;
					lowACount = strandA;
					highACount = strandA;
					aFound = true;
				} else { //if you have already found A's
					if (strandA < lowACount) { //if this strand has fewer A's than previous ones
						lowA = strand;
						lowACount = strandA;
					} if (strandA > highACount) {
						highA = strand;
						highACount = strandA;
					}
				}
			}
			if (strandC > 0) { //if there are C's in the current strand
				if (cFound == false) { //if you haven't found C's yet
					lowC = strand;
					highC = strand;
					lowCCount = strandC;
					highCCount = strandC;
					cFound = true;
				} else { //if you have already found C's
					if (strandC < lowCCount) { //if this strand has fewer C's than others
						lowC = strand;
						lowCCount = strandC;
					} if (strandC > highCCount) {
						highC = strand;
						highCCount = strandC;
					}
				}
			}
			if (strandG > 0) { //if there are G's in the current strand
				if (gFound == false) { //if you haven't found G's yet
					lowG = strand;
					highG = strand;
					lowGCount = strandG;
					highGCount = strandG;
					gFound = true;
				} else { //if you have already found G's
					if (strandG < lowGCount) { //if this strand has fewer G's than others
						lowG = strand;
						lowGCount = strandG;
					} if (strandG > highGCount) {
						highG = strand;
						highGCount = strandG;
					}
				}
			}
			if (strandT > 0) { //if there are T's in the current strand
				if (tFound == false) { //if you haven't found T's yet
					lowT = strand;
					highT = strand;
					lowTCount = strandT;
					highTCount = strandT;
					tFound = true;
				} else { //if you have already found T's
					if (strandT < lowTCount) { //if this strand has fewer T's than others
						lowT = strand;
						lowTCount = strandT;
					} if (strandT > highTCount) {
						highT = strand;
						highTCount = strandT;
					}
				}
			}
			*/
			
			strand = s.next(); //Go to next strand and start again
		}
		
		//Close scanner
		s.close();
		
		//Display counts
		System.out.println("A count: "+ aCount);
		System.out.println("C count: "+ cCount);
		System.out.println("G count: "+ gCount);
		System.out.println("T count: "+ tCount);
		
		System.out.println("Low A count: "+ lowA);
		System.out.println("High A count: "+ highA);
		System.out.println("Low C count: "+ lowC);
		System.out.println("High C count: "+ highC);
		System.out.println("Low G count: "+ lowG);
		System.out.println("High G count: "+ highG);
		System.out.println("Low T count: "+ lowT);
		System.out.println("High T count: "+ highT);
		
    }

}
