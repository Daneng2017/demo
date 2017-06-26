package demo;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class Freestyle {

    private static final String ACOUSTIC_MODEL =
        "resource:/edu/cmu/sphinx/models/en-us/en-us";
    private static final String DICTIONARY_PATH =
          "resource:/edu/cmu/sphinx/models/custom/0_9_text.dic";
     private static final String LANGUAGE_MODEL =
    		"resource:/edu/cmu/sphinx/models/custom/0_9_text.lm";

    public static void main(String[] args) throws Exception {

    	Configuration configuration = new Configuration();
        configuration.setAcousticModelPath(ACOUSTIC_MODEL);
        configuration.setDictionaryPath(DICTIONARY_PATH);
        configuration.setLanguageModelPath(LANGUAGE_MODEL);
//        configuration.setUseGrammar(false);
        
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
      	String utt="";           	
        
      	recognizer.startRecognition(true);     
        SpeechResult result;
    	ReadtoMe m = new ReadtoMe();
		String filename;
		String filepath ="edu/cmu/sphinx/demo/sounds/";
		
		while (true) {           	       
        	while ((result = recognizer.getResult()) != null) {
           		utt = result.getHypothesis();           		         	
           		if (utt.length()>0) {
           				System.out.println("YOU SAID: "+utt);
           				String[] tokens=(utt.split("\\s"));
           				for (int i=0;i<tokens.length;i++) {
           					System.out.println (tokens[i]);	
           					filename = filepath +tokens[i]+".wav";
           					m.playSound(filename);
           				}
           		}
        	}
        }

    }
}