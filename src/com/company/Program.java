package com.company;

import java.io.*;
import java.util.ArrayList;

public class Program implements Serializable {
    private String lirem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    private static BookProgram bookProgram = new BookProgram();
    private static CustomerProgram customerProgram = new CustomerProgram();
    private static LibrarianProgram librarianProgram = new LibrarianProgram();
    private static UserProgram userProgram = new UserProgram();
    //private static ArrayList<Customer> customers = new ArrayList<>();
    //private static ArrayList<Librarian> librarians = new ArrayList<>();
    private static ArrayList<User> customers = new ArrayList<>();
    private static ArrayList<User> librarians = new ArrayList<>();
    //private Customer currentUser = new Customer("", "");
    private User currentUser = null;
    private ArrayList<Book> books = bookProgram.getBooks();
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Program() {
        currentUser = new Customer("Tobbe", "123");
        currentUser.addBook( new Book("Sagans torn", "Ang Jin", "Blabla", true ));
        currentUser.addBook( new Book("Stugan", "Cameron A", "Blabla", true ));
        currentUser.addBook( new Book("Stars", "Abbe FFADF", "Blabla", true ));

        customers.add( new Customer("Tobbe", "123"));
        customers.add( new Customer("Erik", "123"));
        customers.add( new Customer("Florind", "123"));
        customers.add( new Customer("Anna", "123"));
        customers.add( new Customer("Nils", "123"));
        customers.add( new Customer("Johan", "123"));
        customers.add( new Customer("Matthias", "123"));
        customers.add( new Customer("Mantas", "123"));
        customers.add( new Customer("Helena", "123"));
        customers.add( new Customer("Abbe", "123"));

        customers.get(0).addBook( new Book("Sagans torn", "Ang Jin", "Blabla", true ));
        customers.get(0).addBook( new Book("Moroten Sally", "Bruce Lee", "Blabla", true ));
        customers.get(2).addBook( new Book("Stugan", "Cameron A", "Blabla", true ));
        customers.get(2).addBook( new Book("Barnet", "Abbe S", "Blabla", true ));
        customers.get(5).addBook( new Book("Bilen", "Abbe F", "Blabla", true ));
        customers.get(6).addBook( new Book("Bordet", "Abbe G", "Blabla", true ));
        customers.get(6).addBook( new Book("Stars", "Abbe FFADF", "Blabla", true ));
        customers.get(8).addBook( new Book("Youtube", "Abbe FAD", "Blabla", true ));
        customers.get(8).addBook( new Book("Halå", "Abbe AF", "Blabla", true ));
        customers.get(8).addBook( new Book("SHej", "Abbe AF", "Blabla", true ));


        //menuLogIn();
        menuCustomer();
        //menuLibrarian();

        //fileLoadFiles();
        //menuLibrarian();
    }

    ////////////////////////////////////////////////////////////////

    private void fileLoadSavedObject(String userName) {
        /*FileUtility.loadObject(userName + "_user.ser");
        FileUtility.loadObject("libraryProgram.ser");*/
        //FileUtility.loadObject(userName + "_user.ser");

        //currentUser = (Customer) FileUtility.loadObject(userName);
        //currentUser = (User)FileUtility.loadObject(userName);
        books = (ArrayList<Book>) FileUtility.loadObject("books.ser");
        customers = (ArrayList<User>) FileUtility.loadObject("customers.ser");
        librarians = (ArrayList<User>) FileUtility.loadObject("librarians.ser");
    }

    private void createAccount(boolean trueForCustomerFalseForLibrarian) {
        String fileExt = "";
        if (trueForCustomerFalseForLibrarian == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }
        SC.messageFieldCenterWithBlankSpace("Hej och välkommen!");
        String userName = "";
        String userPassword = "";
        while (!userName.equals("1") || userPassword.equals("1")) {
            do {
                SC.messageFieldCenterWithBlankSpace("Vänligen ange ett användarnamn. Använd minst 3 tecken.");
                userName = SC.scanner.nextLine();
                if (userName.equals("9")) {
                    menuLogIn();
                }
                while (checkIfUserAlreadyExists(fileExt + userName) == true) {
                    SC.messageFieldCenterWithBlankSpace("Användarnamnet finns redan!");
                    userName = SC.scanner.nextLine();
                    if (userName.equals("9")) {
                        menuLogIn();
                    }
                }
            } while (userName.isBlank() || userName.length() < 3);
            SC.messageFieldCenterWithBlankSpace("Välkommen " + userName + "! Ange ett lösenord");
            userPassword = SC.scanner.nextLine();
            if (userPassword.equals("9")) {
                menuLogIn();
            }
            while (userPassword.isBlank() || userPassword.length() < 3) {
                SC.messageFieldCenterWithBlankSpace("Försök igen och använd minst 3 tecken.");
                userPassword = SC.scanner.nextLine();
                if (userPassword.equals("9")) {
                    menuLogIn();
                }
            }
            if (trueForCustomerFalseForLibrarian == true) {
                Customer customer = new Customer(userName, userPassword);
                customers.add(customer);
                currentUser = customer;
                FileUtility.saveObject(fileExt + userName + ".ser", checkUserByName(customers, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            } else {
                Librarian librarian = new Librarian(userName, userPassword);
                librarians.add(librarian);
                currentUser = librarian;
                FileUtility.saveObject(fileExt + userName + ".ser", checkUserByName(librarians, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            }
            fileLoadFiles();
            return;
        }
    }

    private void logIn(boolean trueForCustomerFalseForLibrarian) {
        boolean createAccountForCustomer = trueForCustomerFalseForLibrarian;
        String fileExt = "";
        if (createAccountForCustomer == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }

        File file = new File("./");
        String[] files = file.list();

        System.out.println("Vänligen fyll i ditt användarnamn eller tryck [9] för att återvända till startmenyn.");
        String userName = SC.scanner.nextLine();
        if (userName.equals("9")) {
            menuLogIn();
        }
        do {
            for (String str : files) {
                if (str.equalsIgnoreCase(fileExt + userName + ".ser")) {
                    currentUser = (User) FileUtility.loadObject(fileExt + userName + ".ser");
                    fileLoadFiles();

                    System.out.println("Ange ditt lösenord?");
                    String userPass = SC.scanner.nextLine();
                    if (userPass.equals("9")) {
                        menuLogIn();
                    }
                    while (!currentUser.getPassword().equals(userPass)) {
                        System.out.println("Lösenordet stämmer inte. Vänligen försök igen eller tryck [9] för att återvända till startmenyn.");
                        userPass = SC.scanner.nextLine();
                        if (userPass.equals("9")) {
                            menuLogIn();
                        }
                    }
                    return;
                }
            }
            System.out.println("Användarnamnet finns ej registrerat. Försök igen eller tryck [9] för att återvända till startmenyn.");
            userName = SC.scanner.nextLine();
            if (userName.equals("9")) {
                menuLogIn();
            }
        } while (true);
    }

    private boolean checkIfUserAlreadyExists(String userName) {
        File file = new File("./");
        String[] files = file.list();
        for (String str : files) {
            if (str.equalsIgnoreCase(userName + ".ser")) {
                return true;
            }
        }
        return false;
    }

    private User checkUserByName(ArrayList<User> userList, String nameOfUser) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(nameOfUser)) {
                return user;
            }
        }
        return null;
    }

    private void fileLoadFiles() {
        customers = (ArrayList<User>) FileUtility.loadObject("customers.ser");
        librarians = (ArrayList<User>) FileUtility.loadObject("librarians.ser");
        books = (ArrayList<Book>) FileUtility.loadObject("books.ser");
    }

    private void fileSaveFiles() {
        if (currentUser instanceof Customer) {
            FileUtility.saveObject("_customer_" + currentUser.getName() + ".ser", currentUser);
            FileUtility.saveObject("books.ser", books);
            FileUtility.saveObject("customers.ser", customers);
            FileUtility.saveObject("librarians.ser", librarians);
            return;
        }
        FileUtility.saveObject("_librarian_" + currentUser.getName() + ".ser", currentUser);
        FileUtility.saveObject("books.ser", books);
        FileUtility.saveObject("customers.ser", customers);
        FileUtility.saveObject("librarians.ser", librarians);
    }

    ////////////////////////////////////////////////////////////////

    private void menuLogIn() {

        SC.messageFieldCenterWithoutBlankSpace("V Ä L K O M M E N");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("         .--.   .--.");
        SC.messageFieldCenterWithBlankSpace("     .--.|~~|---|  |");
        SC.messageFieldCenterWithBlankSpace(".---.|-P-|  | J |--|");
        SC.messageFieldCenterWithBlankSpace("| O ||-Y-|%%| A |  |");
        SC.messageFieldCenterWithBlankSpace("| O ||= =|  | V |  |");
        SC.messageFieldCenterWithBlankSpace("| P ||- -|%%| A |--|");
        SC.messageFieldCenterWithBlankSpace("`---'^---^--^---'--'");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Logga in som kund");
        SC.messageFieldLeftWithBlankSpace("[2] Logga in som bibliotekarie");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[3] Registrera dig som ny kund");
        SC.messageFieldLeftWithBlankSpace("[4] Registrera dig som ny bibliotekarie");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    logIn(true);
                    menuCustomer();
                    break;
                case "2":
                    logIn(false);
                    menuLibrarian();
                    break;
                case "3":
                    createAccount(true);
                    menuCustomer();
                    break;
                case "4":
                    createAccount(false);
                    menuLibrarian();
                    break;
                case "0":
                    SC.exitProgram();
                    break;
                default:
                    SC.messageFieldCenterWithBlankSpace("Vänligen föesök igen!");
                    break;
            }
        }
    }

    private void menuCustomer() {
        SC.messageFieldCenterWithoutBlankSpace("KUNDMENY");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace(".---.          ");
        SC.messageFieldCenterWithBlankSpace("|~ ~|     .---.");
        SC.messageFieldCenterWithBlankSpace("| J |.---.| H |");
        SC.messageFieldCenterWithBlankSpace("|~ ~||-C-|| T |");
        SC.messageFieldCenterWithBlankSpace("| S ||-S-|| M |");
        SC.messageFieldCenterWithBlankSpace("|~ ~||-S-|| L |");
        SC.messageFieldCenterWithBlankSpace("'---''---''---'");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Visa bibliotekets böcker");
        SC.messageFieldLeftWithBlankSpace("[2] Visa tillgängliga böcker att låna");
        SC.messageFieldLeftWithBlankSpace("[3] Visa mina lånade böcker");
        SC.messageFieldLeftWithBlankSpace("[4] Låna bok");
        SC.messageFieldLeftWithBlankSpace("[5] Lämna tillbaka lånad bok");
        SC.messageFieldLeftWithBlankSpace("[6] Visa listan sorterad efter titel");
        SC.messageFieldLeftWithBlankSpace("[7] Visa listan sorterad efter författare");
        SC.messageFieldLeftWithBlankSpace("[9] Logga ut");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "2":
                    bookProgram.showAvailableOrNotAvailableBook(books, true,
                            "Fins det några snälla böcker kvar?",
                            "Dessa böcker är tillgängliga att låna.",
                           "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "3":
                    bookProgram.showAvailableBookListWithRandomInformation(currentUser.getBooks(), false, true, true, true, false, "Du har har inte lånat något... än ;)");
                    SC.pressKeyToQuitMenu("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "4":
                    bookProgram.showAvailableOrNotAvailableBook(books, true,
                            "Fins det några snälla böcker kvar?",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    customerProgram.addBookBySearch(books, currentUser);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Boken är tillagd! Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "5":
                    bookProgram.showBooksList(currentUser.getBooks(), SC.messageFieldCenterWithBlankSpaceReturn("Din lista är tom. Du har inga lånade böcker."));
                    customerProgram.returnBookBySearch(currentUser.getBooks(), currentUser);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Boken är tillbaka lämnad. Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "6":
                    bookProgram.sortByTitle(books);
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "7":
                    bookProgram.sortByAuthor(books);
                    bookProgram.showBooksList(currentUser.getBooks(), SC.messageFieldCenterWithBlankSpaceReturn("Din lista är tom. Du har inga lånade böcker."));
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "9":
                    fileSaveFiles();
                    menuLogIn();
                    break;
                case "0":
                    fileSaveFiles();
                    SC.exitProgram();
                    break;
                default:
                    SC.messageFieldCenterWithBlankSpace("Vänligen föesök igen!");
                    break;
            }
        }
    }

    private void menuLibrarian() {
        boolean quitMenu = false;

        SC.messageFieldCenterWithoutBlankSpace("BIBLIOTEKARIEMENY");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("         .--.   .--.");
        SC.messageFieldCenterWithBlankSpace("     .--.|~~|---|  |");
        SC.messageFieldCenterWithBlankSpace(".---.|-P-|  | J |--|");
        SC.messageFieldCenterWithBlankSpace("| O ||-Y-|%%| A |  |");
        SC.messageFieldCenterWithBlankSpace("| O ||= =|  | V |  |");
        SC.messageFieldCenterWithBlankSpace("| P ||-3-|%%| A |--|");
        SC.messageFieldCenterWithBlankSpace("`---'^---^--^---'--'");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Visa bibliotekets böcker");
        SC.messageFieldLeftWithBlankSpace("[2] Visa lista med tillgängliga böcker att låna");
        SC.messageFieldLeftWithBlankSpace("[3] Visa lista med utlånade böcker");
        SC.messageFieldLeftWithBlankSpace("[4] Lägg till bok i biblioteket");
        SC.messageFieldLeftWithBlankSpace("[5] Ta bort bok från biblioteket");
        SC.messageFieldLeftWithBlankSpace("[6] Visa lista med alla användare");
        SC.messageFieldLeftWithBlankSpace("[7] Visa lista med alla användare med lånade böcker");
        SC.messageFieldLeftWithBlankSpace("[8] Sök efter användare");
        SC.messageFieldLeftWithBlankSpace("[9] Logga ut");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "2":
                    bookProgram.showAvailableOrNotAvailableBook(books, true, "Här kan du se vad vi har som inte är utlånat... än i alla fall :)",
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "3":
                    bookProgram.showAvailableOrNotAvailableBook(currentUser.getBooks(), false, "Här kan du se vad vi har som inte är utlånat... än i alla fall :)",
                            SC.messageFieldCenterWithBlankSpaceReturn("Alla böcker är utlånade."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    /*bookProgram.showAvailableOrNotAvailableBook(books,false,"Här kan du se vad vi har som inte är utlånat... än i alla fall :)",
                            SC.messageFieldCenterWithBlankSpaceReturn("Alla böcker är utlånade."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));*/
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "4":
                    librarianProgram.addBooksToLibrary(books,
                            SC.messageFieldCenterWithBlankSpaceReturn("Ange bokstitel:"),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skriv författarens namn."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skiv kort om vad boken handlar om."));
                    SC.pressKeyToQuitMenu("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "5":
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    /*bookProgram.showAvailableOrNotAvailableBook(books,true,"Här kan du se vad vi har som inte är utlånat... än i alla fall :)",
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));*/
                    librarianProgram.removeBook(books);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "6":
                    librarianProgram.showUserNameAndOrBooks(customers,false);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "7":
                    librarianProgram.showUserNameAndOrBooks(customers, true);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "8":
                    librarianProgram.showUserByName(customers,"Välkommen","Din sökning gav flera resultat", "Den du sökte efter finns inte.","Listan är tom");
                    menuLibrarian();
                    break;
                case "9":
                    menuLogIn();
                    break;
                case "0":
                    SC.exitProgram();
                    break;
                default:
                    SC.messageFieldCenterWithBlankSpace("Vänligen försök igen!");
                    break;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static BookProgram getBookProgram() {
        return bookProgram;
    }

    public static CustomerProgram getCustomerProgram() {
        return customerProgram;
    }

    public static LibrarianProgram getLibrarianProgram() {
        return librarianProgram;
    }

    public static UserProgram getUserProgram() {
        return userProgram;
    }

    /*public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }*/

}