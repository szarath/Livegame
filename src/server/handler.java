package server;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import px.file.reader;
import px.gui.Frame;

public class handler implements Runnable {
	private Socket connectionToClient;
	private OutputStream os;
	private PrintWriter txtout;
	private BufferedReader txtin, br;
	private String name, score, difficulty;
	private PrintWriter bw;
	private ObjectOutputStream oos;
	private FileOutputStream fos;
	private static String everything;
	public handler(Socket socketConnectionToClient) {
		connectionToClient = socketConnectionToClient;
		try {
			os = connectionToClient.getOutputStream();
			txtin = new BufferedReader(new InputStreamReader(
					connectionToClient.getInputStream()));
			txtout = new PrintWriter(os);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void close() {
		try {
			connectionToClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void sendMessage(String message) {
		txtout.println(message);
		txtout.flush();
	}

	@Override
	public void run() {
		System.out.println("Processing client commands");
		boolean processing = true;

		try {
			while (processing) {
				String message = txtin.readLine();
				StringTokenizer st = new StringTokenizer(message);
				String command = st.nextToken().toUpperCase();
				switch (command) {// switch the message 
				case "HELLO"://if the message is a hello
					name = st.nextToken();
					sendMessage("HELLO " + name);
					
					String path= "./ServerScore.txt";
					try {
						br = new BufferedReader(new FileReader(path));
					    StringBuilder sb = new StringBuilder();
					    String line = br.readLine();

					    while (line != null) {
					        sb.append(line);
					        sb.append(System.lineSeparator());
					        line = br.readLine();
					    }
					    everything = sb.toString();
					}
					catch(FileNotFoundException fex)
					{
						System.out.println(fex.toString());
					}
					catch(IOException iex)
					{
						System.out.println(iex.toString());
						
						
					}
					finally
					{
						
					try {
						if(br != null){
						br.close();
						}
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					}
					sendMessage(everything);
					
					break;
				case "SCORE"://if the message is score 
					score = st.nextToken();
					difficulty = st.nextToken();

					try {
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("./ServerScore.txt",true), "utf-8"));
						//writer.newLine();
				        writer.write(" Name: "+name  + " Score: "+score+ " Difficulty: "+ difficulty+",");
				        writer.flush();
				        writer.close();
				}
					catch(FileNotFoundException fex)
					{
						System.out.println(fex.toString());
						
					}
					break;
				case "EXIT"://if the message  is exit
					sendMessage("Bye");
					processing = false;
					break;
				default:
					sendMessage("Unknown command");
				}
			}
		} catch (IOException ex) {

			ex.printStackTrace();
		} finally {
			close();
		}
	}


}