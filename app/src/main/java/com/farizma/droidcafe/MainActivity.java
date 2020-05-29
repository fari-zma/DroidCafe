package com.farizma.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "OrderMessage";
    String orderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void makeToast() {
        Toast.makeText(getApplicationContext(), orderMessage, Toast.LENGTH_SHORT).show();
    }

    public void showToast(View view) {
        switch (view.getId()) {
            case R.id.imageDonut:
                orderMessage = getString(R.string.donut_order_message);
                makeToast();
                break;
            case R.id.imageIceCream:
                orderMessage = getString(R.string.ice_cream_order_message);
                makeToast();
                break;
            case R.id.imageFroyo:
                orderMessage = getString(R.string.froyo_order_message);
                makeToast();
                break;
        }
    }

    public void order(View view) {

        if(orderMessage == null) {
            Toast.makeText(getApplicationContext(), "You haven't ordered anything", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, orderMessage);
        startActivity(intent);
    }
}
