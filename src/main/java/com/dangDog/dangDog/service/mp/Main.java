package com.dangDog.dangDog.service.mp;

public class Main {

    private static String path = "/ekorhan/bilmembne/e";

    public static void main(String[] args) {
        //BadMediaPlayer badMediaPlayer = new BadMediaPlayer();
        //String response = badMediaPlayer.playVideo(path, "mp4");
        //System.out.println(response);


        GoodMediaPlayer goodMediaPlayer = new GoodMediaPlayer();

        MP4Player player = new MP4Player();

        String response= goodMediaPlayer.playVideo(path, player);
        System.out.println(response);
    }
}
