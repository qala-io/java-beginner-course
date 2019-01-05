package io.qala.javabeginner;

import io.qala.javabeginner.domain.Card;
import io.qala.javabeginner.domain.Column;
import io.qala.javabeginner.domain.User;
import io.qala.javabeginner.repository.CardRepository;
import io.qala.javabeginner.repository.ColumnRepository;
import io.qala.javabeginner.repository.UserRepository;
import io.qala.javabeginner.repository.memory.CardInMemoryRepository;
import io.qala.javabeginner.repository.memory.ColumnInMemoryRepository;
import io.qala.javabeginner.repository.memory.UserInMemoryRepository;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.h2.jdbcx.JdbcDataSource;

import java.util.Scanner;

public class ConsoleApi {
    private final UserRepository userRepository = new UserInMemoryRepository();
    private final CardRepository cardRepository = new CardInMemoryRepository();
    private final ColumnRepository columnRepository = new ColumnInMemoryRepository();

    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new ConsoleApi().run();
    }

    private void run() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:javabeginner;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        new Flyway(new FluentConfiguration().dataSource(dataSource).locations("migrations")).migrate();

        System.out.println("First, log in!");
        User currentUser = enterUser(in);
        System.out.println("Hello " + currentUser.getFullName() + ", now you can create a task.");
        for(;;) {
            System.out.println("Choose 1 to create a card, 2 to show the list of cards, 0 to exit");
            String command = in.nextLine();
            if(command.equals("0"))
                return;
            else if(command.equals("1"))
                createCard(currentUser);
            else if(command.equals("2"))
                showCards();
            else
                System.out.println("The command was not recognized, try again");
        }
    }
    private void showCards() {
        for (Column column : columnRepository.findAllOrderedByPosition()) {
            String line = column.getName() + ":\t";
            for (Card card : cardRepository.findByColumn(column))
                line += card.getTitle() + "(" + card.getAssignee().getFullName() + "), ";
            line = line.substring(0, line.length() - 2);
            System.out.println(line);
        }
    }
    private Card createCard(User currentUser) {
        System.out.print("Title: ");
        String title = in.nextLine();
        System.out.print("Column: ");
        String columnName = in.nextLine();
        Column column = columnRepository.findByName(columnName);
        if(column == null) {
            column = new Column(columnName);
            columnRepository.save(column);
        }
        Card card = new Card(title, currentUser, column);
        System.out.println("You just created a card. Assign it to your friend.");

        User assignee = enterUser(in);
        card.assignTo(assignee);
        cardRepository.save(card);
        return card;
    }

    private User enterUser(Scanner in) {
        System.out.print("Email: ");
        String email = in.nextLine();
        User currentUser = userRepository.findByEmail(email);
        if(currentUser == null) {
            currentUser = new User(email);

            System.out.print("First name: ");
            currentUser.setFirstName(in.nextLine());
            System.out.print("Last name: ");
            currentUser.setLastName(in.nextLine());
            userRepository.saveOrUpdate(currentUser);
        }
        return currentUser;
    }
}
