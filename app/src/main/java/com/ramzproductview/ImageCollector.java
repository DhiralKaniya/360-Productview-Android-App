package com.ramzproductview;

import java.util.ArrayList;

/**
 * Created by The_King on 6/2/2017.
 */

public class ImageCollector {
    private ArrayList<Image> myImages;

    public ImageCollector() {
        this.myImages = new ArrayList<Image>();
    }
    public void addImages(Image image){
        this.myImages.add(image);
    }
    public void removeImage(Image image){
        for(Image img : myImages){
            if(img.getImageurl().equals(image.getImageurl())){
                myImages.remove(img);
                break;
            }
        }
    }
    public int getTotalImages(){
        return this.myImages.size();
    }
}
