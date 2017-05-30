package personal_assitant;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;

public class TextToSpeech {
	private float gain = 1.0f;
	private AudioPlayer		tts;
	private MaryInterface	marytts;
	private View view;
	private boolean interruptAble = false;

	/**
	 * Constructor
	 */
	public TextToSpeech(View view) {
		this.view = view;
		try {
			marytts = new LocalMaryInterface();

		} catch (MaryConfigurationException ex) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
			view.addToLog(ex.getMessage());
		}
		setVoice(Main.EN_GB_FM_VOICE);
	}

	/**
	 * Available voices in String representation
	 * 
	 * @return The available voices for MaryTTS
	 */
	public Collection<Voice> getAvailableVoices() {
		return Voice.getAvailableVoices();
	}

	/**
	 * Change the default voice of the MaryTTS
	 * 
	 * @param voice
	 */
	public void setVoice(String voice) {
		marytts.setVoice(voice);
	}
	
	public void setGain(float gain){
		this.gain = gain;
	}
	
	public float getGain(){
		return this.gain;
	}

	public void setInterruptAble(boolean bool){
		this.interruptAble = bool;
	}
	
	/**
	 * Transform text to speech
	 * 
	 * @param text
	 *            The text that will be transformed to speech
	 * @param daemon
	 *            <br>
	 *            <b>True</b> The thread that will start the text to speech
	 *            Player will be a daemon Thread <br>
	 *            <b>False</b> The thread that will start the text to speech
	 *            Player will be a normal non daemon Thread
	 * @param join
	 *            <br>
	 *            <b>True</b> The current Thread calling this method will
	 *            wait(blocked) until the Thread which is playing the Speech
	 *            finish <br>
	 *            <b>False</b> The current Thread calling this method will
	 *            continue freely after calling this method
	 */
	public void speak(String text) {

		// Stop the previous player
		stopSpeaking();

		try (AudioInputStream audio = marytts.generateAudio(text)) {

			// Player is a thread(threads can only run one time) so it can be
			// used has to be initiated every time
			tts = new AudioPlayer();
			tts.setAudio(audio);
			tts.setGain(gain);
			tts.setDaemon(false);
			tts.start();
			tts.join();

		} catch (SynthesisException ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "Error saying phrase.", ex);
			view.addToLog(ex.getMessage());
		} catch (IOException ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "IO Exception", ex);
			view.addToLog(ex.getMessage());
		} catch (InterruptedException ex) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "Interrupted ", ex);
			view.addToLog(ex.getMessage());
			tts.interrupt();
		}
	}

	/**
	 * Stop the MaryTTS from Speaking
	 */
	public void stopSpeaking() {
		// Stop the previous player
		if (tts != null)
			tts.cancel();
	}

}
