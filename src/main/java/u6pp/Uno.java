package u6pp;

import java.util.ArrayList;
// the game class
public class Uno {
// all neccesary variables from previous files
    private ArrayList<Player> players;
    private CardStack deck;
    private CardStack discard;
    private int currentIndex;
    private boolean reversed;
// this creates the game by adding everything together
    public Uno(ArrayList<Player> players, CardStack deck,
               CardStack discard, int currentIndex, boolean reversed) {

        this.players = players;
        this.deck = deck;
        this.discard = discard;
        this.currentIndex = currentIndex;
        this.reversed = reversed;
    }
// chrcks the # of players and more deck info (sets it and passes cards)
    public Uno(int numPlayers) {

        players = new ArrayList<>();
        deck = new CardStack();
        discard = new CardStack();
        reversed = false;
        currentIndex = 0;

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + i));
        }

        for (String color : Card.COLORS) {
            for (String value : Card.VALUES) {
                try {
                    deck.push(new Card(color, value));
                } catch (Exception e) {}
            }
        }

        deck.shuffle();

        for (int i = 0; i < 7; i++) {
            for (Player p : players) {
                p.getHand().add(deck.pop());
            }
        }

        discard.push(deck.pop());
    }
// gets the player #
    public ArrayList<Player> getPlayers() {
        return players;
    }
// gets the turn 
    public Player getCurrentPlayer() {
        return players.get(currentIndex);
    }
// allows us to use the next player for cards based on next/prev player
    public Player getNextPlayer() {

        int next = currentIndex;

        if (!reversed) {
            next = (next + 1) % players.size();
        } else {
            next = (next - 1 + players.size()) % players.size();
        }

        return players.get(next);
    }
// discarding 
    public Card getTopDiscard() {
        return discard.peek();
    }
// displayers the winner when empty
    public Player getWinner() {
        for (Player p : players)
            if (p.getHand().isEmpty())
                return p;
        return null;
    }
// moves the game 
    private void advanceTurn() {
        if (!reversed)
            currentIndex = (currentIndex + 1) % players.size();
        else
            currentIndex = (currentIndex - 1 + players.size()) % players.size();
    }
// allows players to draw cards
    private void drawCards(Player p, int amount) {

        for (int i = 0; i < amount; i++) {

            if (deck.getSize() == 0) {

                Card top = discard.pop();
                deck.addAll(discard);
                deck.shuffle();
                discard.push(top);
            }

            p.getHand().add(deck.pop());
        }
    }
// this plays the cards based on if statments and uses previous methods to advance 
    public boolean playCard(Card card, String chosenColor) {

        Player current = getCurrentPlayer();

        if (card == null) {
            drawCards(current, 1);
            advanceTurn();
            return true;
        }

        if (!current.getHand().contains(card))
            return false;

        if (!card.canPlayOn(getTopDiscard()))
            return false;

        current.getHand().remove(card);

        if (card.isWild())
            card.trySetColor(chosenColor);

        discard.push(card);

        if (card.getValue().equals(Card.REVERSE)) {
            reversed = !reversed;
            advanceTurn();
        }
        else if (card.getValue().equals(Card.SKIP)) {
            advanceTurn();
            advanceTurn();
        }
        else if (card.getValue().equals(Card.DRAW_2)) {
            Player next = getNextPlayer();
            drawCards(next, 2);
            advanceTurn();
            advanceTurn();
        }
        else if (card.getValue().equals(Card.WILD_DRAW_4)) {
            Player next = getNextPlayer();
            drawCards(next, 4);
            advanceTurn();
            advanceTurn();
        }
        else {
            advanceTurn();
        }

        return true;
    }
}