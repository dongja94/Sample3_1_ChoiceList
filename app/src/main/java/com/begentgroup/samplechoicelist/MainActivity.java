package com.begentgroup.samplechoicelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(mAdapter);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button btn = (Button)findViewById(R.id.btn_delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                    int postion = listView.getCheckedItemPosition();
                    String text = (String)listView.getItemAtPosition(postion);
                    mAdapter.remove(text);
                } else if (listView.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
                    SparseBooleanArray array = listView.getCheckedItemPositions();
                    List<String> temp = new ArrayList<String>();
                    for (int index = 0; index < array.size(); index++) {
                        int position = array.keyAt(index);
                        if (array.get(position)) {
                            temp.add((String)listView.getItemAtPosition(position));
                        }
                    }
                    for (String s : temp) {
                        mAdapter.remove(s);
                    }
                    listView.clearChoices();
                }
            }
        });
        initData();
    }

    private void initData() {
        String[] array = getResources().getStringArray(R.array.list_item);
        for (String s : array) {
            mAdapter.add(s);
        }
    }
}
