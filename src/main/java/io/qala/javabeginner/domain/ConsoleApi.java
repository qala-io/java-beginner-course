package io.qala.javabeginner.domain;

import java.util.Scanner;

public class ConsoleApi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("First, log in!");

        System.out.print("Email: ");
        String email = in.nextLine();
        User currentUser = new User(email);

        System.out.print("First name:");
        currentUser.setFirstName(in.nextLine());
        System.out.print("Last name:");
        currentUser.setLastName(in.nextLine());

        System.out.println("Hello " + currentUser.getFullName() +", now you can create a task.");
        System.out.print("Title: ");
        String title = in.nextLine();
        System.out.print("Column: ");
        Column column = new Column(in.nextLine());
        Card card = new Card(title, currentUser, column);
        System.out.println("You just created a card. Assign it to your friend.");

        System.out.print("Email: ");
        User assignee = new User(in.nextLine());

        System.out.print("First name: ");
        assignee.setFirstName(in.nextLine());
        System.out.print("Last name: ");
        assignee.setLastName(in.nextLine());
        card.assignTo(assignee);
    }
}
