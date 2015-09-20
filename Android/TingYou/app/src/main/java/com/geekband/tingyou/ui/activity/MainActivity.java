package com.geekband.tingyou.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geekband.tingyou.ApplicationContext;
import com.geekband.tingyou.Env;
import com.geekband.tingyou.R;
import com.geekband.tingyou.helper.DBHelper;
import com.geekband.tingyou.ui.fragment.BaseFragment;
import com.geekband.tingyou.ui.fragment.MoreInfoFragment;
import com.geekband.tingyou.ui.fragment.SceneFragment;
import com.geekband.tingyou.ui.fragment.UserInfoFragment;

public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener,
        SceneFragment.OnFragmentInteractionListener,
        UserInfoFragment.OnFragmentInteractionListener,
        MoreInfoFragment.OnFragmentInteractionListener{

    private SceneFragment sceneFragment;
    private UserInfoFragment userInfoFragment;
    private MoreInfoFragment moreInfoFragment;

    public static final String SCENE_TAG = "SCENE_FRAGMENT";
    public static final String USER_INFO_TAG = "USER_INFO_FRAGMENT";
    public static final String MORE_INFO_TAG = "MORE_INFO_FRAGMENT";
    private String fragmentTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView searchTextView = (TextView) findViewById(R.id.search);
        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        // initialize all three fragment that is needed for the activity
        sceneFragment = new SceneFragment();
        userInfoFragment = new UserInfoFragment();
        moreInfoFragment = new MoreInfoFragment();

        // initialize the fragment as the SceneFragment when the Activity is loaded.
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.scene_container, sceneFragment, SCENE_TAG);
        fragmentTransaction.commit();
        fragmentTag = SCENE_TAG;


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });

        new LoadDataBaseTask().execute();

    }

    private void switchFragment(int checkedId) {
        switch (checkedId) {
            case R.id.scene_button:
                loadFragment(sceneFragment, SCENE_TAG);
                break;
            case R.id.user_info_button:
                loadFragment(userInfoFragment, USER_INFO_TAG);
                break;
            case R.id.more_info_button:
                loadFragment(moreInfoFragment, MORE_INFO_TAG);
                break;
        }
    }

    private void loadFragment(Fragment fragment, String tag) {
        if (fragmentTag.equals(tag)) {
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.scene_container, fragment, tag);
        fragmentTransaction.commit();
        fragmentTag = tag;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onFragmentInteraction(Uri uri) {

    }

    class LoadDataBaseTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            //Log.i("TravisDebug", "Table created");

            DBHelper dbHelper = new DBHelper(ApplicationContext.getContext(), DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            /**
            ContentValues contentValues = getValue("北京", null, "故宫", "5A", 12, 13, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("北京", null, "圆明园", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("上海", null, "明珠塔", "5A", 5, 15, 3);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("香港", null, "维多利亚港", "4A", 9, 12, 1);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("香港", null, "海洋公园", "5A", 7, 8, 5);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("澳门", null, "圆明园", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("湖北", "武汉", "aaa", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("湖北", "宜昌", "bbb", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("湖南", "长沙", "ccc", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("广东", "广州", "ddd", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("广东", "深圳", "eee", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("广西", "桂林", "fff", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("广西", "南宁", "ggg", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("山西", "太原", "hhh", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            contentValues.clear();
            contentValues = getValue("山东", "青岛", "iii", "5A", 12, 15, 2);
            db.insert(DBHelper.DATABASE_TABLE, null, contentValues);
            Log.i("TravisDebug", "Test insertion");
            **/

            // to test whether the data has been inserted to the database.
            Cursor cursor = db.query(DBHelper.DATABASE_TABLE, new String[] {
                    Env.KEY_PROVINCE_COLUMN, Env.KEY_CITY_COLUMN, Env.KEY_SCENE_COLUMN, Env.KEY_VOICE_COLUMN,
                    Env.KEY_KNOWLEDGE_COLUMN, Env.KEY_BOUGHT_COLUMN
            }, null, null, null, null, null);
            while (cursor.moveToNext()) {
                StringBuilder sb = new StringBuilder();
                String province = cursor.getString(cursor.getColumnIndex(Env.KEY_PROVINCE_COLUMN));
                sb.append(province);
                sb.append(" ");
                String city = cursor.getString(cursor.getColumnIndex(Env.KEY_CITY_COLUMN));
                sb.append(city);
                sb.append(" ");
                String scene = cursor.getString(cursor.getColumnIndex(Env.KEY_SCENE_COLUMN));
                sb.append(scene);
                sb.append(" ");
                int voice = cursor.getInt(cursor.getColumnIndex(Env.KEY_VOICE_COLUMN));
                sb.append(voice);
                sb.append(" ");
                int knowledge = cursor.getInt(cursor.getColumnIndex(Env.KEY_KNOWLEDGE_COLUMN));
                sb.append(knowledge);
                sb.append(" ");
                String bought = cursor.getString(cursor.getColumnIndex(Env.KEY_BOUGHT_COLUMN));
                sb.append(bought);
                sb.append(" ");
                Log.i("TravisDebug", sb.toString());
            }
            cursor.close();

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {

        }

        private ContentValues getValue(String province, String city, String scene, String level, int voice, int knowledge, int bought) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Env.KEY_PROVINCE_COLUMN, province);
            contentValues.put(Env.KEY_CITY_COLUMN, city);
            contentValues.put(Env.KEY_SCENE_COLUMN, scene);
            contentValues.put(Env.KEY_LEVEL_COLUMN, level);
            contentValues.put(Env.KEY_VOICE_COLUMN, voice);
            contentValues.put(Env.KEY_KNOWLEDGE_COLUMN, knowledge);
            contentValues.put(Env.KEY_BOUGHT_COLUMN, bought);
            return contentValues;
        }
    }
}
