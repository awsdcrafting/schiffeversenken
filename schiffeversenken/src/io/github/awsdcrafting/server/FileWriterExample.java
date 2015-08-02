package io.github.awsdcrafting.server;

	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;

	public class FileWriterExample {
	  
	  FileWriter writer;
	  File file;
	  
	  public void schreiben(){
	     file = new File("DateiTest.txt");
	     try {

	       writer = new FileWriter(file ,true);
	       
	       writer.write("Test");
	       
	       writer.write(System.getProperty("line.separator"));
    
	       writer.write("Test 2");
	       
	       writer.flush();
	       
	       writer.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  public static void main(String[] args) {
	    FileWriterExample fileWriterExample = new FileWriterExample();
	    fileWriterExample.schreiben();
	  }

}
