package a5novice;

import java.util.Iterator;

public class PixelIterator implements Iterator<Pixel> {
	
	private Frame frame;
	private int cur_row;
	private int cur_col;
	
	public PixelIterator(Frame frame){
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
			if (cur_col < frame.getWidth()-1){ //if we can advance to the right
				cur_col++;
			} else {
				cur_col = 0;
				cur_row++;
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
