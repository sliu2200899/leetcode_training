package com.jetbrains.zCompanyInterviewPrep.amazon.ood;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player {
    private List<Song> playlist;
    private HandleRequestStrategy strategy = null;
    private int index;
    private Deque<Integer> queue = new LinkedList<>();

    public void setStrategy(HandleRequestStrategy strategy) {
        this.strategy = strategy;
    }

    public void playSong() {
        this.index = strategy.handleRequest(index, playlist);
    }

    public void skipSong(int timestamp) {

        while (!queue.isEmpty() && (timestamp - queue.peek() > 3600)) {
            queue.poll();
        }

        if (queue.size() < 5) {
            queue.offer(timestamp);
            playSong();
        }
    }


}

interface HandleRequestStrategy {
    public int handleRequest(int curSongIndex, List<Song> playlist);
}

class RandomPlayStrategy implements HandleRequestStrategy {
    @Override
    public int handleRequest(int curSongIndex, List<Song> playlist) {
        Random rand = new Random();
        int n = rand.nextInt(playlist.size());
        playlist.get(n).play();
        return n;
    }
}

class SequentialPlayStrategy implements HandleRequestStrategy {
    @Override
    public int handleRequest(int curSongIndex, List<Song> playlist) {
        int index = (curSongIndex + 1) % playlist.size();
        playlist.get(index).play();
        return index;
    }
}

class Song {
    int id;
    public Song(int i) {
        this.id = i;
    }

    public void play() {
        System.out.println("the song " + this.id + " is playing...");
    }
}
