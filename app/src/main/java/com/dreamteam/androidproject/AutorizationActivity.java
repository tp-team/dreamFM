package com.dreamteam.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Pavel on 26.10.2014.
 */
public class AutorizationActivity extends Activity {

    private EditText Username;
    private EditText Password;
    private Button ComeOnInBtn;
    private TextView DontHaveProfile;
    private TextView ForgotPassword;
    private final String colorBackgroundBtnEmpty = "#E1E1E1";
    private final String colorBackgroundBtn      = "#D51007";
    private final String colorTextBtnEmpty       = "#ACACAC";
    private final String colorTextBtn            = "#FFFFFF";

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
                Intent intent = new Intent(AutorizationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }


    public boolean isEmpty() {
        return Username.getText().toString().equals("") || Password.getText().toString().equals("");
    }


}
