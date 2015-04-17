package a6adept;

public final class FrameArea{
	
	private final int minX;
	private final int minY;
	private final int maxX;
	private final int maxY;
	
	public FrameArea(int min_x, int min_y, int max_x, int max_y){
		this.minX = min_x;
		this.minY = min_y;
		this.maxX = max_x;
		this.maxY = max_y;
	}
	
	public int getMinX(){
		return minX;
	}
	
	public int getMaxX(){
		return maxX;
	}
	
	public int getMinY(){
		return minY;
	}
	
	public int getMaxY(){
		return maxY;
	}
	
	@Override
	public String toString(){
		return("(" + minX +", " + minY +")::(" + maxX + ", " + maxY +")");
	}
	
	public FrameArea intersect(FrameArea other) throws NoIntersectionException{
		//Returns a FrameArea of the intersection of two frames
		int newMinX;
		int newMinY;
		int newMaxX;
		int newMaxY;
		
		if (other==null){
			throw new NoIntersectionException();
		}
		
		int oMinX = other.getMinX();
		int oMinY = other.getMinY();
		int oMaxX = other.getMaxX();
		int oMaxY = other.getMaxY();
		
		if(oMinX > maxX || oMaxX < minX || oMinY > maxY || oMaxY < minY){
			throw new NoIntersectionException();
		}
		newMinX = Math.max(oMinX, minX);
		newMinY = Math.max(oMinY, minY);
		newMaxX = Math.min(oMaxX, maxX);
		newMaxY = Math.min(oMaxY, maxY);
		
		return new FrameArea(newMinX, newMinY, newMaxX, newMaxY);
	}

	public FrameArea union(FrameArea other){
		//Returns a FrameArea of the smallest rectangle enclosing two frames
		int newMinX;
		int newMinY;
		int newMaxX;
		int newMaxY;
		
		//If there's no other frame, a frame can return itself
		if (other==null){
			return this;
		}
		
		int oMinX = other.getMinX();
		int oMinY = other.getMinY();
		int oMaxX = other.getMaxX();
		int oMaxY = other.getMaxY();
		
		newMinX = Math.min(oMinX, minX);
		newMinY = Math.min(oMinY, minY);
		newMaxX = Math.max(oMaxX, maxX);
		newMaxY = Math.max(oMaxY, maxY);
		
		return new FrameArea(newMinX, newMinY, newMaxX, newMaxY);
	}
	
	public FrameArea translate(int dx, int dy){
		//Move the frame over by dx and dy
		int newMinX;
		int newMinY;
		int newMaxX;
		int newMaxY;
		
		newMinX = minX + dx;
		newMinY = minY + dy;
		newMaxX = maxX + dx;
		newMaxY = maxY + dy;
		
		return new FrameArea(newMinX, newMinY, newMaxX, newMaxY);
	}
}
