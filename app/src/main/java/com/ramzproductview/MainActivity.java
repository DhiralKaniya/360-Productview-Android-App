package com.ramzproductview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ramzproductview.Config.Config;

import com.ramzproductview.Modal.Image;
import com.ramzproductview.Modal.ImageCollector;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private ImageCollector myImages;
    private int imageCount;
    private Uri fileUri;

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageCount = 0;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.header_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.camera) {
            imageCount = 0 ;
            myImages = new ImageCollector();
            imageCount = 0;
            OpenCamera();
            //CreateImageGallery();
            return true;
        }
        if(id == R.id.Gallery){
            return true;
        }
        if(id == R.id.view_360){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }


    //open camera
    public void OpenCamera(){
        if(isDeviceSupportCamera()) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                Toast.makeText(getApplicationContext(),"Click Landscape Image only Current Image -> " + imageCount ,Toast.LENGTH_SHORT).show();
                fileUri = getOutputMediaFileUri(Config.MEDIA_TYPE_IMAGE,imageCount);
                //Log.d(TAG,fileUri.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
                startActivityForResult(takePictureIntent, Config.REQUEST_IMAGE_CAPTURE);
            }
        }else{
            Toast.makeText(getApplicationContext(),"Your device is not support camera" ,Toast.LENGTH_SHORT).show();
        }
    }
    //manually check for device camera support
    public boolean isDeviceSupportCamera(){
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if(fileUri != null) {
                Log.d(TAG,"request code -> "+requestCode+" result code->"+resultCode);
                Uri selectedImage =fileUri;
                getContentResolver().notifyChange(selectedImage,null);
                Log.d(TAG,selectedImage.toString());
                Image mImage = new Image(imageCount,selectedImage.toString());
                myImages.addImages(mImage);
//                try {
//                    //Bitmap reduceSizeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
//                    //Log.d(TAG,reduceSizeBitmap.toString());
//
//                }catch (IOException e){
//                    Log.d(TAG,e.getMessage());
//                }
                imageCount++;
                if(imageCount < 19)
                    OpenCamera();
                else{
                    displayImage();
                }
                Log.d(TAG, myImages.getTotalImages() + "");
            }
        }else{
            if(imageCount >= 4 && imageCount <= 19)
                displayImage();
        }
    }
    public void displayImage(){
        Intent intent = new Intent(MainActivity.this,viewImage.class);
        SharedPreferences sharedPreferences = getSharedPreferences("mypreference",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        Log.d(TAG,"Total " + myImages.getTotalImages());
        ArrayList<Image> img = myImages.getAllimage();
        Gson gson = new Gson();
        String myData = gson.toJson(img);
        Log.d(TAG,myData);
        editor.putString("myImages",myData);
        editor.commit();
        startActivity(intent);
        finish();
    }
    public String CurrentDate(){
        return new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(new Date());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(),"Image taken" ,Toast.LENGTH_SHORT).show();
    }


    public Uri getOutputMediaFileUri(int type,int num ){
        return Uri.fromFile(getOutputMediaFile(type,num));
    }
    private static File getOutputMediaFile(int type,int number){
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(),Config.IMAGE_DIRECTORY_NAME);
        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdir()){
                Log.d(TAG,"Unable to create Directory");
                return null;
            }
        }
        String timeStamp =new SimpleDateFormat("yyyyMMss-HH-mm-ss").format(new Date());
        File MedialFile = null;
        if(type == Config.MEDIA_TYPE_IMAGE){
            MedialFile  = new File(mediaStorageDir.getPath()+File.separator+"MyApp_"+timeStamp+"_"+number+".jpg");
        }else{
            return null;
        }
        return MedialFile;
    }

}
