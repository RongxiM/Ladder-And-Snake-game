//--------------------------------------------------
//COMP 249
//Assignment #1
//Question 1
//Written by: Rongxi Meng       Student ID: 40045067
//Due date: Friday, Feb 5, 2021
//--------------------------------------------------
package Assignment_1;
import java.util.Scanner;

public class PlayLadderAndSnake {
    public static void main(String[] args) {
        int numOfPlayers, temp;
        int count = 0;


        Scanner input = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("*                                                *");
        System.out.println("*    WELCOME TO RONGXI'S LADDERANDSNAKE GAME!    *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");

        System.out.print("Please enter the number of players for your game - Number must be between 2 and 4 inclusively!");
        while(true){
            count++;
            temp = input.nextInt();
            if (temp < 2 || temp > 4){
                if (count == 4){
                    System.out.print("Bad Attempt " + count + "! You have exhausted all your chances. Program will terminate!");
                    input.close();
                    System.exit(0);
                }
                System.out.println("Bad Attempt " + count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively");
            }else{
                numOfPlayers = temp;
                break;
            }
        }

        LadderAndSnake newGame = new LadderAndSnake(numOfPlayers);
        input.close();
        System.exit(0);

    }
}
