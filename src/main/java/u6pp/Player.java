package u6pp;

import java.util.ArrayList;
// the class creation for an indivdual 
public class Player {
// variables for each player
    private String name;
    private ArrayList<Card> hand;
// creates a player
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }
// a getter if the name 
    public String getName() {
        return name;
    }
// returns hand
    public ArrayList<Card> getHand() {
        return hand;
    }
}