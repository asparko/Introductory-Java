package a2;

public class A2AdeptTester {

	public static void main(String[] args) {
		DNAStrandAdept s = new DNAStrandAdept("AACGACGA");
	
		boolean test_failed = false;
		
		if ((s.getACount() != 4) || 
			(s.getCCount() != 2) || 
			(s.getGCount() != 2) || 
			(s.getTCount() != 0) ||
			(s.getLength() != 8)) {
			System.out.println("Test 1 failed.");
			test_failed = true;
		}

		s = new DNAStrandAdept("T");
		
		if ((s.getACount() != 0) || 
			(s.getCCount() != 0) || 
			(s.getGCount() != 0) || 
			(s.getTCount() != 1) ||
			(s.getLength() != 1)) {
			System.out.println("Test 2 failed.");
			test_failed = true;
		}

		boolean error_thrown = false;
		try {
			DNAStrandAdept s2 = new DNAStrandAdept("ACGa*T");
		}
		catch (RuntimeException e) {
			error_thrown = true;
		}
		if (!error_thrown) {
			System.out.println("Test 3 failed.");
			test_failed = true;
		}

		s = new DNAStrandAdept("CCCCTAGCTTAGGAGAGTGCCT");
		
		if ((s.getCount("TAG") != 2) || 
			(s.getCount("GAG") != 2) || 
			(s.getCount("CC") != 4) ||
			(s.getCount(s.getStrandString()) != 1) ||
			(s.getCount("T") != s.getTCount()) ||
			(s.getCount("A") != s.getACount()) ||
			(s.getCount("C") != s.getCCount()) ||
			(s.getCount("G") != s.getGCount())) {
			System.out.println("Test 4 failed.");
		}

		if ((s.findPattern("CCCC", 0) != 0) ||
			(s.findPattern("CCCC",  1) != -1) ||
			(s.findPattern("TAG",  0) != 4) ||
			(s.findPattern("TAG",  4) != 4) ||
			(s.findPattern("TAG", 5) != 9) ||
			(s.findPattern("TGCCT", -3) != s.getLength()-5)) {
			System.out.println("Test 5 failed.");
		}
				
		if (!test_failed) {
			System.out.println("Test passes");
		}
		
	}
}