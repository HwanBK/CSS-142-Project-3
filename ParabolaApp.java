import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Calculates f(x), the derivative, and the area of the given parabola.
 *
 * @author Hwansu Kim (Billy)
 * @version 01/21/2022
 */

public class ParabolaApp {
    public static void main(String[] args) {
        System.out.print('\u000c');
        Scanner consoleIn = new Scanner(System.in);

        int menuChoice;
        double inputX;
        int inputRec;
        do {
            System.out.println("------------------------------");
            System.out.println("  Parabola Calculations Menu");
            System.out.println("------------------------------");

            System.out.println("1. Find y for a given x");
            System.out.println("2. Find the slope of the tangenet line at a given x");
            System.out.println("3. Calculate area estimate");
            System.out.println("4. Exit the program\n");

            System.out.print("Enter your choice: ");
            
            do {
                while (!consoleIn.hasNextInt()) {
                    consoleIn.next();
                    System.out.print("The menu selection must be an integer: ");
                }

                menuChoice = consoleIn.nextInt();
                if (menuChoice < 1 || menuChoice > 4) {
                    System.out.print("The menu selection must be between 1 to 4: ");
                }
            } while (menuChoice < 1 || menuChoice > 4);

            if (menuChoice == 1 || menuChoice == 2) {
                System.out.print("Enter the x-value: ");
                
                do {
                    while (!consoleIn.hasNextDouble()) {
                        consoleIn.next();
                        System.out.print("The x-value must be a vaild floating-point number: ");
                    }

                    inputX = consoleIn.nextDouble();
                    if (inputX < 0 || inputX > 5) {
                        System.out.print("The x-value must be between 0 to 5: ");
                    }
                } while (inputX < 0 || inputX > 5);
                double result = 0.0;

                switch (menuChoice) {
                    case 1: result = ParabolaCalc.parabolaFunction(inputX);     break;
                    case 2: result = ParabolaCalc.derivativeFunction(inputX);   break;
                }
                if (menuChoice == 1) {
                    System.out.printf("The y for the given x is: %.3f\n", result);
                    System.out.print("Please press <Enter> to continue.");
                    consoleIn.nextLine();
                    consoleIn.nextLine();
                }else if (menuChoice == 2) {
                    System.out.printf("The tangent line at the given x is: %.3f\n", result);
                    System.out.print("Please press <Enter> to continue.");
                    consoleIn.nextLine();
                    consoleIn.nextLine();
                }
            }else if (menuChoice == 3) {
                System.out.print("Enter the number of rectangles: ");

                do {
                    while (!consoleIn.hasNextInt()) {
                        consoleIn.next();
                        System.out.print("The number of rectangles must be a valid integer: ");
                    }

                    inputRec = consoleIn.nextInt();
                    if (inputRec < 1 || inputRec > 1000) {
                        System.out.print("The number of rectangles must be within 1 to 1000: ");
                    }
                } while (inputRec < 1 || inputRec > 1000);
                double result = ParabolaCalc.leftRiemannSum(inputRec);
                double overEst = ParabolaCalc.overEsti(inputRec);

                System.out.printf("The area under the parabola is: %.2f\n", result);
                System.out.printf("The over-estimation of the area is: %.2f", overEst);
                System.out.println("%");
                
                parabolaGraph(inputRec);

                System.out.print("Please press <Enter> to continue.");
                consoleIn.nextLine();
                consoleIn.nextLine();
            }
        } while (menuChoice != 4);
        System.out.println("You have exited the program.");
    }

    public static void parabolaGraph(int numOfRec) {
        DrawingPanel myPanel = new DrawingPanel(500, 250);
        Graphics myGraphics = myPanel.getGraphics();

        for (int xCoor = 1; xCoor <=499; xCoor++) {
            myGraphics.setColor(Color.RED);
            double yCoor = ParabolaCalc.parabolaFunction(xCoor / 100.0);
            yCoor *= 10;
            yCoor = 250 - yCoor;
            myGraphics.drawLine(xCoor, (int)yCoor, xCoor, (int)yCoor);
            for (int recCount = 1; recCount <= numOfRec; recCount++) {
                myGraphics.setColor(Color.CYAN);
                int recWidth = 500 / numOfRec;
                if (xCoor == 1) {
                    myGraphics.fillRect(xCoor, (int)yCoor, recWidth, 249);
                }else if (xCoor == recWidth * recCount) {
                    myGraphics.fillRect(xCoor, (int)yCoor, recWidth, 249);
                }
            }
        }
    }
}
