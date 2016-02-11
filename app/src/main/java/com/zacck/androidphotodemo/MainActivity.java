package com.zacck.androidphotodemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init ui
        mImageView = (ImageView)findViewById(R.id.ivMImage);
        //make an intent to the photoviewer
        Intent photoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //go to the event
        //using start activity for result
        startActivityForResult(photoIntent, 1/*use this int to check if the intent returning is the one yoou sent*/);

    }

    //a listener for the result of start activity for result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //validate the intent
        if(requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            //collect the returned iMage
            try {
                Uri selectedImage = data.getData();
                Bitmap mBitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                //lets put the image in the image view
                mImageView.setImageBitmap(mBitmapImage);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
