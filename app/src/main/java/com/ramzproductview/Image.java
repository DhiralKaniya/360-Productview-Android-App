package com.ramzproductview;

/**
 * Created by The_King on 6/2/2017.
 */

public class Image {
    private int imageid;
    private String imageurl;

    public Image(int imageid, String imageurl) {
        this.imageid = imageid;
        this.imageurl = imageurl;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
