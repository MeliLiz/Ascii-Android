package com.example.tarea1_1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Cargar y mostrar el ASCII art
        TextView asciiArtTextView = findViewById(R.id.ascii_art_text_view);
        String asciiArt = loadAsciiArt();
        asciiArtTextView.setText(asciiArt);
    }

    private String loadAsciiArt() {
        StringBuilder asciiArt = new StringBuilder();
        try (InputStream inputStream = getResources().openRawResource(R.raw.ascii_art);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                asciiArt.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return asciiArt.toString();
    }
}
