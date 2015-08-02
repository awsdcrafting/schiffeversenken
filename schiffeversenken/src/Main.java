import java.time.LocalTime;


public class Main 
{

	public static void main(String[] args) 
	{

		log("Hello!");
	}
	
	public static void log(String message)
	{
		System.out.println("["+LocalTime.now() +"]"+message);
	}
}
