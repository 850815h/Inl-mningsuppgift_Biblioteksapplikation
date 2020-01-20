package com.company;

import java.util.ArrayList;

public class LibrarianProgram {
    //private ArrayList<Book> sameSearchBooks = new ArrayList<>();

    public LibrarianProgram() {
    }

    public void removeBook(ArrayList<Book> bookListToRemoveBookFrom) {
        bookListToRemoveBookFrom.remove(SC.returnBooksFromLibrary(bookListToRemoveBookFrom,
                "Skriv boktitel eller namnet på författaren för att ta bort boken.",
                "Din sökning gav FLERA resultat, vänligen specifiera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom."));
    }

    public void showUserByName(ArrayList<User> listToReturnFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<User> sameSearchUsers = new ArrayList<>();
        String userInput = "";
        if (listToReturnFrom.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);

            do {
                do {
                    msgIfFail = tempMsgIfFail;
                    eraseUserList(sameSearchUsers);
                    userInput = SC.scanner.nextLine();
                    for (int i = 0; i < listToReturnFrom.size(); i++) {
                        if (listToReturnFrom.get(i).getName().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchUsers.add(listToReturnFrom.get(i));
                        }
                    }
                    if (sameSearchUsers.size() > 1) {
                        System.out.println("4");
                        msgIfFail = tempMsgRefineSearch;
                        showUserNameAndOrBooks(sameSearchUsers, false);
                    }
                    if (sameSearchUsers.size() == 1) {
                        System.out.println("5");
                        SC.messageFieldCenterWithBlankSpace(sameSearchUsers.get(0).getName());
                        //return sameSearchUsers.get(0);
                        return;
                    }
                    SC.messageFieldCenterWithBlankSpace(msgIfFail);
                    System.out.println("6");
                } while (true);
            } while (true);
        }
        SC.messageFieldCenterWithBlankSpace(msgIfEmptyList);
        //return null;
    }

    public void showUserNameAndOrBooks(ArrayList<User> userList, boolean showUserBooksOrNot) {
        if (userList.size() > 0) {
            for (User user : userList) {
                if (showUserBooksOrNot) {
                    if (user.getBooks().size() > 0) {
                        SC.messageFieldCenterWithBlankSpace(user.getName());
                        Program.getBookProgram().showAvailableBookListWithRandomInformation(user.getBooks(), false, true, true, false, false, "Listan är tom tyvärr :(");
                        System.out.println();
                    }
                } else {
                    SC.messageFieldCenterWithBlankSpace(user.getName());
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

    public Book removeBooksFromLibrary(ArrayList<Book> bookListToRemoveFrom, String msgWelcome, String msgRefineSearch, String msgIfFail, String msgIfEmptyList) {
        String tempMsgRefineSearch = msgRefineSearch;
        String tempMsgIfFail = msgIfFail;
        ArrayList<Book> sameSearchBooks = new ArrayList<>();
        String userInput = "";
        if (bookListToRemoveFrom.size() > 0) {
            SC.messageFieldCenterWithBlankSpace(msgWelcome);

            do {
                do {
                    msgIfFail = tempMsgIfFail;
                    SC.eraseBookList(sameSearchBooks);
                    userInput = SC.scanner.nextLine();
                    for (int i = 0; i < bookListToRemoveFrom.size(); i++) {
                        if (bookListToRemoveFrom.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase()) ||
                                bookListToRemoveFrom.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
                            sameSearchBooks.add(bookListToRemoveFrom.get(i));
                        }
                    }
                    if (sameSearchBooks.size() > 0) {
                        msgIfFail = tempMsgRefineSearch;
                        Program.getBookProgram().showAvailableBookListWithRandomInformation(sameSearchBooks, false, true, true, false, false, "Listan är tom tyvärr :(");
                    }
                    if (sameSearchBooks.size() == 1) {
                        bookListToRemoveFrom.remove(sameSearchBooks.get(0));
                        return sameSearchBooks.get(0);
                    }
                    SC.messageFieldCenterWithBlankSpace(msgIfFail);
                } while (true);
            } while (true);
        }
        SC.messageFieldCenterWithBlankSpace(msgIfEmptyList);
        return null;
    }

    public void addBooksToLibrary(ArrayList<Book> bookListToAddTo, String messageTitleInput, String messageAuthorInput, String messageInformationInput) {
        String titleInput = "";
        String authorInput = "";
        String informationInput = "";
        do {
            do {
                System.out.println(messageTitleInput);
                titleInput = SC.scanner.nextLine();
            } while (titleInput.isBlank());
            do {
                System.out.println(messageAuthorInput);
                authorInput = SC.scanner.nextLine();
            } while (authorInput.isBlank());
            do {
                System.out.println(messageInformationInput);
                informationInput = SC.scanner.nextLine();
            } while (informationInput.isBlank());
            bookListToAddTo.add(new Book(titleInput, authorInput, informationInput, true));
            return;
        } while (true);
    }
}
