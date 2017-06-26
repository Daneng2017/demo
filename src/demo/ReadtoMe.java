package demo;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class ReadtoMe {

    private final int BUFFER_SIZE = 128000;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;

    /**
     * @param filename the name of the file that is going to be played
     */
    public void playSound(String filename){

    	AudioInputStream audioStream= null;
    	try {
    		URL url2 = this.getClass().getClassLoader().getResource(filename);
            audioStream = AudioSystem.getAudioInputStream(url2);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        audioFormat = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
       }

       sourceLine.drain();
       sourceLine.close();
    }
    
    public static void main(String[] args) throws Exception {

    	System.out.println("ww");
    	ReadtoMe m = new ReadtoMe();
    	
    	String filename = "edu/cmu/sphinx/demo/sounds/0.wav";
    	m.playSound(filename);
    	filename = "edu/cmu/sphinx/demo/sounds/1.wav";
    	m.playSound(filename);
    	filename = "edu/cmu/sphinx/demo/sounds/2.wav";
    	m.playSound(filename);
    	filename = "edu/cmu/sphinx/demo/sounds/3.wav";
    	m.playSound(filename);
    	filename = "edu/cmu/sphinx/demo/sounds/alpha.wav";
    	m.playSound(filename);
    	filename = "edu/cmu/sphinx/demo/sounds/yes.wav";
    	m.playSound(filename);

    }
}