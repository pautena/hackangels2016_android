package angelshack.com.bluetoothlibrary;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Duffman on 21/5/16.
 */
public class TestAdapter implements IBtBaseAdapter {


    private static final int SIN_SIZE = 360;
    private final Context context;
    private boolean connected=false;

    public TestAdapter(Context context){
        this.context = context;
    }

    @Override
    public void connectHC06() {
        connected=true;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void disconnectHC06() {
        connected=false;
    }

    @Override
    public void viber() {
        Toast.makeText(context,"Viber",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean button1Pressed() {
        return getBooleanRandom();
    }

    @Override
    public boolean button2Pressed() {
        return getBooleanRandom();
    }

    @Override
    public boolean button3Pressed() {
        return getBooleanRandom();
    }

    @Override
    public boolean button4Pressed() {
        return getBooleanRandom();
    }

    private boolean getBooleanRandom(){
        return Math.random() < 0.5;
    }

    @Override
    public float[] getAccX() {
        return getSin(0);
    }

    @Override
    public float[] getAccY() {
        return getSin(120);
    }

    @Override
    public float[] getAccZ() {
        return getSin(240);
    }

    private float[] getSin(float startAngle){
        double radStart   = Math.toRadians(startAngle);
        float[] sin = new float[SIN_SIZE];
        for(int i=0; i<SIN_SIZE;++i){
            double angle = (i+radStart)%360;
            sin[i] = (float) Math.sin(angle);
        }
        return sin;
    }
}
