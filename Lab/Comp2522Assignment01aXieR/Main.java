import java.util.Scanner;

public class Main {
  private String[] database;
  private Scanner input;

  public Main() {
    database = new String[0];
    input = new Scanner(System.in);
  }

  public void add(final String name) {
    // initialize size of array
    int size = database.length + 1;
    // creates new array with size equal to the database array
    String[] temp = new String[size];
    // Name is put into the new array
    temp[size - 1] = name;
    System.arraycopy(database, 0, temp, 0, database.length);
    // replaces the database array with new array
    database = temp;
  }

  public int search(final String name) {

    // a for loop that loops through and compares
    for (int index = 0; index < database.length; index++) {
      // splits on  " " to only match name of person
      if (name.equals(database[index].split(" ")[0])) {
        return index;
      }
    }
    return -1;
  }

  public void displayAll() {
    // a for loop that loops through the database array, displays and formats it
    for (int index = 0; index < database.length; index++) {
      String[] temp = database[index].split(" ");
      System.out.printf("%-20s\t\t%-15s\n", temp[0], temp[1]);
    }
  }

  public boolean remove(final String name) {
    int pos = search(name);
    if (pos >= 0) {
      String[] temp = new String[database.length - 1];
      System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
      System.arraycopy(database, 0, temp, 0, pos);
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
      System.out.print("Enter the persons name ");
      name = input.next();
      System.out.print("\nEnter the persons phone number ");
      phone = input.next();
      System.out.println("");
    } catch (Exception e) {
    }
    add(name + " " + phone);
  }

  public void deletePerson() {
    String name = "";
    try {
      System.out.print("Enter the persons name ");
      name = input.next();
      System.out.println("");
    } catch (Exception e) {
    }

    if (!remove(name))
      System.out.println("Could not delete " + name);
    else
      System.out.println(name + " was deleted successfully");
  }

  public void findPerson() {
    String name = "";
    try {
      System.out.print("Enter the persons name ");
      name = input.next();
      System.out.println("");
    } catch (Exception e) {
    }

    int pos = search(name);

    if (pos >= 0) {
      // ADD YOUR DISPLAY CODE HERE TO DISPLAY A SINGLE PERSON'S INFO
      String[] temp = database[pos].split(" ");
      System.out.printf("%-20s\t\t%-15s\n", temp[0], temp[1]);
    } else {
      System.out.println("No such person");
    }
  }

  public void run() {
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

  public static void main(String[] args) {
    new Main().run();
  }
}