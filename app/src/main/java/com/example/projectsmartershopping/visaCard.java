package com.example.projectsmartershopping;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectsmartershopping.activities.BuyActivity;

public class visaCard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.activity_visa_card, null);
        final EditText card_numer = alertLayout.findViewById(R.id.card_numer);
        final EditText MM = alertLayout.findViewById(R.id.MM);
        final EditText YY = alertLayout.findViewById(R.id.YY);


        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (YY.getText().toString().isEmpty() && MM.getText().toString().isEmpty() && card_numer.getText().toString().isEmpty()) {
                    Toast.makeText(visaCard.this, "" +
                            "please full the fields ...", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(visaCard.this, " Thanks You... \n you wait the message from sell", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}
