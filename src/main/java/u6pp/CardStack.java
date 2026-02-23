package u6pp;

import java.util.ArrayList;
// class and the stack of cards
public class CardStack {

    private ArrayList<Card> stack;
// this creates the new stack of cards
    public CardStack() {
        stack = new ArrayList<>();
    }
// adds to the stack
    public void push(Card c) {
        if (c != null)
            stack.add(c);
    }
// removes card
    public Card pop() {
        if (isEmpty()) return null;
        return stack.remove(stack.size() - 1);
    }
// this gets card
    public Card peek() {
        if (isEmpty()) return null;
        return stack.get(stack.size() - 1);
    }
// checks true of false if empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }
// getter for size (how many cards)
    public int getSize() {
        return stack.size();
    }
// this clears the stack (reset)
    public void clear() {
        stack.clear();
    }
// creates the stack by adding via if statements
    public void addAll(CardStack other) {

        if (other == null || other == this || other.isEmpty())
            return;

        ArrayList<Card> temp = new ArrayList<>();

        while (!other.isEmpty()) {
            temp.add(other.pop());
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            this.push(temp.get(i));
        }
    }
// shuffles the deck
    public void shuffle() {

        for (int i = stack.size() - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));

            Card temp = stack.get(i);
            stack.set(i, stack.get(j));
            stack.set(j, temp);
        }
    }
}