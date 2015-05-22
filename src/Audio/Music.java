package Audio;

import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	
	// music clips
	static private String track0name = "bin/Audio/resource/nicedaytodie.mp3";
	
	private ArrayList<Media> tracks;
	private int currenttrack;
	
	//player
	private MediaPlayer mediaPlayer;

	boolean neverplay;
	
	public Music(){
		new JFXPanel(); // this will prepare JavaFX toolkit and environment
		
		// add tracks
		tracks = new ArrayList<Media>();
		tracks.add(new Media(Paths.get(track0name).toUri().toString()));
		
		currenttrack = 0;
		
		mediaPlayer = new MediaPlayer(tracks.get(currenttrack));
		
		neverplay = false;
		
	}
	
	/**
	 * plays the current track from the given offset
	 * @param offset an integer representing the start point of the track. usually 0 if starting from beginning of track
	 */
	public void playTrack(int offset){
		if (!neverplay){
			mediaPlayer.stop();
			mediaPlayer.play();

		}
	}
	public void stop(){
		mediaPlayer.stop();
	}
	
	public void nextTrack(){
		currenttrack++;
		if (currenttrack == tracks.size()){
			currenttrack = 0;
		}
		mediaPlayer.stop();
		mediaPlayer = new MediaPlayer(tracks.get(currenttrack));
		mediaPlayer.play();
	}
}
