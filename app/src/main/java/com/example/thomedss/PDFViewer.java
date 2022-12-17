package com.example.thomedss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_pdfviewer);

        Intent intent = getIntent();

        PDFView pdfView = findViewById(R.id.pdf_view);

        pdfView.fromAsset(intent.getStringExtra("fileName")).load();

    }
}