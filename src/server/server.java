package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server
{
	private ServerSocket	server;
	private boolean			running;

	public server(int port)
	{
		try
		{
			System.out.println("Creating server");
			server = new ServerSocket(port);
			running = true;
			start();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public void start()// start the server 
	{
		System.out.println("Starting server");
		while (running)//run the server with multiple clients 
		{
			try
			{
				Socket connectionToClient = server.accept();//accept the server 
				System.out.println("New client");
				handler handler = new handler(connectionToClient);
				Thread clientThread = new Thread(handler);//thread the clients 
				System.out.println("Starting client thread");
				clientThread.start();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	
	/**
	 * 
	 * @param port the port to connect to 
	 */
	public static void done(int port)
	{
		server server = new server(port);
		
	}
}
