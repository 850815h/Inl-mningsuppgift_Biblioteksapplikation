package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Book> books = new ArrayList<>();

    public Program() {
        //menuCustomer();
        //menuLibrarian();
        //menuCustomer();
        //logInUser();

        //Save
        /*System.out.println(customers.size());
        User user = new Customer("Hassan","1234");
        currentUser = user;
        customers.add( user );
        System.out.println( currentUser.getName());
        FileUtility.saveObject("_customer_"+currentUser.getName()+".ser",currentUser);
        FileUtility.saveObject("customers.ser",customers);
        System.out.println(customers.size());*/

        //Load
        /*System.out.println(customers.size());
        currentUser = (User)FileUtility.loadObject("_customer_Hassan.ser");
        customers = (ArrayList<User>)FileUtility.loadObject("customers.ser");
        System.out.println( currentUser.getName());
        System.out.println(customers.size());*/

        logIn(true); //customer
        /*String str = "0123456789";
        String[] ss = str.split("");
        String userI = SC.scanner.nextLine();
            boolean a = false;
        do {
            for (String s : ss) {
                if (s.equalsIgnoreCase(userI)) {
                    System.out.println("Gööt");
                    return;
                }
            }
            System.out.println("igen");
            userI = SC.scanner.nextLine();
        }while(a == false);*/

    }

    private void saveSettingsAndLogOut() {
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

    private void loadSavedObject(String userName) {
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
        boolean createAccountForCustomer = trueForCustomerFalseForLibrarian;
        String fileExt = "";
        if (createAccountForCustomer == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }
        SC.messageFieldCenterWithBlankSpace("Hej och välkommen!");
        //SC.messageFieldCenterWithBlankSpace("Vänligen ange ett användarnamn. Använd minst 3 tecken.");
        String userName = "";//SC.scanner.nextLine();
        do {
            SC.messageFieldCenterWithBlankSpace("Vänligen ange ett användarnamn. Använd minst 3 tecken.");
            userName = SC.scanner.nextLine();
            while (checkIfUserAlreadyExists(fileExt + userName) == true) {
                SC.messageFieldCenterWithBlankSpace("Användarnamnet finns redan!");
                userName = SC.scanner.nextLine();
            }
        } while (userName.isBlank() || userName.length() < 3);
        SC.messageFieldCenterWithBlankSpace("Vänligen ange ett lösenord. Använd minst 3 tecken.");
        String userPassword = SC.scanner.nextLine();
        while (userPassword.isBlank() || userPassword.length() < 3) {
            SC.messageFieldCenterWithBlankSpace("Vänligen ange ett lösenord igen. Använd minst 3 tecken.");
            userPassword = SC.scanner.nextLine();
        }
        if (createAccountForCustomer == true) {
            customers.add(new Customer(userName, userPassword));
            FileUtility.saveObject(fileExt + userName + ".ser", searchCustomerByName(customers, userName));
            loadSavedObject(fileExt + userName + ".ser");
        } else {
            librarians.add(new Customer(userName, userPassword));
            FileUtility.saveObject(fileExt + userName + ".ser", searchCustomerByName(librarians, userName));
            loadSavedObject(fileExt + userName + ".ser");
        }
        return;
        //createNewCustomerAccount();
    }

    /*
    private void createNewCustomerAccount(boolean accountForCustomer) {
        boolean createAccountForCustomer = accountForCustomer;
        String fileExt = "";
        if( createAccountForCustomer == true ){
            fileExt = "_user.ser";
        } else {
            fileExt = "_librarian.ser";
        }
        SC.messageFieldCenterWithBlankSpace("Hej och välkommen!");
        SC.messageFieldCenterWithBlankSpace("Vänligen ange ett användarnamn. Använd minst 3 tecken.");
        String userName = SC.scanner.nextLine();
        do{
            SC.messageFieldCenterWithBlankSpace("Vänligen ange ett användarnamn igen. Använd minst 3 tecken.");
            userName = SC.scanner.nextLine();
            while (checkIfUserAlreadyExists(userName) == true) {
                SC.messageFieldCenterWithBlankSpace("Användarnamnet finns redan!");
                userName = SC.scanner.nextLine();
            }
        }while (userName.isBlank() || userName.length() < 3);
            SC.messageFieldCenterWithBlankSpace("Vänligen ange ett lösenord. Använd minst 3 tecken.");
        String userPassword = SC.scanner.nextLine();
        while (userPassword.isBlank() || userPassword.length() < 3) {
            SC.messageFieldCenterWithBlankSpace("Vänligen ange ett lösenord igen. Använd minst 3 tecken.");
            userPassword = SC.scanner.nextLine();
        }
        LibraryProgram.addCustomer(new Customer(userName, userPassword));
        FileUtility.saveObject(userName + "_user.ser", searchCustomerByName(customers, userName));
        saveSettingsAndLogOut();

        /*FileUtility.saveObject("books.ser", books);
        FileUtility.saveObject("customers.ser", customers);
        FileUtility.saveObject("librarians.ser", librarians);
    //FileUtility.saveObject("libraryProgram.ser", libraryProgram);
    //LibraryProgram libraryProgram = new LibraryProgram();

        return;
    //createNewCustomerAccount();
}
     */

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

    private User searchCustomerByName(ArrayList<User> userList, String userToFind) {
        if (userList.size() > 0) {
            for (User user : userList) {
                if (user.getName().equalsIgnoreCase(userToFind)) {
                    return user;
                }
            }
        }
        SC.messageFieldCenterWithBlankSpace("Listan av customers är tom!");
        return null;
    }

    private void showUsers(ArrayList<User> userList) {
        if (userList.size() > 0) {
            for (User user : userList) {
                SC.messageFieldCenterWithBlankSpace(user.getName() + " " + user.getPassword());
            }
            return;
        }
        SC.messageFieldCenterWithBlankSpace("Listan av customers är tom!");
    }

    private void fileLoadListCustomers() {
        customers = (ArrayList<User>) FileUtility.loadObject("customers.ser");
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

        String strstr = "0123456789";
        String[] ss = strstr.split("");
        boolean aaa = false;

        System.out.println("Ange ett nummer");
        String userI = SC.scanner.nextLine();
        do {
            for (String s : ss) {
                System.out.println(s);
                if (s.equalsIgnoreCase(userI)) {
                    System.out.println("Gööt");
                    aaa = true;
                    break;
                }
            }
            System.out.println("Testa igen med numret");
            userI = SC.scanner.nextLine();
        }while(aaa == false);

        System.out.println("Vänligen fyll i ditt användarnamn");
        String userName = SC.scanner.nextLine();
        do {
            for (String str : files) {
                System.out.println(str);
                if (str.equalsIgnoreCase(fileExt + userName + ".ser")) {
                    System.out.println("Gööt");
                    return;
                }
            }
            System.out.println("Testa igen med numret");
            userName = SC.scanner.nextLine();
        }while(true);

        /*System.out.println("Vänligen fyll i ditt användarnamn");
        String userName = SC.scanner.nextLine();
        do {
            for (String str : files) {
                if (str.equalsIgnoreCase(fileExt + userName + ".ser")) {
                    System.out.println(str);

                    currentUser = (User) FileUtility.loadObject(fileExt + userName + ".ser");
                    fileLoadListCustomers();
                    //customers = (ArrayList<User>)FileUtility.loadObject("customers.ser");
                    //System.out.println( currentUser.getName());

                    System.out.println("lösen: " + currentUser.getPassword());
                    System.out.println("Fyll i ditt lösenord?");
                    String userPass = SC.scanner.nextLine();
                    while (!currentUser.getPassword().equals(userPass)) {
                        System.out.println("Lösenordet stämmer inte, vänligen försök igen!");
                        userPass = SC.scanner.nextLine();
                    }
                    System.out.println("Välkommen tillbaka " + currentUser.getName());
                    //System.out.println("Välkommen tillbaka " + LibraryProgram.getCurrentCustomer().getName());
                    //loadSavedObject(userName);
                    //loadSavedObject("libraryProgram.ser");

                    //loadSavedObject(fileExt+userName + ".ser");
                    //currentUser = (Customer) FileUtility.loadObject(fileExt+userName + ".ser");
                    //LibraryProgram.setCurrentCustomer((Customer) FileUtility.loadObject(userName + "_user.ser"));
                    return;
                }
                System.out.println("Användarnamnet finns ej! Vänligen försök igen.");
                userName = SC.scanner.nextLine();
            }
        }while (true);*/
    }
    /*
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
        String userName = "";
        do {
            System.out.println("Vänligen skriv in ditt användarnamn");
            userName = SC.scanner.nextLine();
            for (String str : files) {
                if (str.equalsIgnoreCase(fileExt + userName + ".ser")) {
                    //loadSavedObject(fileExt+userName + ".ser");
                    //currentUser = (Customer) FileUtility.loadObject(fileExt+userName + ".ser");
                    //LibraryProgram.setCurrentCustomer((Customer) FileUtility.loadObject(userName + "_user.ser"));
                }

                System.out.println("lösen: " + currentUser.getPassword());
                System.out.println("Ange ditt lösenord?");
                String userPass = SC.scanner.nextLine();
                //while (!currentCustomer.getPassword().equals(userPass)) {
                while (!currentUser.getPassword().equals(userPass)) {
                    System.out.println("Lösenordet stämmer inte, vänligen försök igen!");
                    userPass = SC.scanner.nextLine();
                }
                System.out.println("Välkommen tillbaka " + currentUser.getName());
                //System.out.println("Välkommen tillbaka " + LibraryProgram.getCurrentCustomer().getName());
                //loadSavedObject(userName);
                //loadSavedObject("libraryProgram.ser");
                return;
            }
        } while (true);
    }
     */

    private void menuMain() {
        boolean quitMenu = false;

        SC.messageFieldCenterWithoutBlankSpace("MAIN  MENU");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("         .--.   .--.");
        SC.messageFieldCenterWithBlankSpace("     .--.|~~|---|  |");
        SC.messageFieldCenterWithBlankSpace(".---.|-P-|  | J |--|");
        SC.messageFieldCenterWithBlankSpace("| O ||-Y-|%%| A |  |");
        SC.messageFieldCenterWithBlankSpace("| O ||= =|  | V |  |");
        SC.messageFieldCenterWithBlankSpace("| P ||- -|%%| A |--|");
        SC.messageFieldCenterWithBlankSpace("`---'^---^--^---'--'");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Logga in");
        SC.messageFieldLeftWithBlankSpace("[2] Registrera dig");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    showBooksInLibrary();
                    //mainMenu();
                    break;
                case "2":
                    editBooksInLibrary();
                    //mainMenu();
                    break;
                case "9":
                    SC.exitProgram();
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

    private void menuLogIn() {
        boolean quitMenu = false;

        SC.messageFieldCenterWithoutBlankSpace("MAIN  MENU");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("Vänligen välj ett av följande alternativ");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Logga in som kund");
        SC.messageFieldLeftWithBlankSpace("[1] Logga in som bibliotekarie");
        SC.messageFieldLeftWithBlankSpace("[9] Återvänd till startmeny");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    menuLogInCutomer();
                    //mainMenu();
                    break;
                case "2":
                    editBooksInLibrary();
                    //mainMenu();
                    break;
                case "9":
                    menuMain();
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

    private void menuLogInCutomer() {
        boolean quitMenu = false;

        SC.messageFieldCenterWithoutBlankSpace("MAIN  MENU");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace(".---.");
        SC.messageFieldCenterWithBlankSpace("| O |");
        SC.messageFieldCenterWithBlankSpace("| O |");
        SC.messageFieldCenterWithBlankSpace("| P |");
        SC.messageFieldCenterWithBlankSpace("`---'");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("Vänligen fyll i dina uppgifter och");
        SC.messageFieldCenterWithBlankSpace("tryck sedan på [ ENTER ]");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[9] Återvänd till startmeny");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "9":
                    menuMain();
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

    private void menuRegisterNewUser() {
        boolean quitMenu = false;

        SC.messageFieldCenterWithoutBlankSpace("LOGGA IN MENY");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("         .--.   .--.");
        SC.messageFieldCenterWithBlankSpace("     .--.|~~|---|  |");
        SC.messageFieldCenterWithBlankSpace(".---.|-P-|  | J |--|");
        SC.messageFieldCenterWithBlankSpace("| O ||-Y-|%%| A |  |");
        SC.messageFieldCenterWithBlankSpace("| O ||= =|  | V |  |");
        SC.messageFieldCenterWithBlankSpace("| P ||- -|%%| A |--|");
        SC.messageFieldCenterWithBlankSpace("`---'^---^--^---'--'");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Logga in");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    showBooksInLibrary();
                    //mainMenu();
                    break;
                case "2":
                    editBooksInLibrary();
                    //mainMenu();
                    break;
                case "9":
                    SC.exitProgram();
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
        SC.messageFieldLeftWithBlankSpace("[6] Sök efter användare med lånade böcker");
        SC.messageFieldLeftWithBlankSpace("[7] Spara mina uppgifter");
        SC.messageFieldLeftWithBlankSpace("[9] Återgå till huvudmeny");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    bookProgram.showAvailableBookWithRandomInformation(false, true, true, true, true);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "2":
                    bookProgram.showAvailableOrNotAvailableBook(true,
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "3":
                    bookProgram.showAvailableOrNotAvailableBook(false,
                            SC.messageFieldCenterWithBlankSpaceReturn("Alla böcker är utlånade."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "4":
                    librarianProgram.addBooksToLibrary(
                            SC.messageFieldCenterWithBlankSpaceReturn("Ange bokstitel:"),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skriv författarens namn."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skiv kort om vad boken handlar om."));
                    SC.pressKeyToQuitMenu("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "5":
                    bookProgram.showAvailableBookWithRandomInformation(false, true, true, true, true);
                    librarianProgram.removeBooksFromLibrary(
                            SC.messageFieldCenterWithBlankSpaceReturn("Skriv boktitel eller namnet på författaren för att ta bort boken."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Tyvärr hittades inget med din sökning, försök igen!"),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom."));
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "6":
                    editBooksInLibrary();
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "7":
                    editBooksInLibrary();
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "9":
                    menuMain();
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
        boolean quitMenu = false;

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
        SC.messageFieldLeftWithBlankSpace("[8] Spara mina uppgifter");
        SC.messageFieldLeftWithBlankSpace("[9] Logga ut och spara mina uppgifter");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    bookProgram.showAvailableBookWithRandomInformation(false, true, true, true, true);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "2":
                    bookProgram.showAvailableOrNotAvailableBook(true,
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "3":
                    customerProgram.showMyBorrowedBooks(currentUser.getBooks(), SC.messageFieldCenterWithBlankSpaceReturn("Din lista är tom. Du har inga lånade böcker."));
                    //SC.messageFieldCenterWithBlankSpace( "Boken ");
                    SC.pressKeyToQuitMenu("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "4":
                    //bookProgram.sortByTitle();
                    bookProgram.showAvailableBookWithRandomInformation(false, true, true, true, true);
                    customerProgram.addBookBySearch(currentUser);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Boken är tillagd! Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "5":
                    bookProgram.sortByAuthor();
                    customerProgram.showMyBorrowedBooks(currentUser.getBooks(), SC.messageFieldCenterWithBlankSpaceReturn("Din lista är tom. Du har inga lånade böcker."));
                    customerProgram.returnBookBySearch(currentUser);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Boken är tillbaka lämnad. Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "6":
                    bookProgram.sortByAuthor();
                    bookProgram.showAvailableBookWithRandomInformation(false, true, true, true, true);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "7":
                    bookProgram.sortByTitle();
                    bookProgram.showAvailableBookWithRandomInformation(false, true, true, true, true);
                    SC.pressKeyToQuitMenu(SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "9":
                    menuMain();
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

    private void showBooksInLibrary() {
        SC.messageFieldCenterWithoutBlankSpace("BÖCKER SOM FINNS I BIBLIOTEKET");
        //bookProgram.showAllBookList();
        SC.messageFieldWholeWithoutBlankSpace();

        SC.messageFieldCenterWithoutBlankSpace("BÖCKER I BIBLIOTEKET");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldCenterWithBlankSpace("Vänligen välj ett av följande alternativ");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[1] Låna tillgänglig bok");
        SC.messageFieldLeftWithBlankSpace("[2] Återlämna lånad bok");
        SC.messageFieldLeftWithBlankSpace("[3] Sök efter titel");
        SC.messageFieldLeftWithBlankSpace("[4] Sök efter författare");
        SC.messageFieldLeftWithBlankSpace("[9] Återgå till huvudmeny");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    menuBorrowAvailableBook();
                    //menuCustomer();
                    //menuMain();
                    break;
                case "2":
                    menuReturnBook();
                    //menuMain();
                    break;
                case "3":
                    menuSearchByTitle();
                    //menuMain();
                    break;
                case "4":
                    menuSearchByAuthor();
                    //menuMain();
                    break;
                case "9":
                    menuMain();
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

    private void menuBorrowAvailableBook() {

    }

    private void menuReturnBook() {

    }

    private void menuSearchByTitle() {

    }

    private void menuSearchByAuthor() {

    }

    private void editBooksInLibrary() {

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