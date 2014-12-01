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
import android.widget.Toast;

import com.dreamteam.androidproject.Handlers.Authorization;


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

    private int requestId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
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
                requestId = getServiceHelper().getAuthorization(Username.getText().toString(), Password.getText().toString());
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

        if (resultCode == Authorization.RESPONSE_SUCCESS) {
            Log.d("TAGGG", resultData.getString(Authorization.SUCCESS));
            Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
                startActivity(intent);
                AuthorizationActivity.this.finish();
        }
        else if (resultCode == Authorization.RESPONSE_FAILURE) {
            Log.d("TAGGG", resultData.getString(Authorization.FAILURE));
            Password.setText("");
            Toast.makeText(this, "Failure, incorrect login or password", Toast.LENGTH_LONG).show();
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
