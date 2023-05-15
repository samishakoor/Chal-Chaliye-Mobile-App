package com.example.smd_project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingDialog extends Dialog {
    private EditText commentInput;
    private RatingBar ratingInput;

    public RatingDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_box);

        commentInput = findViewById(R.id.comment_input);
        ratingInput = findViewById(R.id.rating_input);

        // Set up the "OK" button to return the user's input
        Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return the user's input as a Rating object
                String comment = commentInput.getText().toString();
                float rating = ratingInput.getRating();
                Rating result = new Rating(rating, comment);
                if (String.valueOf(rating).isEmpty() || comment.isEmpty()){

                   Toast toast=new Toast(getContext());
                   toast.setText("Please enter data");
                   toast.setDuration(Toast.LENGTH_SHORT);
                   toast.show();
                }
                else{

                dismiss();
                listener.onRatingSelected(result);
                }
            }
        });

        // Set up the "Cancel" button to close the dialog box
        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private OnRatingSelectedListener listener;
    public interface OnRatingSelectedListener {
        void onRatingSelected(Rating rating);
    }

    public void setOnRatingSelectedListener(OnRatingSelectedListener listener) {
        this.listener = listener;
    }
}