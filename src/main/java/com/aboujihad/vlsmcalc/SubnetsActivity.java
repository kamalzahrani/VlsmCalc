package com.aboujihad.vlsmcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class SubnetsActivity extends AppCompatActivity {

    EditText et_oct1 ;
    EditText et_oct2 ;
    EditText et_oct3 ;
    EditText et_oct4 ;

    EditText et_mask1 ;
    EditText et_mask2 ;
    EditText et_mask3 ;
    EditText et_mask4 ;

    EditText et_nbrOfSubnets;

    ArrayList listOfVlsmInfo =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subnets);

        et_oct1 = findViewById(R.id.txt_oct1);
        et_oct2 = findViewById(R.id.txt_oct2);
        et_oct3 = findViewById(R.id.txt_oct3);
        et_oct4 = findViewById(R.id.txt_oct4);

        et_mask1 = findViewById(R.id.txt_mask1);
        et_mask2 = findViewById(R.id.txt_mask2);
        et_mask3 = findViewById(R.id.txt_mask3);
        et_mask4 = findViewById(R.id.txt_mask4);

        et_nbrOfSubnets = findViewById(R.id.input_nbrOfSubnets);

        et_oct1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_oct1.getText().length() == 3)) {

                    //do code
                    et_oct2.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_oct2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_oct2.getText().length() == 3)) {

                    //do code
                    et_oct3.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_oct3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_oct3.getText().length() == 3)) {

                    //do code
                    et_oct4.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_oct4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_oct4.getText().length() == 3)) {

                    //do code
                    et_mask1.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_mask1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_mask1.getText().length() == 3)) {

                    //do code
                    et_mask2.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_mask2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_mask2.getText().length() == 3)) {

                    //do code
                    et_mask3.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_mask3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_mask3.getText().length() == 3)) {

                    //do code
                    et_mask4.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        et_mask4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (et_mask4.getText().length() == 3)) {

                    //do code
                    et_nbrOfSubnets.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });

    }

    public void showResultForSubnetSegmentation(View view) {




        String str_oct1 = et_oct1.getText().toString();
        String str_oct2 = et_oct2.getText().toString();
        String str_oct3 = et_oct3.getText().toString();
        String str_oct4 = et_oct4.getText().toString();



        String str_mask1 = et_mask1.getText().toString();
        String str_mask2 = et_mask2.getText().toString();
        String str_mask3 = et_mask3.getText().toString();
        String str_mask4 = et_mask4.getText().toString();


        String str_nbrSubnets = et_nbrOfSubnets.getText().toString();

        int oct1 = Integer.parseInt(str_oct1);
        int oct2 = Integer.parseInt(str_oct2);
        int oct3 = Integer.parseInt(str_oct3);
        int oct4 = Integer.parseInt(str_oct4);

        int mask1 = Integer.parseInt(str_mask1);
        int mask2 = Integer.parseInt(str_mask2);
        int mask3 = Integer.parseInt(str_mask3);
        int mask4 = Integer.parseInt(str_mask4);

        int nbrOfSubnets = Integer.parseInt(str_nbrSubnets);

        int[] ip_saisi = {oct1, oct2, oct3, oct4};
        int[] msk_saisi = {mask1, mask2, mask3, mask4};

        if(MyException.checkIp(ip_saisi).matches("") & MyException.checkMask(msk_saisi).matches("") & MyException.chekSubNetSize(ip_saisi, msk_saisi, nbrOfSubnets).matches("")){

            IP ip = new IP();
            ip.setIP_DEC(ip_saisi);
            ip.setMASK_DEC(msk_saisi);

           ArrayList<int[]> allSubnet=ip.getAllSubnet(ip_saisi, msk_saisi, nbrOfSubnets);

            for(int i=0;i<allSubnet.size();i++){
                listOfVlsmInfo.add("Subnet Id : "+(i+1)+"\n" +
                        "Subnet Address : "+ip.getIptoString(allSubnet.get(i))+"\n" +
                        "Subnet Mask : "+ip.getIptoString(ip.getNewMask(ip_saisi, msk_saisi, nbrOfSubnets))+"\n" +
                        "Number Of Use Address : "+ip.getNbrOfUseAddr(ip.getNewMask(ip_saisi, msk_saisi, nbrOfSubnets))+"\n" +
                        "First Address : "+ip.getIptoString(ip.getFirstAddress(allSubnet.get(i), ip.getNewMask(ip_saisi, msk_saisi, nbrOfSubnets)))+"\n" +
                        "Last Address : "+ip.getIptoString(ip.getLastAddress(allSubnet.get(i), ip.getNewMask(ip_saisi, msk_saisi, nbrOfSubnets)))+"\n" +
                        "Broadcast Address : "+ip.getIptoString(ip.getBroadCastAddress(allSubnet.get(i), ip.getNewMask(ip_saisi, msk_saisi, nbrOfSubnets)))+"\n" +
                        "\n");

            }
            // starting new activity
            Intent resultActivity = new Intent(SubnetsActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("VLSMINFO",listOfVlsmInfo);
            resultActivity.putExtras(bundle);

            startActivity(resultActivity);

            listOfVlsmInfo.clear();


        }else {

            showAlertDialog(MyException.getMsgErrors());

            MyException.setMsgErrors("");


        }

    }

    public void showAlertDialog(final String msgError) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Error Message");

        builder.setMessage(msgError)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       clearInputFields(msgError);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void clearInputFields(String msgError) {

        if (msgError.matches("ip number error !!")){
            et_oct1.setText("");
            et_oct2.setText("");
            et_oct3.setText("");
            et_oct4.setText("");
            et_oct1.requestFocusFromTouch();
        }else if(msgError.matches("reserved ip number !!") || msgError.matches("multicast ip number !!")){
            et_oct1.setText("");
            et_oct1.requestFocusFromTouch();
        }else if (msgError.matches("ip mask error !!")){
            et_mask1.setText("");
            et_mask2.setText("");
            et_mask3.setText("");
            et_mask4.setText("");
            et_mask1.requestFocusFromTouch();
        }else if (msgError.matches("Network Size Limited !!")){
            et_nbrOfSubnets.setText("");
            et_nbrOfSubnets.requestFocusFromTouch();
        }

    }


}
