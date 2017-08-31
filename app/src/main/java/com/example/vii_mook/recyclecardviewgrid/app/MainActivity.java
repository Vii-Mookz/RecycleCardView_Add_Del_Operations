package com.example.vii_mook.recyclecardviewgrid.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.vii_mook.recyclecardviewgrid.R;
import com.example.vii_mook.recyclecardviewgrid.adapter.RecyclerAdapter;
import com.example.vii_mook.recyclecardviewgrid.model.Animal;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

    private Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home Page");
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(this, Animal.getData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.linearViewHorizontal:
                LinearLayoutManager mlinearLayoutManagerHorizontal = new LinearLayoutManager(this);
                mlinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mlinearLayoutManagerHorizontal);
                break;
            case R.id.linearViewVertical:
                LinearLayoutManager mlinearLayoutManagerVertical = new LinearLayoutManager(this);
                mlinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mlinearLayoutManagerVertical);
                break;
            case R.id.gridView:
                GridLayoutManager mgridLayoutManager = new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(mgridLayoutManager);
                break;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager mstaggeredGridLayoutManagerHorizontal = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mstaggeredGridLayoutManagerHorizontal);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager mstaggeredGridLayoutManagerVertical = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mstaggeredGridLayoutManagerVertical);
                break;
        }
        return true;
    }
}
