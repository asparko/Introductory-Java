package a3jedi;

import java.util.Iterator;

public class ZigZagIterator implements Iterator<Pixel> {
	
	private Frame frame;
	private int cur_row;
	private int cur_col;
	
	public ZigZagIterator(Frame frame){
		this.frame=frame;
	}
	
	public boolean hasNext() {
		if (cur_row < frame.getHeight() && cur_col < frame.getWidth()){
			return true;
		} else {
			return false;
		}
	}

	public Pixel next() {
		if (hasNext()){
			Pixel p = frame.getPixel(cur_col, cur_row);
			if(cur_row == 0 && (cur_col + cur_row) % 2 == 0){ //if in top row and cur_col+cur_row = even number
				if(cur_col < frame.getWidth()-1){ //if there are columns remaining
					cur_col++; //move to the right
				}else{
					cur_row++; //else move down
				}
			} else if (cur_col == 0 && (cur_col + cur_row) % 2 != 0){ //if on leftmost side and cur_col+cur_row = odd number
				if(cur_row < frame.getHeight()-1){ //if there are rows remaining
					cur_row++; //move down
				}else{
					cur_col++; //else move right
				}
			} else if ((cur_col + cur_row) % 2 == 0){ //if cur_col + cur_row = even
				if (cur_col < frame.getWidth()-1){ //if there are cols remaining
					cur_col++;
					cur_row--; //move diagonally up					
				} else {
					cur_row++; //else move down
				}
			} else { //if cur_col + cur_row = odd
				if (cur_row < frame.getHeight()-1){ //if there are rows remaining
					cur_row++;
					cur_col--; //move diagonally down
				} else {
					cur_col++; //else move right
				}
			}
			return p;
		} else{
			return null;
		}
	}

	//to test where in frame the iterator is (for debugging purposes):
	public int getRow(){
		return cur_row;
	}
	public int getCol(){
		return cur_col;
	}
	
	
	public void remove() {
		throw new RuntimeException("Remove not supported");
	}
}
