import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        Deck deck = new Deck();
        CombinationsLogic combo = new CombinationsLogic();

        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> cardsOnTable = new ArrayList<>();
        
        deck.shuffleDeck();

        //deck.dealCards(5, cardsOnTable);
        //deck.dealCards(2, playerHand);

        playerHand.add("Ah");
        playerHand.add("2h");
        cardsOnTable.add("3h");
        cardsOnTable.add("4h");
        cardsOnTable.add("5h");
        cardsOnTable.add("7d");
        cardsOnTable.add("Aq");

        System.out.println(playerHand);
        System.out.println(cardsOnTable);

        System.out.println(combo.checkForStraightFlush(playerHand, cardsOnTable));


    }
}
