package com.samuelekman.tegdub.utils.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.samuelekman.tegdub.R;

/**
 * Created by samuel on 2018-01-11.
 */

public class DeleteDialog {
    public interface ButtonPressedDialogListener{
        void onFinishDialog(String buttonPressed);
    }

    private ButtonPressedDialogListener listener;

    public void showDialog(final Activity activity, String message){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.delete_dialog);

        TextView textView = (TextView) dialog.findViewById(R.id.deleteDialogTxt);
        textView.setText(message);

//        listener = (ButtonPressedDialogListener) activity;

        Button noButton = (Button) dialog.findViewById(R.id.deleteNoButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button yesButton = (Button) dialog.findViewById(R.id.deleteYesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Function isn't implemented yet", Toast.LENGTH_SHORT).show();
                //listener = (ButtonPressedDialogListener) getActivity();
                //listener.onFinishDialog("Delete");

                //Delete the transaction and notify the adapter tried using a interface - didn't get it to work because I want it in a fragment not an activity. TO-DO
            }
        });



        dialog.show();
    }
}

