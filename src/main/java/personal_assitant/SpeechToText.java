package personal_assitant;

import java.io.IOException;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class SpeechToText {
	private CommandHandler cHandler;
	private View view;
	public SpeechToText(CommandHandler cHandler, View view) {
		this.cHandler = cHandler;
		this.view = view;
	}
	
	private void init() throws IOException{
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath(Main.ENGLISHACOUSTICMODELPATH); 
		configuration.setDictionaryPath(Main.ENGLISHDICTIONARYPATH); 
		configuration.setLanguageModelPath(Main.ENGLISHLANGUAGEMODELPATH);
		
		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);
        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            view.showRecognizedSpeech(result.getHypothesis());
            cHandler.handleCommand(result.getHypothesis());
        }
        recognizer.stopRecognition();
	}

}
