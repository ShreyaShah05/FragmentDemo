package com.example.o.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    FragmentManager fManager;
    FragmentTransaction fTransaction;
    Button mButton, mButton1, mButton2, mButton3;
    MyFragment1 mFragment1;
    MyFragment2 mFragment2;
    MyFragment3 mFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton3 = (Button) findViewById(R.id.button3);

        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);

        fManager = getFragmentManager();

        mFragment1 = new MyFragment1();
        mFragment2 = new MyFragment2();
        mFragment3 = new MyFragment3();

        //if savedInstanceState != null (may be when activity is recreated after orientation change), don't show fragment one.
        if (savedInstanceState == null)
        {
            fTransaction = fManager.beginTransaction();
            fTransaction.replace(R.id.fragment_container, mFragment1);
            fTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        fTransaction = fManager.beginTransaction();
        switch (v.getId())
        {
            case R.id.button:
            case R.id.button1:
                fTransaction.replace(R.id.fragment_container, mFragment1);
                break;
            case R.id.button2:
                fTransaction.replace(R.id.fragment_container, mFragment2);
                break;
            case R.id.button3:
                fTransaction.replace(R.id.fragment_container, mFragment3);
                break;
        }
        fTransaction.commit();
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
    public void onBackPressed() {
        super.onBackPressed();
    }
}
