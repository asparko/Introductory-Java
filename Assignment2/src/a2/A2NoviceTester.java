package a2;

public class A2NoviceTester {

	public static void main(String[] args) {
		DNAStrandNovice s = new DNAStrandNovice("ACGACGA");
	
		boolean test_failed = false;
		
		if ((s.getACount() != 3) || 
			(s.getCCount() != 2) || 
			(s.getGCount() != 2) || 
			(s.getTCount() != 0) ||
			(s.getLength() != 7)) {
			System.out.println("Test 1 failed.");
			test_failed = true;
		}

		s = new DNAStrandNovice("T");
		
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
			DNAStrandNovice s2 = new DNAStrandNovice("ACGa*T");
		}
		catch (RuntimeException e) {
			error_thrown = true;
		}
		if (!error_thrown) {
			System.out.println("Test 3 failed.");
			test_failed = true;
		}

		if (!test_failed) {
			System.out.println("Test passes");
		}
		
	}
}