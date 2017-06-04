package com.ramzproductview;

import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ramzproductview.Modal.Image;
import com.ramzproductview.Modal.ImageCollector;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class viewImage extends AppCompatActivity {
    private ImageCollector myImages;
    private ProductShowCaseWebView myWebView;

    private static final String TAG = "viewImage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        myWebView = null;
        myWebView = (ProductShowCaseWebView) findViewById(R.id.web_view);
        boolean flag = fetchImages();
        if(flag == true){
            String Image360 =  "";
            if(myImages.getTotalImages() == 19){
                for(Image img : myImages.getAllimage()){
                    Image360+="<img src=\""+img.getUrl()+"\">";
                }
            }
            else if(myImages.getTotalImages() == 8 ){
                Image360+="<img src=\""+myImages.getFirstImage().getUrl()+"\">";
                Image360+="<img src=\""+myImages.getFirstImage().getUrl()+"\">";
                Image360+="<img src=\""+myImages.getFirstImage().getUrl()+"\">";
                for(Image img : myImages.getAllimage()){
                    Image360+="<img src=\""+img.getUrl()+"\">";
                    Image360+="<img src=\""+img.getUrl()+"\">";
                }
            }
            else if(myImages.getTotalImages() == 4){
                Image360+="<img src=\""+myImages.getFirstImage().getUrl()+"\">";
                Image360+="<img src=\""+myImages.getFirstImage().getUrl()+"\">";
                Image360+="<img src=\""+myImages.getFirstImage().getUrl()+"\">";
                for(Image img : myImages.getAllimage()){
                    Image360+="<img src=\""+img.getUrl()+"\">";
                    Image360+="<img src=\""+img.getUrl()+"\">";
                    Image360+="<img src=\""+img.getUrl()+"\">";
                    Image360+="<img src=\""+img.getUrl()+"\">";
                }
            }
            Log.d(TAG,Image360);
            myWebView.loadDataWithBaseURL("",Image360,"text/html","UTF-8",null);
        }
    }
    public boolean fetchImages(){
        SharedPreferences sharedPreferences = getSharedPreferences("mypreference",MODE_PRIVATE);
        Gson gson = new Gson();
        String myData = sharedPreferences.getString("myImages",null);
        Log.d(TAG,myData);
        Type type = new TypeToken<ArrayList<Image>>(){}.getType();
        ArrayList<Image> data = gson.fromJson(myData,type);
        Log.d(TAG,""+data.size());
        myImages = new ImageCollector(data);

        if(myImages != null) {
            Log.d(TAG, "Total Images -> " + myImages.getTotalImages());
            if (myImages.getTotalImages() <= 0)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(viewImage.this,MainActivity.class);
        //myWebView.reload();

        myWebView = null;
        startActivity(mainIntent);
        finish();
    }
}
