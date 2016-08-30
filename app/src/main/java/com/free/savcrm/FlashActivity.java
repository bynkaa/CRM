package com.free.savcrm;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.free.savcrm.model.FlashItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class FlashActivity extends AppCompatActivity {

    int[] imageIds = {R.drawable.img_background01, R.drawable.img_background02, R.drawable.img_background03};
    String[] tutorialNameList = {"SNAP TO SELL, CHAT TO BUY", "DEAL ITEMS NEAR & FAR", "SNAP, LIST, SELL IN 30 SECONDS"};
    String[] tutorialDescList = {"SAV CMR is the simplest way to buy & sell with a friendly and trusted community", "Deal in-person for nearby listings and have items shipped from acreoss the country", "Snap a photo, describe your listing and set a price. Easy! And it's free"};

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator circleIndicator;
    CallbackManager callbackManager;

    @Bind(R.id.facebook)
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    Toast.makeText(FlashActivity.this, "Welcome: " + response.getJSONObject().get("name"), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FlashActivity.this, CategoryActivity.class);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    Log.e("@@@", e.getMessage());
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(FlashActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(FlashActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        circleIndicator.setViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FlashFragment.newInstance(new FlashItem(imageIds[position], tutorialNameList[position], tutorialDescList[position]));
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @OnClick(R.id.flash_login_by_email_btn)
    void doLoginByEmail() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
