package com.geekband.tingyou.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geekband.tingyou.ApplicationContext;
import com.geekband.tingyou.Env;
import com.geekband.tingyou.R;
import com.geekband.tingyou.helper.DBHelper;
import com.geekband.tingyou.ui.activity.DetailActivity;
import com.geekband.tingyou.ui.activity.MainActivity;
import com.geekband.tingyou.ui.adapter.DetailRecyclerAdapter;
import com.geekband.tingyou.ui.adapter.RecyclerViewArrayAdapter;
import com.geekband.tingyou.ui.listener.DetailLongClickListener;
import com.geekband.tingyou.ui.listener.ProvinceClickListener;
import com.geekband.tingyou.ui.listener.ProvinceLongClickListener;
import com.geekband.tingyou.ui.listener.DetailClickListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SceneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SceneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SceneFragment extends BaseFragment implements
        View.OnClickListener, ProvinceClickListener,
        ProvinceLongClickListener, DetailClickListener, DetailLongClickListener {

    public static final String MY_COLLECTION = "我的收藏";
    public static final String RECENT_ONLINE = "新上线";
    public static final String ON_ACTIVITY = "活动中";
    public static final String FIVE_A = "5A";
    public static final String FOUR_A = "4A";
    public static final String CLASSIFY = "DEFINE_WHICH";

    private static final String QUERY_OBJECT = "Object";
    private static final String OBJECT_TYPE = "Type";
    //private static final String


    // to decide whether the detail recycler view is to show city detail or scene detail
    public static final boolean SIGN_FOR_CITY = true;
    public static final boolean SIGN_FOR_SCENE = false;

    private OnFragmentInteractionListener mListener;

    private Context context;
    private View view;

    // parameters for detail of the scenes.
    private static final String[] dataSet = {"北京", "上海", "香港", "澳门", "湖北", "湖南", "广东", "广西", "山西", "山东"};
    private static String[] detailDataSet;

    private RecyclerView detailRecyclerView;

    public SceneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_scene, container, false);

        RecyclerView provinceView = (RecyclerView) view.findViewById(R.id.recycler_view);
        provinceView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerViewArrayAdapter recyclerViewArrayAdapter = new RecyclerViewArrayAdapter(dataSet, this, this);
        provinceView.setAdapter(recyclerViewArrayAdapter);
        provinceView.setItemAnimator(new DefaultItemAnimator());



        // TODO: set up the adapter for detail recycler view.
        detailRecyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler_view);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        Bundle detailIntent = new Bundle();
        detailIntent.putString(QUERY_OBJECT, "北京");
        detailIntent.putBoolean(OBJECT_TYPE, SIGN_FOR_SCENE);
        new LoadDetailTask().execute(detailIntent);

        initTextView();

        return view;
    }

    //set the TextView for the classify part, then set the onClickListener.
    private void initTextView() {
        TextView myCollectionView = (TextView) view.findViewById(R.id.classifyMyCollection);
        myCollectionView.setOnClickListener(this);
        TextView recentOnlineView = (TextView) view.findViewById(R.id.classifyRecentOnline);
        recentOnlineView.setOnClickListener(this);
        TextView onActivityView = (TextView) view.findViewById(R.id.classifyOnActivity);
        onActivityView.setOnClickListener(this);
        TextView fiveAView = (TextView) view.findViewById(R.id.classify5a);
        fiveAView.setOnClickListener(this);
        TextView fourAView = (TextView) view.findViewById(R.id.classify4a);
        fourAView.setOnClickListener(this);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        switch (v.getId()) {
            case (R.id.classifyMyCollection):
                intent.putExtra(CLASSIFY, MY_COLLECTION);
                Log.i("TravisDebug", "1st chosen");
                break;
            case (R.id.classifyRecentOnline):
                intent.putExtra(CLASSIFY, RECENT_ONLINE);
                Log.i("TravisDebug", "2nd chosen");
                break;
            case (R.id.classifyOnActivity):
                intent.putExtra(CLASSIFY, ON_ACTIVITY);
                Log.i("TravisDebug", "3rd chosen");
                break;
            case (R.id.classify5a):
                intent.putExtra(CLASSIFY, FIVE_A);
                Log.i("TravisDebug", "4th chosen");
                break;
            case (R.id.classify4a):
                intent.putExtra(CLASSIFY, FOUR_A);
                Log.i("TravisDebug", "5th chosen");
                break;
            default:
                return;
        }

        startActivity(intent);
    }

    @Override
    public void onProvinceClick(View view, int position) {
        String province = dataSet[position];
        Bundle bundle = new Bundle();
        bundle.putString(QUERY_OBJECT, province);
        bundle.putBoolean(OBJECT_TYPE, SIGN_FOR_SCENE);
        new LoadDetailTask().execute(bundle);

        Log.i("TravisDebug", province);
    }

    @Override
    public void onLongProvinceClick(View view, int position) {
        String province = dataSet[position];
        Bundle bundle = new Bundle();
        bundle.putString(QUERY_OBJECT, province);
        bundle.putBoolean(OBJECT_TYPE, SIGN_FOR_SCENE);
        new LoadDetailTask().execute(bundle);

        Log.i("TravisDebug", province);
    }

    @Override
    public void onDetailClick(View v, int position) {
        String detail = detailDataSet[position];
        Toast.makeText(context, detail, Toast.LENGTH_SHORT).show();
        Log.i("TravisDebug", detail);
    }

    @Override
    public void onDetailLongClick(View v, int position) {
        String detail = detailDataSet[position];
        Toast.makeText(context, detail, Toast.LENGTH_SHORT).show();
        Log.i("TravisDebug", detail);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    private class LoadDetailTask extends AsyncTask<Bundle, Void, Bundle> {


        @Override
        protected Bundle doInBackground(Bundle... params) {
            String object = params[0].getString(QUERY_OBJECT);
            boolean signForSearch = params[0].getBoolean(OBJECT_TYPE);
            String queryColumn = Env.KEY_SCENE_COLUMN;
            Log.i("TravisDebug", object + "   " + signForSearch);

            // TODO: further implement for different searching situation.
//            if (signForSearch) {
//
//            }
//            else {
//
//            }
            String where = Env.KEY_PROVINCE_COLUMN + "=?" ;
            String[] whereArgs = new String[] {object};

            DBHelper dbHelper = new DBHelper(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query(DBHelper.DATABASE_TABLE, new String[]{
                    Env.KEY_PROVINCE_COLUMN, Env.KEY_CITY_COLUMN, Env.KEY_SCENE_COLUMN
            }, where, whereArgs, null, null, null);

            ArrayList<String> result = new ArrayList<>();
            while (cursor.moveToNext()) {
                int columnIndex = cursor.getColumnIndex(queryColumn);
                if (columnIndex > -1) {
                    String detail = cursor.getString(columnIndex);
                    result.add(detail);
                }
            }
            int size = result.size();
            detailDataSet = new String[size];
            for (int i = 0; i < size; i++) {
                detailDataSet[i] = result.get(i);
            }

            Log.i("TravisDebug", "detail length: " + detailDataSet.length);
            cursor.close();
            return params[0];
        }

        @Override
        protected void onPostExecute(Bundle bundle) {
            boolean signForSearch = bundle.getBoolean(OBJECT_TYPE);

            // TODO: the adapter of detail recycler view has not be linked to the UI of the fragment yet.
            // TODO: the 3rd and 4th parameter of the new statement need to be further specified.
            //Fragment thisFragment = getFragmentManager().findFragmentByTag(MainActivity.SCENE_TAG);
            DetailRecyclerAdapter detailRecyclerAdapter = new DetailRecyclerAdapter(detailDataSet, signForSearch, null, null);
            detailRecyclerView.setAdapter(detailRecyclerAdapter);
        }
    }

}
