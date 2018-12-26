package com.example.hasib.a2dcomicspuzzlegame.PuzzeAnimation;

import android.view.animation.TranslateAnimation;

public class AnimationPuzze {
   public static TranslateAnimation downtoupanimation;
   public static  TranslateAnimation lefttorightanimation;
   public static TranslateAnimation righttoleft;
   public static TranslateAnimation uptodown;

   public  AnimationPuzze (){

   }

    public static void uptodown() {
        uptodown=new TranslateAnimation(0,0,-1000,0);
        uptodown.setDuration(500);
        uptodown.setFillAfter(true);
    }

    public static void righttoleft() {
        righttoleft=new TranslateAnimation(1000,0,0,0);
        righttoleft.setDuration(500);
        righttoleft.setFillAfter(true);
    }

    public static void lefttoright() {
        lefttorightanimation=new TranslateAnimation(-1000,0,0,0);
        lefttorightanimation.setDuration(500);
        lefttorightanimation.setFillAfter(true);
    }

    public static   void downtoup() {
        downtoupanimation=new TranslateAnimation(0,0,1000,0);
        downtoupanimation.setDuration(500);
        downtoupanimation.setFillAfter(true);
    }
}
