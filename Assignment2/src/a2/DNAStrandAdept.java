package a2;

class DNAStrandAdept {

	private String strand;
	
	public DNAStrandAdept(String strand) {
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
		
		//loop through strand
		for(int i=0; i<getLength(); i++){
			
			//if any chars are not valid, set bad to true and leave loop
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

	public int getCount(String pattern){

		//Returns number of times a pattern appears in the strand
		
		int pattCount = 0;
		int pattLength = pattern.length();
		
		//Loop through strand
		for (int i=0; i<getLength(); i++){
			
			//If there are at least pattLength characters left in strand
			if (strand.substring(i).length() >= pattLength){
				
				//If find pattern, add one to pattCount
				if (strand.substring(i,i+pattLength).equals(pattern)){
					pattCount++;
				}
			}
		}
		return pattCount;
	}
		
	
	public int findPattern(String pattern, int startIndex){
		
		//This method returns the position of the first occurence of pattern
		//in the strand starting at position startIndex. Returns -1 if pattern is 
		//not found. 

		int firstIndex = -1;
		int pattLength = pattern.length();
		
		
		//Set to look from beginning of strand if startIndex < 0
		if (startIndex < 0) {
			startIndex = 0;
		}
		
		//If startIndex is within strand length
		if (startIndex < getLength()){
			
			//Loop through strand starting at startIndex
			for (int i = startIndex; i < getLength(); i++){
				
				//If there are at least pattLength characters left in strand
				if (strand.substring(i).length() >= pattLength){
					
					//If find pattern, note the index and leave loop
					if (strand.substring(i,i+pattLength).equals(pattern)){
						firstIndex = i;
						break;
					}
				}
			}
		}
		return firstIndex;
	}
	
}
