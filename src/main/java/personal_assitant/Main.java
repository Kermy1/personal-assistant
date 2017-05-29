package personal_assitant;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class Main {
	private static final String ENGLISHACOUSTICMODELPATH = "src/main/resources/en-us/en-us";
	private static final String ENGLISHDICTIONARYPATH = "src/main/resources/test/1314.dic";
	private static final String ENGLISHLANGUAGEMODELPATH = "src/main/resources/test/1314.lm";
	
	public static void main(String[] args) throws Exception {
		View view = new View();
		SpeechToText stt = new SpeechToText();
		TextToSpeech tts = new TextToSpeech();
		
		/*Configuration configuration = new Configuration();
		configuration.setAcousticModelPath(ENGLISHACOUSTICMODELPATH); 
		configuration.setDictionaryPath(ENGLISHDICTIONARYPATH); 
		configuration.setLanguageModelPath(ENGLISHLANGUAGEMODELPATH);
		
		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        InputStream stream = new FileInputStream(new File("src/main/resources/test.wav"));

        //recognizer.startRecognition(stream);
        recognizer.startRecognition(true);
        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n",  result.getHypothesis());
            
            String command = result.getHypothesis();
            //Match recognized speech with our commands
            if(command.equalsIgnoreCase("open file manager")) {
                System.out.println("File Manager Opened!");
            } else if (command.equalsIgnoreCase("close file manager")) {
                System.out.println("File Manager Closed!");
            } else if (command.equalsIgnoreCase("open browser")) {
                System.out.println("Browser Opened!");
            } else if (command.equalsIgnoreCase("close browser")) {
                System.out.println("Browser Closed!");
            }
        }
        recognizer.stopRecognition();*/
    }
}
