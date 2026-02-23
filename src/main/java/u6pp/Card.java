package u6pp;
// class and values
public class Card {

    public static String RED = "RED";
    public static String GREEN = "GREEN";
    public static String BLUE = "BLUE";
    public static String YELLOW = "YELLOW";

    public static String ZERO = "0";
    public static String ONE = "1";
    public static String TWO = "2";
    public static String THREE = "3";
    public static String FOUR = "4";
    public static String FIVE = "5";
    public static String SIX = "6";
    public static String SEVEN = "7";
    public static String EIGHT = "8";
    public static String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD";
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD}; 
    public static String[] VALUES = {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4
    };
// variables 
    private String color;
    private String value;
// my card, creates and meets the test cases I need to, checks for color and value and disallows null, aka value setter
    public Card(String color, String value) {

        if (color == null || value == null)
            throw new IllegalArgumentException();

        if (!isInArray(color, COLORS) || !isInArray(value, VALUES))
            throw new IllegalArgumentException();

        if (value.equals(WILD) || value.equals(WILD_DRAW_4)) {
            this.color = WILD;
        } else {
            if (color.equals(WILD))
                throw new IllegalArgumentException();
            this.color = color;
        }

        this.value = value;
    }
// getter for color
    public String getColor() {
        return color;
    }
// getter for value
    public String getValue() {
        return value;
    }
// this will set a new cloe if it must
    public boolean trySetColor(String newColor) {

        if (newColor == null) return false;
        if (!isWild()) return false;
        if (!isInArray(newColor, COLORS)) return false;
        if (newColor.equals(WILD)) return false;

        this.color = newColor;
        return true;
    }
// this will check for wild 
    public boolean isWild() {
        return value.equals(WILD) || value.equals(WILD_DRAW_4);
    }
// this will check if the game can continue through a series of if statments
    public boolean canPlayOn(Card topCard) {

        if (topCard == null) return false;

        if (this.isWild()) return true;

        if (topCard.isWild() && topCard.color.equals(WILD))
            return false;

        if (this.color.equals(topCard.color)) return true;
        if (this.value.equals(topCard.value)) return true;

        return false;
    }
// checks if what is happening actcually exists apart of the game, had to had to fix the whole class
    private boolean isInArray(String str, String[] arr) {
        for (String s : arr)
            if (s.equals(str)) return true;
        return false;
    }
}