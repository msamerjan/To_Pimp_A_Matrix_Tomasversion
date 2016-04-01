package com.mobiledev.topimpamatrix;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by tomas on 3/17/16.
 */
public class OCR extends AppCompatActivity {

    private final String apiKey = "4571bd58-9662-4d0d-9dfa-23f0479db860";

    public static final int SELECT_PICTURE = 1;
    private static final int TAKE_PICTURE = 2;
    private String mImageFullPathAndName = "";

    public void DoShowImagePicker(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE ||
                requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                // get the selected image full path and name
                mImageFullPathAndName = cursor.getString(columnIndex);
                cursor.close();
            }
        }
    }

    public void DoTakePhoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, TAKE_PICTURE);
    }
}
