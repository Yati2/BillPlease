package sg.edu.rp.c346.id20028056.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //handles
    EditText etAmount;
    EditText etNum;
    EditText etDiscount;
    TextView tvTotal;
    TextView tvEach;

    ToggleButton tbSvs;
    ToggleButton tbGst;
    RadioGroup rgPayment;
    Button btnSplit;
    Button btnReset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bridges
        etAmount=findViewById(R.id.etAmount);
        etNum=findViewById(R.id.etNum);
        etDiscount=findViewById(R.id.etDiscount);
        tvTotal=findViewById(R.id.tvTotal);
        tvEach=findViewById(R.id.tvEach);
        tbSvs=findViewById(R.id.tbSvs);
        tbGst=findViewById(R.id.tbGst);
        rgPayment=findViewById(R.id.rgPayment);
        btnSplit=findViewById(R.id.btnSplit);
        btnReset=findViewById(R.id.btnReset);


        btnSplit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                //conversions
                String getAmount = etAmount.getText().toString();
                String getNum = etNum.getText().toString();
                String getDiscount = etDiscount.getText().toString();
                int getPayment=rgPayment.getCheckedRadioButtonId();

                if (!(getAmount.length() == 0 || getDiscount.length() == 0
                        || getNum.length() == 0 ))
                {
                    double amount = Double.parseDouble(getAmount);
                    int num = Integer.parseInt(getNum);
                    int discount=Integer.parseInt(getDiscount);
                    double total = amount;
                    if (amount != 0 && num != 0) {
                        if(discount!=0)
                        {
                            total=amount*(1-(discount/100.0));
                        }
                        if (tbSvs.isChecked()) {
                            total =+ total * 1.1;
                        }
                        if (tbGst.isChecked()) {
                            total =+ total * 1.07;
                        }
                        tvTotal.setText(String.format("Total price: $%.2f", total));
                        if(getPayment==R.id.rbPayNow)
                        {
                            tvEach.setText(String.format("Each pays: $%.2f via PayNow to 912345678"
                                    , (total / num)));
                        }
                        else
                        {
                            tvEach.setText(String.format("Each pays: $%.2f in cash"
                                    , (total / num)));
                        }

                    } else {
                        tvTotal.setText("Please enter valid inputs!");
                    }
                } else {
                    tvTotal.setText("Fields cannot be empty!!");
                }
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                etNum.setText("");
                etAmount.setText("");
                etDiscount.setText("");
                rgPayment.clearCheck();
                tbSvs.setChecked(false);
                tbGst.setChecked(false);
            }
            });



    }


}