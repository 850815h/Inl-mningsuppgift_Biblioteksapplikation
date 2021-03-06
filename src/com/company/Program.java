package com.company;

import java.io.*;
import java.util.ArrayList;

public class Program implements Serializable {
    private static BookProgram bookProgram = new BookProgram();
    private static CustomerProgram customerProgram = new CustomerProgram();
    private static LibrarianProgram librarianProgram = new LibrarianProgram();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Book> books = bookProgram.getBooks();
    private static Integer currentUserIndex = 0;

    public Program() {
        //fileSaveFiles();
        menuLogIn();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
            } else {
                Librarian librarian = new Librarian(userName, userPassword);
                users.add(librarian);
                currentUserIndex = returnUserIndex(librarian);
                FileUtility.saveObject(fileExt + userName + ".ser", checkUserByName(users, userName));
            }
            fileSaveFiles();
            return;
        }
    }

    private void logIn(boolean customerIfTrueLibrarianIfFalse) {
        SC.messageFieldCenterWithBlankSpace("Vänligen fyll i ditt användarnamn ");
        SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
        String userName = SC.scanner.nextLine();
        if (userName.equals("9")) {
            menuLogIn();
        }

        if (customerIfTrueLibrarianIfFalse) {
            while (searchForCustomerByName(users, userName) == null) {
                SC.messageFieldCenterWithBlankSpace("Användarnamnet finns ej registrerat. Vänligen försök igen.");
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                userName = SC.scanner.nextLine();
                if (userName.equals("9")) {
                    return;
                }
            }

            SC.messageFieldCenterWithBlankSpace("Ange ditt lösenord?");
            String userPass = SC.scanner.nextLine();
            if (userPass.equals("9")) {
                return;
            }

            while (!searchForCustomerByName(users, userName).getPassword().equals(userPass)) {
                SC.messageFieldCenterWithBlankSpace("Lösenordet stämmer inte. Vänligen försök igen.");
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                userPass = SC.scanner.nextLine();
                if (userPass.equals("9")) {
                    return;
                }
            }

            currentUserIndex = returnUserIndex(searchForCustomerOrLibrarianByName(users, userName));
            if (users.get(currentUserIndex).getBooks().size() > 0) {
                SC.notificationMsgForBorrowDays(users.get(currentUserIndex).getBooks());
                SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att fortsätta till kundmenyn.");
            }
            menuCustomer();

        } else {

            while (searchForLibrarianByName(users, userName) == null) {
                SC.messageFieldCenterWithBlankSpace("Användarnamnet finns ej registrerat. Vänligen försök igen.");
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                userName = SC.scanner.nextLine();
                if (userName.equals("9")) {
                    return;
                }
            }

            SC.messageFieldCenterWithBlankSpace("Ange ditt lösenord?");
            String userPass = SC.scanner.nextLine();
            if (userPass.equals("9")) {
                return;
            }

            while (!searchForLibrarianByName(users, userName).getPassword().equals(userPass)) {
                SC.messageFieldCenterWithBlankSpace("Lösenordet stämmer inte. Vänligen försök igen.");
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                userPass = SC.scanner.nextLine();
                if (userPass.equals("9")) {
                    return;
                }
            }
            menuLibrarian();
        }
    }

    private User searchForCustomerOrLibrarianByName(ArrayList<User> userList, String CustomerOrLibrarianName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(CustomerOrLibrarianName) && user instanceof Customer) {
                return user;
            } else if (user.getName().equalsIgnoreCase(CustomerOrLibrarianName) && user instanceof Librarian) {
                return user;
            }
        }
        return null;
    }

    private User searchForCustomerByName(ArrayList<User> userList, String customerName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(customerName) && user instanceof Customer) {
                return user;
            }
        }
        return null;
    }

    private User searchForLibrarianByName(ArrayList<User> userList, String librarianName) {
        for (User user : userList) {
            if (user.getName().equalsIgnoreCase(librarianName) && user instanceof Librarian) {
                return user;
            }
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
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        SC.messageFieldWholeWithoutBlankSpace();
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
                    menuLogIn();
                    break;
                case "2":
                    logIn(false);
                    menuLogIn();
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
                    SC.msgWelcomeSquare("Detta är bibliotekets bokregister. Något intressant kanske?");
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till kundmenyn.");
                    menuCustomer();
                    break;
                case "2":
                    SC.msgWelcomeSquare("Tillgängliga böcker att låna");
                    bookProgram.showOnlyAvailableBooks(books, true,
                            "Finns det några snälla böcker kvar?",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till kundmenyn.");
                    menuCustomer();
                    break;
                case "3":
                    SC.msgWelcomeSquare("Mina lånade böcker");
                    bookProgram.showAvailableBookListWithRandomInformation(users.get(currentUserIndex).getBooks(), false, true, true, true, false, "Du har har inte lånat något... än ;)");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till kundmenyn.");
                    menuCustomer();
                    break;
                case "4":
                    SC.msgWelcomeSquare("Sök på en del av- eller hela namnen för att lägga till boken");
                    bookProgram.showAvailableBookListWithRandomInformation(books,
                            false,
                            true,
                            true,
                            false,
                            true,
                            "Du har inte lånat något... än ;)");
                    customerProgram.borrowBook();
                    menuCustomer();
                    break;
                case "5":
                    SC.msgWelcomeSquare("Kom ihåg att alltid lämna tillbaka din bok i tid!");
                    Program.getBookProgram().showAvailableBookListWithRandomInformation(
                            users.get(currentUserIndex).getBooks(),
                            false,
                            true,
                            true,
                            false,
                            false,
                            "Du har inte lånat något som ska lämnas tillbaka.");
                    customerProgram.returnBorrowedBook();
                    menuCustomer();
                    break;
                case "6":
                    SC.msgWelcomeSquare("Sorterar efter boktitel");
                    bookProgram.sortByTitle(books);
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till kundmenyn.");
                    menuCustomer();
                    break;
                case "7":
                    SC.msgWelcomeSquare("Sorterar efter bokförfattare");
                    bookProgram.sortByAuthor(books);
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, false, "Listan är tom tyvärr :(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till kundmenyn.");
                    menuCustomer();
                    break;
                case "8":
                    SC.msgWelcomeSquare("Bra att hålla koll på när min bok ska lämnas tillbaka");
                    SC.notificationMsgForBorrowDays(users.get(currentUserIndex).getBooks());
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till kundmenyn.");
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
                    SC.messageFieldCenterWithBlankSpace("Vänligen försök igen!");
                    break;
            }
        }
    }

    private void menuLibrarian() {
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
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till bibliotekariemenyn.");
                    menuLibrarian();
                    break;
                case "2":
                    SC.msgWelcomeSquare("Böcker som kunder kan låna");
                    bookProgram.showOnlyAvailableBooks(books, true,
                            "Finns det några snälla böcker kvar?",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till bibliotekariemenyn.");
                    menuLibrarian();
                    break;
                case "3":
                    SC.msgWelcomeSquare("Utlånade böcker");
                    bookProgram.showOnlyAvailableBooks(books, false,
                            "",
                            "Dessa böcker är tillgängliga att låna.",
                            "Nej vad tråkigt... Alla böcker är utlånade tyvärr :´(");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till bibliotekariemenyn.");
                    menuLibrarian();
                    break;
                case "4":
                    SC.msgWelcomeSquare("Lägg till böcker och attrahera fler läsare till oss");
                    librarianProgram.addBooksToLibrary(
                            SC.messageFieldCenterWithBlankSpaceReturn("Ange bokstitel:"),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skriv författarens namn."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Skiv kort om vad boken handlar om."));
                    menuLibrarian();
                    break;
                case "5":
                    SC.msgWelcomeSquare("Har du hittat något tråkigt att ta bort?");
                    bookProgram.showAvailableBookListWithRandomInformation(books, false, true, true, true, true, "Listan är tom tyvärr :(");
                    librarianProgram.removeBook(books);
                    menuLibrarian();
                    break;
                case "6":
                    SC.msgWelcomeSquare("Här visas listan med bibliotekets alla registrerade användare");
                    librarianProgram.showUserNameAndOrBooks(users, false);
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till bibliotekariemenyn.");
                    menuLibrarian();
                    break;
                case "7":
                    SC.msgWelcomeSquare("Här visas bibliotekets utlånade böcker och till vilka användare");
                    librarianProgram.showUserNameAndOrBooks(users, true);
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända till bibliotekariemenyn.");
                    menuLibrarian();
                    break;
                case "8":
                    SC.msgWelcomeSquare("Sök efter användare med hela namnet eller bara en bokstav?");
                    librarianProgram.searchAndShowUserByName(users, "",
                            "Din sökning gav flera resultat",
                            "Ingen kund hittades med det namnet. Försök igen. ",
                            "Listan är tom");
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

    public static void addBook(Book bookToAdd) {
        books.add(bookToAdd);
    }

    public static BookProgram getBookProgram() {
        return bookProgram;
    }

    public static User getCurrentUser() {
        return users.get(currentUserIndex);
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}