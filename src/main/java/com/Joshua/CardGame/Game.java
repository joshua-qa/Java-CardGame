package com.Joshua.CardGame;

import java.util.*;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Game {
    private Stack<Card> tempDeck, mainDeck;
    private Field[] field;
    private Home[] home; // 0 : spade, 1 : heart, 2 : diamond, 3 : club
    ArrayList<Integer> fieldSizeArray = new ArrayList<>();

    public void init(Deck deck) {
        home = new Home[4];
        field = new Field[7];
        tempDeck = new Stack<>();
        mainDeck = new Stack<>();

        for (int i = 0; i < home.length; i++) {
            home[i] = new Home();
        }

        for (int i = 0; i < field.length; i++) {
            field[i] = new Field();
            for(int j = 0; j <= i; j++) {
                field[i].add(deck.getCards().pop());
            }
        }

        mainDeck = (Stack<Card>)deck.getCards().clone();
        deck.getCards().clear();
    }

    public boolean deckCardPop() {
        if (mainDeck.isEmpty()) {
            System.err.print("덱에 더 이상 카드가 없습니다.\n");
            return false;
        } else {
            tempDeck.push(mainDeck.pop());
            System.out.println(tempDeck.peek() + " 뽑음");
        }
        return true;
    }

    public boolean move() {
        return true;
    }

    public boolean moveHome() {
        return true;
    }

    public boolean isKing() {
        return true;
    }

    public boolean isAce() {
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
            System.out.println("             [    ]\n");
        } else {
            System.out.println("              " + tempDeck.peek().toString() + "\n");
        }
    }

    private void cardDisplay() {
        for (int i = 0; i < getFieldMaxSize(); i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[j].size() <= i) {
                    System.out.print("       ");
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
        menu();
    }

    public void menu() {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int select = 0;

        // 메서드 분리 필요할듯.
        while(flag) {
            gameDisplay();
            System.out.println("명령어를 입력해주세요. 1 : 카드 넘기기, 2 : 카드 이동, 9: 종료");
            select = inputCheck(scan);
            if(select == 1) {
                deckCardPop();
            } else if(select == 2) {
                System.out.println("이동할 카드가 있는 필드 번호를 입력해주세요. (1-7)");
                select = inputCheck(scan);
                if(select > 0 && select <= 7) {
                    System.out.println("이동하려는 위치를 입력해주세요. (필드는 1-7, 홈은 0)");
                    select = inputCheck(scan);
                    if(select == 0) {
                        moveHome();
                    } else if(select > 0 && select <= 7) {
                        move();
                    } else {
                        inputError();
                    }
                } else {
                    inputError();
                }
            } else if(select == 9) {
                flag = false;
                break;
            } else {
                inputError();
            }
        }

        if(!flag) {
            gameover();
        }
    }

    private void gameover() {
        System.out.println("game over");
    }

    private void inputError() {
        System.err.println("입력 오류. 다시 입력해주세요.");
    }

    public int inputCheck(Scanner sc) {
        String inputString = "";
        int result = 0;
        inputString = sc.next();
        try {
            result = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            System.err.println("숫자를 입력해 주세요.");
            return inputCheck(sc);
        }

        return result;
    }
}
