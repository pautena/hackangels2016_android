package angelshack.com.bluetoothlibrary;

/**
 * Created by Duffman on 21/5/16.
 */
public interface IBtBaseAdapter {
    void connectHC06();

    boolean isConnected();

    void disconnectHC06();

    void viber();

    boolean button1Pressed();

    boolean button2Pressed();

    boolean button3Pressed();

    boolean button4Pressed();

    float[] getAccX();

    float[] getAccY();

    float[] getAccZ();
}
