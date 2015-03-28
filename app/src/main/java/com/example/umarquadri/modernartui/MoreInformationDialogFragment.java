package com.example.umarquadri.modernartui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Umar Quadri on 22-03-2015.
 */
public class MoreInformationDialogFragment extends DialogFragment {


    public static MoreInformationDialogFragment newInstance(int title,int message) {

        MoreInformationDialogFragment statInfoFrag=new MoreInformationDialogFragment();
        Bundle tmpBundle=new Bundle();
        tmpBundle.putInt("Title",title);
        tmpBundle.putInt("Message",message);

        statInfoFrag.setArguments(tmpBundle);
        return statInfoFrag;

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // int title=savedInstanceState.getInt("Title");
        // int message=savedInstanceState.getInt("Message");
        int title=getArguments().getInt("Title");
        int message=getArguments().getInt("Message");

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.more_info_dialog_Yes,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((ModernUI)getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.more_info_dialog_No,new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        }
                );
        // Create the AlertDialog object and return it
        return builder.create();
    }

}


