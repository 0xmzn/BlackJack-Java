/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author msaad
 */
public class Game {
    public Player[] players = new Player[4];
    public Card[] cards = new Card[52];
    public int highscore[] = new int[4];
    Scanner scanner = new Scanner(System.in);
    
    public void definePlayers()
    {
        for(int i = 0; i<4;i++)
        {
            players[i] = new Player();
        }
    }
    
    public void gameScoreUpdate()
    {
        for(int i=0;i<4;i++)
        {
            if(players[i].score <= 21)
                highscore[i] = players[i].score;
            else
                highscore[i] = 0;
        }
    }
    
    public void getPlayerInfo()
    {
        for(int i = 0; i<3;i++)
        {
            System.out.printf("Name of Player %d: ", i+1);
            players[i].name = scanner.next();
            if (players[i].cardIndex < 11)
            {
                Card card = drawCard();
                players[i].pCards[players[i].cardIndex] = card;
                players[i].score+= card.getValue();
                players[i].cardIndex++;
            }
            if (players[i].cardIndex < 11)
            {
                Card card = drawCard();
                players[i].pCards[players[i].cardIndex] = card;
                players[i].score+= card.getValue();
                players[i].cardIndex++;
            }
        }
        if (players[3].cardIndex < 11)
        {
            players[3].name = "Dealer";
            Card card = drawCard();
            players[3].pCards[players[3].cardIndex] = card;
            players[3].score+= card.getValue();
            players[3].cardIndex++;
        }
        if (players[3].cardIndex < 11)
        {
            Card card = drawCard();
            players[3].pCards[players[3].cardIndex] = card;
            players[3].score+= card.getValue();
            players[3].cardIndex++;
        }
        
    }
    
    
    public void generateDeck()
    {
        int pos =0;
        for(int i=0;i<4;i++)
        {
            for (int j=0;j<13;j++)
            {
                cards[pos] = new Card(i, j);
                pos++;
            }
        }
    }
    
    private int generateRandomNum()
    {
        Random rand = new Random();
        int upperbound = 52; // generate num from 0-51
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }
    public Card drawCard()
    {
        int randNum = generateRandomNum();
        Card card = cards[randNum];
        while (card == null)
        {
            randNum = generateRandomNum();
            card = cards[randNum];
        }
        cards[randNum] = null;
        return card;
    }
}