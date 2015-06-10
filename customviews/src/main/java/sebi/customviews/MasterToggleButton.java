package sebi.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Sebi on 10.06.15.
 */
public class MasterToggleButton extends ToggleButton {

    public ArrayList<ToggleButton> toggleButtonArrayList;
    private boolean disableSlaves = false;

    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
        toggleButtonArrayList = new ArrayList<>();
    }

    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
        toggleButtonArrayList = new ArrayList<>();
    }

    public MasterToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
        toggleButtonArrayList = new ArrayList<>();
    }

    public MasterToggleButton(Context context) {
        super(context);
        toggleButtonArrayList = new ArrayList<>();
    }

    public void addSlaves(ToggleButton toggleButton) {
        toggleButtonArrayList.add(toggleButton);
    }

    @Override
    public boolean callOnClick() {
        Log.d("test", "clicked");
        return super.callOnClick();

    }

    public boolean getDisabledSlave() {
        return disableSlaves;
    }

    public void setDisableSlaves(boolean value) {
        this.disableSlaves = value;
        disableSlaveButtons(value);
    }

    public void disableSlaveButtons(boolean value) {

        for (int i = 0; i < toggleButtonArrayList.size(); i++) {
            if (value) {
                //toggleButtonArrayList.get(i).setEnabled(false);
                toggleButtonArrayList.get(i).setClickable(false);
                if(toggleButtonArrayList.get(i) instanceof MasterToggleButton )
                {
                    ((MasterToggleButton) toggleButtonArrayList.get(i)).setDisableSlaves(value);
                }
            } else {
                toggleButtonArrayList.get(i).setClickable(true);
                if(toggleButtonArrayList.get(i) instanceof MasterToggleButton )
                {
                    ((MasterToggleButton) toggleButtonArrayList.get(i)).setDisableSlaves(value);
                }
            }
        }

    }

    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.MasterToggleButton);
        disableSlaves = a.getBoolean(R.styleable.MasterToggleButton_disableSlaves, disableSlaves);
        a.recycle();
    }
}
