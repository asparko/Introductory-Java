package a7;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener {

	private FrameView[][] frame_view;
	private IndirectFrame[][] tiles;
	private IndirectFrame blankTile;
	
	private int blankTileI;
	private int blankTileJ;
	
	public FramePuzzleWidget(Frame f) {
		setLayout(new GridLayout(5,5));

		// Create tiles
		tiles = f.makeTiles(5, 5);
		
		// Make the blank tile
		ColorFrame blankFrame = new ColorFrame(tiles[4][4].getWidth(),tiles[4][4].getHeight(),new ColorPixel(0.3,0,0.7),"Blank");
		blankTile = new IndirectFrame(blankFrame,0,0,blankFrame.getWidth(),blankFrame.getHeight());
		tiles[4][4]=blankTile;
		blankTileI = 4;
		blankTileJ = 4;
		
		// Turn each tile into a frame_view, and add each to grid holder
		frame_view = new FrameView[5][5];
		for(int j=0; j<5; j++){
			for(int i=0; i<5; i++){
				frame_view[i][j]=new FrameView(tiles[i][j]);
				frame_view[i][j].addMouseListener(this);
				frame_view[i][j].setFocusable(true);
				frame_view[i][j].addKeyListener(this);
				add(frame_view[i][j]);
			}
		}
		//this.setFocusable(true);
		//this.addKeyListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Get tile at clicked location
		FrameView clickedTile = (FrameView)e.getSource();
		
		//Get tile array coordinates of click
		int iClick =0;
		int jClick =0;
		for (int i=0; i<5; i++){
			for(int j=0; j<5; j++){
				if (clickedTile.equals(frame_view[i][j])){
					iClick = i;
					jClick = j;
				}
			}
		}
		
		//If blank tile is in same column as click:
		if(iClick==blankTileI){ 
			//If blank tile is below click
			if(jClick < blankTileJ){ 
				for(int j=blankTileJ; j>jClick; j--){
					tiles[iClick][j]=tiles[iClick][j-1];
					frame_view[iClick][j].setFrame(tiles[iClick][j]);
				}
				
			//If blank tile is above click:	
			} else if (jClick > blankTileJ){ 
				for(int j=blankTileJ; j<jClick; j++){
					tiles[iClick][j]=tiles[iClick][j+1];
					frame_view[iClick][j].setFrame(tiles[iClick][j]);
				}				
			}
			frame_view[iClick][jClick].setFrame(blankTile);
			blankTileJ = jClick;
			
		//Else if blank tile is in same row as click:	
		} else if (jClick==blankTileJ){
			//If blank tile is right of click
			if (iClick < blankTileI){ 
				for(int i=blankTileI; i>iClick; i--){
					tiles[i][jClick]=tiles[i-1][jClick];
					frame_view[i][jClick].setFrame(tiles[i][jClick]);
				}
				
			//If blank tile is left of click:	
			} else if (iClick > blankTileI){ 
				for(int i=blankTileI; i<iClick; i++){
					tiles[i][jClick]=tiles[i+1][jClick];
					frame_view[i][jClick].setFrame(tiles[i][jClick]);
				}	
			}
			frame_view[iClick][jClick].setFrame(blankTile);
			blankTileI = iClick;
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			//If blank tile is not at the top:
			if(!(blankTileJ==0)){
				tiles[blankTileI][blankTileJ]=tiles[blankTileI][blankTileJ-1];
				frame_view[blankTileI][blankTileJ].setFrame(tiles[blankTileI][blankTileJ]);
				frame_view[blankTileI][blankTileJ-1].setFrame(blankTile);
				blankTileJ--;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			//If blank tile is not at the bottom:
			if(!(blankTileJ==4)){
				tiles[blankTileI][blankTileJ]=tiles[blankTileI][blankTileJ+1];
				frame_view[blankTileI][blankTileJ].setFrame(tiles[blankTileI][blankTileJ]);
				frame_view[blankTileI][blankTileJ+1].setFrame(blankTile);
				blankTileJ++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			//If blank tile is not at the far left:
			if(!(blankTileI==0)){
				tiles[blankTileI][blankTileJ]=tiles[blankTileI-1][blankTileJ];
				frame_view[blankTileI][blankTileJ].setFrame(tiles[blankTileI][blankTileJ]);
				frame_view[blankTileI-1][blankTileJ].setFrame(blankTile);
				blankTileI--;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			//If blank tile is not at far right:
			if(!(blankTileI==4)){
				tiles[blankTileI][blankTileJ]=tiles[blankTileI+1][blankTileJ];
				frame_view[blankTileI][blankTileJ].setFrame(tiles[blankTileI][blankTileJ]);
				frame_view[blankTileI+1][blankTileJ].setFrame(blankTile);
				blankTileI++;
			}
		}		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}	
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}