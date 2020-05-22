package com.example.hang_man;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.*;
import android.database.Cursor;

import com.example.hangman.R;

public class AddQuestionActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button addBtn;
    EditText tvWord,tvHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        addBtn = (Button)findViewById(R.id.aqa_addBtn);
        tvWord = (EditText) findViewById(R.id.aqa_word);
        tvHint = (EditText) findViewById(R.id.aqa_hint);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long resultAdd = dbHelper.Insert(getValue(tvWord));
            }
        });
    }
}
