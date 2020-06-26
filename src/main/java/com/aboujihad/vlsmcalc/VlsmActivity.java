package com.aboujihad.vlsmcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VlsmActivity extends AppCompatActivity {

    public static boolean ASC = true;
    public static boolean DESC = false;

    EditText et_oct1 ;
    EditText et_oct2 ;
    EditText et_oct3 ;
    EditText et_oct4 ;

    EditText et_mask1 ;
    EditText et_mask2 ;
    EditText et_mask3 ;
    EditText et_mask4 ;

    ArrayList listOfSubnetName = new ArrayList<String>();
    ArrayList listOfSubnetSize =  new ArrayList<String>();
    ArrayList listOfVlsmInfo =  new ArrayList<String>();


    private EditText subnetName;
    private EditText subnetSize;

    TextView mListView;

    private TableLayout table = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlsm);

        mListView = findViewById(R.id.lstSunetAndSize);

        et_oct1 = findViewById(R.id.txt_oct1);
        et_oct2 = findViewById(R.id.txt_oct2);
        et_oct3 = findViewById(R.id.txt_oct3);
        et_oct4 = findViewById(R.id.txt_oct4);

        et_mask1 = findViewById(R.id.txt_mask1);
        et_mask2 = findViewById(R.id.txt_mask2);
        et_mask3 = findViewById(R.id.txt_mask3);
        et_mask4 = findViewById(R.id.txt_mask4);

        subnetName = findViewById(R.id.txt_nameSubnet);


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
                    subnetName.requestFocusFromTouch();
                    return true;

                }
                return false;
            }
        });
        //do not exceed 8 characters
        subnetName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (subnetName.getText().length() == 10)) {

                    //do code
                    showAlertDialog("do not exceed 8 characters");
                    return true;

                }
                return false;
            }
        });


    }

    public void addToSubnetsList(View view) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();

        subnetSize = findViewById(R.id.txt_sizeOfSubnet);

        listOfSubnetName.add(subnetName.getText().toString().trim());
        listOfSubnetSize.add(subnetSize.getText().toString().trim());

        System.out.println(listOfSubnetName.size()+"   "+listOfSubnetSize);

        Toast.makeText(this, subnetName.getText().toString().trim(), Toast.LENGTH_LONG).show();


        mListView.append("Name : "+subnetName.getText().toString().trim() +"\nSize : "+subnetSize.getText().toString().trim()+"\n\n");

        subnetName.setText("");
        subnetSize.setText("");
        subnetName.requestFocusFromTouch();


    }

    public void showResultForVlsmSegmentation(View view) {


        String str_oct1 = et_oct1.getText().toString();
        String str_oct2 = et_oct2.getText().toString();
        String str_oct3 = et_oct3.getText().toString();
        String str_oct4 = et_oct4.getText().toString();



        String str_mask1 = et_mask1.getText().toString();
        String str_mask2 = et_mask2.getText().toString();
        String str_mask3 = et_mask3.getText().toString();
        String str_mask4 = et_mask4.getText().toString();

        int oct1 = Integer.parseInt(str_oct1);
        int oct2 = Integer.parseInt(str_oct2);
        int oct3 = Integer.parseInt(str_oct3);
        int oct4 = Integer.parseInt(str_oct4);

        int mask1 = Integer.parseInt(str_mask1);
        int mask2 = Integer.parseInt(str_mask2);
        int mask3 = Integer.parseInt(str_mask3);
        int mask4 = Integer.parseInt(str_mask4);

        int[] ip_saisi = {oct1, oct2, oct3, oct4};
        int[] msk_saisi = {mask1, mask2, mask3, mask4};

        int k = listOfSubnetName.size(); // get number of subnets

        int[] val_col=new int[k]; // list of subnets size

        Map<String, Integer> unsortMap = new HashMap<String, Integer>();
            // create map and adding list of subnets names and size
        for(int i=0;i<k;i++){
            unsortMap.put(listOfSubnetName.get(i).toString(), Integer.parseInt(listOfSubnetSize.get(i).toString()));
        }
            // put the previous list in order
        Map<String, Integer> sortedMapDesc = Calculer.sortByComparator(unsortMap, DESC);

        ArrayList nameOfSubnets= new ArrayList();
        ArrayList sizeOfSubnets= new ArrayList();

        for (Map.Entry<String, Integer> entry : sortedMapDesc.entrySet())
        {
            nameOfSubnets.add(entry.getKey());
        }

        for (Map.Entry<String, Integer> entry : sortedMapDesc.entrySet())
        {
            sizeOfSubnets.add(entry.getValue());
        }

        for(int i=0;i<val_col.length;i++){
            val_col[i] = (int) sizeOfSubnets.get(i);
        }

        IP ip = new IP();
        ip.setIP_DEC(ip_saisi);
        ip.setMASK_DEC(msk_saisi);

        if(MyException.checkIp(ip_saisi).matches("") & MyException.checkMask(msk_saisi).matches("") & MyException.chekVlsmSupported(ip_saisi, msk_saisi, val_col).matches("")){


             ArrayList<int[]> allVlsmMask=ip.getAllVlsmSubnetMask(val_col);
            ArrayList<int[]> VlsmSubNet=ip.getAllVlsmSubNet(ip_saisi, msk_saisi, val_col);

            for (int i=0;i<listOfSubnetName.size();i++){
                listOfVlsmInfo.add("Name : "+nameOfSubnets.get(i).toString()+"\n" +
                                "Subnet Id : "+ip.getIptoString(VlsmSubNet.get(i))+"\n" +
                        "Subnet Mask : "+ip.getIptoString(allVlsmMask.get(i))+"\n" +
                        "Number Of Use Address : "+ip.getNbrOfUseAddr(allVlsmMask.get(i))+"\n" +
                        "First Address : "+ip.getIptoString(ip.getFirstAddress(VlsmSubNet.get(i), allVlsmMask.get(i)))+"\n" +
                        "Last Address : "+ip.getIptoString(ip.getLastAddress(VlsmSubNet.get(i),allVlsmMask.get(i)))+"\n" +
                        "Broadcast Address : "+ip.getIptoString(ip.getBroadCastAddress(VlsmSubNet.get(i),allVlsmMask.get(i)))+"\n" +
                        "\n");
            }


        // starting new activity
            Intent resultActivity = new Intent(VlsmActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("VLSMINFO",listOfVlsmInfo);
            resultActivity.putExtras(bundle);

            startActivity(resultActivity);

            listOfVlsmInfo.clear();

        }else{

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
            mListView.setText("");
            subnetName.requestFocusFromTouch();


        }

    }
}
