import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        Deck deck = new Deck();
        ArrayList<String> playerHand = new ArrayList<>();
        deck.shuffleDeck();
        System.out.println(deck.getDeck());
        deck.dealCards(2, playerHand);
        System.out.println(playerHand);
        System.out.println(deck.getDeck());
    }
}
