package com.farizma.droidcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "OrderMessage";
    String orderMessage;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme1);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        //toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrder();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.order: makeToast(getString(R.string.action_order_message));
                             goToOrder();
                             return true;
            case R.id.status: makeToast(getString(R.string.action_status_message)); return true;
            case R.id.favorites: makeToast(getString(R.string.action_favorites_message)); return true;
            case R.id.contact: makeToast(getString(R.string.action_contact_message)); return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void showToast(View view) {
        switch (view.getId()) {
            case R.id.imageDonut:
                orderMessage = getString(R.string.donut_order_message);
                makeToast(orderMessage);
                break;
            case R.id.imageIceCream:
                orderMessage = getString(R.string.ice_cream_order_message);
                makeToast(orderMessage);
                break;
            case R.id.imageFroyo:
                orderMessage = getString(R.string.froyo_order_message);
                makeToast(orderMessage);
                break;
        }
    }

    public void goToOrder() {

        if(orderMessage == null) {
            makeToast(getString(R.string.have_not_order_message));
            return;
        }
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, orderMessage);
        startActivity(intent);
    }
}
