package com.example.umarquadri.modernartui;


import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;


public class ModernUI extends ActionBarActivity {

    private static final String URL="http://moma.org";
    private SeekBar seekBar;
    private int seekPos;
    private LinearLayout tile1,tile2,tile3,tile4,tile5;
    private ColorDrawable tile1Color, tile2Color, tile3Color, tile4Color, tile5Color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_ui);
        initializeVariables();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int initprogress=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                initprogress = progress;
                updateColors();

            }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initializeVariables() {

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tile1=(LinearLayout)findViewById(R.id.tileOne);
        tile2=(LinearLayout)findViewById(R.id.tileTwo);
        tile3=(LinearLayout)findViewById(R.id.tileThree);
        tile4=(LinearLayout)findViewById(R.id.tileFour);
        tile5=(LinearLayout)findViewById(R.id.tileFive);


        tile1Color=new ColorDrawable();
        tile2Color=new ColorDrawable();
        tile3Color=new ColorDrawable();
        tile4Color=new ColorDrawable();
        tile5Color=new ColorDrawable();

        //store color of each tile in ColorDrawable object
        tile1Color.setColor(((ColorDrawable) tile1.getBackground()).getColor());
        tile2Color.setColor(((ColorDrawable)tile2.getBackground()).getColor());
        tile3Color.setColor(((ColorDrawable)tile3.getBackground()).getColor());
        tile4Color.setColor(((ColorDrawable)tile4.getBackground()).getColor());
        tile5Color.setColor(((ColorDrawable)tile5.getBackground()).getColor());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modern_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

    
        if (id == R.id.more_info_item_menu) {
            showDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    void showDialog() {
        DialogFragment newFragment = MoreInformationDialogFragment.newInstance(
                R.string.more_info_dialog_title,R.string.more_info_dialog_text);
        newFragment.show(getFragmentManager(),"Dialog");
    }

    public void doPositiveClick() {
        Intent webIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(webIntent);
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void updateColors() {
        seekPos=seekBar.getProgress();

        tile1.setBackgroundColor(offsettedColor(tile1Color, seekPos));
        tile2.setBackgroundColor(offsettedColor(tile2Color, seekPos));
        tile3.setBackgroundColor(offsettedColor(tile3Color, seekPos));
        tile4.setBackgroundColor(offsettedColor(tile4Color, seekPos));
        tile5.setBackgroundColor(offsettedColor(tile5Color, seekPos));

    }

    //Offsets the input color using the position of the seekbar and returns the updated Color.
    private int offsettedColor(ColorDrawable tileColor, int progress) {

        int R,G,B;
        R=Color.red(tileColor.getColor());
        G=Color.green(tileColor.getColor());
        B=Color.blue(tileColor.getColor());

        //if RGB value is 255 or 0 do no change it.This is for increasing or decreasing the shade
        //also if a tile is white/black it's color will not change
        if(!(R==255||R==0))
            R=Math.max(R-progress,0);
        if(!(G==255||G==0))
            G=Math.max(G-progress,0);
        if(!(B==255||B==0))
            B=Math.max(B-progress,0);


        return Color.rgb(R,G,B);

    }
}
