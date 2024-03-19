package A1a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
    private String[] database;
    Scanner input;
    int choice;
    Graphics g;

    public Main() {
        database = new String[0];
        input = new Scanner(System.in);
        
    }

    public void add(final String name) {
        String[] temp = new String[database.length + 1];
        System.arraycopy(database, 0, temp, 0, database.length);
        temp[database.length] = name;
        database = temp;
    }

    public int search(final String name) {
        String name2;
        int space = 0;

        for (int pos = 0; pos < database.length; pos++) {
            Scanner extract = new Scanner(database[pos]);
            name2 = extract.next();

            if (name.compareToIgnoreCase(name2) == 0) {
                return pos;
            }
        }
        return -1;
    }

    public void display(int pos) {
        String name, phone;
        Scanner extract = new Scanner(database[pos]);
        name = extract.next();
        phone = extract.next();
        System.out.printf("%-20s%-15s\n", name, phone);

    }
    
    /**
     * Displays the people records passed in.
     * 
     * @param people
     *            Person[] - records of people (from address book)
     */
    public void display(String[] people) {
        String msg = "";
        // create a string of all the enteries in the address book
        // no formating of the data - chose to keep it simple
        for (String s : people) {
        	Scanner sc = new Scanner(s);
            msg += sc.next() + "\t\t\t";
            msg += sc.next() + "\n";
        }
        JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays the people records passed in.
     * 
     * @param people
     *            Person[] - records of people (from address book)
     */
    public void displaygui(int pos) {
        String msg = "";
        // create a string of all the enteries in the address book
        // no formating of the data - chose to keep it simple
    	Scanner sc = new Scanner(database[pos]);
        msg += sc.next() + "\t\t\t";
        msg += sc.next() + "\n";
        JOptionPane.showMessageDialog(this, msg, "Address book enteries", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        System.out.printf("%-20s%-15s\n", heading1, heading2);
    }

    public void displayAll() {
        displayHeading();
        for (int i = 0; i < database.length; i++) {
            display(i);
        }
    }

    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[] temp = new String[database.length - 1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }

    public void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }

    public int getChoice() {
        int choice = 4;// default
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
            }
            if (choice > 0 && choice <= 5)
                done = true;
            else
                System.out.println("\nYour choice is incorrect, please try again");
        }
        return choice;
    }

    public void addPerson() {
        String name = "";
        String phone = "";
        boolean done = false;
        try {
        	name = JOptionPane.showInputDialog("Enter the person's name ");
            phone = JOptionPane.showInputDialog("Enter the person's phone number ");
        } catch (Exception e) {
        }
        add(name + " " + phone);
    }

    public void deletePerson() {
        String name = "";
        try {
        	name = JOptionPane.showInputDialog("Enter the person's name ");
        } catch (Exception e) {
        }
        if (!remove(name))
        	displayErrorMsg("Could not delete " + name);
        else
        	displayMsg(name + " was deleted successfully");
    }

    public void findPerson() {
        String name = "";
        try {
        	name = JOptionPane.showInputDialog("Enter the person's name ");
        } catch (Exception e) {
        }
        int pos = search(name);
        if (pos >= 0) {
            //displayHeading();
            displaygui(pos);
        } else {
        	displayErrorMsg("No such person");
        }
    }

    public void console() {
        int choice = 0;
        do {
            displayMenu();
            choice = getChoice();
            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    deletePerson();
                    break;
                case 3:
                    findPerson();
                    break;
                case 4:
                    displayAll();
                default:
                    // should not get here
            }

        } while (choice != 5);
    }
    
    // ------------------------------------------------------------ //
    						//GUI CODE//
    // ------------------------------------------------------------ //
    
    public void gui() {
        setSize(400, 400);// fix window size
        setVisible(true);// make window visible
        addKeyListener(new KeyBoardInput());// listen to keyboard input
    }
    
    /**
     * Clears and draws the main menu on the window.
     * 
     * @param g
     *            Graphics - device context to allow drawing on this window
     */
    private void displayMenuGUI(Graphics g) {
        Color c = this.getBackground();// colour to clear screen with
        g.setColor(c);// use that colour
        // colour in a rectangle the size of the window with that colour
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);// set colour to draw with now to black
        g.drawString("1) Add", 100, 100);
        g.drawString("2) Delete", 100, 120);
        g.drawString("3) Search", 100, 140);
        g.drawString("4) Display All", 100, 160);
        g.drawString("5) Exit", 100, 180);
    }
    
    /**
     * Displays the menu when window requires repainting.
     * 
     * @param g
     *            Graphics - device context for the window to draw on
     */
    public void paint(Graphics g) {
        displayMenuGUI(g);
    }
    
    /**
     * Displays a message on the title bar of the window.
     * 
     * @param msg
     *            String - non-error message to display
     */
    public void displayMsg(String msg) {
        setTitle(msg);
    }
    
    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /*
     * KeyBoardInput.
     *
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Responds when a key is pressed on the keyboard.
         * 
         * @param e
         *            KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent e) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + e.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
        }
    }
    
    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {

        switch (choice) {
        case 1:
            addPerson();
            break;
        case 2:
            deletePerson();
            break;
        case 3:
            findPerson();
            break;
        case 4:
        	display(database);
//            displayAll();
            break;
        case 5:
            System.exit(0);
            break;

        default:
            // should not get here
        }

    }
    
    public void run() {
    	gui();
    }
    
    // ------------------------------------------------------------
    
    
    public static void main(String[] args) {
    	
        new Main().run();
    }
}
