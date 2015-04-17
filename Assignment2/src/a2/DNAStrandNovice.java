package a2;

class DNAStrandNovice {

	private String strand;
	
	public DNAStrandNovice(String strand) {
		this.strand = strand;
		
		if (checkStrand()){
			throw new RuntimeException("Illegal DNA Strand");
		}
	}
	
	public int getACount(){
		
		//Initialize count
		int aCount=0;
		
		//Loop through each character in the strand
		for (int i=0; i<strand.length(); i++) {
			if (strand.charAt(i) == 'A'){
				aCount ++;
			}
		}
		return aCount;
	}
				
	public int getCCount(){
		
		//Initialize count
		int cCount=0;
		
		//Loop through each character in the strand
		for (int i=0; i<strand.length(); i++) {
			if (strand.charAt(i) == 'C'){
				cCount ++;
			}
		}
		return cCount;
	}
	public int getGCount(){
		
		//Initialize count
		int gCount=0;
		
		//Loop through each character in the strand
		for (int i=0; i<strand.length(); i++) {
			if (strand.charAt(i) == 'G'){
				gCount ++;
			}
		}
		return gCount;
		
	}
	public int getTCount(){
		
		//Initialize count
		int tCount=0;
		
		//Loop through each character in the strand
		for (int i=0; i<strand.length(); i++) {
			if (strand.charAt(i) == 'T'){
				tCount ++;
			}
		}
		return tCount;
	}
	
	public String getStrandString(){
		return strand;
	}
	
	public int getLength(){
		return strand.length();
	} 
	
	private boolean checkStrand(){
		//check characters in strand
		boolean bad = false;
		
		for(int i=0; i<getLength();i++){
			if (strand.charAt(i)!='A' && 
					strand.charAt(i) != 'C' &&
					strand.charAt(i) != 'G' &&
					strand.charAt(i)!= 'T'){
				bad = true;
				break;
			}
		}
		
		return bad;
	}

	

}
