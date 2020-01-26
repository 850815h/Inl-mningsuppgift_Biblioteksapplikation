package com.company;

import java.util.ArrayList;

public class LibrarianProgram {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addBooksToLibrary(String messageTitleInput, String messageAuthorInput, String messageInformationInput) {
        String[] msgs = {messageTitleInput, messageAuthorInput, messageInformationInput};
        String[] userInputs = {"", "", ""};

        for (int i = 0; i < userInputs.length; i++) {
            do {
                System.out.println(msgs[i]);
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
                userInputs[i] = SC.scanner.nextLine();
                if (userInputs[i].equals("9")) {
                    return;
                }
                if (i == 1) {
                    for (int j = 0; j < Program.getBooks().size(); j++) {
                        if (userInputs[0].equalsIgnoreCase(Program.getBooks().get(j).getTitle()) &&
                                userInputs[1].equalsIgnoreCase(Program.getBooks().get(j).getAuthor())) {
                            SC.messageFieldCenterWithBlankSpace("Du har redan en bok med titeln \"" + userInputs[0] + "\"");
                            SC.messageFieldCenterWithBlankSpace("av författaren " + userInputs[1]);
                            SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att lägga till en bok på nytt.");
                            addBooksToLibrary(messageTitleInput, messageAuthorInput, messageInformationInput);
                        }
                    }
                }
            } while (userInputs[i].isBlank());
        }

        Book book = new Book(userInputs[0], userInputs[1], userInputs[2], true);
        Program.addBook(book);
        System.out.println();
        SC.messageFieldCenterWithBlankSpace("Boken \"" + book.getTitle() + "\" av " + book.getAuthor() + " lades till i biblioteket.");
        SC.messageFieldCenterWithBlankSpace("Se över alla böcker som finns i systemet genom menyval [1]");
        SC.messageFieldCenterWithBlankSpace("Vill du lägga till fler böcker kanske?");
        addBooksToLibrary(messageTitleInput, messageAuthorInput, messageInformationInput);
    }

    public void removeBook(ArrayList<Book> bookListToRemoveBookFrom) {
        Book book = SC.returnBooksFromLibrary(bookListToRemoveBookFrom,
                "Skriv boktitel eller namnet på författaren för att ta bort boken.",
                "Din sökning gav FLERA resultat, vänligen specificera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom.");
        if (book != null) {
            for (User user : Program.getUsers()) {
                for (Book userBook : user.getBooks()) {
                    if (userBook.getTitle().equals(book.getTitle()) &&
                            userBook.getAuthor().equals(book.getAuthor()) &&
                            userBook.getInformation().equals(book.getInformation())) {
                        userBook.deletePermanentlyFromLibrary();
                    }
                }
            }
            bookListToRemoveBookFrom.remove(book);
            SC.messageFieldCenterWithBlankSpace("Boken \"" + book.getTitle() + "\" av " + book.getAuthor());
            SC.messageFieldCenterWithBlankSpace("är nu borttagen från biblioteket och ifall en kund lånat");
            SC.messageFieldCenterWithBlankSpace("den, behöver kunden inte längre lämna tillbaka den.");
            SC.messageFieldCenterWithBlankSpace("Vill du ta bort fler kanske?");
            removeBook(bookListToRemoveBookFrom);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void searchAndShowUserByName(ArrayList<User> listToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<User> sameSearchUsers = new ArrayList<>();
        String userInput = "";
        if (listToReturnFrom.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);
            SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");

            do {
                msgIfFail = tempMsgIfFail;
                eraseUserList(sameSearchUsers);
                userInput = SC.scanner.nextLine();
                if (userInput.equals("9")) {
                    return;
                }
                for (int i = 0; i < listToReturnFrom.size(); i++) {
                    if (listToReturnFrom.get(i).getName().toLowerCase().contains(userInput.toLowerCase()) &&
                            listToReturnFrom.get(i) instanceof Customer &&
                            !userInput.isBlank()) {
                        sameSearchUsers.add(listToReturnFrom.get(i));
                    }
                }
                if (sameSearchUsers.size() > 1) {
                    msgIfFail = tempMsgRefineSearch;
                    showUserNameAndOrBooks(sameSearchUsers, false);
                }
                if (sameSearchUsers.size() == 1) {
                    SC.messageFieldCenterWithBlankSpace(sameSearchUsers.get(0).getName());
                    SC.messageFieldCenterWithBlankSpace("( ... Hittades med din sökning. )");
                    SC.msgPressAnyKeyToQuit("Tryck på [ ENTER ] för att återvända.");
                    return;

                }
                SC.messageFieldCenterWithBlankSpace(msgIfFail);
                SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
            } while (true);
        }
        SC.messageFieldCenterWithBlankSpace(msgIfEmptyList);
    }

    public void showUserNameAndOrBooks(ArrayList<User> userList, boolean showUserBooksOrNot) {
        if (userList.size() > 0) {
            for (User user : userList) {
                if (user instanceof Customer) {
                    System.out.println();
                    SC.messageFieldCenterWithBlankSpace(user.getName());
                    if (showUserBooksOrNot) {
                    SC.messageFieldWholeWithoutBlankSpace();
                        for (Book book : user.getBooks()) {
                            if (book.getIsDeletedFromLibrarianSystem() == false) {
                                Program.getBookProgram().showAvailableBookWithRandomInformation(book, false, true, true, false, false);
                            }
                        }
                    SC.messageFieldWholeWithoutBlankSpace();
                    }
                }
            }
            return;
        }
        SC.messageFieldCenterWithBlankSpace("Listan av customers är tom!");
    }

    public static void eraseUserList(ArrayList<User> userListToEmpty) {
        if (userListToEmpty.size() > 0) {
            do {
                userListToEmpty.remove(userListToEmpty.get(0));
            } while (userListToEmpty.size() > 0);
            return;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
