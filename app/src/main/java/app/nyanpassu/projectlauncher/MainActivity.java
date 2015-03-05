package app.nyanpassu.projectlauncher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.tool_bar)
    Toolbar mToolbar;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    ActivitiesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setAdapter(getAdapter());
        mRecyclerView.setLayoutManager(getLayoutManager());
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

    private RecyclerView.LayoutManager getLayoutManager() {
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(this);
        }
        return mLayoutManager;
    }

    private ActivitiesAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new ActivitiesAdapter(this);
        }
        return mAdapter;
    }

}
