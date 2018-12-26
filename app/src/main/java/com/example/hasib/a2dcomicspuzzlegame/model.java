package com.example.hasib.a2dcomicspuzzlegame;

/**
 * Created by HASIB on 5/16/2018.
 */

public class model {
    private int Image;
    private String mode;

    private String time;


    public model() {
    }



    public model(int image, String mode, String time) {
        Image = image;
        this.mode = mode;
        this.time = time;
    }


    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
