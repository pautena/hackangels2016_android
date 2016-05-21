package angelshack.com.bluetoothlibrary;

/**
 * Created by Duffman on 21/5/16.
 */
public class ArduinoBluetoothAdapter implements IBtBaseAdapter{
    public static ArduinoBluetoothAdapter initialize(){
        return new ArduinoBluetoothAdapter();
    }


    private ArduinoBluetoothAdapter(){}

    @Override
    public void connectHC06() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void disconnectHC06() {

    }

    @Override
    public void viber() {

    }

    @Override
    public boolean button1Pressed() {
        return false;
    }

    @Override
    public boolean button2Pressed() {
        return false;
    }

    @Override
    public boolean button3Pressed() {
        return false;
    }

    @Override
    public boolean button4Pressed() {
        return false;
    }

    @Override
    public float[] getAccX() {
        return new float[0];
    }

    @Override
    public float[] getAccY() {
        return new float[0];
    }

    @Override
    public float[] getAccZ() {
        return new float[0];
    }
}
