package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private int finalTime = 0;
    private int startTime = 0;
    private int currentPosition = 0;
    private int jump = 5000;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        TextView tx1 = (TextView) findViewById(R.id.duration);
        Button back = (Button) findViewById(R.id.back);
        Button forward = (Button) findViewById(R.id.forward);

        String music = "sound";
        int sound = getResources().getIdentifier(music, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);

        // Start playing sound
        // Display toast message
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                Toast.makeText(getApplicationContext(), "The sound is playing", Toast.LENGTH_SHORT).show();
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();
                tx1.setText("Total time: " + String.format("%d s", TimeUnit.MILLISECONDS.toSeconds((long) finalTime)));

            }
        });

        // Pause the sound
        // Display toast message
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                currentPosition = mediaPlayer.getCurrentPosition();
                Toast.makeText(getApplicationContext(), "The sound is paused", Toast.LENGTH_SHORT).show();
            }
        });

        // Skips back 5 seconds from current position
        // Display toast message
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int temp = mediaPlayer.getCurrentPosition();

                if ((temp - jump) > 0) {
                    startTime = temp - jump;
                    mediaPlayer.seekTo(startTime);
                    Toast.makeText(getApplicationContext(), "Jumped back 5 seconds", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Cant jump back ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Skips forward 5 seconds from current position
        // Display toast message
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = startTime;
                if ((temp + jump) < finalTime) {
                    startTime = startTime + jump;
                    mediaPlayer.seekTo(startTime);
                    Toast.makeText(getApplicationContext(), "Jumped forward 5 seconds", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Cant jump forward ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // On complete release resources
        // Display toast massage
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                Toast.makeText(getApplicationContext(), "I'm done !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}