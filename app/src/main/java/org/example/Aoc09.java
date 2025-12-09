package org.example;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Aoc09 {
    public static void task9Rectangles(String[] lines) {
        final int tileCount = lines.length;
        final int[][] tileCoordinates = new int[2][tileCount];
        for (int i = 0; i < tileCount; i++) {
            final String[] line = lines[i].split(",");
            tileCoordinates[0][i] = Integer.parseInt(line[0]);
            tileCoordinates[1][i] = Integer.parseInt(line[1]);
        }
        final Polygon polygon = new Polygon(tileCoordinates[0], tileCoordinates[1], tileCount);

        final long[][] rectangleSizes = new long[tileCount][tileCount];
        long max = 0;
        for (int i = 0; i < tileCount; i++) {
            for (int j = 0; j < i; j++) {
                final int x = Math.min(tileCoordinates[0][i], tileCoordinates[0][j]);
                final int y = Math.min(tileCoordinates[1][i], tileCoordinates[1][j]);
                final int width = Math.abs(tileCoordinates[0][i] - tileCoordinates[0][j]);
                final int height = Math.abs(tileCoordinates[1][i] - tileCoordinates[1][j]);
                final Rectangle2D rectangle = new Rectangle2D.Double(x,y,width,height);
                if (polygon.contains(rectangle)) {
                    rectangleSizes[i][j] = (width + 1L) * (height + 1L);
                    if  (rectangleSizes[i][j] > max) {
                        max = rectangleSizes[i][j];
                    }
                }
            }
        }
        System.out.println(max);
    }
}
