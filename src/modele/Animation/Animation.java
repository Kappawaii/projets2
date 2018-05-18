package modele.Animation;

import java.awt.image.BufferedImage;

public abstract class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	private int numFrames;
	
	private int count;
	private int retard;
	
	private int timesPlayed;
	
	public Animation() {
		timesPlayed = 0;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		retard = 2;
		numFrames = frames.length;
	}
	
	public void setRetard(int i) {
		retard = i;
	}
	
	public void setFrame(int i) {
		currentFrame = i;
	}
	
	public void setNumFrames(int i) {
		numFrames = i;
	}
	
	public void update() {
		if (retard == -1) {
			return;
		}
		count++;
		if (count == retard) {
			currentFrame++;
			count = 0;
		}
		if (currentFrame == numFrames) {
			currentFrame = 0;
			timesPlayed++;
		}
	}
	
	
	public int getFrame() {
		return currentFrame;
	}
	
	public int getCount() {
		return count;
	}
	
	public BufferedImage getImage() {
		return frames[currentFrame];
	}
	
	public boolean hasplayedOne() {
		return timesPlayed > 0 ;
	}
	
	public boolean hasPlayed(int i) {
		return timesPlayed == i;
	}
	
	

}
