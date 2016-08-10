package com.example.administrator.idea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList values = new ArrayList();
    public String str0[]={"int","double","void","char","float","double","short","long","signed","unsigned","struct","union","enum","typedef","sizeof","auto","static","register","extern","const",
            "volatile","return","continue","break","goto","if","else","switch","case","default","for","do","while","String","string","include","public","private","protected"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteEditText et=(AutoCompleteEditText)findViewById(R.id.et);
        for(int i=0;i<str0.length;i++)values.add(str0[i]);
        et.setResultsValues(values);
    }
}
