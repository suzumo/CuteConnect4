package Audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;


/**
 * @author Jaan
 * 
 * A class that allows for music to be played
 * 
 */
public class Music {
	
	private static String audiorespath = "sounds/";
	
	// music clip names
	private String track0name = audiorespath + "nicedaytodie.wav";
	
	// tracks
	private ArrayList<String> tracks;
	private int currenttrack;
	
	// sound effects
	// names
	static public String drop = audiorespath + "clang.wav";
	static public String win = audiorespath + "imacstartup.wav";
	static public String lose=  audiorespath + "baritone.wav";
	static public String start =  audiorespath + "helloson.wav";
	static public String colfull =  audiorespath + "sneeze.wav";
	static public String quit =  audiorespath + "bye.wav";
	
	
	// player
	private AudioInputStream audioIn;
	private AudioInputStream effectIn;
	private Clip currentclip;
	private Clip currentsound;
	
	// never play
	boolean disablesound;
	
	/**
	 * constructor
	 */
	public Music(){
	
		// add music tracks
		tracks = new ArrayList<String>();
		tracks.add(track0name);
		currenttrack = 0;
		System.out.println(tracks.size());
		
		// add sound effects		
		disablesound = false;	
	}
	
	/**
	 * plays the current track
	 */
	public void playTrack(){
		if (disablesound) return;

		try {
			audioIn = AudioSystem.getAudioInputStream(new File(tracks.get(currenttrack)));
			AudioFormat format = audioIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			
			currentclip = (Clip)AudioSystem.getLine(info);
			currentclip.open(audioIn);
			currentclip.loop(10);
		} catch (UnsupportedAudioFileException | IOException e) {
//			 TODO Auto-generated catch block
//			e.printStackTrace();
			disablesound = true;
			JOptionPane.showMessageDialog(null, "Sorry, Senpai, the music won't work!");
		} catch (LineUnavailableException e){
			JOptionPane.showMessageDialog(null, "Sorry, Senpai, the music won't work!");

			disablesound = true;
		}
		
	}
	/**
	 * stops the current track
	 */
	public void stop(){
		currentclip.stop();
	}
	
	/**
	 * goes to and starts the next music track
	 */
	public void nextTrack(){
		currenttrack++;
		if (currenttrack == tracks.size()){
			currenttrack = 0;
		}
		playTrack();
	}
	
	/**
	 * plays a sound effect
	 * @param s the file path to the sound to be played
	 */
	public void playEffect(String s){
		if (disablesound) return;
		
		try {
			effectIn = AudioSystem.getAudioInputStream(new File(s));
			AudioFormat format = effectIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			
			currentsound = (Clip) AudioSystem.getLine(info);
			currentsound.open(effectIn);
		} catch (LineUnavailableException |UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			disablesound = true;
		}
	}
}
