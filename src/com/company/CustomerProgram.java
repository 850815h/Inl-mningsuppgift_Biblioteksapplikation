package com.company;

public class CustomerProgram {
    private final int MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK = 7;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void borrowBook() {
        Book book = SC.returnBooksFromLibrary(Program.getBooks(),
                "Sök efter boktitel eller namnet på författaren.",
                "Din sökning gav FLERA resultat, vänligen specificera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tommmmmmm.");
        if (book != null && book.isAvailability() == true) {
            book.setAvailability(false);
            book.setReturnDate(Program.getBookProgram().setDateToBorrowBook(MAX_AMOUNT_OF_DAYS_TO_BORROW_BOOK));
            Program.getCurrentUser().addBook(book);
            SC.messageFieldCenterWithBlankSpace("Boken \"" + book.getTitle() + "\" av " + book.getAuthor());
            SC.messageFieldCenterWithBlankSpace("är tillagd i din lista. Se dina lånade böcker genom menyval [3]");
            SC.messageFieldCenterWithBlankSpace("Vill du lägga till fler böcker kanske?");
            borrowBook();
        } else if (book != null && book.isAvailability() == false) {
            SC.messageFieldCenterWithBlankSpace("Tyvärr är boken utlånad... :(");
            SC.messageFieldCenterWithBlankSpace("Men det kanske finns annat intressant att söka efter?");
            SC.messageFieldCenterWithBlankSpace("(Återgå till startmenyn genom att när som helst trycka [9])");
            borrowBook();
        }
    }

    public void returnBorrowedBook() {
        Book book = SC.returnBooksFromLibrary(Program.getCurrentUser().getBooks(),
                "Sök efter boktitel eller namnet på författaren.",
                "Din sökning gav FLERA resultat, vänligen specificera din sökning.",
                "Din sökning gav inget resultat. Vänligen försök igen.",
                "Listan är tom.");
        if( book != null ) {
            for( Book librarianBook : Program.getBooks()){
                if (librarianBook.getTitle().equals(book.getTitle()) &&
                        librarianBook.getAuthor().equals(book.getAuthor()) &&
                        librarianBook.getInformation().equals(book.getInformation())) {
                    librarianBook.setAvailability( true );
                }
            }
            Program.getCurrentUser().removeBook(book);
            SC.messageFieldCenterWithBlankSpace("Boken \"" + book.getTitle() + "\" av " + book.getAuthor());
            SC.messageFieldCenterWithBlankSpace("är borttagen från din lista. Se dina lånade böcker genom menyval [3]");
            SC.messageFieldCenterWithBlankSpace("Vill du ta bort fler kanske?");
            returnBorrowedBook();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
