package com.Joshua.CardGame;

import java.util.Stack;

/**
 * Created by jgchoi.qa on 2017. 4. 30..
 */
public class Display {
    public void homeDisplay(Home[] home) {
        for (int i = 0; i < home.length; i++) {
            if (home[i].size() == 0) {
                System.out.print("[    ] ");
            } else {
                System.out.print(home[i].peek().toString() + " ");
            }
        }
    }

    public void deckDisplay(Stack<Card> tempDeck) {
        if (tempDeck.size() == 0) {
            System.out.println("             [    ]\n");
        } else {
            System.out.println("              " + tempDeck.peek().toString() + "\n");
        }
    }

    public void cardDisplay(Field[] field) {
        for (int i = 0; i < Field.getFieldMaxSize(field); i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[j].size() <= i) {
                    System.out.print("------ ");
                } else {
                    System.out.print(field[j].elementAt(i).toString() + " ");
                }
            }
            System.out.print("\n");
        }
    }
}
