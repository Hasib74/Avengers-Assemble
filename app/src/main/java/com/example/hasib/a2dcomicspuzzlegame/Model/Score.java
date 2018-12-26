package com.example.hasib.a2dcomicspuzzlegame.Model;


public class Score {
    private String mode;
    private String name;
    private String playerName;
    private String time;
    private  int image;

    public Score(String mode, String name, String playerName, String time, int image) {
        this.mode = mode;
        this.name = name;
        this.playerName = playerName;
        this.time = time;
        this.image = image;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
