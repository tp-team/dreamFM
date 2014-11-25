package com.dreamteam.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dreamteam.androidproject.messagesSystem.AuthorizationMessage;
import com.dreamteam.androidproject.services.ServiceApi;
import com.squareup.otto.Subscribe;

public class AuthorizationActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.autorization_main);

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        ComeOnInBtn = (Button) findViewById(R.id.comeOnInBtn);
        DontHaveProfile = (TextView) findViewById(R.id.dontHaveProfile);
        ForgotPassword = (TextView) findViewById(R.id.forgotPassword);

        View.OnKeyListener onKey = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isEmpty()) {
                    ComeOnInBtn.setBackgroundColor(Color.parseColor(colorBackgroundBtnEmpty));
                    ComeOnInBtn.setTextColor(Color.parseColor(colorTextBtnEmpty));
                    return false;
                }
                ComeOnInBtn.setBackgroundColor(Color.parseColor(colorBackgroundBtn));
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
//                Intent intent = new Intent(AuthorizationActivity.this, ServiceApi.class);
//                intent.putExtra("USERNAME", Username.getText().toString());
//                intent.putExtra("PASSWORD", Password.getText().toString());
//                Log.d(TAG, "IN CLICK");
//                startService(intent);
                Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                startActivity(intent);
                AuthorizationActivity.this.finish();
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

    @Subscribe
    public void onAutorizationMessage(AuthorizationMessage event) {
        Log.d(TAG, event.response);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public boolean isEmpty() {
        return Username.getText().toString().equals("") || Password.getText().toString().equals("");
    }


}
