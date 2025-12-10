package org.example;

import java.util.BitSet;
import java.util.List;

import static org.example.App.findCombination;

public class Aoc10 {
    public static void task1(final String[] lines) {
        long sum = 0;
        for(int i = 0; i < lines.length; i++){
            final String[] line = lines[i].split(" ");
            final String correctString = line[0].substring(1, line[0].length() - 1);
            final BitSet correctBitSet = new BitSet(correctString.length());
            for(int j = 0; j < correctString.length(); j++){
                if(correctString.charAt(j) == '#'){
                    correctBitSet.set(j);
                }
            }
            final BitSet[] buttons = new BitSet[line.length - 2];
            for(int j = 1; j < line.length - 1; j++){
                final int buttonsIndex = j - 1;
                buttons[buttonsIndex] = new BitSet(correctBitSet.length());
                final String buttonsString = line[j].substring(1, line[j].length() - 1);
                final String[] buttonLights = buttonsString.split(",");
                for (final String buttonLight : buttonLights) {
                    buttons[buttonsIndex].set(Integer.parseInt(buttonLight));
                }
            }

            int hitInputSize = -1;
            for(int j = 1; j <= buttons.length && hitInputSize < 1; j++){
                final List<List<Integer>> combinationList = findCombination(buttons.length, j);
                for(final List<Integer> combination : combinationList){
                    final BitSet result = new BitSet(correctBitSet.length());
                    for (final Integer buttonIndex : combination) {
                        result.xor(buttons[buttonIndex]);
                    }
                    if(result.equals(correctBitSet)){
                        hitInputSize = j;
                    }
                }
            }
            sum += hitInputSize;
            System.out.println("Hit input size for line " + i + ": " + hitInputSize);
        }
        System.out.println("Min hit input size: " + sum);
    }
}
