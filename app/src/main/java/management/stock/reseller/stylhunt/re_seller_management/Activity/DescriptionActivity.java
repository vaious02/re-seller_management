package management.stock.reseller.stylhunt.re_seller_management.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import management.stock.reseller.stylhunt.re_seller_management.Fragment.DescriptionFragment;
import management.stock.reseller.stylhunt.re_seller_management.R;

public class DescriptionActivity extends AppCompatActivity {


    int product_id, shop_id;
    private Toolbar toolbar;
    private ActionBar ab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        product_id = getIntent().getIntExtra("product_id",0);
        shop_id = getIntent().getIntExtra("shop_id", 0);
        String product_name = getIntent().getStringExtra("product_name");


        initInstance(product_name);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, DescriptionFragment.newInstance(product_id, shop_id))
                .commit();
    }

    private void initInstance(String name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();
        ab.setTitle(name);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
