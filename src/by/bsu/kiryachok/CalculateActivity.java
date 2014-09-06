package by.bsu.kiryachok;

import java.util.Vector;

import by.bsu.kiryachok.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import VoiceParser.VoiceParser;

public class CalculateActivity extends Activity implements OnClickListener{

	ImageButton speak;
	
	TextView result;
	
	Vector<Button> buttons = new Vector<Button>();
	Logic logic = new Logic();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        
    	int ids[] = {
				R.id.zero,
				R.id.one,
		        R.id.two,
		        R.id.three,
		        R.id.four,
		        R.id.five,
		        R.id.six,
		        R.id.seven,
		        R.id.eight,
		        R.id.nine,
		        R.id.point,
		        R.id.equally,
		        R.id.div,
		        R.id.mylt,
		        R.id.sub,
		        R.id.add,
		        R.id.delete,
		        R.id.cancel,
		        R.id.leftBracket,
		        R.id.rightBracket
    	};
    	
    	for(int i = 0; i < Logic.idButtons.eCountButtons.ordinal(); i++) {
    		buttons.add((Button) findViewById(ids[i]));
    		buttons.elementAt(i).setOnClickListener(this);
    	}
    	
    	result = (TextView) findViewById(R.id.result);
        speak = (ImageButton) findViewById(R.id.speakButton);
        speak.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) { 
  
    	if(v.getId() == R.id.speakButton) {
    		if(logic.isCheckUnswer() == true) {
    			logic.clearInput();
    			logic.setCountBracket(0);
    		}
    	    Intent intent = new Intent(this, VoiceActivity.class);
    	    startActivityForResult(intent, 1);
    	}
    	else logic.filter(v.getId(), (String)((Button)v).getText());
    	    	
    	result.setText(logic.getInput());
    }


	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (data == null) {return;}
    	String inputActivity = data.getStringExtra("inputActivity");
    	VoiceParser vp = new VoiceParser(inputActivity);
    	logic.addInput(vp.go());
    	result.setText(logic.getInput());
    }
}
