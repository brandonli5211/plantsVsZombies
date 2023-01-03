//code taken from: https://www.geeksforgeeks.org/play-audio-file-using-java/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer
{

    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    // constructor to initialize streams and clip
    public MusicPlayer()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        List<String> playList = new ArrayList<String>();
        playList.add("music/grassWalk.wav");
        playList.add("music/brainiacManiac.wav");
        playList.add("music/ultimateBattle.wav");
        playList.add("music/zenGarden.wav");
        int curr = (int)(Math.random() * 4);
        this.filePath = playList.get(curr);

        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}