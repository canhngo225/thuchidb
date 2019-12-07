package com.example.moneymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    String default_pin="1234";
    EditText etOldPin,etNewPin,etRePin;
    Button btnCPin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etLogin=findViewById(R.id.etLogin);
        Button btnLogin=findViewById(R.id.btnLogin);
        Button btnReset=findViewById(R.id.btnReset);
        preferences=getSharedPreferences("account",MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storedPin=preferences.getString("pin","1234");

                if(etLogin.getText().toString().equals(storedPin)){
                    Intent i =new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    etLogin.setError("Sai mã PIN");
                }

            }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPin();
            }
        });

        etLogin.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String storedPin=preferences.getString("pin","1234");

                    if(etLogin.getText().toString().equals(storedPin)){
                        Intent i =new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        etLogin.setError("Sai mÃ PIN");
                    }
                    return true;
                }
                return false;
            }
        });

    }
    private void showDialogPin() {
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        View dialogView=inflater.inflate(R.layout.change_pin, null);
        alertBuilder.setView(dialogView);
        final AlertDialog alertDialog=alertBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
        etOldPin=alertDialog.findViewById(R.id.etold);
        etNewPin=alertDialog.findViewById(R.id.etnew);
        etRePin=alertDialog.findViewById(R.id.etrepin);
        btnCPin=alertDialog.findViewById(R.id.savechange);

        btnCPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOldPin=etOldPin.getText().toString();
                String strNewPin=etNewPin.getText().toString();
                String strRePin=etRePin.getText().toString();

                editor=preferences.edit();
                String storedPin=preferences.getString("pin","1234");
                if (strOldPin.equals("") || strNewPin.equals("") || strRePin.equals("")) {
                    Toast.makeText(LoginActivity.this, "(*)Chưa nhập dữ liệu", Toast.LENGTH_SHORT).show();
                } else {
                    if (strOldPin.equals(storedPin)) {
                        if (strNewPin.equals(strRePin)) {
                            editor.putString("pin",strNewPin);
                            editor.commit();
                            editor.clear();
                            Toast.makeText(LoginActivity.this, "Đổi PIN thành công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(LoginActivity.this, "PIN không trùng khớp" , Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "PIN cũ sai", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


}


