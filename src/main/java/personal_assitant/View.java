package personal_assitant;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class View extends JFrame implements ActionListener{
	private JScrollPane logScrollPane;
	private JTextArea logTextArea;
	private JScrollPane dictionaryScrollPane;
	private JTextArea dictionaryTextArea;
	private JScrollPane commandsScrollPane;
	private JTextArea commandsTextArea;
	private JTabbedPane tabbedPane;
	private JPanel dictionaryPanel;
	private JPanel logPanel;
	private JPanel commandsPanel;
	private JButton dictionaryReadButton;
	private JButton dictionaryWriteButton;
	private JButton commandsReadButton;
	private JButton commandsWriteButton;
	private JTextField latestSpeechRecognition;
	public View(){
		super("Personal Assistant");
		
		logPanel = new JPanel();				
		logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.PAGE_AXIS));
		logTextArea = new JTextArea();
		logTextArea.setColumns(20);
		logTextArea.setLineWrap(true);
		logTextArea.setRows(5);
		logTextArea.setWrapStyleWord(true);
		logTextArea.setEditable(false);
        logScrollPane = new JScrollPane(logTextArea);
        latestSpeechRecognition = new JTextField();
        latestSpeechRecognition.setEditable(false);
        latestSpeechRecognition.setBackground(Color.WHITE);
        latestSpeechRecognition.setPreferredSize(new Dimension(1000, 25));
        latestSpeechRecognition.setText("Speech recognition: ");
        
        dictionaryPanel = new JPanel();
        dictionaryPanel.setLayout(new BoxLayout(dictionaryPanel, BoxLayout.PAGE_AXIS));
		dictionaryWriteButton = new JButton("Write");
		dictionaryWriteButton.addActionListener(this);
		dictionaryReadButton = new JButton("Read");
		dictionaryReadButton.addActionListener(this);
		JPanel buttonPane = new JPanel();
        buttonPane.add(dictionaryWriteButton);
        buttonPane.add(dictionaryReadButton);
        dictionaryTextArea = new JTextArea();
        dictionaryTextArea.setColumns(20);
        dictionaryTextArea.setLineWrap(true);
        dictionaryTextArea.setRows(5);
        dictionaryTextArea.setWrapStyleWord(true);
        dictionaryTextArea.setEditable(true);
		dictionaryScrollPane = new JScrollPane(dictionaryTextArea);
		readFile(Main.DICTIONARYFILE, dictionaryTextArea);
		
		commandsPanel = new JPanel();
		commandsPanel.setLayout(new BoxLayout(commandsPanel, BoxLayout.PAGE_AXIS));
		commandsWriteButton = new JButton("Write");
		commandsWriteButton.addActionListener(this);
		commandsReadButton = new JButton("Read");
		commandsReadButton.addActionListener(this);
		JPanel buttonPane1 = new JPanel();
        buttonPane1.add(commandsWriteButton);
        buttonPane1.add(commandsReadButton);
        commandsTextArea = new JTextArea();
        commandsTextArea.setColumns(20);
        commandsTextArea.setLineWrap(true);
        commandsTextArea.setRows(5);
        commandsTextArea.setWrapStyleWord(true);
        commandsTextArea.setEditable(true);
        commandsScrollPane = new JScrollPane(commandsTextArea);
		readFile(Main.COMMANDSFILE, commandsTextArea);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.add("Log", logPanel);
		tabbedPane.add("Dictionary", dictionaryPanel);
		tabbedPane.add("Commands", commandsPanel);
		
        getContentPane().add(tabbedPane);
        logPanel.add(logScrollPane);
        logPanel.add(latestSpeechRecognition);
        dictionaryPanel.add(dictionaryScrollPane);
        dictionaryPanel.add(buttonPane);
        commandsPanel.add(commandsScrollPane);
        commandsPanel.add(buttonPane1);
        
        logScrollPane.setPreferredSize(new Dimension(1000,500));
        dictionaryScrollPane.setPreferredSize(new Dimension(1000,500));
        commandsScrollPane.setPreferredSize(new Dimension(1000,500));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	private void readFile(String path, JTextArea textArea){
		FileReader reader;
		try {
			reader = new FileReader(path);
	        BufferedReader br = new BufferedReader(reader);
	        textArea.read( br, null );
	        br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeFile(String path, JTextArea textArea){
		FileWriter writer;
		try {
			writer = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(writer);
			textArea.write(bw);
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addToLog(String msg){
		logTextArea.append(msg+"\r\n");
	}
	
	public void showRecognizedSpeech(String msg){
		latestSpeechRecognition.setText("Speech recognition: "+msg);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==dictionaryWriteButton){
			writeFile(Main.DICTIONARYFILE, dictionaryTextArea);
		}else if(e.getSource()==dictionaryReadButton){
			readFile(Main.DICTIONARYFILE, dictionaryTextArea);
		}else if(e.getSource()==commandsWriteButton){
			writeFile(Main.COMMANDSFILE, commandsTextArea);
		}else if(e.getSource()==commandsReadButton){
			readFile(Main.COMMANDSFILE, commandsTextArea);
		}
	}
}
