package org.example;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

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


    static List<List<Integer>> findCombination(final int indexesSize, final int combinationSize) {
        final int[] indexes = new int[indexesSize];
        for(int i = 0; i < indexesSize; i++){
            indexes[i] = i;
        }
        // to store the result
        final List<List<Integer>> result = new ArrayList<>();
        // Temporary array to store current combination
        final List<Integer> data = new ArrayList<>();
        combinationUtil(0, combinationSize, data, result, indexes);
        return result;
    }

    static void combinationUtil(final int index, final int combinationSize, final List<Integer> combination,
                                final List<List<Integer>> result, final int[] indexes) {
        final int indexesSize = indexes.length;
        // If size of current combination is combinationSize
        if (combination.size() == combinationSize) {
            result.add(new ArrayList<>(combination));
            return;
        }
        // Replace index with all possible elements
        for (int i = index; i < indexesSize; i++) {
            // Current element is included
            combination.add(indexes[i]);
            // Recur for next elements
            combinationUtil(i + 1, combinationSize, combination, result, indexes);
            // Backtrack to find other combinations
            combination.removeLast();
        }
    }
}
