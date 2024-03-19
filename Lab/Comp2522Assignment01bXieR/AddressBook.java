/*
 * AddressBook.java

 */


public class AddressBook
{
    private final Database database;
    private final UI       ui;

    public AddressBook(final UI u)
    {
        ui       = u;
        database = new Database();
    }

    public void addPerson()
    {
    	// adds a person
        Person p = ui.readPerson();
        database.add(p);
    }

    public void deletePerson()
    {
    	// deletes the person
        String name = ui.readName();
        boolean isRemoved = remove(name);

        if (isRemoved) {
            ui.displayMsg(name + " was successfully deleted!");
        } else {
            ui.displayMsg("There is an error!\n" + name + "was not found in the database!");
        }
    }

    public void findPerson()
    {
    	// finds a person in the array
        String name = ui.readName();
        Person p = search(name);

        if (p != null) {
            ui.displayMsg(name);
        } else {
            ui.displayErrorMsg(name + "cannot be found! ");
        }
    }

    private boolean remove(final String name)
    {
        return (database.removeByName(name) != null);
    }

    private Person search(final String name)
    {
        return (database.findByName(name));
    }

    public void displayAll ()
    {
        Person[] everyone = database.getAll();
        ui.displayAll(everyone);
    }

    private void display(final Person person)
    {
        ui.display(person);
    }
}
