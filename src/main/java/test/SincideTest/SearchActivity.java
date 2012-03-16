package test.SincideTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MultiAutoCompleteTextView;

public class SearchActivity extends Activity implements OnClickListener{

	public static final String SEARCH_FIELD = "SearchField";
	private MultiAutoCompleteTextView searchField;
	private View goButton;

	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        searchField = (MultiAutoCompleteTextView) findViewById(R.id.search_field);
	        goButton = findViewById(R.id.go_button);
	        goButton.setOnClickListener(this);
	        
	 }

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.go_button:
			Intent i  = new Intent(this, HelloAndroidActivity.class);
			i.putExtra(SEARCH_FIELD, searchField.getText().toString());
			startActivity(i);
			
		}
		
	}
}
