package A1b;

public class Main //extends Application
{
    public static void main(String[] args) {
    	UI ui = null;
    	AddressBook book;
	 
    	if (args.length > 0){
    		if (args[0].compareToIgnoreCase("console")==0)
    			//your code goes here
    			ui = new ConsoleUI();
    	else if (args[0].compareToIgnoreCase("gui")==0) {
    		//your code goes here
    		ui = new GUI();
    		
      }
      }
	  
      if (ui != null){
        book = new AddressBook(ui);
        ui.run(book);
      }
	  
    }
}