package sebi.speakercontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import sebi.customviews.MasterToggleButton;

public class MainActivity extends AppCompatActivity {

    private MasterToggleButton mtbAll, mtbFront, mtbRear;
    private ToggleButton tbCenter, tbSubwoofer, tbFrontLeft, tbFrontRight, tbRearLeft, tbRearRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSlaves();
        disableSlaves();
    }


    public void createSlaves()
    {
        mtbAll = (MasterToggleButton) findViewById(R.id.mtbAll);
        mtbFront = (MasterToggleButton) findViewById(R.id.mtbFront);
        mtbRear = (MasterToggleButton) findViewById(R.id.mtbRear);
        tbCenter = (ToggleButton) findViewById(R.id.tbCenter);
        tbSubwoofer = (ToggleButton) findViewById(R.id.tbSubwoofer);
        tbFrontLeft = (ToggleButton) findViewById(R.id.tbFrontLeft);
        tbFrontRight = (ToggleButton) findViewById(R.id.tbFrontRight);
        tbRearLeft = (ToggleButton) findViewById(R.id.tbRearLeft);
        tbRearRight = (ToggleButton) findViewById(R.id.tbRearRight);

        mtbAll.addSlaves(tbCenter);
        mtbAll.addSlaves(tbSubwoofer);
        mtbAll.addSlaves(mtbFront);
        mtbAll.addSlaves(mtbRear);
        mtbFront.addSlaves(tbFrontLeft);
        mtbFront.addSlaves(tbFrontRight);
        mtbRear.addSlaves(tbRearLeft);
        mtbRear.addSlaves(tbRearRight);
    }

    private void disableSlaves()
    {
        mtbFront.setDisableSlaves(true);
    }


    public void onClickMTB (View view)
    {
        MasterToggleButton mtbButton = (MasterToggleButton) view;

        Log.d("test", "onClickMTB: " + mtbButton.getText());


    }

    public void onClickTB (View view)
    {
        Log.d("test", "onClickTB: " + ((Button) view).getText());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
