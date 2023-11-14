import java.util.ArrayList;
import java.util.HashSet;

public class CombinationsLogic {
    

    public Boolean checkForPair(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

        String playerAndTableCards = turnCardListIntoString(playerCards, cardsOnTable);
        //using hashset to find duplicates
        HashSet<Character> uniqueCards = new HashSet<>();

        for (int i = 0; i < playerAndTableCards.length(); i++) {
            //we return true if we find a duplicate letter
            if(!uniqueCards.add(playerAndTableCards.charAt(i))) {
                return true; 
            } 
        }
        return false;
    }


    public void checkForTwoPair(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

    }

    public void checkForThreeOfaKind() {
        
    }

    public void checkForStraight() {
        
    }

    public void checkForFlush() {
        
    }

    public void checkForFullHouse() {
        
    }

    public void checkForFourOfaKind() {

    }

    public void checkForStraightFlush() {

    }

    public void checkForRoyalFlush() {

    }

    public void checkForHighCard(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        
    }
    //cards come like "Kd","2s" for example, this removes the suit and give us "K", "2" with we need for out other functions
    public void removeSuitsFromCards(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        
        for (int i = 0; i < cardsOnTable.size(); i++) {
            // removes the last characther from string that in this case is the suit
            String cardWithOutSuit = cardsOnTable.get(i).substring(0, 1); 
            cardsOnTable.set(i, cardWithOutSuit); // replaces the card with the one without a suit

        }       
        for (int i = 0; i < playerCards.size(); i++) {
            String cardWithOutSuit = playerCards.get(i).substring(0, 1);
            playerCards.set(i, cardWithOutSuit);

        }

    }

    //turns arraylist of cards into a string so its easier to use in combination logic so [Aq] [Th] would return a string like this "AT"
    public String turnCardListIntoString(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

        removeSuitsFromCards(playerCards, cardsOnTable);
        String stringToReturn = "";

        for (String string : playerCards) {
            stringToReturn += string;
        }

        for (String string : cardsOnTable) {
            stringToReturn += string;
        }

        return stringToReturn;
    }

}
