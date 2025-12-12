package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Aoc12 {
    public static void main(final String[] args) {
        final InputStream is = App.class.getClassLoader().getResourceAsStream("testinput.txt");
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final String[] lines = br.lines().toArray(String[]::new);
    }
}
