//--------------------------------------------------
//COMP 249
//Assignment #1
//Question 1
//Written by: Rongxi Meng       Student ID: 40045067
//Due date: Friday, Feb 5, 2021
//--------------------------------------------------
package Assignment_1;

public class Player {
    //player's name
    private String name;
    //record player's current position
    private int currentPosition;

    public Player() {
        this.name = "";
        this.currentPosition = 0;
    }

    /**
     * getters and setters
     * @return players' name
     */
    public String getName() {
        return name;
    }

    /**
     * set player's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * getters and setters
     * @return players' current position
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * set player's current position
     * @param currentPosition
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
