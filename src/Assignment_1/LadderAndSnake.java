//--------------------------------------------------
//COMP 249
//Assignment #1
//Question 1
//Written by: Rongxi Meng       Student ID: 40045067
//Due date: Friday, Feb 5, 2021
//--------------------------------------------------
package Assignment_1;

import java.util.Random;
import java.util.Scanner;

public class LadderAndSnake {
    //creation of the game
    public LadderAndSnake(int numOfPlayers) {
        play(numOfPlayers);
    }

    /**
     *create the game board, assign value to every squares on the board in order to realise the function of "LADDER" and
     *"SNAKE"
     * @return game board with assigned value
     */
     private static int[] createBoard() {
        int num = 1;
        int[] board = new int[100];
        for (int i = 0; i < 100; i++) {
            board[i] = num;
            num ++;
        }
        board[0] = 38;
        board[3] = 14;
        board[8] = 31;
        board[27] = 84;
        board[20] = 42;
        board[35] = 44;
        board[50] = 67;
        board[79] = 100;
        board[70] = 91;
        board[15] = 6;
        board[47] = 30;
        board[61] = 19;
        board[63] = 60;
        board[97] = 78;
        board[96] = 76;
        board[94] = 24;
        board[92] = 68;
        return board;
    }

    /**
     * function "flipDIce" simulate a real dice which can generate a random number between 1 - 6
     * @return a random number between 1 - 6
     */
    private static int flipDice() {
        int dice;
        Random randomNum = new Random();
        //bound is 6 means a number between 0 - 5, so ((0-5)+1) == 1 - 6
        dice = randomNum.nextInt(6) + 1;
        return dice;
    }

    /**
     * method which prints out the game board
     */
    public void printLadderAndSnakeBoard() {
        int n = 100;
        //double "for" loop to print rows and columns
        for (int i = 10; i > 0; i--) {
            System.out.print("    ");
            for (int j = 10; j > 0; j--) {
                //even rows numbers-- (from left to right)
                if (i % 2 == 0) {
                    System.out.print(String.format("%02d\t", n) + " ");
                    n--;
                    if (j == 1) {
                        n -= 9;
                    }
                //odd rows numbers++ (from left to right)
                } else {
                    System.out.print(String.format("%02d\t", n) + " ");
                    n++;
                    if (j == 1) {
                        n -= 11;
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * core engine
     * @param numOfPlayers number of players
     */
    public void play(int numOfPlayers) {
        boolean flag = true;
        int tempPosition = 0; //record number of dice
        Player tempPlayer = new Player();
        Player[] players = new Player[numOfPlayers]; //array of 2 - 4 players
        int[] board = createBoard(); //game board created
        System.out.println();
        System.out.println("This is the game board!");
        printLadderAndSnakeBoard(); //game board printed
        Scanner input = new Scanner(System.in); //record number of players
        String temp = "";

        System.out.println();
        System.out.println("This game is played by " + numOfPlayers + " players");

        /*
        record each player's name
         */
        for (int i = 0; i < numOfPlayers; i++) {
            System.out.println("Please enter #" + (i + 1) + " Player's name:");
            players[i] = new Player();
            temp = input.next();
            players[i].setName(temp);
        }

        /*
        print out each player's name
         */
        System.out.println("We have " + numOfPlayers + " players, their names are:");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName());
        }

        /*
        players roll the dice first
         */
        System.out.println("Now deciding which player will start playing first.");
        for (int i = 0; i < players.length; i++) {
            players[i].setCurrentPosition(flipDice());
            System.out.println("Player " + players[i].getName() + " got a dice value of " + players[i].getCurrentPosition());
        }

        /*
        decide which player to move first
         */
        while(flag){
            flag = false;  //skip the while loop if no condition has been met
            // 2 players
            if (numOfPlayers == 2) {
                //two players achieved a tie
                if(players[0].getCurrentPosition() == players[1].getCurrentPosition()){
                    System.out.println("A tie was achieved between " + players[0].getName() + ", " + players[1].getName() + ". Attempting to break the tie");
                    players[0].setCurrentPosition(flipDice());
                    players[1].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[0].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[1].getName() + " got dice value of " + players[1].getCurrentPosition());
                    flag = true;
                }
            }

            //3 players
            if (numOfPlayers == 3){
                //three players achieved a tie
                if(players[0].getCurrentPosition() == players[1].getCurrentPosition() && players[1].getCurrentPosition() == players[2].getCurrentPosition()) {
                    System.out.println("A tie was achieved between " + players[0].getName() + ", " + players[1].getName() + " and " + players[2].getName() +
                            ". Attempting to break the tie");
                    players[0].setCurrentPosition(flipDice());
                    players[1].setCurrentPosition(flipDice());
                    players[2].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[0].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[1].getName() + " got dice value of " + players[1].getCurrentPosition());
                    System.out.println("Player " + players[2].getName() + " got dice value of " + players[2].getCurrentPosition());
                    flag = true;
                }else{
                    //double "for" loop used to implement traversing the players array
                    for (int i = 0; i < players.length; i++){
                        for (int j = i + 1; j < players.length; j++){
                            if (players[i].getCurrentPosition() == players[j].getCurrentPosition()) {
                                System.out.println("A tie was achieved between " + players[i].getName() + " and " + players[j].getName());
                                players[i].setCurrentPosition(flipDice());
                                players[j].setCurrentPosition(flipDice());
                                System.out.println("Player " + players[i].getName() + " got dice value of " + players[i].getCurrentPosition());
                                System.out.println("Player " + players[j].getName() + " got dice value of " + players[j].getCurrentPosition());
                                //flag must be set to "true" in order to check players array again
                                flag = true;
                            }
                        }
                    }
                }
            }
            //4 players
            if(numOfPlayers == 4){
                //3 players roll the same number
                if(players[0].getCurrentPosition() == players[1].getCurrentPosition() && players[1].getCurrentPosition() == players[2].getCurrentPosition()){
                    System.out.println("A tie was achieved between " + players[0].getName() + ", " + players[1].getName() + " and " + players[2].getName() +
                            ". Attempting to break the tie");
                    players[0].setCurrentPosition(flipDice());
                    players[1].setCurrentPosition(flipDice());
                    players[2].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[0].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[1].getName() + " got dice value of " + players[1].getCurrentPosition());
                    System.out.println("Player " + players[2].getName() + " got dice value of " + players[2].getCurrentPosition());
                    flag = true;
                }
                //3 players roll the same number
                if(players[1].getCurrentPosition() == players[2].getCurrentPosition() && players[2].getCurrentPosition() == players[3].getCurrentPosition()){
                    System.out.println("A tie was achieved between " + players[1].getName() + ", " + players[2].getName() + " and " + players[3].getName() +
                            ". Attempting to break the tie");
                    players[1].setCurrentPosition(flipDice());
                    players[2].setCurrentPosition(flipDice());
                    players[3].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[1].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[2].getName() + " got dice value of " + players[1].getCurrentPosition());
                    System.out.println("Player " + players[3].getName() + " got dice value of " + players[2].getCurrentPosition());
                    flag = true;
                }
                //3 players roll the same number
                if(players[0].getCurrentPosition() == players[2].getCurrentPosition() && players[2].getCurrentPosition() == players[3].getCurrentPosition()){
                    System.out.println("A tie was achieved between " + players[0].getName() + ", " + players[2].getName() + " and " + players[3].getName() +
                            ". Attempting to break the tie");
                    players[0].setCurrentPosition(flipDice());
                    players[2].setCurrentPosition(flipDice());
                    players[3].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[0].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[2].getName() + " got dice value of " + players[1].getCurrentPosition());
                    System.out.println("Player " + players[3].getName() + " got dice value of " + players[2].getCurrentPosition());
                    flag = true;
                }
                //3 players roll the same number
                if(players[0].getCurrentPosition() == players[1].getCurrentPosition() && players[1].getCurrentPosition() == players[3].getCurrentPosition()){
                    System.out.println("A tie was achieved between " + players[0].getName() + ", " + players[1].getName() + " and " + players[3].getName() +
                            ". Attempting to break the tie");
                    players[0].setCurrentPosition(flipDice());
                    players[1].setCurrentPosition(flipDice());
                    players[3].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[0].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[1].getName() + " got dice value of " + players[1].getCurrentPosition());
                    System.out.println("Player " + players[3].getName() + " got dice value of " + players[2].getCurrentPosition());
                    flag = true;
                }
                //4 players roll the same number
                if(players[0].getCurrentPosition() == players[1].getCurrentPosition() && players[1].getCurrentPosition() == players[2].getCurrentPosition()
                        && players[2].getCurrentPosition() == players[3].getCurrentPosition()){
                    System.out.println("A tie was achieved between " + players[0].getName() + ", " + players[1].getName() + ", " + players[2].getName() +
                            " and " + players[3].getName() + ". Attempting to break the tie");
                    players[0].setCurrentPosition(flipDice());
                    players[1].setCurrentPosition(flipDice());
                    players[2].setCurrentPosition(flipDice());
                    players[3].setCurrentPosition(flipDice());
                    System.out.println("Player " + players[0].getName() + " got dice value of " + players[0].getCurrentPosition());
                    System.out.println("Player " + players[1].getName() + " got dice value of " + players[1].getCurrentPosition());
                    System.out.println("Player " + players[2].getName() + " got dice value of " + players[2].getCurrentPosition());
                    System.out.println("Player " + players[3].getName() + " got dice value of " + players[3].getCurrentPosition());
                    flag = true;
                }
                //2 of the 4 players roll same number
                for (int i = 0; i < players.length; i++){
                    for (int j = i + 1; j < players.length; j++){
                        if (players[i].getCurrentPosition() == players[j].getCurrentPosition()){
                            System.out.println("A tie was achieved between " + players[i].getName() + " and " + players[j].getName());
                            players[i].setCurrentPosition(flipDice());
                            players[j].setCurrentPosition(flipDice());
                            System.out.println("Player " + players[i].getName() + " got dice value of " + players[i].getCurrentPosition());
                            System.out.println("Player " + players[j].getName() + " got dice value of " + players[j].getCurrentPosition());
                            flag = true;
                        }
                    }
                }
            }
        }

        /*
        substitution method used to sort the players array
         */
        flag = true;
        while(flag){
            flag = false;
            for (int i = 0; i < players.length - 1; i++){
                if (players[i].getCurrentPosition() < players[i + 1].getCurrentPosition()){
                    tempPlayer = players[i];
                    players[i] = players[i + 1];
                    players[i + 1] = tempPlayer;
                    flag = true;
                }
            }
        }

        /*
        print out sorted players' names
         */
        System.out.print("Reached final decision on order of playing: ");
        for (int i = 0; i < players.length; i ++){
            players[i].setCurrentPosition(0);
            if (i != (players.length - 1)){
                System.out.print(players[i].getName() + ", ");
            }else{
                System.out.print(players[players.length - 1].getName());
            }
        }
        System.out.println();
        System.out.println("Now the game begins, press any key to continue!");
        input.next();

        /*
        CORE ENGINE: compare the potential position of the player(current position + dice number), if the potential
        position is equal to the value of the square of the board, the player moves to that square; if the potential
        position is greater than the value of the square of the board, it means that the player is stepping on the head
        of the snake so that the player will move down to the square where the tail of the snake lies; if the potential
        position is less than the value of the square of the board, it means that the player is at the bottom of a ladder
        so that the player will move up to the square where the top of the ladder lies
         */
        flag = false;
        while(!flag) {
            for (int i = 0; i < players.length; i++) {
                tempPosition = flipDice();
                System.out.println("Player " + players[i].getName() + " got dice value of " + tempPosition + ";");

                if ((players[i].getCurrentPosition() + tempPosition) > 100) {
                    tempPosition = (100 - ((players[i].getCurrentPosition() + tempPosition) - 100));
                    players[i].setCurrentPosition(tempPosition);
                    if (tempPosition == board[tempPosition - 1]) {
                        players[i].setCurrentPosition(tempPosition);
                        System.out.print(players[i].getName() + " now in square " + players[i].getCurrentPosition());
                    }
                    if (tempPosition > board[tempPosition - 1]) {
                        players[i].setCurrentPosition(board[tempPosition - 1]);
                        System.out.print(players[i].getName() + " gone to square " + tempPosition + " then down to square " + players[i].getCurrentPosition());
                        System.out.println();
                    }
                } else if ((players[i].getCurrentPosition() + tempPosition) == board[(players[i].getCurrentPosition() + tempPosition) - 1]) {
                    tempPosition += players[i].getCurrentPosition();
                    players[i].setCurrentPosition(tempPosition);
                    System.out.println(players[i].getName() + " now in square " + players[i].getCurrentPosition());
                    System.out.println();
                } else if ((players[i].getCurrentPosition() + tempPosition) < board[(players[i].getCurrentPosition() + tempPosition) - 1]) {
                    tempPosition += players[i].getCurrentPosition();
                    players[i].setCurrentPosition(board[tempPosition - 1]);
                    System.out.print(players[i].getName() + " gone to square " + tempPosition + " then up to square " + players[i].getCurrentPosition());
                    System.out.println();
                } else if ((players[i].getCurrentPosition() + tempPosition) > board[(players[i].getCurrentPosition() + tempPosition) - 1]) {
                    tempPosition += players[i].getCurrentPosition();
                    players[i].setCurrentPosition(board[tempPosition - 1]);
                    System.out.print(players[i].getName() + " gone to square " + tempPosition + " then down to square " + players[i].getCurrentPosition());
                    System.out.println();
                }
                //any player who reach the square "100", win the game
                if (players[i].getCurrentPosition() == 100) {
                    System.out.println("We have the winner now! " + players[i].getName() + " has won the game!");
                    flag = true; //which makes "!flag" false
                    break;
                }
                System.out.println();
            }
            if (!flag){
                System.out.println("Game not over; flipping again");
                System.out.println("Enter any key to continue!");
                input.next();
            }else{
                break;
            }
        }
    }
}

