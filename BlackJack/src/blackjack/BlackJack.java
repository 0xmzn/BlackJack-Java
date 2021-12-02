/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author msaad
 */
public class BlackJack {
    static Game game = new Game();
    static Scanner scanner = new Scanner(System.in);
    static GUI gui = new GUI();
    public static void main(String args[]) 
    {
        game.generateDeck();
        game.definePlayers();
        game.getPlayerInfo();
        
        gui.runGUI(game.cards, game.players[0].pCards, game.players[1].pCards,
        game.players[2].pCards, game.players[3].pCards);
        game.gameScoreUpdate();
        gamePhases();
        game.gameScoreUpdate();
        getWinner();
    }

    public static void gamePhases()
    {
        for(int i=0;i<3;i++)
        {
            System.out.println(game.players[i].name +", Hit or Stand?");
            String command = scanner.next();
            while(!command.toLowerCase().equals("stand"))
            {
                // normal player  
                if(command.equals("hit"))
                {
                    Card card = game.drawCard();
                    game.players[i].pCards[game.players[i].cardIndex] = card;
                    game.players[i].score+= card.getValue();
                    game.players[i].cardIndex++;
                    gui.updatePlayerHand(card, i);
                }
                if(game.players[i].score >= 21)
                    break;
                    
                System.out.println(game.players[i].name +", Hit or Stand?");
                command = scanner.next();
            }
        }
        // dealer
        game.gameScoreUpdate();
        boolean isWinning = true;
        int highestScore = 0;
        for (int j = 0; j < 3; j++) 
        {
            if (game.highscore[j] >= game.players[3].score)
                isWinning = false;

            if (game.highscore[j] > highestScore)
                highestScore = game.highscore[j];
        }
        if (isWinning == false) 
        {
            while (game.players[3].score <= highestScore) 
            {
                Card card = game.drawCard();
                game.players[3].pCards[game.players[3].cardIndex] = card;
                game.players[3].score += card.getValue();
                game.players[3].cardIndex++;
                gui.updateDealerHand(card, game.cards);
                if (game.players[3].score == 21)
                    break;
            }
            
        }         
    }
    public static void getWinner()
    {
        int highestScore = 0;
        int counter = 0;
        int player = -1;
        for( int i = 0; i< 4 ;i++)
        {
            if(game.highscore[i] > highestScore)
            {
                highestScore = game.highscore[i];
            } 
        }
        for (int i=0;i<4;i++)
        {
            if(game.highscore[i] == highestScore)
            {
                counter++;
                player = i;
            } 
        }
        if ( counter > 1 )
        {
            System.out.println("A Push!");
            return;
        }
        else 
        {
            System.out.println("The Winner is " + game.players[player].name + " with score of " + game.players[player].score);
            return;
        }
    }


}
