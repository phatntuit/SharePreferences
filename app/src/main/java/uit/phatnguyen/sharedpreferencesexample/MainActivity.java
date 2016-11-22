package uit.phatnguyen.sharedpreferencesexample;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //set color
    Spinner spColorChooser ;
    ArrayList<MyColor> colorArrayList ;
    ColorAdapter colorAdapter;

    //array cua color trong file xml
    int[] myIntColors;
    String[] myStringColors;
    LinearLayout llmain;

    //color in preferences
    String color ;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getControls();
        setDefault();
        addEvents();
    }
    private void getControls(){
        //Phuc vu cho color
        spColorChooser = (Spinner) findViewById(R.id.spColorChooser);
        llmain = (LinearLayout) findViewById(R.id.activity_main);

        sharedPreferences = getSharedPreferences("share_phatnguyen", MODE_PRIVATE);
        color = getColorPreferences();
    }
    private void addEvents(){
        //su kien tren spinner
        spColorChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyColor myColor = colorArrayList.get(position);
                llmain.setBackgroundColor(myColor.getColorValue());

                //set color to save SharedPreferences
                color = myColor.getColorKey();
                Log.d("Set color for save",color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setDefault(){
        int index = 0;
        //color
        colorArrayList = new ArrayList<MyColor>();
        colorAdapter = new ColorAdapter(this,R.layout.color_row,colorArrayList);
        myIntColors = getResources().getIntArray(R.array.intColors);
        myStringColors = getResources().getStringArray(R.array.stringColors);
        for (int i = 0 ; i<myStringColors.length ; i++ ){
            colorArrayList.add(new MyColor(myStringColors[i],myIntColors[i]));
        }
        spColorChooser.setAdapter(colorAdapter);


        //lay color ra tu sharedPreferences de set cho color spinner
        //array color begin with 0
        //
        if (color!=null){
            for (int i = 0 ; i<colorArrayList.size() ; i++ ){
                String colorAtIndex = colorArrayList.get(i).getColorKey();
                if(colorAtIndex.equals(color)){
                    index = i ;
                    break;
                }
            }
        }
        //spColorChooser.setSelection(0);
        Log.d("Set color for save",index + "--" + color);
        spColorChooser.setSelection(index);
    }
    /*
    – Hàm lưu bạn gọi trong sự kiện onPause

    – Hàm đọc bạn gọi trong sự kiện onResume.
     */
    private void savecolorPreferences(String color){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("color",color);
        editor.commit();
    }
    private String getColorPreferences(){
        Log.d("SharedPreferences",sharedPreferences.toString());
        String getColor = sharedPreferences.getString("color",null);
        Log.d("Maulayduoc",getColor);
        color = getColor;
        return getColor;
    }

    @Override
    protected void onPause() {
        super.onPause();
        savecolorPreferences(color);
        Log.d("onPause","saved!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        color = getColorPreferences();
        Log.d("onResume","got it" + color);
    }

    @Override
    protected void onStop() {
        super.onStop();
        savecolorPreferences(color);
        Log.d("onPause","saved!" + color);
    }
}
