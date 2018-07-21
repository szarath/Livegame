
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import px.gui.Frame;
import px.gui.Window;
import server.server;

public class Main {
public static boolean c;

	public static void main(String[] args) {

		Frame f = new Frame();// creates the new farame and passes the name
		f.setSize(800, 600);// size of frame
		f.setLocationRelativeTo(null);// location of frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// close opration
		f.setVisible(true);// set true to view
		f.setResizable(false);// fix frame size so it never moves
		f.repaint();// repaints the application
		f.revalidate();// revalidates the frame
		int choice = JOptionPane.showOptionDialog(null,
				"You want to host a server", "Server",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (choice == JOptionPane.YES_OPTION) {
			String port = JOptionPane.showInputDialog("Server Port to host on",
					"1995");
			server.done(Integer.parseInt(port));
			
		}
		
		
	}
	
	

}
