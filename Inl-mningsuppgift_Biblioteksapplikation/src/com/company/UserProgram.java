package com.company;

import java.util.ArrayList;

public class UserProgram {
    private ArrayList<User> customers = new ArrayList<>();
    private ArrayList<User> librarians = new ArrayList<>();

    public UserProgram() {
        /*showInformation(customers, true, true, "Det finns inga användare registrerade här!" );
        System.out.println();
        System.out.println(customers.size());
        System.out.println();
        addUser("Ange användarnamn.", "Ange lösenord");
        System.out.println();
        showInformation(customers, true, true, "Det finns inga användare registrerade här!" );
        System.out.println();
        System.out.println(customers.size());
        System.out.println();
        addUser("Ange användarnamn.", "Ange lösenord");
        System.out.println();
        showInformation(customers, false, false, "Det finns inga användare registrerade här!" );
        System.out.println();
        System.out.println(customers.size());
        System.out.println();
        addUser("Ange användarnamn.", "Ange lösenord");
        System.out.println();
        showInformation(customers, true, false, "Det finns inga användare registrerade här!" );
        System.out.println();
        System.out.println(customers.size());
        searchUserByName(customers,"Vem söker du efter?" ,"Listan av användare är tom!", "Vänligen försök igen.\nAnvändaren du söker finns inte");
*/
    }

    private User searchUserByName(ArrayList<User> listToSearchIn, String messageWelcome, String messageIfEmptyList, String messageIfUserDoesntExist){
        if( listToSearchIn.size() > 0 ) {
            SC.messageFieldCenterWithBlankSpace( messageWelcome );
            do {
                String userNameToSearchFor = SC.scanner.nextLine();
                for (User user : listToSearchIn) {
                    if (user.getName().equals(userNameToSearchFor.toLowerCase())) {
                        SC.messageFieldCenterWithBlankSpace(printInformation(user, true, true));
                        //(Customer)user.getB
                        return user;
                    } else {
                        SC.messageFieldCenterWithBlankSpace( messageIfUserDoesntExist );
                    }
                }
            }while( true );
        }
        System.out.println( messageIfEmptyList );
        return null;
    }

    private void addUser(String messageNameInput, String messagePassInput) {
        do {
            System.out.println(messageNameInput);
            String userName = SC.scanner.nextLine();
            System.out.println(messagePassInput);
            String userPassword = SC.scanner.nextLine();
            customers.add(new Customer(userName, userPassword));
            return;
        } while (true);
    }

    private void addLibrarian(String messageNameInput, String messagePassInput) {
        do {
            System.out.println(messageNameInput);
            String userName = SC.scanner.nextLine();
            System.out.println(messagePassInput);
            String userPassword = SC.scanner.nextLine();
            librarians.add(new Customer(userName, userPassword));
            return;
        } while (true);
    }

    private void showInformation(ArrayList<User> listToShowInformation, boolean showName, boolean showPassword, String messageIfEmptyList) {
        if (listToShowInformation.size() > 0) {
            for (User user : listToShowInformation) {
                //System.out.println( printInformation(user, showName, showPassword));
                SC.messageFieldCenterWithBlankSpace( printInformation(user, showName, showPassword));
            }
            return;
        }
        System.out.println(messageIfEmptyList);
    }

    private String printInformation(User user, boolean printName, boolean printPassword) {
        if (printName == true && printPassword == true) {
            return String.format("%-20s %-20s", user.getName(), user.getPassword());
            //return "Namn: " + user.getName() + ". Lösenord: " + user.getPassword();
        } else if (printName == true && printPassword == false) {
            return String.format("%-20s", user.getName());
        } else if (printName == false && printPassword == true) {
            return String.format("%-20s", user.getPassword());
        } else {
            return "";
        }
    }

}
