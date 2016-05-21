package angelshack.com.angelshack2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import angelshack.com.bluetoothlibrary.IBtBaseAdapter;
import angelshack.com.bluetoothlibrary.TestAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private static final int MAX_CHART_SIZE=20;
    private LineChart chart;
    private Button btnConnect;
    private Button btnViber;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private View viewJoystick;

    private IBtBaseAdapter btAdapter;
    int[] mColors = ColorTemplate.VORDIPLOM_COLORS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btAdapter = new TestAdapter(this);

        chart = (LineChart) findViewById(R.id.chart);
        btnConnect = (Button) findViewById(R.id.btn_connect);
        btnViber = (Button) findViewById(R.id.btn_viber);
        button1 = (RadioButton) findViewById(R.id.rb_1);
        button2 = (RadioButton) findViewById(R.id.rb_2);
        button3 = (RadioButton) findViewById(R.id.rb_3);
        button4 = (RadioButton) findViewById(R.id.rb_4);
        viewJoystick = findViewById(R.id.view_joystick);

        chart.setData(new LineData());

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClickConnect");
                String buttonText;
                if(btAdapter.isConnected()) {
                    btAdapter.disconnectHC06();
                    buttonText = "Connect";
                }else {
                    btAdapter.connectHC06();
                    buttonText= "Disconnect";
                    updateValues();
                }
                btnConnect.setText(buttonText);
            }
        });

        btnViber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClickViber");
                if(btAdapter.isConnected()){
                    btAdapter.viber();
                }
            }
        });
    }

    private void updateValues() {
        button1.setChecked(btAdapter.button1Pressed());
        button2.setChecked(btAdapter.button2Pressed());
        button3.setChecked(btAdapter.button3Pressed());
        button4.setChecked(btAdapter.button4Pressed());

        //TODO: set acc and gyro values
        float[] accx = btAdapter.getAccX();
        float[] accy = btAdapter.getAccY();
        float[] accz = btAdapter.getAccZ();

        Log.d(TAG,"accx: "+accx);
        Log.d(TAG,"accy: "+accy);
        Log.d(TAG,"accz: "+accz);

        addDataSet(accx);
        addDataSet(accx);
        addDataSet(accx);


        //TODO: set joystick values
    }

    private void addDataSet(float[] acc) {

        LineData data = chart.getData();

        if(data != null) {

            int count = (data.getDataSetCount() + 1);

            // create 10 y-vals
            ArrayList<Entry> yVals = new ArrayList<Entry>();

            if(data.getXValCount() == 0) {
                for (int i = 0; i<MAX_CHART_SIZE; i++) {
                    data.addXValue("" + (i+1));
                }
            }


            int offset;
            if(acc.length>MAX_CHART_SIZE) offset =0;
            else offset= acc.length- MAX_CHART_SIZE;

            for (int i = 0; i < data.getXValCount(); i++) {
                yVals.add(new Entry(acc[i+offset],i));
            }

            LineDataSet set = new LineDataSet(yVals, "DataSet " + count);
            set.setLineWidth(2.5f);
            set.setCircleRadius(4.5f);

            int color = mColors[count % mColors.length];

            set.setColor(color);
            set.setCircleColor(color);
            set.setHighLightColor(color);
            set.setValueTextSize(10f);
            set.setValueTextColor(color);

            data.addDataSet(set);
            chart.notifyDataSetChanged();
            chart.invalidate();
        }
    }
}
