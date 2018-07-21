package px.file;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;







import px.gui.Frame;
import px.gui.Window;

public class Writer {


   private  PrintWriter bw;
   private ObjectOutputStream oos;
   private FileOutputStream fos;
 
   /*
    * the Writer constructor which take in object to serialize and throws a ioexpection
    */
	public Writer(Window w,String name) throws IOException {


		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("Score.txt",true), "utf-8"));
			writer.newLine();
	        writer.write("Name: "+name +"," + "Score: "+w.getScore()+","+"Difficulty: "+ Frame.getEnumber());
	        writer.flush();
	        writer.close();
	}
		catch(FileNotFoundException fex)
		{
			System.out.println(fex.toString());
			
		}
		
		
	}

}
