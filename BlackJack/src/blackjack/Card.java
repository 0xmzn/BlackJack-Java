/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import static java.lang.Math.min;

/**
 *
 * @author msaad
 */
public class Card {
    // suits (0->♣  1->♦ 2->♥ 3->♠)
    private final int suit;
    private final int value;
    private final int rank;
    
    public Card(int suit, int rank)
    {
        this.suit = suit;
        this.value = min(rank+1, 10);
        this.rank = rank;
    }
    // copyConstructor
    public Card(Card tempCard)
    {
        this.suit = tempCard.suit;
        this.value = tempCard.value;
        this.rank = tempCard.rank;
    }
    public int getSuit() 
    {
        return suit;
    }
    public int getRank() 
    {
        return rank;
    }
    public int getValue() 
    {
        return value;
    }
 
}
