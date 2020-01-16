package com.company;

import java.util.ArrayList;

public class Program {
    private String lirem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    private static BookProgram bookProgram = new BookProgram();
    private static CustomerProgram customerProgram = new CustomerProgram();
    private static LibrarianProgram librarianProgram = new LibrarianProgram();
    private static UserProgram userProgram = new UserProgram();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Librarian> librarians = new ArrayList<>();

    public Program() {
        //menuCustomer();
        //menuLibrarian();
        menuCustomer();
    }

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

    private void menuLogIn() {
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

        SC.messageFieldCenterWithoutBlankSpace("HUVUDMENY");
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
                    bookProgram.showAvailableBookWithRandomInformation(false,true,true,true,true);
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "2":
                    bookProgram.showAvailableOrNotAvailableBook(true,
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "3":
                    bookProgram.showAvailableOrNotAvailableBook(false,
                            SC.messageFieldCenterWithBlankSpaceReturn("Alla böcker är utlånade."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
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
                    bookProgram.showAvailableBookWithRandomInformation(false,true,true,true,true);
                    librarianProgram.removeBooksFromLibrary(
                            SC.messageFieldCenterWithBlankSpaceReturn("Skriv boktitel eller namnet på författaren för att ta bort boken."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Tyvärr hittades inget med din sökning, försök igen!"),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom."));
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "6":
                    editBooksInLibrary();
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuLibrarian();
                    break;
                case "7":
                    editBooksInLibrary();
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
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

        SC.messageFieldCenterWithoutBlankSpace("HUVUDMENY");
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
                    bookProgram.showAvailableBookWithRandomInformation(false,true,true,true,true);
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "2":
                    bookProgram.showAvailableOrNotAvailableBook(true,
                            SC.messageFieldCenterWithBlankSpaceReturn("Dessa böcker är tillgängliga att låna."),
                            SC.messageFieldCenterWithBlankSpaceReturn("Listan är tom. Det finns inget att visa."));
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "3":
                    customerProgram.showMyBorrowedBooks(
                            SC.messageFieldCenterWithBlankSpaceReturn("Din lista är tom. Du har inga lånade böcker."));
                    SC.pressKeyToQuitMenu( "Tryck på [ ENTER ] för att återvända.");
                    menuCustomer();
                    break;
                case "4":
                    bookProgram.sortByTitle();
                    bookProgram.showAvailableBookWithRandomInformation(false,true,true,true,true);
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
                    menuCustomer();
                    break;
                case "5":
                    bookProgram.sortByAuthor();
                    bookProgram.showAvailableBookWithRandomInformation(false,true,true,true,true);
                    SC.pressKeyToQuitMenu( SC.messageFieldCenterWithBlankSpaceReturn("Tryck på [ ENTER ] för att återvända."));
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

    private void menuBorrowAvailableBook(){

    }

    private void menuReturnBook(){

    }

    private void menuSearchByTitle(){

    }

    private void menuSearchByAuthor(){

    }

    private void editBooksInLibrary() {

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Customer> getCustomers(){
        return customers;
    }

    public static BookProgram getBookProgram(){
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

    public static ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

}