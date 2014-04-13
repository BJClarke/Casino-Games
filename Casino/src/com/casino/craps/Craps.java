package com.casino.craps;

import java.util.Random;

// -------------------------------------------------------------------------
/**
 * This is a new class to make a craps game
 *
 * @author Brian Clarke
 * @version Apr 9, 2014
 */
public class Craps
{

    private int       dice1;
    private int       dice2;
    private final int INITIAL_MONEY = 100;
    private int       money;
    private int       combinedRoll;
    private int       count;


    // ----------------------------------------------------------
    /**
     * Create a new Craps object.
     */
    public Craps()
    {
        money = INITIAL_MONEY;
        count = 0;
    }


    // ----------------------------------------------------------
    /**
     * This generates two random numbers between 1 and 6
     */
    public void roll()
    {
        dice1 = new Random().nextInt(6) + 1;
        dice2 = new Random().nextInt(6) + 1;
        combinedRoll = dice1 + dice2;
        count++;
    }


    // ----------------------------------------------------------
    /**
     * If the next roll is a 7 or 11, you win! If the next roll is a 2, 3, or
     * 12, you lose! Otherwise, the dice continue to be rolled until that number
     * appears again which result in a win, or a 7 which results in a loss
     *
     * @param bet
     *            is the player's bet
     */
    public void passLine(int bet)
    {
        if (money > 0 && bet <= money && bet >= 0)
        {
            this.roll();
            if (combinedRoll == 7 || combinedRoll == 11)
            {
                money += bet;
            }
            else if (combinedRoll == 2 || combinedRoll == 3
                || combinedRoll == 12)
            {
                money -= bet;
            }
            else
            {
                this.passLine(bet);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Only after the first roll and if a 7 or 11 is rolled, player wins!
     *
     * @param bet
     *            is the player's bet
     */
    public void COME(int bet)
    {
        if (money > 0 && bet <= money && bet >= 0 && count != 0)
        {
            this.roll();
            if (combinedRoll == 7 || combinedRoll == 11)
            {
                money += bet;
            }
            else
            {
                money -= bet;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Almost opposite of passLine Win if roll is 2 or 3 Lose if roll is 7 or 11
     * Draw if roll is 12 otherwise roll again
     *
     * @param bet
     *            is the player's bet
     */
    public void dontPassLine(int bet)
    {
        if (money > 0 && bet <= money && bet >= 0)
        {
            this.roll();
            if (combinedRoll == 2 || combinedRoll == 3)
            {
                money += bet;
            }
            else if (combinedRoll == 7 || combinedRoll == 11)
            {
                money -= bet;
            }
            else if (combinedRoll == 12)
            {
                money += 0;
            }
            else
            {
                this.dontPassLine(bet);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Simple place bets that you can win on next roll minimum bid of 5 for 4,
     * 5, 9, or 10 minimum bid of 6 for 6 or 8
     *
     * @param num
     *            is number they place a bet on
     * @param bet
     *            is the player's bet
     */
    public void placeBets(int bet, int... num)
    {
        this.roll();
        for (int num1 : num)
        {
            if ((money > 0 && bet <= money && bet >= 5)
                && (num1 == 4 || num1 == 5 || (num1 == 6 && bet >= 6)
                    || (num1 == 8 && bet >= 6) || num1 == 9 || num1 == 10))
            {
                if (combinedRoll == num1)
                {
                    money += bet;
                }
                else
                {
                    money -= bet;
                }
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Place your bet on a 6 or 8 If that number is rolled before a 7, you win!
     * Otherwise, you lose!
     *
     * @param num
     *            is the number they place a bet on
     * @param bet
     *            is the player's bet
     */
    public void bigBet(int bet, int... num)
    {
        this.roll();
        for (int num1 : num)
        {
            if ((money > 0 && bet <= money && bet >= 0)
                && (num1 == 6 || num1 == 8))
            {
                if (combinedRoll == 7)
                {
                    money -= bet;
                }
                else if (combinedRoll == num1)
                {
                    money += bet;
                }
                else
                {
                    this.bigBet(bet, num1);
                }
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * A simple field bet that will pay if your number is rolled next If that
     * number you select is a 2 or 12 then you win double Otherwise, you lose!
     *
     * @param num
     *            is the number they place a bet on
     * @param bet
     *            is the player's bet
     */
    public void fieldBet(int bet, int... num)
    {
        this.roll();
        for (int num1 : num)
        {
            if ((money > 0 && bet <= money && bet >= 0)
                && (num1 == 2 || num1 == 3 || num1 == 4 || num1 == 9 || num1 == 10
                    || num1 == 11 || num1 == 12))
            {
                if (combinedRoll == num1)
                {
                    if (num1 == 2 || num1 == 12)
                    {
                        money += bet;
                    }
                    money += bet;
                }
                else
                {
                    money -= bet;
                }
            }
        }
    }

}
