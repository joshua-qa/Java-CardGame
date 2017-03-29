package com.Joshua.CardGame;

import java.util.*;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Game {
    private Stack<Card> tempDeck;
    private Field[] field;
    private Home[] home; // 0 : spade, 1 : heart, 2 : diamond, 3 : club
    ArrayList<Integer> fieldSizeArray = new ArrayList<>();

    private void init(Deck deck) {
        home = new Home[4];
        field = new Field[7];
        tempDeck = new Stack<>();

        for (int i = 0; i < home.length; i++) {
            home[i] = new Home();
        }

        for (int i = 0; i < field.length; i++) {
            field[i] = new Field();
            for(int j = 0; j <= i; j++) {
                field[i].add(deck.getCards().pop());
            }
        }
    }

    private boolean deckCardPop(Deck deck) {
        if (deck.getCards().isEmpty()) {
            System.err.print("덱에 더 이상 카드가 없습니다.\n");
            return false;
        } else {
            tempDeck.push(deck.getCards().pop());
        }
        return true;
    }

    private void homeDisplay() {
        for (int i = 0; i < home.length; i++) {
            if (home[i].size() == 0) {
                System.out.print("[    ] ");
            } else {
                System.out.print(home[i].peek().toString() + " ");
            }
        }
    }

    private void deckDisplay() {
        if (tempDeck.size() == 0) {
            System.out.println(" [    ]");
        } else {
            System.out.println("   " + tempDeck.peek().toString());
        }
    }

    private void cardDisplay() {
        for (int i = 0; i < getFieldMaxSize(); i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[j].size() <= i) {
                    System.out.print("      ");
                } else {
                    System.out.print(field[j].elementAt(i).toString() + " ");
                    if (field[j].elementAt(i).getCardNumber().getRank() != 10) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }
    }

    private void gameDisplay() {
        homeDisplay();
        deckDisplay();
        cardDisplay();
    }

    private int getFieldMaxSize() {
        for (int i = 0; i < field.length; i++) {
            fieldSizeArray.add(field[i].size());
        }
        return Collections.max(fieldSizeArray);
    }

    public void play() {
        Deck deck = new Deck();
        init(deck);
        gameDisplay();
    }

    public void menu() {
        boolean flag = true;

        while(flag) {
            
        }

        if(!flag) {
            gameover();
        }
    }

    private void gameover() {
        System.out.println("game over");
    }
}
