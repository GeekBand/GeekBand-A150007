package com.geekband.tingyou.ui.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.geekband.tingyou.Env;
import com.geekband.tingyou.R;
import com.geekband.tingyou.helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener, SearchView.OnQueryTextListener{

    private SearchView searchView;
    private TextView textView;
    private ImageButton imageButton;

    private String[] hintData;


    final String[] resultColumns = new String[] {
            Env.KEY_ID, Env.KEY_PROVINCE_COLUMN, Env.KEY_CITY_COLUMN, Env.KEY_SCENE_COLUMN
    };
    String where;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        searchView.setIconifiedByDefault(false);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return true;
            }
        });
        searchView.setOnQueryTextListener(this);

        textView = (TextView) findViewById(R.id.search_suggestion);

        imageButton = (ImageButton) findViewById(R.id.back_button);
        imageButton.setOnClickListener(this);

        new GetSearchHintDataTask().execute();
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, resultColumns, )
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button) {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    class GetSearchHintDataTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Cursor cursor = getCursor();
            List<String> hintString = new ArrayList<>();
            while (cursor.moveToNext()) {
                String province = cursor.getString(cursor.getColumnIndex(Env.KEY_PROVINCE_COLUMN));
                hintString.add(province);

                String city = cursor.getString(cursor.getColumnIndex(Env.KEY_CITY_COLUMN));
                if (city != null) {
                    hintString.add(city);
                }

                String scene = cursor.getString(cursor.getColumnIndex(Env.KEY_SCENE_COLUMN));
                hintString.add(scene);
            }
            cursor.close();

            int len = hintString.size();
            hintData = new String[len];
            for (int i = 0; i < len; i++) {
                hintData[i] = hintString.get(i);
            }
            return null;
        }
    }

    //get all the names of provinces, cities, and scenes for
    private Cursor getCursor() {
        SQLiteDatabase db = this.openOrCreateDatabase(DBHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
        return db.query(DBHelper.DATABASE_TABLE, resultColumns, null, null, null, null, null);
    }

    // check whether the target string contains subString.
    private boolean containsCurrentString(String subString, String target) {
        int lenSub = subString.length();
        int lenTarget = target.length();
        if (lenSub > lenTarget) {
            return false;
        }

        for (int i = 0; i < lenTarget - lenSub + 1; i++) {
            if (target.substring(i, i + lenSub).equals(subString)) {
                return true;
            }
        }

        return false;
    }
}
