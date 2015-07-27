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

    private ArrayList<ToggleButton> toggleButtonArrayList = new ArrayList<>();
    private boolean disableSlaves = false;

    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
    }

    public MasterToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
    }

    public MasterToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
    }

    public MasterToggleButton(Context context) {
        super(context);
    }

    public void addSlaves(ToggleButton toggleButton) {
        toggleButtonArrayList.add(toggleButton);
    }

    @Override
    public boolean callOnClick() {
        Log.d("test", getClass().getName() + " clicked");
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

    private void setChecked(boolean checked) {

        for (int i = 0; i < mtbButton.toggleButtonArrayList.size(); i++) {
            if (mtbButton.isChecked()) {
                mtbButton.toggleButtonArrayList.get(i).setChecked(true);
                mtbButton.toggleButtonArrayList.get(i).callOnClick();
            } else {
                mtbButton.toggleButtonArrayList.get(i).setChecked(false);
                mtbButton.toggleButtonArrayList.get(i).callOnClick();
            }
        }
    }

    private void parseAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.MasterToggleButton);
        setDisableSlaves(a.getBoolean(R.styleable.MasterToggleButton_disableSlaves, disableSlaves));
        a.recycle();
    }
}
