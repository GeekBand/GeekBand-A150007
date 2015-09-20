package com.geekband.tingyou.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.geekband.tingyou.R;
import com.geekband.tingyou.ui.fragment.SceneFragment;

public class DetailActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    private TextView textView;
    private Spinner spinner;
    private final String[] dataList= {"我的收藏", "新上线", "活动中", "5A", "4A"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO: textView should be replace by RecyclerView to show the detail in further implementation;
        textView = (TextView) findViewById(R.id.test_text);
        String itemChose = getIntent().getStringExtra(SceneFragment.CLASSIFY);
        textView.setText(itemChose);

        spinner = (Spinner) findViewById(R.id.classify_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, dataList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        setOriginChoice(itemChose);


        ImageButton imageButton = (ImageButton) findViewById(R.id.back_button2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // TODO: detail of RecyclerView should be controlled here in further implementation.
        textView.setText(dataList[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setOriginChoice(String choice) {
        int len = dataList.length;
        for (int i = 0; i < len; i++) {
            if (dataList[i].equals(choice)) {
                spinner.setSelection(i);
                return;
            }
        }
    }
}
