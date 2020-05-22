package com.example.hang_man;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hangman.R;

public class QuestionActivity extends AppCompatActivity {

    EditText SearchBar;
    ListView lvQuestion;
    Button addBtn;
    DBHelper dbHelper;

    private String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        setTitle("Question Manager");
        SearchBar = (EditText)findViewById(R.id.qa_searchbar);
        addBtn = (Button)findViewById(R.id.qa_addbtn);
        lvQuestion = (ListView)findViewById(R.id.qa_listview);

        dbHelper = new DBHelper(QuestionActivity.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(QuestionActivity.this, AddQuestionActivity.class);
                startActivity(addIntent);
            }
        });

        showWord();
    }

    public void showWord(){
        Cursor cursor = dbHelper.getAllRecord();

        QuestionManageCursorAdapter adapter = new QuestionManageCursorAdapter(QuestionActivity.this,R.layout.activity_question_edit,cursor,0);
        lvQuestion.setAdapter(adapter);
    }
}
