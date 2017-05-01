package com.Joshua.CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Field extends Stack<Card> {
    private static ArrayList<Integer> fieldSizeArray = new ArrayList<>();

    public Field() {
        super();
    }

    public static int getFieldMaxSize(Field[] fields) {
        fieldSizeArray.clear();
        IntStream.range(0, fields.length).forEach((int i) -> fieldSizeArray.add(fields[i].size()));
        return Collections.max(fieldSizeArray);
    }

    public static void checkInvisibleCard(Field[] field) {
        for (int i = 0; i < field.length; i++) {
            if (field[i].isEmpty()) {
                continue;
            } else {
                if (!field[i].peek().getVisibleType()) {
                    field[i].peek().setVisibleType(true);
                }
            }
        }
    }
}
