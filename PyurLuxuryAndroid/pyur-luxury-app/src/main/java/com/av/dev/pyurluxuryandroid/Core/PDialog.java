package com.av.dev.pyurluxuryandroid.Core;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.av.dev.pyurluxuryandroid.Activity.MainActivity;
import com.av.dev.pyurluxuryandroid.R;
import com.av.dev.pyurluxuryandroid.View.Fonts;

/**
 * Created by CodeSyaona on 30/08/2017.
 */

public class PDialog {

    public static void showDialogSuccess(final BaseActivity baseActivity){

        final Dialog dialog = new Dialog(baseActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_booking);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView txtMessage = dialog.findViewById(R.id.message);
        TextView txtOk = dialog.findViewById(R.id.ok);

        txtMessage.setTypeface(Fonts.latoRegular);
        txtOk.setTypeface(Fonts.latoRegular);

        LinearLayout mButtonDone = (LinearLayout) dialog.findViewById(R.id.btnOk);
        mButtonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                baseActivity.startActivity(new Intent(baseActivity, MainActivity.class));
                baseActivity.finish();

            }
        });

        dialog.show();

    }

}
