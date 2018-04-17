package com.example.wojtas.morsecodesender;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.codes;

public class MainActivity extends AppCompatActivity {
    final boolean[] torchRequest = {false};
    String morseCodeToSend = "... --- ...";
    Code[] codes = new Code[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final boolean[] torchRequest = {false};

        codes[0] = new Code("a",".-");
        codes[1] = new Code("b","-...");
        codes[2] = new Code("c","-.-.");
        codes[3] = new Code("d","-..");
        codes[4] = new Code("e",".");
        codes[5] = new Code("f","..-.");
        codes[6] = new Code("g","--.");
        codes[7] = new Code("h","....");
        codes[8] = new Code("i","..");
        codes[9] = new Code("j",".---");
        codes[10] = new Code("k","-.-");
        codes[11] = new Code("l",".-..");
        codes[12] = new Code("m","--");
        codes[13] = new Code("n","-.");
        codes[14] = new Code("o","---");
        codes[15] = new Code("p",".--.");
        codes[16] = new Code("q","--.-");
        codes[17] = new Code("r",".-.");
        codes[18] = new Code("s","...");
        codes[19] = new Code("t","-");
        codes[20] = new Code("u","..-");
        codes[21] = new Code("v","...-");
        codes[22] = new Code("w",".--");
        codes[23] = new Code("x","-..-");
        codes[24] = new Code("y","-.--");
        codes[25] = new Code("z","--..");

        Button morseCodeSendButton = (Button) findViewById(R.id.morseCodeSendButton);
        final EditText textToTranslate = (EditText) findViewById(R.id.textToTranslate);

        morseCodeSendButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //morseCodeSendButton.setText("siusiak");
                /*torchRequest[0] = !torchRequest[0];

                try {
                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    for (String id : cameraManager.getCameraIdList()) {

                        // Turn on the flash if camera has one
                        if (cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                            cameraManager.setTorchMode(id, torchRequest[0]);
                        }
                    }
                } catch (CameraAccessException e) {}*/
                morseCodeToSend = translateFromTextToMorseCode(textToTranslate.getText().toString().toLowerCase());
                siet();
            }
        });

    }

    protected void siet()
    {
        //Button morseCodeSendButton = (Button)findViewById(R.id.morseCodeSendButton);
        for (int i=0;i<morseCodeToSend.length();i++){
            if(morseCodeToSend.charAt(i)=='.'){
                changeTorchlightState();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeTorchlightState();
            }
            else if(morseCodeToSend.charAt(i)=='-'){
                changeTorchlightState();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeTorchlightState();
            }
            else if (morseCodeToSend.charAt(i)==' '){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //morseCodeSendButton.setText("hello");
        }
        /*new CountDownTimer(6000,6000) {

            public void onTick(long millisUntilFinished) {
                //changeTorchlightState();
            }

            public void onFinish() {
                //changeTorchlightState();
            }
        }.start();*/
        /*torchRequest[0] = !torchRequest[0];

        try {
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            for (String id : cameraManager.getCameraIdList()) {

                // Turn on the flash if camera has one
                if (cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                    cameraManager.setTorchMode(id, torchRequest[0]);
                }
            }
        } catch (CameraAccessException e) {}*/
    }

    protected void changeTorchlightState(){
        torchRequest[0] = !torchRequest[0];

        try {
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            for (String id : cameraManager.getCameraIdList()) {

                // Turn on the flash if camera has one
                if (cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                    cameraManager.setTorchMode(id, torchRequest[0]);
                }
            }
        } catch (CameraAccessException e) {}
    }

    protected String translateFromTextToMorseCode(String text){
        int length = text.length();
        String output = "";
        /*String output = codes[0].code;
        if(text.charAt(0)=='b'){
            output = String.valueOf(codes[1].character);
        }*/
        for (int i = 0; i < length; i++) {
            if(text.charAt(i)==' '){
                output+=" ";
            }
            else{
                for (int j = 0; j < 26; j++) {
                    if(codes[j].character.equals((String.valueOf(text.charAt(i))))){
                        output+=codes[j].code+" ";
                        break;
                    }
                }
            }
        }
        return output;
    }
}