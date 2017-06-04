package com.ramzproductview.Modal;

import android.graphics.Bitmap;

/**
 * Created by The_King on 6/2/2017.
 */

/*
@param imageid -> specify image number
@param imageBitmap -> specify Image Bitmap
@param imageUrl -> specify image stored path
 */
public class Image {
    private int imageid;
    private Bitmap imageBitmap;
    private String imageurl;
    public Image(int imageid, Bitmap imageurl) {
        this.imageid = imageid;
        this.imageBitmap = imageurl;
        this.imageurl = new String();
    }
    public Image(int imageid,String imageurl){
        this.imageurl = imageurl;
        this.imageid = imageid;
        this.imageBitmap = null;
    }
    public Image(int imageid, Bitmap imagebtm,String imageuri) {
        this.imageid = imageid;
        this.imageBitmap = imagebtm;
        this.imageurl = imageuri;
    }
    public void addUrl(String url){
        this.imageurl = url;
    }
    public String getUrl(){
        return this.imageurl;
    }
    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public Bitmap getImageurl() {
        return imageBitmap;
    }

    public void setImageurl(Bitmap imagebitmap) {
        this.imageBitmap = imagebitmap;
    }

}
