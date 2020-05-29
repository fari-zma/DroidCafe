package com.farizma.droidcafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textView;
    private Spinner spinner;
    String orderMessage;
    private String[] toppings = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        statusBarConfig(findViewById(R.id.rootLayout));

        textView = findViewById(R.id.textView);
        orderMessage = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText(orderMessage);

        spinner = findViewById(R.id.spinner);
        if(spinner != null)
            spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner != null)
            spinner.setAdapter(adapter);
    }

    void dialNumber(String phoneText) {
        String phoneNumber = null;
        if(phoneText != null)
            phoneNumber = "tel:" + phoneText;

        Intent intentDial = new Intent(Intent.ACTION_DIAL);
        intentDial.setData(Uri.parse(phoneNumber));

        if(intentDial.resolveActivity(getPackageManager()) != null)
            startActivity(intentDial);
    }

    public void displayToppings(View view) {
        String msg = "";
        for(int i=0; i<4; i++)
            if(toppings[i] != null)
                msg += toppings[i] + " ";
        if(msg != "")
            displayToast("Topping: "+msg);
    }

    public void getToppings(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()) {
            case R.id.topping_1:
                if(checked)
                    toppings[0] = getString(R.string.topping1);
                else
                    toppings[0] = null;
                break;
            case R.id.topping_2:
                if(checked)
                    toppings[1] = getString(R.string.topping2);
                else
                    toppings[1] = null;
                break;
            case R.id.topping_3:
                if(checked)
                    toppings[2] = getString(R.string.topping3);
                else
                    toppings[2] = null;
                break;
            case R.id.topping_4:
                if(checked)
                    toppings[3] = getString(R.string.topping4);
                else
                    toppings[3] = null;
                break;
        }
    }

    void displayToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.sameday:
                if(checked)
                    displayToast(getString(R.string.same_day));
                break;
            case R.id.nextday:
                if(checked)
                    displayToast(getString(R.string.next_day));
                break;
            case R.id.pickup:
                if(checked)
                    displayToast(getString(R.string.pick_up));
                break;
        }
    }

    public void goBack(View view) {
        finish();
    }

    public void call(View view) {
        showDialog();
    }

    void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_view);

        // setup buttons
        Button cancel = dialog.findViewById(R.id.cancel);

        final EditText phoneText = dialog.findViewById(R.id.phoneText);
        if(phoneText != null)
            phoneText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    boolean handled = false;
                    if(actionId == EditorInfo.IME_ACTION_SEND) {
                        dialNumber(phoneText.getText().toString());
                        handled = true;
                    }
                    return handled;
                }
            });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    void statusBarConfig(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            this.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        displayToast(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
