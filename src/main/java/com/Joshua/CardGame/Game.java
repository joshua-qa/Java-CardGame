package com.Joshua.CardGame;

import java.util.*;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Game {
    private Stack<Card> tempDeck, mainDeck;
    private Field[] field;
    private Home[] home; // 0 : spade, 1 : heart, 2 : diamond, 3 : club

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
        if (mainDeck.isEmpty() && tempDeck.size() > 0) {
            System.out.println("이전에 넘긴 카드를 다시 덱으로 돌립니다.");
            int deckSize = tempDeck.size();
            for(int i = 0; i < deckSize; i++) {
                mainDeck.push(tempDeck.pop());
            }

            return false;
        } else if (mainDeck.isEmpty() && tempDeck.size() == 0) {
            System.err.println("더 이상 넘길 카드가 없습니다.\n");
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
        if(isHomeMovable(start.peek())) {
            int homeNumber = start.peek().getCardType().getCardValue();
            moveCard(start, home[homeNumber], 1);
        } else {
            return false;
        }
        return true;
    }

    private boolean isHomeMovable(Card card) {
        int homeNumber = card.getCardType().getCardValue();

        // home이 비어있을 경우 카드가 Ace인지 확인
        if(home[homeNumber].isEmpty()) {
            if(isAce(card)) {
                return true;
            } else {
                System.err.println("해당 무늬의 Ace부터 채워져야 합니다. 메뉴로 돌아가서 다시 시도해주세요.");
                return false;
            }
        } else {
            if(home[homeNumber].peek().getCardNumber().getRank() + 1 == card.getCardNumber().getRank()) {
                return true;
            } else {
                System.err.println("해당 무늬의 " + (card.getCardNumber().getRank() - 1) + "번째 카드까지 홈에 채워주신 후 다시 시도해주세요.");
                return false;
            }
        }
    }

    private boolean moveDeck(Scanner sc) {
        if(tempDeck.isEmpty()) {
            System.err.println("먼저 카드 넘기기를 한 뒤 시도해주세요.");
            return false;
        }

        System.out.println("이동하려는 위치를 입력해주세요. (1-7, 홈은 0)\n메뉴로 돌아가려면 8번을 입력해주세요.");
        int select = inputCheck(sc);

        if(select == 0) {
            moveHome(tempDeck);
        } else if(select > 0 && select <= 7) {
            if(field[select-1].isEmpty()) {
                if (isKing(tempDeck.peek())) {
                    moveCard(tempDeck, field[select-1], 1);
                } else {
                    System.out.println("해당 필드가 비어있지만 옮기려는 카드가 킹이 아닙니다. 메뉴로 돌아가서 다시 시도해주세요.");
                    return false;
                }
            } else {
                if (isMovable(tempDeck.peek(), field[select - 1].peek())) {
                    moveCard(tempDeck, field[select - 1], 1);
                } else {
                    System.err.println("그곳으로는 이동하실 수 없습니다. 다른 위치를 입력해주세요.");
                }
            }
        } else if(select == 8) {
            return false;
        } else {
            inputError();
            return moveDeck(sc);
        }

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
            if (! ((field.elementAt(i).getCardNumber().getRank() - 1 == field.elementAt(i+1).getCardNumber().getRank())
                && (!field.elementAt(i).getColorType().getColorTypeValue().equals( field.elementAt(i+1).getColorType().getColorTypeValue() ))) ) {
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

    private void gameDisplay() {
        homeDisplay();
        deckDisplay();
        cardDisplay();
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
            System.out.println("명령어를 입력해주세요. 1 : 카드 넘기기, 2 : 카드 이동(필드), 3 : 카드 이동(덱), 9: 종료");
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
            } else if(select == 3) {
                moveDeck(scan);
            } else if(select == 9) {
                flag = false;
                break;
            } else {
                inputError();
            }

            if(isGameClear()) {
                System.out.println("게임에서 승리하셨습니다.");
                flag = false;
                break;
            }
        }

        if(!flag) {
            gameover();
        }
    }

    private boolean isGameClear() {
        for (int i = 0; i < home.length; i++) {
            if (home[i].isEmpty()) {
                return false;
            } else {
                if (home[i].peek().getCardNumber().getRank() != 13) {
                    return false;
                }
            }
        }
        return true;
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
