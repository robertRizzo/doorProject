import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        System.out.println("Welcome to the Monty Hall Game!");

        do {
            Door door1 = new Door("Door1");
            Door door2 = new Door("Door2");
            Door door3 = new Door("Door3");
            Door[] doors = {door1, door2, door3};

            // Tilldela priset till en slumpmässig dörr
            Random random = new Random();
            int winningIndex = random.nextInt(3);
            doors[winningIndex].winningDoor = true;

            // Användarens val
            int userChoiceIndex;
            while (true) {
                System.out.print("\nPlease choose between door number 1, 2, or 3 > ");
                try {
                    int userChoice = scanner.nextInt();

                    // Kontrollera om userChoice är mellan 1 och 3
                    if (userChoice < 1 || userChoice > 3) {
                        System.out.println("\nInvalid input! Please enter a number between 1 and 3.");
                    } else {
                        userChoiceIndex = userChoice - 1;  // Omvandla till arrayindex (alltså 0-2)
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input! Please enter a *number* between 1 and 3.");
                    scanner.nextLine();
                }
            }

            // Kontrollera om användaren valde rätt dörr
            if (doors[userChoiceIndex].winningDoor) {
                System.out.println("\nCongratulations! You chose the right door and won!\n");
            } else {
                System.out.println("\nSorry, you chose the wrong door and lost.\n");
            }

            // Visa var priset fanns
            for (Door door : doors) {
                System.out.println(door.name + " contained " + (door.winningDoor ? "the prize!" : "nothing."));
            }

            // Fråga om spelaren vill spela igen
            System.out.print("\nPlay again? Enter (\"y\" -> yes) & (\"n\" -> no) > ");
            playAgain = scanner.next();

            // Kontrollera giltig inmatning för spela igen-frågan
            while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n")) {
                System.out.println("\nInvalid input! Please enter 'y' to play again or 'n' to quit.");
                System.out.print("\nPlay again? Enter (\"y\" -> yes) & (\"n\" -> no) > ");
                playAgain = scanner.next();
            }

        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("\nOkay, goodbye!");
        scanner.close();
    }
}