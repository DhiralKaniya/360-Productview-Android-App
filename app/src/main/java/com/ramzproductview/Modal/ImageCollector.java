package com.ramzproductview.Modal;

import java.util.ArrayList;

/**
 * Created by The_King on 6/2/2017.
 */

public class ImageCollector {
    private ArrayList<Image> myImages;
    public ImageCollector(ArrayList<Image> myimage){
        this.myImages = myimage;
    }
    public ImageCollector() {
        this.myImages = new ArrayList<Image>();
    }
    public void addImages(Image image){
        this.myImages.add(image);
    }
    public ArrayList<Image> getAllimage(){ return this.myImages; }
    public void removeImage(Image image){
        for(Image img : myImages){
            if(img.getImageurl().equals(image.getImageurl())){
                myImages.remove(img);
                break;
            }
        }
    }
    public void addMultiple(ArrayList<Image> image){
        this.myImages = image;
    }
    public int getTotalImages(){
        return this.myImages.size();
    }
    public Image getFirstImage(){return this.myImages.get(0); }
}
