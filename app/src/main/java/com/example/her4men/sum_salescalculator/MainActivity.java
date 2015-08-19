package com.example.her4men.sum_salescalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private int[] p_Range = {45,55,65,85,120,199,249,299,399},   //9 itesm
                  qty_array; //9 different qty
    private double[] disc_range = { 1, 0.9, 0.8, 0.7, 0.6,
                                    0.5, 0.4, 0.3, 0.2, 0.1, 0};
    private Spinner spinner1,
                    spinner2,
                    spinner3,
                    spinner4,
                    spinner5,
                    spinner6,
                    spinner7,
                    spinner8,
                    spinner9;
    private Button btnCal;
    private boolean in_range = true;
    private TextView textCal, textMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCal = (TextView) findViewById(R.id.result);
        createSpinners();
        addListenerOnButton();
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

    public void createSpinners()
    {
        spinner1 = (Spinner) findViewById(R.id.d45price_spinner);
        spinner2 = (Spinner) findViewById(R.id.d55price_spinner);
        spinner3 = (Spinner) findViewById(R.id.d65price_spinner);
        spinner4 = (Spinner) findViewById(R.id.d85price_spinner);
        spinner5 = (Spinner) findViewById(R.id.d120price_spinner);
        spinner6 = (Spinner) findViewById(R.id.d199price_spinner);
        spinner7 = (Spinner) findViewById(R.id.d249price_spinner);
        spinner8 = (Spinner) findViewById(R.id.d299price_spinner);
        spinner9 = (Spinner) findViewById(R.id.d399price_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.price_range, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);
        spinner8.setAdapter(adapter);
        spinner9.setAdapter(adapter);
    }

    public boolean checkqty(int[] total_qty)
    {

        if(totalQty(total_qty) <= 11)
        {
            in_range = true;
        }
        else
        {
            in_range = false;
        }
        return in_range;
    }

    private int totalQty(int[] total_qty)
    {
        int qty = 0;
        for(int i = 0; i < 9; i++)
        {
            qty += total_qty[i];
        }
        return qty;
    }

    public void addListenerOnButton() {


        spinner1 = (Spinner) findViewById(R.id.d45price_spinner);
        spinner2 = (Spinner) findViewById(R.id.d55price_spinner);
        spinner3 = (Spinner) findViewById(R.id.d65price_spinner);
        spinner4 = (Spinner) findViewById(R.id.d85price_spinner);
        spinner5 = (Spinner) findViewById(R.id.d120price_spinner);
        spinner6 = (Spinner) findViewById(R.id.d199price_spinner);
        spinner7 = (Spinner) findViewById(R.id.d249price_spinner);
        spinner8 = (Spinner) findViewById(R.id.d299price_spinner);
        spinner9 = (Spinner) findViewById(R.id.d399price_spinner);

        btnCal = (Button) findViewById(R.id.btnCal);
        textCal = (TextView) findViewById(R.id.result);
        textMsg = (TextView) findViewById(R.id.error_msg);
        btnCal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int[] qty_array = new int[9];
                qty_array[0] = Integer.valueOf(String.valueOf(spinner1.getSelectedItem()));
                qty_array[1] = Integer.valueOf(String.valueOf(spinner2.getSelectedItem()));
                qty_array[2] = Integer.valueOf(String.valueOf(spinner3.getSelectedItem()));
                qty_array[3] = Integer.valueOf(String.valueOf(spinner4.getSelectedItem()));
                qty_array[4] = Integer.valueOf(String.valueOf(spinner5.getSelectedItem()));
                qty_array[5] = Integer.valueOf(String.valueOf(spinner6.getSelectedItem()));
                qty_array[6] = Integer.valueOf(String.valueOf(spinner7.getSelectedItem()));
                qty_array[7] = Integer.valueOf(String.valueOf(spinner8.getSelectedItem()));
                qty_array[8] = Integer.valueOf(String.valueOf(spinner9.getSelectedItem()));

                double total_price = 0;
                if(checkqty(qty_array) == true)
                {
                    textCal.setText("0");
                    textMsg.setText(""); //msg reset
                    total_price = 0; //reset
                    //p_range
                    //disc_range
                    int dis_pos = 0;
                    //9 prices,
                    for(int i = 8; i >= 0 ; i--)
                    {
                        for(int j = 0; j < qty_array[i]; j++)
                        {
                            total_price += p_Range[i] * disc_range[dis_pos];
                            dis_pos++;
                        }
                    }
                    textCal.setText(String.valueOf(total_price));
                }
                else
                {
                    total_price = 0;//reset
                    textCal.setText("0");
                    textMsg.setText("Total Qty must be between 0-11");
                }

            }

        });
    }


}
