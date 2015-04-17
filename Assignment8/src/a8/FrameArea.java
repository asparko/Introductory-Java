package a8;

public class FrameArea {

	private int min_x;
	private int min_y;
	private int max_x;
	private int max_y;
	
	public FrameArea(int min_x, int min_y, int max_x, int max_y) {
		this.min_x = min_x;
		this.min_y = min_y;
		this.max_x = max_x;
		this.max_y = max_y;
	}
	
	public int getMinX() {
		return min_x;
	}
	
	public int getMaxX() {
		return max_x;
	}
	
	public int getMinY() {
		return min_y;
	}
	
	public int getMaxY() {
		return max_y;
	}
	
	public FrameArea intersect(FrameArea other) throws NoIntersectionException {
		if (other == null) {
			throw new NoIntersectionException();
		}
		
		if (getMaxX() < other.getMinX() ||
			getMinX() > other.getMaxX() ||
			getMaxY() < other.getMinY() ||
			getMinY() > other.getMaxY()) {
			throw new NoIntersectionException();
		}
		
		int inter_min_x = getMinX() > other.getMinX() ? getMinX() : other.getMinX();
		int inter_min_y = getMinY() > other.getMinY() ? getMinY() : other.getMinY();
		int inter_max_x = getMaxX() < other.getMaxX() ? getMaxX() : other.getMaxX();
		int inter_max_y = getMaxY() < other.getMaxY() ? getMaxY() : other.getMaxY();
		
		return new FrameArea(inter_min_x, inter_min_y, inter_max_x, inter_max_y);
	}

	public FrameArea union(FrameArea other) {
		if (other == null) {
			return this;
		}
		
		int union_min_x = getMinX() < other.getMinX() ? getMinX() : other.getMinX();
		int union_min_y = getMinY() < other.getMinY() ? getMinY() : other.getMinY();
		int union_max_x = getMaxX() > other.getMaxX() ? getMaxX() : other.getMaxX();
		int union_max_y = getMaxY() > other.getMaxY() ? getMaxY() : other.getMaxY();
		
		return new FrameArea(union_min_x, union_min_y, union_max_x, union_max_y);
	}
	
	public FrameArea translate(int dx, int dy) {
		return new FrameArea(getMinX()+dx, getMinY()+dy, getMaxX()+dx, getMaxY()+dy);
	}

	@Override
	public String toString() {
		return "(" + getMinX() + ", " + getMinY() + ")::(" + getMaxX() + ", " + getMaxY() + ")";
	}
}
