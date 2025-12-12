package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Aoc12 {
    static List<Shape> presentList;

    public static void main(final String[] args) {
        final InputStream is = App.class.getClassLoader().getResourceAsStream("input12.txt");
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final String[] lines = br.lines().toArray(String[]::new);
        presentList = new ArrayList<>();
        int trivialPass = 0;
        int trivialFail = 0;
        int nonTrivial = 0;
        for(int i = 0; i < lines.length; i++) {
            if(lines[i].length() == 2){
                int index = Integer.parseInt(lines[i].substring(0,1));
                int[][] shape = new int[3][3];
                int size = 0;
                for(int j = 1; j <= 3; j++){
                    char[] linechars = lines[i+j].toCharArray();
                    for(int k = 0; k < 3; k++){
                        if(linechars[k] == '#'){
                            shape[k][j - 1] = 1;
                            size++;
                        }
                    }
                }
                presentList.add(new Shape(index, size, shape));
                i += 4;
            }
            if(lines[i].length() > 3){
                String[] line = lines[i].split(" ");
                String[] dimensions = line[0].split("x");
                int x = Integer.parseInt(dimensions[0]);
                int y = Integer.parseInt(dimensions[1].substring(0,dimensions[1].length()-1));
                int area = x * y;
                int[] presentCounts = new int[presentList.size()];
                int sum = 0;
                int trivialSum = 0;
                for(int j = 1; j < line.length; j++){
                    int presentIndex = j - 1;
                    presentCounts[presentIndex] = Integer.parseInt(line[j]);
                    sum += presentCounts[presentIndex] * presentList.get(presentIndex).size();
                    trivialSum += presentCounts[presentIndex] * 9;
                }

                if(trivialSum <= area){
                    trivialPass++;
                } else if(sum > area){
                    trivialFail++;
                } else {
                    nonTrivial++;
                }
            }
        }
        System.out.println("Present List: " +  presentList);
        System.out.println("Trivial Pass: " + trivialPass);
        System.out.println("Trivial Fail: " + trivialFail);
        System.out.println("Non Trivial: " + nonTrivial);


    }

    private static record Shape(int index, int size, int[][] shape) {
    }
}
