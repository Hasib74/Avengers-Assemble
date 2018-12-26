package com.example.hasib.a2dcomicspuzzlegame.Model;

public class ScoreMedium {

    private   String playerName;
    private   String time;
    private   String imageName;
    private  String mode;

    public ScoreMedium(String playerName, String time, String imageName, String mode) {
        this.playerName = playerName;
        this.time = time;
        this.imageName = imageName;
        this.mode = mode;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
