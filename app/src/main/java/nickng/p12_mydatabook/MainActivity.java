package nickng.p12_mydatabook;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    ArrayAdapter<String> aa;
    String currentTitle;
    ActionBar ab;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.left_drawer);

        drawerItems = new ArrayList<String>();
        drawerItems.add(0, "Bio");
        drawerItems.add(1, "Vaccination");
        drawerItems.add(2, "Anniversary");
        drawerItems.add(3, "About Us");
        ab = getSupportActionBar();

        aa = new CustomAdapter(this, R.layout.row, drawerItems);
        drawerList.setAdapter(aa);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                if(position == 0)
                    fragment = new BioFragment();
                else if(position == 1)
                    fragment = new VaccinationFragment();
                else if(position == 2)
                    fragment = new AnniversaryFragment();
                else if(position == 3) {
                    Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
                    startActivity(i);
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                if(position < 3){
                    trans.replace(R.id.content_frame, fragment);
                    trans.commit();
                }

                drawerList.setItemChecked(position, true);
                currentTitle = drawerItems.get(position);
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }
        });

        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
