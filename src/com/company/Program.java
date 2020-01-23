package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
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
    private static ArrayList<User> users = new ArrayList<>();
    //private Customer currentUser = new Customer("", "");
    //private static User currentUser = new Customer("Admin", "1234");
    private static Integer currentUserIndex = 0;
    private static ArrayList<Book> books = bookProgram.getBooks();
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    private final int MAX_ALLOWED_BORROW_DAY = 234;
    private Integer[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 31, 31};
    private LocalDate localDate = LocalDate.now();
    private int thisDay = localDate.getDayOfMonth();
    private int thisMonth = localDate.getMonthValue();
    private int thisYear = localDate.getYear();
    private int returnDay = localDate.getDayOfMonth();
    private int returnMonth = localDate.getMonthValue();
    private int returnYear = localDate.getYear();

    //private LocalDate dateToReturnBook = LocalDate.of( localDate.getYear(), localDate.getMonthValue()+1, localDate.getDayOfMonth());


    public Program() {
        //fileSaveFiles();
        fileLoadFiles();
        menuLogIn();
        System.out.println(books.size());

        /*users.add( new Customer("Hassan", "123"));
        FileUtility.saveObject("aaa.ser",users.get(0));
        //menuCustomer();*/

    }

    ///////////////////////////////////////////////////////////////


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

    ////////////////////////////////////////////////////////////////////////////

    private void createAccount(boolean trueForCustomerFalseForLibrarian) {
        String fileExt = "";
        if (trueForCustomerFalseForLibrarian == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }
        SC.messageFieldCenterWithBlankSpace("Hej och välkommen!");
        SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
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
                    SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");

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
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                userPassword = SC.scanner.nextLine();
                if (userPassword.equals("9")) {
                    menuLogIn();
                }
            }
            if (trueForCustomerFalseForLibrarian == true) {
                Customer customer = new Customer(userName, userPassword);
                users.add(customer);
                currentUserIndex = returnUserIndex(customer);
                FileUtility.saveObject(fileExt + userName + ".ser", checkUserByName(users, userName));
                //currentUser = customer;
                //fileLoadSavedObject(fileExt + userName + ".ser");
            } else {
                Librarian librarian = new Librarian(userName, userPassword);
                users.add(librarian);
                currentUserIndex = returnUserIndex(librarian);
                FileUtility.saveObject(fileExt + userName + ".ser", checkUserByName(users, userName));
                //fileLoadSavedObject(fileExt + userName + ".ser");
            }
            fileSaveFiles();
            //fileLoadFiles();
            return;
        }
    }
    /*
    private void createAccount(boolean trueForCustomerFalseForLibrarian) {
        String fileExt = "";
        if (trueForCustomerFalseForLibrarian == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }
        SC.messageFieldCenterWithBlankSpace("Hej och välkommen!");
        SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
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
                    SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");

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
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
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
            fileSaveFiles();
            fileLoadFiles();
            return;
        }
    }
     */

    private void logIn(boolean trueForCustomerFalseForLibrarian) {
        fileLoadFiles();
        boolean createAccountForCustomer = trueForCustomerFalseForLibrarian;
        String fileExt = "";
        if (createAccountForCustomer == true) {
            fileExt = "_customer_";
        } else {
            fileExt = "_librarian_";
        }

        File file = new File("./");
        String[] files = file.list();

        SC.messageFieldCenterWithBlankSpace("Vänligen fyll i ditt användarnamn ");
        SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
        String userName = SC.scanner.nextLine();
        if (userName.equals("9")) {
            menuLogIn();
        }
        do {
            for (String str : files) {
                if (str.equalsIgnoreCase(fileExt + userName + ".ser")) {
                    //currentUser = (User) FileUtility.loadObject(fileExt + userName + ".ser");
                    //fileLoadFiles();
                    //User user = (User) FileUtility.loadObject(fileExt + userName + ".ser");
                    User user = (User) FileUtility.loadObject(fileExt + userName + ".ser");
                    currentUserIndex = returnUserIndex(user)+1;
                    System.out.println("currentUserIndex " +currentUserIndex);

                    System.out.println("Ange ditt lösenord?");
                    String userPass = SC.scanner.nextLine();
                    if (userPass.equals("9")) {
                        menuLogIn();
                    }
                    //try {
                        while (!user.getPassword().equals(userPass)) {
                            //while (!users.get(currentUserIndex).getPassword().equals(userPass)) {
                            System.out.println("p currentUserIndex: " + currentUserIndex);
                            SC.messageFieldCenterWithBlankSpace("Lösenordet stämmer inte. Vänligen försök igen.");
                            SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                            userPass = SC.scanner.nextLine();
                            if (userPass.equals("9")) {
                                menuLogIn();
                            }
                        }
                    //}catch (Exception e){}
                    return;
                }
            }
            SC.messageFieldCenterWithBlankSpace("Användarnamnet finns ej registrerat. Vänligen försök igen.");
            SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
            userName = SC.scanner.nextLine();
            if (userName.equals("9")) {
                menuLogIn();
            }
        } while (true);
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

        SC.messageFieldCenterWithBlankSpace("Vänligen fyll i ditt användarnamn ");
        SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
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
                        SC.messageFieldCenterWithBlankSpace("Lösenordet stämmer inte. Vänligen försök igen.");
                        SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                        userPass = SC.scanner.nextLine();
                        if (userPass.equals("9")) {
                            menuLogIn();
                        }
                    }
                    return;
                }
            }
            SC.messageFieldCenterWithBlankSpace("Användarnamnet finns ej registrerat. Vänligen försök igen.");
            SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
            userName = SC.scanner.nextLine();
            if (userName.equals("9")) {
                menuLogIn();
            }
        } while (true);
    }
     */

    private User findUser(User user){
        if( users.contains( user )){
            return user;
        }
        return null;
    }

    private int returnUserIndex(User user) {
        return users.indexOf(user);
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
        System.out.println("checkUserByName()");
        return null;
    }

    private void fileLoadFiles() {
        users = (ArrayList<User>) FileUtility.loadObject("users.ser");
        books = (ArrayList<Book>) FileUtility.loadObject("books.ser");
    }

    private void fileSaveFiles() {
        FileUtility.saveObject("users.ser", users);
        FileUtility.saveObject("books.ser", books);
        /*if (currentUser instanceof Customer) {
            FileUtility.saveObject("_customer_" + currentUser.getName() + ".ser", currentUser);
            FileUtility.saveObject("books.ser", books);
            FileUtility.saveObject("customers.ser", customers);
            FileUtility.saveObject("librarians.ser", librarians);
            return;
        }
        FileUtility.saveObject("_librarian_" + currentUser.getName() + ".ser", currentUser);
        FileUtility.saveObject("books.ser", books);
        FileUtility.saveObject("customers.ser", customers);
        FileUtility.saveObject("librarians.ser", librarians);*/
    }

    ////////////////////////////////////////////////////////////////////////////

    private void menuLogIn() {
        fileLoadFiles();
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
                    fileLoadFiles();
                    logIn(true);
                    menuCustomer();
                    break;
                case "2":
                    fileLoadFiles();
                    logIn(false);
                    menuLibrarian();
                    break;
                case "3":
                    fileLoadFiles();
                    createAccount(true);
                    menuCustomer();
                    break;
                case "4":
                    fileLoadFiles();
                    createAccount(false);
                    menuLibrarian();
                    break;
                case "0":
                    fileSaveFiles();
                    SC.exitProgram();
                    break;
                default:
                    SC.messageFieldCenterWithBlankSpace("Vänligen försök igen!");
                    break;
            }
        }
    }

    private void menuCustomer() {
        SC.notificationMsgForNotReturnedBookInTime(users.get(currentUserIndex).getBooks());

        SC.messageFieldCenterWithoutBlankSpace("KUNDMENY");
        SC.messageFieldCenterWithoutBlankSpace("Välkommen " + users.get(currentUserIndex).getName());
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
        SC.messageFieldLeftWithBlankSpace("[8] Visa lånetid för varje bok");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldLeftWithBlankSpace("[9] Logga ut");
        SC.messageFieldLeftWithBlankSpace("[0] Avsluta applikation");
        SC.messageFieldLeftWithBlankSpace("");
        SC.messageFieldWholeWithoutBlankSpace();

        while (true) {
            switch (SC.userInput()) {
                case "1":
                    SC.notificationMsgForBorrowDays(users.get(currentUserIndex).getBooks());
                    SC.msgWelcomeSquare("Bibliotekets bokregister. Något intressant kanske?");
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "2":
                    //bookProgram.showAvailableOrNotAvailableBook(books, true,
                    SC.msgWelcomeSquare("Tillgängliga böcker att låna");
                    bookProgram.showOnlyAvailableBooks(books, true,
                            "Fins det några snälla böcker kvar?",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "3":
                    SC.msgWelcomeSquare("Mina lånade böcker");
                    bookProgram.showAvailableBookListWithRandomInformation(users.get(currentUserIndex).getBooks(), false, true, true, true, false, "Du har har inte lånat något... än ;)");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "4":
                    SC.msgWelcomeSquare("Låna här och hitta dem i \"Visa mina lånade böcker\"");
                    bookProgram.showAvailableBookListWithRandomInformation(books,
                            false,
                            true,
                            true,
                            false,
                            true,
                            "Du har har inte lånat något... än ;)");
                    customerProgram.loanBook();
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "5":
                    SC.msgWelcomeSquare("Kom ihåg att alltid lämna din bok i tid!");
                    //bookProgram.showBooksList(users.get(currentUserIndex).getBooks(), "Din lista är tom. Du har inga lånade böcker.");
                    Program.getBookProgram().showAvailableBookListWithRandomInformation(
                            users.get(currentUserIndex).getBooks(),
                            false,
                            true,
                            true,
                            false,
                            false,
                            "Du har inte lånat något som ska lämnas tillbaka.");
                    customerProgram.returnBook(users.get(currentUserIndex).getBooks(), users.get(currentUserIndex));
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "6":
                    SC.msgWelcomeSquare("Sorterar efter boktitel");
                    bookProgram.sortByTitle(books);
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "7":
                    SC.msgWelcomeSquare("Sorterar efter bokförfattare");
                    bookProgram.sortByAuthor(books);
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "8":
                    SC.msgWelcomeSquare("Bra att hålla koll på när min bok ska lämnas tillbaka");
                    SC.notificationMsgForBorrowDays(users.get(currentUserIndex).getBooks());
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
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
                    SC.msgWelcomeSquare("Som bibliotekarie kan du lägga till / ta bort böcker");
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "2":
                    SC.msgWelcomeSquare("Böcker som kunder kan låna");
                    bookProgram.showOnlyAvailableBooks(books, true,
                            "Fins det några snälla böcker kvar?",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "3":
                    SC.msgWelcomeSquare("Utlånade böcker");
                    bookProgram.showOnlyAvailableBooks(books, false,
                            "",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "4":
                    SC.msgWelcomeSquare("Lägg till böcker och attrahera fler läsare till oss");
                    librarianProgram.addBooksToLibrary(books,
                            SC.messageFieldCenterWithBlankSpaceReturn("Ange bokstitel:"),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skriv författarens namn."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skiv kort om vad boken handlar om."));
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "5":
                    SC.msgWelcomeSquare("Har du hittat något tråkigt att ta bort?");
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    /*bookProgram.showAvailableOrNotAvailableBook(books,true,"Här kan du se vad vi har som inte är utlånat... än i alla fall :)",
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));*/
                    librarianProgram.removeBook(books);
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "6":
                    SC.msgWelcomeSquare("Här visas listan med alla registrerade användare");
                    librarianProgram.showUserNameAndOrBooks(users, false);
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "7":
                    SC.msgWelcomeSquare("Utlånade böcker och till vilka användare");
                    librarianProgram.showUserNameAndOrBooks(users, true);
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
                    break;
                case "8":
                    SC.msgWelcomeSquare("Sök efter användare med hela namnet eller bara en bokstav?");
                    librarianProgram.showUserNameAndOrBooks(users, false);
                    librarianProgram.showUserByName(users, "Välkommen", "Din sökning gav flera resultat", "Den du sökte efter finns inte.", "Listan är tom");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    menuLibrarian();
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

    public static User getCurrentUser() {
        return users.get(currentUserIndex);
    }

    public static ArrayList<Book> getBooks(){
        return books;
    }

    public static void addBook(Book bookToAdd){
        books.add( bookToAdd);
    }

    /*public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }*/

}