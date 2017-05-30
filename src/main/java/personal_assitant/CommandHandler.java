package personal_assitant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class CommandHandler {
	private Semaphore inConversation;
	private TextToSpeech tts;
	private static final String[] confirmationMessages  = {"That is correct", "yes that is correct", ""};
	public CommandHandler(TextToSpeech tts) {
		inConversation = new Semaphore(1);
		this.tts = tts;
	}

	
	public String getHandlerInitMessage(){
		if(checkHandlers()){
			return "All commands have registered handlers.";
		}else{
			return "One or more commands don't have handlers registered, this may cause problems.";
		}
	}
	/*
	 * Check if all the commands in the commands.txt have registered handlers.
	 * @return boolean true if all commands have handlers, false otherwise.
	 */
	private boolean checkHandlers(){
		tts.setInterruptAble(true);
		boolean returnValue = true;
		float gain = tts.getGain();
		tts.setGain(0f);
		FileReader reader;
		try {
			reader = new FileReader(Main.COMMANDSFILE);
	        BufferedReader br = new BufferedReader(reader);
	        String line;
	        while((line = br.readLine()) != null){
	        	if(handleCommand(line)==null){
	        		returnValue = false;
	        		break;
	        	}
	        }
	        tts.stopSpeaking();
	        tts.setGain(gain);
	        br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tts.setInterruptAble(false);
		return returnValue;
	}
	
	/*
	 * Recognises and handles commands.
	 * @return String the command that was recognised, otherwise null.
	 */
	public String handleCommand(String speech){
		inConversation.acquireUninterruptibly();
		String returnString;
		switch(speech){
			case "help":
				
				returnString = "help";
				break;
			case "what is the time":
				
				returnString = "what is the time";
				break;
			case "how late is it":
				
				returnString = "how late is it";
				break;
			case "list commands":
				
				returnString = "list commands";
				break;
			default:
				returnString = null;
				break;
		}
		inConversation.release();
		return returnString;
	}
	
	private void speak(String msg){
		tts.speak(msg);
	}
}
