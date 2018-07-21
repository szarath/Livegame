package px.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class reader {
private BufferedReader br;

public static String everything;
public reader() 
{
	String path= "./Score.txt";
	try {
		br = new BufferedReader(new FileReader(path));
	    StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	    setEverything(sb.toString());
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


}
public static String getEverything() {
	return everything;
}
public static void setEverything(String everything) {
	reader.everything = everything;
}
}
