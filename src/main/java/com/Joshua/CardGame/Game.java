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

    public boolean move(Stack<Card> start, Stack<Card> dest, Scanner sc) {
        System.out.println("이동할 카드 장수를 입력해주세요. 메뉴로 돌아가시려면 0번을 입력해주세요");
        int select = inputCheck(sc);
        boolean flag = true;

        while(flag) {
            if (select == 0) {
                return false;
            } else if (select > 0 && select <= start.size()) {
                if (isChain(start, select)) {
                    flag = false;
                    break;
                } else {
                    System.out.println("카드가 연결되어 있지 않습니다. 연결되어있는 장수 이내에서 입력해주세요.");
                    return move(start, dest, sc);
                }
            } else {
                System.err.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                return move(start, dest, sc);
            }
        }

        if(dest.size() == 0) {
            
        }

        if(isKing(start.elementAt(cardPosition(start, select))) && dest.isEmpty()) {
            moveCard(start, dest, select);
            return true;
        } else if(!isKing(start.elementAt(cardPosition(start, select))) && dest.isEmpty()) {
            System.err.println("이동하려는 필드가 비어있지만 옮기려는 카드에 킹이 포함되어 있지 않습니다.\n메뉴로 돌아가서 이동할 위치를 다시 입력해주세요.");
            return false;
        }

        if(isMovable(start.elementAt(cardPosition(start, select)), dest.peek())) {
            moveCard(start, dest, select);
        } else {
            System.err.println("그곳으로는 이동할 수 없습니다.\n이동할 카드 장수를 다시 입력해주시거나, 메뉴로 돌아가서 이동할 위치를 다시 입력해주세요.");
            return move(start, dest, sc);
        }

        return true;
    }

    private int cardPosition(Stack<Card> target, int index) {
        return target.size() - index;
    }

    public boolean moveHome(Stack<Card> start) {
        return true;
    }

    private void moveCard(Stack<Card> start, Stack<Card> dest, int select) {
        Stack<Card> temp = new Stack<>();

        for (int i = 0; i < select; i++) {
            temp.push(start.pop());
        }
        for (int j = 0; j < select; j++) {
            dest.push(temp.pop());
        }

        System.out.println("카드가 " + select + "장 이동되었습니다.");
    }

    public boolean isKing(Card card) {
        return card.getCardNumber().getRank() == 13;
    }

    public boolean isAce(Card card) {
        return card.getCardNumber().getRank() == 1;
    }

    public boolean isChain(Stack<Card> field, int input) {
        for (int i = field.size() - input; i < field.size() - 1; i++) {
            if (! ((field.elementAt(i).getCardNumber().getRank() + 1 == field.elementAt(i-1).getCardNumber().getRank())
                && (!field.elementAt(i).getColorType().getColorTypeValue().equals( field.elementAt(i-1).getColorType().getColorTypeValue() ))) ) {
                return false;
            }
        }

        return true;
    }

    public boolean isMovable(Card start, Card dest) {
        if (! ((start.getCardNumber().getRank() == dest.getCardNumber().getRank()-1)
                && (!start.getColorType().getColorTypeValue().equals( dest.getColorType().getColorTypeValue() ))) ) {
            return false;
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
            System.out.println("             [    ]\n");
        } else {
            System.out.println("              " + tempDeck.peek().toString() + "\n");
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
        menu();
    }

    public void menu() {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        int select = 0, selectTarget = 0;

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
                if((select > 0 && select <= 7) && !field[select-1].isEmpty()) {
                    System.out.println("이동하려는 위치를 입력해주세요. (필드는 1-7, 홈은 0)");
                    selectTarget = inputCheck(scan);
                    if(selectTarget == 0) {
                        moveHome(field[select-1]);
                    } else if(selectTarget > 0 && selectTarget <= 7) {
                        move(field[select-1], field[selectTarget-1], scan);
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
