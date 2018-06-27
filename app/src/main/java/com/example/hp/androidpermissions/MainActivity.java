package com.example.hp.androidpermissions;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

   private EditText phoneEdit,messageEdit;
   private Button Sendbtn;
    private int REQUEST_CODE_SMS=101;


//    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneEdit = findViewById(R.id.nbrtx);
        messageEdit = findViewById(R.id.msgtx);
        Sendbtn = findViewById(R.id.button2);


        String[] permission = {
                Manifest.permission.SEND_SMS
        };


        if (ActivityCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, permission,REQUEST_CODE_SMS);

        }

        Sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNo = phoneEdit.getText().toString().trim();
                String message = messageEdit.getText().toString().trim();

                SmsManager sms =SmsManager.getDefault();
                Random r =new Random();
                int i1 =r.nextInt(9999 - 1111) + 1111;
                String MessageNumber=i1+"";

                sms.sendTextMessage(phoneNo,null,MessageNumber,null,null);



                Intent intent =new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("message",MessageNumber);
                startActivity(intent);

            }
        });

    }






        @Override
        public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);


            if (requestCode == 123) {

                if (grantResults.length > 0 &&

                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this, "Permission Granted !", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(MainActivity.this,"Permission not granted",Toast.LENGTH_SHORT).show();
            }
        }
    }

}


