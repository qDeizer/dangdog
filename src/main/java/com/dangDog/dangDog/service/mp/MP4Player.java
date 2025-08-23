package com.dangDog.dangDog.service.mp;

public class MP4Player implements IMediaPlayer {
    private int counter = 1;

    public MP4Player() {
        counter = counter + 1;
    }

    public MP4Player getOwn() {
        return this;
    }

    @Override
    public String play(String fileNameWithPath) {
        return "Mp4 oynatildi!";
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
