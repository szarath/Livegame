package px.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;











import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;

import px.file.reader;
import server.server;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7178011279279511826L;
	private String number;
	private static int enumber;
	private JMenuItem btnDEasy, btnDNormal, btnDHard, btnDCus, btnscore,btnconnect,btnExit;
	private Window w;
	private boolean buttoncheck = true;
	private reader r;
	private String score;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public static String name;
	 


	private server server;
	
	private Socket			clientSocket;
	private InputStream		is;
	private static PrintWriter		txtout;
	private static BufferedReader	txtin;

	/**
	 * 
	 * Constructor for game
	 */
	public Frame() {
		
		setTitle("Live");// set the title of the applicaiton
		JMenuBar mainPanel = new JMenuBar();// create a new menu bar
		JMenu menu = new JMenu("Game");// create a new menu item
		r = new reader();

		btnDEasy = new JMenuItem("Diffculty : Easy(20)");// show in the menu bar
		btnDNormal = new JMenuItem("Diffculty : Normal(40)");// show in the menu
																// bar
		btnDHard = new JMenuItem("Diffculty : Hard(80)");// show in the menu bar
		btnDCus = new JMenuItem("Diffculty : Custom(?)");// show in the menu bar
		btnscore = new JMenuItem("Score");
		
		btnconnect = new JMenuItem("Connect");
		while (!buttoncheck) {

		}
		 btnExit = new JMenuItem("Exit");
		 name = JOptionPane.showInputDialog(Frame.this,"what is your name", "Noname");
		
		/**
		 * button btnDEasy to set the difficult size
		 */
		btnDEasy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Frame.this.remove(scrollPane);

				buttoncheck = false;
				btnDEasy.setVisible(buttoncheck);
				btnDNormal.setVisible(buttoncheck);
				btnDHard.setVisible(buttoncheck);
				btnDCus.setVisible(buttoncheck);
				btnscore.setVisible(buttoncheck);
				btnconnect.setVisible(buttoncheck);
				enumber = 20;
			
				Frame.this.add(w = new Window(enumber, Frame.this));
				repaint();
				revalidate();

			}
		});
		/**
		 * button btnDNormal to set the difficult size
		 */
		btnDNormal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttoncheck = false;
				btnDEasy.setVisible(buttoncheck);
				btnDNormal.setVisible(buttoncheck);
				btnDHard.setVisible(buttoncheck);
				btnDCus.setVisible(buttoncheck);
				btnscore.setVisible(buttoncheck);
				btnconnect.setVisible(buttoncheck);
				enumber = 40;
				Frame.this.add(w = new Window(enumber, Frame.this));
				repaint();
				revalidate();

			}
		});
		/**
		 * button btnDHard to set the difficult size
		 */
		btnDHard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttoncheck = false;
				btnDEasy.setVisible(buttoncheck);
				btnDNormal.setVisible(buttoncheck);
				btnDHard.setVisible(buttoncheck);
				btnDCus.setVisible(buttoncheck);
				btnscore.setVisible(buttoncheck);
				btnconnect.setVisible(buttoncheck);
				enumber = 80;
				Frame.this.add(w = new Window(enumber, Frame.this));

				repaint();
				revalidate();

			}
		});
		/**
		 * button btnDCus the user gets to set the number
		 */
		btnDCus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				buttoncheck = false;
				btnDEasy.setVisible(buttoncheck);
				btnDNormal.setVisible(buttoncheck);
				btnDHard.setVisible(buttoncheck);
				btnDCus.setVisible(buttoncheck);
				btnscore.setVisible(buttoncheck);
				btnconnect.setVisible(buttoncheck);
				number = JOptionPane.showInputDialog("What is the number");
				enumber = Integer.parseInt(number);
				w = new Window(Integer.parseInt(number), Frame.this);
				Frame.this.add(w);

				repaint();
				revalidate();

			}
		});
		/**
		 * button to see previous score
		 */
		btnscore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				score = reader.getEverything();
				textArea = new JTextArea(score);
				scrollPane = new JScrollPane(textArea);
				textArea.setEditable(false);

				Frame.this.add(scrollPane);
				repaint();
				revalidate();
			}
		});
		/**
		 * buttton for the connection to the server 
		 */
		btnconnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String host = JOptionPane.showInputDialog(Frame.this, "Hostname", "localhost");//set the hostname
				String port = JOptionPane.showInputDialog(Frame.this, "Port", "1995");//set the port address
				try
				{
					clientSocket = new Socket(host, Integer.parseInt(port));
					is = clientSocket.getInputStream();// get the stream 
					txtout = new PrintWriter(clientSocket.getOutputStream());
					txtin = new BufferedReader(new InputStreamReader(is));
					
					sendCommand("HELLO " + name);
					readResponse();
					html();
					Window.setCheck(true);
				}
				catch (NumberFormatException ex)
				{
					ex.printStackTrace();
				}
				catch (UnknownHostException ex)
				{
					ex.printStackTrace();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
				
				btnconnect.setVisible(false);
				
				repaint();
				revalidate();
				
				
			}
		});
		
		
		
		/**
		 * button btnExit to exit from the application
		 */
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendCommand("EXIT");
				readResponse();
				close();
				
				System.exit(0);
			}
		});

		menu.add(btnDEasy);
		menu.add(btnDNormal);
		menu.add(btnDHard);
		menu.add(btnDCus);
		menu.add(btnscore);
		menu.add(btnconnect);
		
		menu.add(btnExit);
		mainPanel.add(menu);

		add(mainPanel, BorderLayout.NORTH);
		repaint();
		revalidate();
		
	}

	


	public static int getEnumber() {
		return enumber;
	}
	
	private void close()
	{
		if (clientSocket != null)
		{
			try
			{
				clientSocket.close();
				clientSocket = null;
			}
			catch (IOException ex)
			{
				
				ex.printStackTrace();
			}
		}

	}
/**
 * 
 * @param message to send 
 */
	protected static void sendCommand(String message)
	{
		txtout.println(message);
		txtout.flush();
	}

	private static void readResponse()
	{
		try
		{
			String response = txtin.readLine();
			System.out.println(response);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void html()
	{
		try {
			String check ="";
			String info  = txtin.readLine();
			
			StringTokenizer st = new StringTokenizer(info,",");
			while(st.hasMoreTokens())
			{
				check += "<br >" +st.nextToken();
				
			}
			String page = "<html><header><title>Live Game session </title ></header><body><p>" + check + "</p> </body ></html>";
		
			BufferedWriter writer = null;
			try
			{
			    writer = new BufferedWriter( new FileWriter( "./Score.html"));
			    writer.write( page);

			}
			catch ( IOException e)
			{
			}
			finally
			{
			    try
			    {
			        if ( writer != null)
			        writer.close( );
			    }
			    catch ( IOException e)
			    {
			    }
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
	}
}
