package com.Joshua.CardGame;

import java.util.List;
import java.util.Stack;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Game {
    private Stack<Card> home_spade, home_club, home_heart, home_diamond, tempDeck;
    private Field[] field;

    private void init(Deck deck) {
        field = new Field[7];

        for(int i = 0; i < field.length; i++) {
            field[i] = new Field();
            for(int j = 0; j <= i; j++) {
                field[i].add(deck.getCards().pop());
                System.out.println("field " + (i+1) + " card add - " + field[i].peek());
            }
        }
    }

    public void play() {
        Deck deck = new Deck();
        init(deck);
    }


}
