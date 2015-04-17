package a8;

import java.util.List;
import java.util.ArrayList;

abstract public class FrameImpl implements Frame {
	private int width;
	private int height;
	private List<FrameAreaObserver> observers;
	private boolean suspended;
	private FrameArea area_to_update;

	public FrameImpl(int width, int height) {
		if (width < 1 || height < 1) {
			throw new RuntimeException("Illegal dimensions.");
		}

		this.width = width;
		this.height = height;
		this.observers = new ArrayList<FrameAreaObserver>();
		suspended = false;
		area_to_update = null;
	}

	abstract public String getTitle();

	abstract public void setTitle(String new_title);

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	abstract public Pixel getPixel(int x, int y);

	abstract public void setPixel(int x, int y, Pixel p);

	protected void check_coordinates(int x, int y) {
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
	}

	public boolean equals(Frame other) {
		if (other.getWidth() != getWidth() ||
				other.getHeight() != getHeight()) {
			return false;
		}
		for (int y=0; y<getHeight(); y++) {
			for (int x=0; x<getWidth(); x++) {
				if (!(getPixel(x,y).equals(other.getPixel(x,y)))) {
					return false;
				}
			}
		}
		return true;
	}

	abstract public String toString();

	@Override
	public ColorPixel getAverage() {
		double red_sum = 0.0;
		double green_sum = 0.0;
		double blue_sum = 0.0;

		for (int x=0; x<getWidth(); x++) {
			for (int y=0; y<getHeight(); y++) {
				Pixel p = getPixel(x,y);
				red_sum += p.getRed();
				green_sum += p.getGreen();
				blue_sum += p.getBlue();
			}
		}
		double num_pixels = getWidth() * getHeight();
		return new ColorPixel(red_sum / num_pixels,
				green_sum / num_pixels,
				blue_sum / num_pixels);
	}

	@Override
	public String render() {
		String result = "";

		for (int y=0; y<getHeight(); y++) {
			for (int x=0; x<getWidth(); x++) {
				result += getPixel(x,y).asChar();
			}
			if (y != getHeight()-1) {
				result += "\n";
			}
		}
		return result;
	}

	@Override
	public IndirectFrame crop(int x, int y, int width, int height) {
		return new IndirectFrame(this, x, y, width, height);
	}

	public IndirectFrame[][] makeTiles(int num_across, int num_down) {
		IndirectFrame[][] tiles = new IndirectFrame[num_across][num_down];

		int tile_width = getWidth()/num_across;
		int width_remainder = getWidth()%num_across;
		int tile_height = getHeight()/num_down;
		int height_remainder = getHeight()%num_down;

		int tile_x = 0;
		for (int c=0; c < num_across; c++) {
			int extra_width = 0;
			if (c < width_remainder) {
				extra_width = 1;
			}

			int tile_y = 0;
			for (int r=0; r<num_down; r++) {
				int extra_height = 0;
				if (r < height_remainder) {
					extra_height = 1;
				}

				tiles[c][r] = crop(tile_x, tile_y, tile_width+extra_width, tile_height+extra_height);
				tile_y += tile_height+extra_height;
			}

			tile_x += tile_width+extra_width;
		}
		return tiles;
	}

	public void registerFrameObserver(FrameObserver observer) {
		registerFrameObserver(observer, new FrameArea(0, 0, getWidth()-1, getHeight()-1));
	}

	public void registerFrameObserver(FrameObserver observer, FrameArea area_of_interest) {
		observers.add(new FrameAreaObserverImpl(observer, area_of_interest));
	}

	public void unregisterFrameObserver(FrameObserver observer) {
		for (FrameAreaObserver fao : observers) {
			if (fao.getObserver() == observer) {
				observers.remove(fao);
				return;
			}
		}
	}

	protected void notifyObservers(int x, int y) {
		FrameArea change_area = new FrameArea(x, y, x, y);
		notifyObservers(change_area);
	}

	protected void notifyObservers(FrameArea change_area) {
		if (suspended) {
			if (area_to_update == null) {
				area_to_update = change_area;
			} else {
				area_to_update = area_to_update.union(change_area);
			}
		} else {
			for (FrameObserver o : observers) {
				o.update(this, change_area);
			}		
		}
	}

	public void suspendNotifications() {
		if (!suspended) {
			suspended = true;
		}
	}

	public void resumeNotifications() {
		if (suspended) {
			suspended = false;

			FrameArea a = area_to_update;
			area_to_update = null;

			if (a != null) {
				notifyObservers(a);
			}
		}
	}
	
	public Frame copy() {
		ColorFrame copy = new ColorFrame(getWidth(), getHeight());
		for (int x=0; x<getWidth(); x++) {
			for (int y=0; y<getHeight(); y++) {
				copy.setPixel(x, y, getPixel(x,y));
			}
		}
		return copy;
	}
}
