package a6jedi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class FrameImpl implements Frame {
	
	private int height;
	private int width;
	private String title;
	
	private boolean notifying; //true if a frame should notify observers of its changes
	
	private List<FrameAreaObserver> observers;
	protected FrameArea changedArea; //keep track of changed area while notifications are off

	//CONSTRUCTOR
	public FrameImpl(int width, int height, String title){
		//Check for valid parameters:
		if (title == null) {
			throw new RuntimeException("Illegal initial title: null");
		}
		
		if (width < 1 || height < 1) {
			throw new RuntimeException("Illegal dimensions.");
		}
		
		//If parameters are valid, construct!
		this.height = height;
		this.width = width;
		this.title = title;
		notifying = true; //should notify observers by default
		
		observers = new ArrayList<FrameAreaObserver>();
	}
	
	
	//METHODS
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String new_title) {
		if (new_title == null){
			throw new RuntimeException("Cannot set title as null");
		} else {
			title = new_title;
		}	
	}
	
	public IndirectFrame crop(int x, int y, int width, int height){
		//Check for illegal values first:
		if(x<0 || y<0){
			throw new IllegalArgumentException("Crop corner out of bounds.");
		}
		if(width<1 || height<1){
			throw new IllegalArgumentException("Cropped Frame must have positive dimensions.");
		}
		if(x+width > getWidth() | y+height > getHeight()){
			throw new IllegalArgumentException("Crop dimensions extend beyond original Frame.");
		}
		
		//If parameters are okay, crop the frame:
		return new IndirectFrame(this, x, y, width, height);
	}
	
	public IndirectFrame[][] makeTiles(int num_across, int num_down) {
		//Initialize variables
		IndirectFrame[][] tiles = new IndirectFrame[num_across][num_down];
		int[] widthArray = new int[num_across]; //width of each tile
		int[] heightArray = new int[num_down]; //height of each tile
		int remainderAcross = getWidth()%num_across;
		int remainderDown = getHeight()%num_down;
		int cornerX = 0; //x-coordinate of top-left corner of tile
		int cornerY = 0; //y-coordinate of top-left corner of tile
		
		//Fill width and height array with minimum dimension of tiles
		Arrays.fill(widthArray, getWidth()/num_across);
		Arrays.fill(heightArray, getHeight()/num_down);
		
		//Add extra pixels to height and width arrays if there is a remainder
		for(int r = 0; r<remainderAcross; r++){
			widthArray[r] ++;
		}
		
		for(int r =0; r< remainderDown; r++){
			heightArray[r] ++;
		}
		
		for(int x=0; x<num_across; x++){
			for(int y=0; y<num_down; y++){
				tiles[x][y] = crop(cornerX, cornerY, widthArray[x], heightArray[y]);
				cornerY += heightArray[y]; //At each new row, add previous tile height to get new y-coordinate
			}
			cornerY = 0; //Reset y-coordinate to 0 before moving to next column
			cornerX += widthArray[x]; //Add each new column, add previous width to get new x-coordinate
		}
		return tiles;
	}
	
	public void registerFrameObserver(FrameObserver observer){	
		//call other register method with entire frame as the area of interest
		registerFrameObserver(observer, new FrameArea(0, 0, getWidth()-1, getHeight()-1));
	}
	
	public void registerFrameObserver(FrameObserver observer, FrameArea interest){	
		//Turn observer into a decorated observer with an associated interest area
		FrameAreaObserverImpl observerDeco = new FrameAreaObserverImpl(observer, interest);
		
		//add observer to list of observers
		observers.add(observerDeco);
	}
	
	public void unregisterFrameObserver(FrameObserver observer){
		//remove observer from list of observers
		for (FrameAreaObserver o : observers){
			//find which decorated observer corresponds to the plain observer input parameter
			if (o.getWrappedObserver().equals(observer)){ 
				observers.remove(o);
				break;
			}
		}
	}

	public void suspendNotifications(){
		notifying = false;
	}
	
	public void resumeNotifications(){
		notifying = true;
		notifyObservers(changedArea);
		
		//reset changedArea
		changedArea = null;
	}
	
	public void notifyObservers(FrameArea changed) {
		//If frame should notify observers:
		if (notifying == true){
			
			//then trigger update method on all observers if the observers
			//are interested in the parts that changed
			for (FrameAreaObserver o : observers) {
				try {
					o.getInterestArea().intersect(changed);
					o.update(this, changed);
				} catch (NoIntersectionException e) {
	
				} 
			}
		} else { //if notifying = false, collect changedArea for later
			changedArea = changed.union(changedArea);
		}
	}

}
