//code taken from: https://www.geeksforgeeks.org/play-audio-file-using-java/
import java.io.File;
import java.io.IOException;


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
    public MusicPlayer(String filePath)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        this.filePath = filePath;
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }





    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
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