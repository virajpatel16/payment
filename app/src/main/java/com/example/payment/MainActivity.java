package com.example.payment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    TextView paytext;
    private EditText amountEdt;
    private Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        payBtn = findViewById(R.id.idBtnMakePaymanet);
        Checkout.preload(getApplicationContext());
        paytext = findViewById(R.id.idTVHead);


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        // adding on click listener to our button.
    }

    public void startPayment() {
        final Activity activity = this;
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_c23Ru1wb5iwiA2");
        /**
         * Instantiate Checkout
         */
        int amount = 1000;

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.ic_launcher_background);

        /**
         * Reference to currenft activity
         */


        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "MerchantName");
            options.put("description", "Reference No. #123456");
            options.put("image", R.drawable.img);
//            options.put("order_id", "order_IluGWxBm9U8zJ8");//from response of step 3.
            options.put("theme.color", "#fff");
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits
            options.put("prefill.email", "virajpatel98256@example.com");
            options.put("prefill.contact", "9825615263");


            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        paytext.setText("successfull payment ID " + s);

    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("filed payment ID " + s);

    }
}
