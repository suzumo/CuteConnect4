package Audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Music {
	
	// music clip names
	static private String track0name = "bin/Audio/resource/nicedaytodie.wav";
	
	// tracks
	private ArrayList<String> tracks;
	private int currenttrack;
	
	// player
	private AudioInputStream audioIn;
	private Clip currentclip;
	
	// never play
	boolean neverplay;
	
	public Music(){
	
		// add tracks
		tracks = new ArrayList<String>();
		tracks.add(track0name);
		currenttrack = 0;
		
		neverplay = false;
			
	}
	
	/**
	 * plays the current track from the given offset
	 */
	public void playTrack(){
		if (!neverplay){
			
			try {
				audioIn = AudioSystem.getAudioInputStream(new File(tracks.get(currenttrack)));
				AudioFormat format = audioIn.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				System.out.println(format.toString());
				
				currentclip = (Clip)AudioSystem.getLine(info);
				currentclip.open(audioIn);
				currentclip.loop(10);
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void stop(){
		currentclip.stop();
	}
	
	public void nextTrack(){
		currenttrack++;
		if (currenttrack == tracks.size()){
			currenttrack = 0;
		}
		playTrack();
	}
}
