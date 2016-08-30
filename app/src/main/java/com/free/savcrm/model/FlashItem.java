package com.free.savcrm.model;

/**
 * Created by bynkaa on 7/22/2016.
 */
public class FlashItem
{
    private int drawableBackground;
    private String title;
    private String message;

    public FlashItem(int drawableBackground, String title, String message) {
        this.drawableBackground = drawableBackground;
        this.title = title;
        this.message = message;
    }

    public int getDrawableBackground() {
        return drawableBackground;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
