package com.dangDog.dangDog.service.mp;

public class GoodMediaPlayer {
    public String playVideo(String path, IMediaPlayer player) {

        return player.play(path);
    }
}
