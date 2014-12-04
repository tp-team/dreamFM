package com.dreamteam.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamteam.androidproject.api.answer.UserInfoAnswer;
import com.dreamteam.androidproject.api.query.Auth;
import com.dreamteam.androidproject.api.query.UserInfo;
import com.dreamteam.androidproject.handlers.AuthorizationHandler;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.template.Common;
import com.dreamteam.androidproject.storages.PreferencesSystem;

import java.net.Authenticator;


public class AuthorizationActivity extends BaseActivity {

    private EditText Username;
    private EditText Password;
    private Button ComeOnInBtn;
    private TextView DontHaveProfile;
    private TextView ForgotPassword;
    private final String colorBackgroundBtnEmpty = "#E1E1E1";
    private final String colorBackgroundBtn      = "#D51007";
    private final String colorTextBtnEmpty       = "#ACACAC";
    private final String colorTextBtn            = "#FFFFFF";

    private final String TAG = "___AUTORIZATION___";

    private int authId     = -1;
    private int userInfoId = -1;
    private PreferencesSystem prefSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        prefSystem = new PreferencesSystem(getApplicationContext());
        setContentView(R.layout.autorization_main);

        Username        = (EditText) findViewById(R.id.username);
        Password        = (EditText) findViewById(R.id.password);
        ComeOnInBtn     = (Button) findViewById(R.id.comeOnInBtn);
        DontHaveProfile = (TextView) findViewById(R.id.dontHaveProfile);
        ForgotPassword  = (TextView) findViewById(R.id.forgotPassword);

        View.OnKeyListener onKey = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isEmpty()) {
                    ComeOnInBtn.setBackgroundColor(Color.parseColor(colorBackgroundBtnEmpty));
                    ComeOnInBtn.setTextColor(Color.parseColor(colorTextBtnEmpty));
                    return false;
                }
                ComeOnInBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.item_pressed));
                ComeOnInBtn.setTextColor(Color.parseColor(colorTextBtn));
                return false;
            }
        };

        Username.setOnKeyListener(onKey);
        Password.setOnKeyListener(onKey);

        ComeOnInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty()) {
                    return;
                }
                authId = getServiceHelper().getAuthorization(Username.getText().toString(), Password.getText().toString());
            }
        });

        DontHaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click Dont have a profile");
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click Forgot password");
            }
        });

    }

    @Override
    public void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle resultData) {
        super.onServiceCallback(requestId, requestIntent, resultCode, resultData);

        if (AuthorizationActivity.this.authId == requestId) {
            callbackAuth(requestIntent, resultCode, resultData);
        }
        else if (AuthorizationActivity.this.userInfoId == requestId) {
            callbackUserInfo(requestIntent, resultCode, resultData);
        }

    }


    public void callbackAuth(Intent requestIntent, int resultCode, Bundle resultData) {
        switch (resultCode) {
            case AuthorizationHandler.RESPONSE_SUCCESS: {
                String status = resultData.getString(AuthAnswer.STATUS);
                if (status.equals(Common.STATUS_OK)) {
                    prefSystem.setText(AuthAnswer.NAME, resultData.getString(AuthAnswer.NAME));
                    prefSystem.setText(AuthAnswer.KEY, resultData.getString(AuthAnswer.KEY));
                    userInfoId = getServiceHelper().getUserInfo(resultData.getString(AuthAnswer.NAME));
                }
                else {
                    Toast.makeText(this, resultData.getString(AuthAnswer.TEXT_STATUS), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case AuthorizationHandler.RESPONSE_FAILURE: {
                Toast.makeText(this, resultData.getString(AuthAnswer.TEXT_STATUS), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void callbackUserInfo(Intent requestIntent, int resultCode, Bundle resultData) {
        switch (resultCode) {
            case AuthorizationHandler.RESPONSE_SUCCESS: {
                String status = resultData.getString(UserInfoAnswer.STATUS_USER_INFO);
                if (status.equals(Common.STATUS_OK)) {
                    prefSystem.setText(UserInfoAnswer.USERNAME, resultData.getString(UserInfoAnswer.USERNAME));
                    prefSystem.setText(UserInfoAnswer.PLAYS_COUNT, resultData.getString(UserInfoAnswer.PLAYS_COUNT));
                    prefSystem.setText(UserInfoAnswer.USER_PHOTO_RES, resultData.getString(UserInfoAnswer.USER_PHOTO_RES));
                    prefSystem.setText(UserInfoAnswer.USER_BIG_IMAGE_RES, resultData.getString(UserInfoAnswer.USER_BIG_IMAGE_RES));
                    Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                    startActivity(intent);
                    AuthorizationActivity.this.finish();
                }
                else {
                    Toast.makeText(this, resultData.getString(AuthAnswer.TEXT_STATUS), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case AuthorizationHandler.RESPONSE_FAILURE: {
                Toast.makeText(this, resultData.getString(AuthAnswer.TEXT_STATUS), Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public boolean isEmpty() {
        return Username.getText().toString().equals("") || Password.getText().toString().equals("");
    }


}
