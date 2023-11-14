import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    // works almost like checkforpair but it counts how manny duplicates there are and if its 2 or more its a 2 pair or better
    public Boolean checkForTwoPair(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

        String playerAndTableCards = turnCardListIntoString(playerCards, cardsOnTable);
        HashSet<Character> uniqueCards = new HashSet<>();

        int counter = 0;

        for (int i = 0; i < playerAndTableCards.length(); i++) {
            if(!uniqueCards.add(playerAndTableCards.charAt(i))) {
                counter++;
            } 
        }
        if(counter >= 2) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkForThreeOfaKind(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        String playerAndTableCards = turnCardListIntoString(playerCards, cardsOnTable);

        //compares a card with all the other ones and if it finds that 3 times we have a three of a kind
        for (int i = 0; i < playerAndTableCards.length(); i++) {
            int counter = 0;
            for (int j = 0; j < playerAndTableCards.length(); j++) {
                if(playerAndTableCards.charAt(i) == playerAndTableCards.charAt(j)) {
                    counter++;
                    if(counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    public boolean checkForStraight(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

        ArrayList<String> allCards = new ArrayList<>();
        removeSuitsFromCards(playerCards, cardsOnTable);
        //ads playercards and cards on the table into one list without its suits
        allCards.addAll(playerCards);
        allCards.addAll(cardsOnTable);
        //adds it to a hashset to remove duplicates as we need that to find the straight later in function
        HashSet<String> test = new HashSet<>(allCards);
        //we add the hashset to our list again, and this time there is no duplicates
        allCards.clear();
        allCards.addAll(test);
        //defining the oder we want to sort the allcards after
        String straightOder = "23456789TJQKA";
        //sort allcards list with this oder
        allCards.sort((card1, card2) -> straightOder.indexOf(card1) - straightOder.indexOf(card2));
        //turns list into a single string
        String allCardsSortedAsString = String.join("", allCards);
        //arraylist of all diffrent straight combos
        ArrayList<String> allStraightCombinations = new ArrayList<>();
        allStraightCombinations.add("A2345");
        allStraightCombinations.add("23456");
        allStraightCombinations.add("34567");
        allStraightCombinations.add("45678");
        allStraightCombinations.add("56789");
        allStraightCombinations.add("6789T");
        allStraightCombinations.add("789TJ");
        allStraightCombinations.add("89TJQ");
        allStraightCombinations.add("9TJQK");
        allStraightCombinations.add("TJQKA");
        //loop through straight combo list and check if we have a straight
        for (String straightCombo : allStraightCombinations) {
            if(allCardsSortedAsString.contains(straightCombo)) {
                int indexOfWinningStraight = allStraightCombinations.indexOf(straightCombo);//will add feature to return strenght of straight 
                System.out.println(allStraightCombinations.get(indexOfWinningStraight));    // so if 2 players have straight we can know who wins
                return true;
            }
        }
        return false;
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
