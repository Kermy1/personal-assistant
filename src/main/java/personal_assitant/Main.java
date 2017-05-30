package personal_assitant;

import marytts.modules.synthesis.Voice;

public class Main {
	public static final String ENGLISHACOUSTICMODELPATH = "src/main/resources/en-us/en-us";
	public static final String ENGLISHDICTIONARYPATH = "src/main/resources/test/1314.dic";
	public static final String ENGLISHLANGUAGEMODELPATH = "src/main/resources/test/1314.lm";
	public static final String AI_NAME = "eleanor";
	public static final String DICTIONARYFILE = "src/main/resources/dictionary.txt";
	public static final String COMMANDSFILE = "src/main/resources/commands.txt";
	public static final String EN_GB_FM_VOICE = "dfki-prudence-hsmm";
	public static final String EN_M_VOICE = "dfki-spike-hsmm";
	
	public static void main(String[] args) throws Exception {
		View view = new View();
		TextToSpeech tts = new TextToSpeech(view);
		tts.setGain(2f);
		CommandHandler cHandler = new CommandHandler(tts);
		SpeechToText stt = new SpeechToText(cHandler, view);
		view.addToLog(cHandler.getHandlerInitMessage());
		view.addToLog("Application started succesfully.");
    }
}
