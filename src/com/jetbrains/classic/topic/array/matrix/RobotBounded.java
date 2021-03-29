package com.jetbrains.classic.topic.array.matrix;

public class RobotBounded {
    public boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
        // initial position
        int x = 0, y = 0;
        // facing north
        int idx = 0;

        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                x += directions[idx][0];
                y += directions[idx][1];
            } else if (c == 'L') {
                idx = (idx + 3) % 4;
            } else if (c == 'R') {
                idx = (idx + 1) % 4;
            }
        }

        // after one cycle, robot returns into initial position or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }
}
