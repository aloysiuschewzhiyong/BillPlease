package sg.edu.rp.c346.id22001095.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    Button split;
    Button reset;
    EditText discount;
    RadioGroup radioGroupPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editTextAmount);
        pax = findViewById(R.id.editTextPax);
        svs = findViewById(R.id.toggleButtonSvs);
        gst = findViewById(R.id.toggleButtonGST);
        totalBill = findViewById(R.id.textViewTotal);
        eachPays = findViewById(R.id.textViewEachPay);
        split = findViewById(R.id.ButtonSplit);
        reset = findViewById(R.id.ButtonReset);
        discount = findViewById(R.id.editTextDiscount);
        radioGroupPayment = findViewById(R.id.radioGroupPayment);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0) {
                    double amt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        amt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        amt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        amt = Double.parseDouble(amount.getText().toString()) * 1.08;
                    } else {
                        amt = Double.parseDouble(amount.getText().toString()) * 1.18;
                    }

                    if(discount.getText().toString().trim().length()!=0 ){
                        amt *= 1 - Double.parseDouble(amount.getText().toString()) / 100;

                    }



                    int checkedRadioId = radioGroupPayment.getCheckedRadioButtonId();
                    if(checkedRadioId == R.id.radioButtonCash){

                        totalBill.setText("Total Bill: $" + String.format("%.2f", amt));
                        int numPerson = Integer.parseInt(pax.getText().toString());
                        if (numPerson != 1) {
                            eachPays.setText("Each Pays: $" + String.format("%.2f", amt / numPerson) + " in cash");
                        }
                        else {
                            eachPays.setText("Each Pays: $" + amt + " in cash");
                        }
                    }
                    else{
                        totalBill.setText("Total Bill: $" + String.format("%.2f", amt));
                        int numPerson = Integer.parseInt(pax.getText().toString());
                        if (numPerson != 1) {
                            eachPays.setText("Each Pays: $" + String.format("%.2f", amt / numPerson) + " in Paynow");
                        }
                        else {
                            eachPays.setText("Each Pays: $" + amt + " in Paynow");
                        }
                    }

                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });

    }
}