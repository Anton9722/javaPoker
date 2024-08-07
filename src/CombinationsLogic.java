import java.util.ArrayList;
import java.util.Collections;
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
    // we return a string here as we use that in our straight flush function. we could just copy this into other function but for now i have it like this
    public String checkForStraight(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

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
        // this if statment adds an ace to beginning of string if it contains a string so we can check for both A2345 and TJQKA
        if(allCardsSortedAsString.contains("A")) {
            allCardsSortedAsString = "A" + allCardsSortedAsString;
        }
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
                return allStraightCombinations.get(indexOfWinningStraight);
            }
        }
        return "NoStraight";
    }

    public boolean checkForFlush(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        // for loop to remove all card values and only get the suits in a string so [As],[Jh] would give us a string like "sh"
        //for playercards
        String cardSuits = "";
        for (int i = 0; i < playerCards.size(); i++) {
            char suit = playerCards.get(i).charAt(1); 
            cardSuits += String.valueOf(suit);
        }
        //for cardson table
        for (int i = 0; i < cardsOnTable.size(); i++) {
            char suit = cardsOnTable.get(i).charAt(1); 
            cardSuits += String.valueOf(suit);
        }
        
        //go through all the suits and if it finds 5 of the same one we have a flush and returns true
        //this function works the same as the one used for threeOfaKind
        for (int i = 0; i < cardSuits.length(); i++) {
            int counter = 0;
            for (int j = 0; j < cardSuits.length(); j++) {
                if(cardSuits.charAt(i) == cardSuits.charAt(j)) {
                    counter++;
                    if(counter == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    public boolean checkForFullHouse(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        //removes suits from cards
        removeSuitsFromCards(playerCards, cardsOnTable);
        //we add all playerhand and cards on table into one arraylist
        ArrayList<String> allCards = new ArrayList<>();
        allCards.addAll(playerCards);
        allCards.addAll(cardsOnTable);

        //if we find a 3 of the same cards and then three or two of the same cards we have found a fullhouse
        for (String card : allCards) {
            int amoutOfDuplicatedCards = Collections.frequency(allCards, card);
            if(amoutOfDuplicatedCards == 3) {
                // we remove the 3 cards that are the same so we dont count the same cards again and get true if we have a tripple for example
                for (int i = 0; i < allCards.size(); i++) {
                    if(card == allCards.get(i)) {
                        allCards.remove(i);
                    }
                }
                for (String card2 : allCards) {
                    int amoutOfDuplicatedCards2 = Collections.frequency(allCards, card2);
                    if(amoutOfDuplicatedCards2 == 3 || amoutOfDuplicatedCards2 == 2) {
                        return true;
                    }
                }
            }
        }
        return false;
        
    }

    public boolean checkForFourOfaKind(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        //removes suits from cards
        removeSuitsFromCards(playerCards, cardsOnTable);
        //we add all playerhand and cards on table into one arraylist
        ArrayList<String> allCards = new ArrayList<>();
        allCards.addAll(playerCards);
        allCards.addAll(cardsOnTable);

        // if we find 4 of the same cards we return true
        for (String card : allCards) {
            int amoutOfDuplicatedCards = Collections.frequency(allCards, card);
            if(amoutOfDuplicatedCards == 4) {
                return true;
            }
        }
        return false;



    }

    public boolean checkForStraightFlush(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {
        //1.put all cards involved with a straight in one list 2. if we find 5 of the same colors in those card we have a straight flush
        ArrayList<String> allCards = new ArrayList<>();
        //put all cards into one list
        allCards.addAll(playerCards);
        allCards.addAll(cardsOnTable);
        //if there is a straight we get the straight combo as a string, if there is no straigt combo we have no straigh flush and return false
        String straightCombo = checkForStraight(playerCards, cardsOnTable);
        if(checkForStraight(playerCards, cardsOnTable) == "NoStraight") {
            return false;
        }
        ArrayList<String> cardsInStraightWithOnlySuit = new ArrayList<>();
        // go through all cards and if the card is involved with the straight add its suit to "cardsInStraightWithOnlySuit"
        for (String card : allCards) {
            String onlyCardValue = String.valueOf(card.charAt(0));
            if(straightCombo.contains(onlyCardValue)) {
                String onlyCardSuit = String.valueOf(card.charAt(1));
                cardsInStraightWithOnlySuit.add(onlyCardSuit);
            }
        }

        for (String suit : cardsInStraightWithOnlySuit) {
            int amountOfSameSuits = Collections.frequency(cardsInStraightWithOnlySuit, suit);
            if(amountOfSameSuits == 5) {
                return true;
            }
        }

        return false;
    }

    public boolean checkForRoyalFlush(ArrayList<String> playerCards, ArrayList<String> cardsOnTable) {

        ArrayList<String> allCards = new ArrayList<>();
        allCards.addAll(playerCards);
        allCards.addAll(cardsOnTable);

        ArrayList<String> spades = new ArrayList<>();
        spades.add("As");
        spades.add("Ks");
        spades.add("Qs");
        spades.add("Js");
        spades.add("Ts");
        int counter = 0;
        for (String spade : spades) {
            for (String card : allCards) {
                if(card == spade) {
                    counter++;
                    if(counter == 5) {
                        return true;
                    }
                }
                
            }
        }
        counter = 0;

        ArrayList<String> hearts = new ArrayList<>();
        hearts.add("Ah");
        hearts.add("Kh");
        hearts.add("Qh");
        hearts.add("Jh");
        hearts.add("Th");
        for (String heart : hearts) {
            for (String card : allCards) {
                if(card == heart) {
                    counter++;
                    if(counter == 5) {
                        return true;
                    }
                }
                
            }
        }
        counter = 0;

        ArrayList<String> clubs = new ArrayList<>();
        clubs.add("Aq");
        clubs.add("Kq");
        clubs.add("Qq");
        clubs.add("Jq");
        clubs.add("Tq");
        for (String club : clubs) {
            for (String card : allCards) {
                if(card == club) {
                    counter++;
                    if(counter == 5) {
                        return true;
                    }
                }
                
            }
        }
        counter = 0;
        
        ArrayList<String> diamonds = new ArrayList<>();
        diamonds.add("Ad");
        diamonds.add("Kd");
        diamonds.add("Qd");
        diamonds.add("Jd");
        diamonds.add("Td");
         for (String diamond : diamonds) {
            for (String card : allCards) {
                if(card == diamond) {
                    counter++;
                    if(counter == 5) {
                        return true;
                    }
                }
                
            }
        }
        return false;
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
