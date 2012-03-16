package test.SincideTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.sindice.Sindice;
import com.sindice.SindiceException;
import com.sindice.result.SearchResult;
import com.sindice.result.SearchResults;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class HelloAndroidActivity extends ListActivity {

	private static String TAG = "SincideTest";
	private ArrayList<SearchResult> list;

	/**
	 * Called when the activity is first created.
	 * @param savedInstanceState If the activity is being re-initialized after 
	 * previously being shut down then this Bundle contains the data it most 
	 * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		String searchTerm = getIntent().getStringExtra(SearchActivity.SEARCH_FIELD);

		String[] results = findResults("http://api.sindice.com/v3/search?"+ constructQuery(searchTerm));
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, results));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), findInfo(view), Toast.LENGTH_LONG).show();
			}

			private String findInfo(View view) {
				String s = (String) ((TextView) view).getText();
//				SearchResult temp = null;
//				boolean found = false;
//				for(int i = 0; i < list.size() && !found; i++) {
//					if(list.get(i).getTitle().equals(s)) {
//						temp = list.get(i);
//						found = true;
//					}
//				}
//				return temp.toString();
				return s;
			}

		});

		// Create the list fragment and add it as our sole content.
		//        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
		//            ArrayListFragment list = new ArrayListFragment();
		//            getFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
		//        }

		//		setContentView(R.layout.main);
		//        try {
		//			createSindice();
		//		} catch (SindiceException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}


	private String constructQuery(String searchTerm) {
		 
		return "q=" + searchTerm + "&fq=class:title&format=rdfxml";
	}


	private String[] findResults(String searchTerm) {
	
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(searchTerm);
		String[] strings = null;
		
		try{
			HttpResponse response = client.execute(request);
			HttpEntity e = response.getEntity();
			Log.i("Sindice", e.toString());
			strings = createSindice(e.getContent());
			Log.i("Sindice", strings[0]);
		}catch(Exception ex){
			strings = new String[1];
			strings[0] = "Failed";
			ex.printStackTrace();
		}


		return strings;
	}

	public String[] createSindice(InputStream input) {
		StringBuilder sb = new StringBuilder();
		if(input != null) {
			BufferedReader br;
			try {
				br = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				String line;
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();	
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] liste = new String[1];
			liste[0] = sb.toString();
			return liste;
//			JSONObject s = null ;
//			try {
//				s = new JSONObject(sb.toString());
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			s.

		} else {
			return null;
		}


		//		list = new ArrayList<SearchResult>();
		//    	Sindice sindice = new Sindice();
		//    	
		//    	SearchResults searchResults = sindice.termSearch(searchTerm);
		//    		
		//  
		//    	for(SearchResult searchResult : searchResults) {
		//    		list.add(searchResult);
		//    		
		//    	}
		//    	

	}

}


