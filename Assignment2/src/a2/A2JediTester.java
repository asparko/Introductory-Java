package a2;

public class A2JediTester {

	public static void main(String[] args) {
		DNAStrandJedi s = new DNAStrandJedi("AACGACGA");

		boolean test_failed = false;

		if ((s.getACount() != 4) || 
				(s.getCCount() != 2) || 
				(s.getGCount() != 2) || 
				(s.getTCount() != 0) ||
				(s.getLength() != 8)) {
			System.out.println("Test 1 failed.");
			test_failed = true;
		}

		s = new DNAStrandJedi("T");

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
			DNAStrandJedi s2 = new DNAStrandJedi("ACGa*T");
		}
		catch (RuntimeException e) {
			error_thrown = true;
		}
		if (!error_thrown) {
			System.out.println("Test 3 failed.");
			test_failed = true;
		}

		s = new DNAStrandJedi("CCCCTAGCTTAGGAGAGTGCCT");

		if ((s.getCount("TAG") != 2) || 
				(s.getCount("GAG") != 2) || 
				(s.getCount("CC") != 4) ||
				(s.getCount(s.getStrandString()) != 1) ||
				(s.getCount("T") != s.getTCount()) ||
				(s.getCount("A") != s.getACount()) ||
				(s.getCount("C") != s.getCCount()) ||
				(s.getCount("G") != s.getGCount())) {
			System.out.println("Test 4 failed.");
			test_failed = true;
		}

		if ((s.findPattern("CCCC", 0) != 0) ||
				(s.findPattern("CCCC",  1) != -1) ||
				(s.findPattern("TAG",  0) != 4) ||
				(s.findPattern("TAG",  4) != 4) ||
				(s.findPattern("TAG", 5) != 9) ||
				(s.findPattern("TGCCT", -3) != s.getLength()-5)) {
			System.out.println("Test 5 failed.");
			test_failed = true;
		}


		if ((s.findPattern("*", -5) != 0) ||
			(s.findPattern("ccctTTgG", 0) != 4) ||
			(s.findPattern("**GAG**", 9) != 10) || 
			(s.findPattern("**GAG**",  11) != 12) ||
			(s.findPattern("Cca*g",  0) != -1)
			) {	
			System.out.println("Test 6 failed.");
			test_failed = true;
		}
		
		if (!(s.snip(null,  null).getStrandString().equals(s.getStrandString()))) {
			System.out.println("Test 7 failed");
			test_failed = true;
		}
		
		if (!s.snip(null,  "TAG").getStrandString().equals("CCCC")) {
			System.out.println("Test 8 failed");
			test_failed = true;
		}
		if (s.snip("TAGG", "TAG") != null) {
			System.out.println("Test 9 failed");
			test_failed = true;
		}

		if (!s.snip("TAG",  "").getStrandString().equals("CTTAGGAGAGTGCCT")) {
			System.out.println("Test 10 failed");
			test_failed = true;
		}
		
		if (!s.snip("AG", "AG").getStrandString().equals("CTT")) {
			System.out.println("Test 11 failed");
			test_failed = true;
		}

		
		if (s.snip("CC", "CC") != null) {
			System.out.println("Test 12 failed");
			test_failed = true;
		}

		if (!test_failed) {
			System.out.println("Test passes");
		}

		if (s.snip("*Ca", "cat").getStrandString().equals("CT")) {
			System.out.println("Extra credit for wild card support in snip()");
		}

	}
}