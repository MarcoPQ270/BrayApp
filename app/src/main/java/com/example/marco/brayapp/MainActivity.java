package com.example.marco.brayapp;

import android.os.Bundle;
import java.io.File;
import java.io.IOException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnCompletionListener {

    ImageButton record;
    Button stop, rep;
    TextView tv1;
    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        record = (ImageButton) findViewById(R.id.record);
        stop = (Button) findViewById(R.id.stop);
        rep=(Button) findViewById(R.id.rep);
        tv1=(TextView) findViewById(R.id.tv1);


    }
    public void grabar(View v) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory()
                .getPath());
        try {
            archivo = File.createTempFile("temporal", ".3gp", path);
        } catch (IOException e) {
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
        }
        recorder.start();
        record.setEnabled(false);
        tv1.setText("Grabando");
        stop.setEnabled(true);

    }
    public void detener(View v) {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IOException e) {
        }
        record.setEnabled(true);
        stop.setEnabled(false);
        rep.setEnabled(true);
        tv1.setText("Listo para reproducir");
    }
    public void reproducir(View v) {
        player.start();
        record.setEnabled(false);
        stop.setEnabled(false);
        rep.setEnabled(false);
        tv1.setText("Reproduciendo");

    }
    public void onCompletion(MediaPlayer mp) {
        record.setEnabled(true);
        stop.setEnabled(true);
        rep.setEnabled(true);
        tv1.setText("Listo");

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }
}
