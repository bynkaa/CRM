package com.free.savcrm;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.free.savcrm.utils.DataUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bynkaa on 7/22/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private static final int GALLERY_INTENT = 11, REQ_CODE_CROP_PHOTO = 12;

    @Bind(R.id.toggle_login)
    ToggleButton tgLogin;
    @Bind(R.id.toggle_sign_up)
    ToggleButton tgSignUp;
    @Bind(R.id.toggle_male)
    ToggleButton toggleMale;
    @Bind(R.id.toggle_female)
    ToggleButton toggleFemale;

    @Bind(R.id.login_block)
    LinearLayout loginBlock;
    @Bind(R.id.sign_up_block)
    RelativeLayout signUpBlock;
    @Bind(R.id.avatar)
    ImageView ivAvatar;
    @Bind(R.id.etDateOfBirth)
    EditText etDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tgLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tgSignUp.setChecked(!isChecked);
                showLoginOrSignUp(isChecked);
            }
        });

        tgSignUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tgLogin.setChecked(!isChecked);
                showLoginOrSignUp(!isChecked);
            }
        });
        toggleMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleFemale.setChecked(!isChecked);
            }
        });

        toggleFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toggleMale.setChecked(!isChecked);
            }
        });

    }

    void showLoginOrSignUp(boolean isLogin)
    {
        loginBlock.setVisibility(isLogin ? View.VISIBLE : View.GONE);
        signUpBlock.setVisibility(isLogin ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.cancel)
    void doCancel()
    {
        onBackPressed();
    }

    @OnClick(R.id.done)
    void doLogin()
    {

        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @OnClick(R.id.avatar)
    void chooseAvatar()
    {
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setType("image/jpeg");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            try {
                startActivityForResult(Intent.createChooser(intent, "Select file to upload "), GALLERY_INTENT);
            } catch (ActivityNotFoundException e) {
                // Do nothing for now
            }

        } else {
            Intent intent = new Intent();
            // call android default gallery
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            try {
                startActivityForResult(Intent.createChooser(intent,
                        "Complete action using"), GALLERY_INTENT);
            } catch (ActivityNotFoundException e) {
                // Do nothing for now
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == GALLERY_INTENT ) {
                Bitmap cacheFile = decodeUriAsBitmap(data.getData());
                cropImage(getImageUri(this, cacheFile, "avatar"));
            }
            else if (requestCode == REQ_CODE_CROP_PHOTO) {
                if (data.getData() != null) {
                    Bitmap photo = decodeUriAsBitmap(data.getData());
                    ivAvatar.setImageBitmap(photo);
                }
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage, String title) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, title, null);
        return Uri.parse(path);
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    private void cropImage(Uri fileUri) {
        // Use existing crop activity.

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(fileUri, "image/*");
        intent.putExtra("crop", "true");
        // Specify image size
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("scale", true);
        // Specify aspect ratio, 1:1

        intent.putExtra("return-data", false);
        // REQUEST_CODE_CROP_PHOTO is an integer tag you defined to
        // identify the activity in onActivityResult() when it returns
        startActivityForResult(intent, REQ_CODE_CROP_PHOTO);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    @OnClick(R.id.etDateOfBirth)
    void onDateOfBirthClick()
    {
        dateOfBirthDialog();
    }

    private void dateOfBirthDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
                        Calendar cal=Calendar.getInstance();
                        cal.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
                        final String myDate = simpleDateFormat.format(new Date(cal.getTimeInMillis()));
                        etDateOfBirth.setText(myDate);
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        dpd.show(getFragmentManager(), "Date Of Birth");
    }
}
