package a2;

class DNAStrandJedi {

	private String strand;
	
	public DNAStrandJedi(String strand) {
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
		
		if (strand == null){
			bad = true;
		} else {
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
		
		/*findPattern method allows the pattern to include the
		following wildcard characters:
		 
		* : matches any of the nucleobases
		a : matches any base *but* A (i.e., C, G, or T)
		c : matches any base but C
		t : matches any base but T
		g : matches any base but G
		*/
		
		
		//This method returns the position of the first occurrence of pattern
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
					for (int j = 0; j < pattLength; j++){
						firstIndex = i; //assume strand starting at i matches
						if (!comparePatt(strand.charAt(i+j),pattern.charAt(j))) {
							firstIndex = -1; //if at any point it doesn't match, change to -1 and break out
							break;
						} else {
							continue;
						}
					}
					
				}
				if (firstIndex != -1){ //if there was a match starting at i
					break;
				} else { //else, add 1 to i and keep looking
					continue;
				}
			}
		}
		return firstIndex;
	}
	
	
	public DNAStrandJedi snip(String startPattern, String endPattern){
		
		/*
		This method first attempts to find the first occurrence of startPattern
		in the strand. If found, then it tries to find the first occurrence of
		endPattern that occurs after the match with startPattern. If found, it
		returns a new DNAStrandJedi object that represents the substrand
		of nucleobases that occurs between the two matches. */
		
		String snip = null;
		
		int startLength = 0;
		//int endLength = 0;
		
		int startIndex = 0;
		int endIndex = getLength(); //initialize endIndex to last index in strand
		
		//If there's a legit start pattern to search for, find its starting index
		if (startPattern != null && !startPattern.equals("")){
			
			startLength = startPattern.length(); //find length of start string
			startIndex = findPattern(startPattern,0); //look for start index of startPattern in strand

			//if we found the startPattern, or if there was no startPattern to search for
			if (startIndex != -1){
				startIndex = startIndex + startLength; //where to start snipped strand
			}
		}
		if (startIndex != -1){ //if startPattern was found, or if there was no startPattern to search for
			//Look for end pattern
			if (endPattern != null && !endPattern.equals("")){
				endIndex = findPattern(endPattern,startIndex); //Look for endPattern starting at beg of snipped strand
			}
		}
		if (startIndex != -1 && endIndex != -1 && endIndex != startIndex){ //if both are found and one is not immediately after other
			snip = strand.substring(startIndex,endIndex);
			DNAStrandJedi snippedStrand = new DNAStrandJedi(snip);
			return snippedStrand;
		} else {
			return null;
		}

	}	
	
	
	//This method compares characters, taking into account wildcards
	private boolean comparePatt(char strandChar, char pattChar){
		switch (pattChar){
		case '*':
			return true;
		case 'a':
			if (strandChar == 'A'){
				return false;
			} else {
				return true;
			}
		case 'c':
			if (strandChar == 'C'){
				return false;
			} else {
				return true;
			}
		case 'g':
			if (strandChar == 'G'){
				return false;
			} else {
				return true;
			}
		case 't':
			if (strandChar == 'T'){
				return false;
			} else {
				return true;
			}
		default:
			if (strandChar == pattChar) {
				return true;
			} else {
				return false;
			}
		}	
		
		
	}

	
}
