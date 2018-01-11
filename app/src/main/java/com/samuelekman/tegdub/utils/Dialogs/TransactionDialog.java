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

public class TransactionDialog {
    public void showDialog(final Activity activity, String message){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.transaction_dialog);

        TextView textView = (TextView) dialog.findViewById(R.id.dialogTxt);
        textView.setText(message);

        Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button editButton = (Button) dialog.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take me to the editActivity!
                Toast.makeText(activity, "Function not implemented", Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteButton = (Button) dialog.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete the transaction (fire a new dialog?)
                DeleteDialog deleteDialog = new DeleteDialog();
                deleteDialog.showDialog(activity, "Are you sure you want to delete the transaction");
                //dialog.dismiss();
            }
        });

        dialog.show();
    }
}
