import java.util.Scanner;


public class ConsoleUI implements UI
{
    private final Scanner input;
    private AddressBook addressBook;

    public ConsoleUI()
    {
        input = new Scanner(System.in);
    }

    public int readChoice() {

        int choice = 0;

        do {
            displayMsg("Choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                displayMsg("\nYour choice is not recognized, please try again! ");
                choice = input.nextInt();
            }
        } while (choice < 1 || choice > 5);

        return choice;
    }

    public void display(Person person) {
        System.out.println(person);
    }

    public void displayAll(Person[] people) {
        // a for loop that loops through the person array, displays and formats it
        for (int i = 0; i < people.length; i++) {
            String name = people[i].getName();
            String phone = people[i].getPhoneNumber();
            System.out.printf("%-20s\t\t%-15s\n", name, phone);
        }

    }

    public String readName() {
    	// reads a name
        String name;
        displayMsg("Enter a name: ");
        name = input.next();
        return name;
    }

    public Person readPerson() {
    	
    	// grabs input from user
        String name;
        String phoneNum;

        displayMsg("Enter a name: ");
        name = input.next();
        displayMsg("Enter a Phone Number: ");
        phoneNum = input.next();

        return new Person(name, phoneNum);
    }

    public void run(AddressBook book) {
    	// runs the program
        int choice;

        do {
            displayMenu();
            choice = readChoice();

            switch (choice) {
                case 1:
                    book.addPerson();
                    break;
                case 2:
                    book.deletePerson();
                    break;
                case 3:
                    book.findPerson();
                    break;
                case 4:
                    book.displayAll();
                    break;
                case 5:
                    displayMsg("Exiting the program...\nBye Bye!");
                    break;
                default:
                    displayErrorMsg("Invalid Choice, please choose another option: ");
            }
        } while (choice != 5);
    }

    public void displayMenu() {
    	// displays the menu
        displayMsg("\n\n\n1) Add");
        displayMsg("2) Delete");
        displayMsg("3) Search");
        displayMsg("4) Display All");
        displayMsg("5) Exit\n");
    }

    public void displayMsg(String msg) {
        System.out.println(msg);
    }

    public void displayErrorMsg(String msg) {
        System.out.println(msg);
    }
}

