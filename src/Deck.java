import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<String> deck = new ArrayList<>(Arrays.asList(
            "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "Ts", "Js", "Qs", "Ks", "As",
            "2q", "3q", "4q", "5q", "6q", "7q", "8q", "9q", "Tq", "Jq", "Qq", "Kq", "Aq",
            "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "Th", "Jh", "Qh", "Kh", "Ah",
            "2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "Td", "Jd", "Qd", "Kd", "Ad"));
    
    //randomize element indexes in deck
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    //deal choosen amount of cards to a List
    public void dealCards(int amountOfCardsToDeal, ArrayList<String> listToDealTo) {
        Random random = new Random();
        for(int i = 0; i < amountOfCardsToDeal; i++) {
            int randomIndexInDeck = random.nextInt(deck.size());
            listToDealTo.add(deck.get(randomIndexInDeck));
            deck.remove(randomIndexInDeck);
        }
    }

    public List<String> getDeck() {
        return deck;
    }
    
}
