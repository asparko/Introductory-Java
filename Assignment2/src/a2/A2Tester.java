package a2;


public class A2Tester {

	public static void main(String[] args) {
	
		String strand = "CCCCTAGCTTAGGAGAGTGCCT";
		String pattern = "*";
	
		
///////////////////// TESTING NOVICE
		/*
		DNAStrandNovice myStrand = new DNAStrandNovice(strand);
		
		System.out.println("Strand used: " + myStrand.getStrandString());
		System.out.println("Length of Strand: " + myStrand.getLength());
		System.out.println("Number of A's: " + myStrand.getACount());
		System.out.println("Number of C's: " + myStrand.getCCount());
		System.out.println("Number of G's: " + myStrand.getGCount());
		System.out.println("Number of T's: " + myStrand.getTCount());
		
///////////////////// TESTING ADEPT		
		DNAStrandAdept myStrand2 = new DNAStrandAdept(strand);
		
		System.out.println("Pattern " + pattern + 
				" appears this many times: " + myStrand2.getCount(pattern));
		
		System.out.println(myStrand2.findPattern(pattern, 1));
		*/
///////////////////// TESTING JEDI
		DNAStrandJedi myStrand3 = new DNAStrandJedi(strand);
		DNAStrandJedi snipped = myStrand3.snip("","CC");
		if (snipped != null){
			System.out.println(snipped.getStrandString());
		}else{
			System.out.println("No Such Snip");
		}
		System.out.println("Strand used: " + myStrand3.getStrandString());
		
		System.out.println(myStrand3.findPattern("TAG",-3));

	}

	
	
}
