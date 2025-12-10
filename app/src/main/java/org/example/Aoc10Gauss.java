package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import Jama.Matrix;

public class Aoc10Gauss {

    public static void main(String[] args) {
        final InputStream is = App.class.getClassLoader().getResourceAsStream("testinput.txt");
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final String[] lines = br.lines().toArray(String[]::new);
        for (int i = 0; i < lines.length; i++) {
            final String[] line = lines[i].split(" ");
            final String[] correctVoltageString = line[line.length - 1].substring(1, line[line.length - 1].length() - 1).split(",");
            int currentLineMachineCount = correctVoltageString.length;
            final double[] correctVoltage = new double[currentLineMachineCount];
            for(int j = 0; j < currentLineMachineCount; j++){
                correctVoltage[j] =  Integer.parseInt(correctVoltageString[j]);
            }

            final double[][] buttons = new double[correctVoltage.length][line.length - 2];
            for(int j = 1; j < line.length - 1; j++){
                final int buttonsIndex = j - 1;
                final double[] buttonLine = new double[correctVoltage.length];
                final String buttonsString = line[j].substring(1, line[j].length() - 1);
                final String[] buttonLights = buttonsString.split(",");
                for (final String buttonLight : buttonLights) {
                    buttonLine[Integer.parseInt(buttonLight)] = 1;
                }
                for (int k = 0; k < buttonLine.length; k++) {
                    buttons[k][buttonsIndex] = buttonLine[k];
                }
            }
            System.out.println("Line"+i+":" + Arrays.deepToString(buttons));

            Matrix voltageMatrix = new Matrix(correctVoltage, 1).transpose();
            Matrix buttonsMatrix = new Matrix(buttons);
            Matrix resultMatrix = buttonsMatrix.solve(voltageMatrix);
            System.out.println(resultMatrix);

            /*
            GaussianElimination gaussianElimination = new GaussianElimination(buttons, correctVoltage);
            double[] primal = gaussianElimination.primal();
            System.out.println(Arrays.toString(primal));
             */
        }
    }
}
