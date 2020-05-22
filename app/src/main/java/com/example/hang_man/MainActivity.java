package com.example.hang_man;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.hangman.R;

/**
 * This is demo code to accompany the Mobiletuts+ tutorial:
 * - Android SDK: Create a Hangman Game
 * 
 * Sue Smith - January 2014
 */

public class MainActivity extends Activity  implements OnClickListener {
	DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button playBtn = (Button)findViewById(R.id.playBtn);
		Button QuestionManBtn = (Button)findViewById(R.id.QuestionManBtn);
		playBtn.setOnClickListener(this);
		QuestionManBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		//handle clicks
		if(view.getId()==R.id.playBtn){
			Intent playIntent = new Intent(this, GameActivity.class);
			this.startActivity(playIntent);
		}
		if(view.getId()==R.id.QuestionManBtn){
			Intent qaIntent = new Intent(this,QuestionActivity.class);
			this.startActivity(qaIntent);
		}
	}
}
