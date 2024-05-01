package com.example.finalactivity;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Drawable doneDrawable;
    private Drawable passwordDrawable;
    private CheckBox rememberMeCheckBox;
    private TextView forgotPassword, textView;

    private static final String PREFS_NAME = "LoginPrefs";
    private static final String PREF_EMAIL = "email";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_REMEMBER_ME = "rememberMe";
    private ImageView imageView;
    private CardView cardView;
    private LinearLayout linearlayout;
    private MaterialButton materialbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.emailedittext);
        passwordEditText = (EditText) findViewById(R.id.passwordedittext);
        doneDrawable = getResources().getDrawable(R.drawable.done);
        passwordDrawable = getResources().getDrawable(R.drawable.password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.rememberme);
        imageView = (ImageView) findViewById(R.id.eacschool);
        cardView = (CardView) findViewById(R.id.cardview);
        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);

        // Set click listeners in drawableclicklistener
        setDrawableClickListener(emailEditText, doneDrawable);
        setDrawableClickListener(passwordEditText, passwordDrawable);

        // Set vector drawables
        doneDrawable = tintDrawable(this, R.drawable.done, R.color.green);

        setonClicklistenerForgotPassword();
        setoncheckedlistener();
        animateImageView();
        animateCardView();
        loginclicklistener();

        // Load saved preferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean(PREF_REMEMBER_ME, false);
        if (rememberMe) {
            String savedEmail = prefs.getString(PREF_EMAIL, "");
            String savedPassword = prefs.getString(PREF_PASSWORD, "");
            emailEditText.setText(savedEmail);
            passwordEditText.setText(savedPassword);
            rememberMeCheckBox.setChecked(true);
        }

        textView = (TextView) findViewById(R.id.textview);

        // Calculate the width of the screen to determine the starting and ending positions
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Create a translate animation that moves the TextView from right to left
        Animation animation = new TranslateAnimation(screenWidth, -screenWidth, 0, 0);
        animation.setDuration(8000); // Set the duration of the animation (in milliseconds)
        animation.setInterpolator(new LinearInterpolator()); // Set linear interpolation for constant speed
        animation.setRepeatCount(Animation.INFINITE); // Set the animation to loop infinitely
        animation.setRepeatMode(Animation.RESTART); // Set the animation to restart from the beginning when it reaches the end
        textView.startAnimation(animation); // Start the animation
    }


    private void setDrawableClickListener(final EditText editText, final Drawable drawable) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if the entered text ends with "@gmail.com" or "@yahoo.com"
                if (s.toString().endsWith("@gmail.com") || s.toString().endsWith("@yahoo.com")) {
                    // Change the color of the drawable icon to green
                    if (drawable != null) {
                        // Update the color of the drawable
                        drawable.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                        // Set the compound drawable with the updated color
                        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    }
                } else {
                    // If the entered text does not match, reset the color of the drawable icon to its original color
                    if (drawable != null) {
                        // Remove the color filter to reset the color
                        drawable.clearColorFilter();
                        // Set the compound drawable with the original color
                        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    }
                }
            }
        });

        editText.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Calculate the x coordinate of the touch event relative to the EditText's right edge
                float touchX = event.getRawX() - (editText.getRight() - editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width());

                // Check if the touch event occurred within the bounds of the drawable
                if (touchX >= 0 && touchX <= editText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()) {
                    // Handle click on password drawable (for passwordEditText)
                    if (drawable == passwordDrawable) {
                        // Check the current input type to determine whether to show or hide password
                        int inputType = editText.getInputType();
                        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD || inputType == InputType.TYPE_CLASS_TEXT) {
                            // Show password
                            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            // Hide password
                            editText.setInputType(InputType.TYPE_CLASS_TEXT);
                        }
                        // Move cursor to the end
                        editText.setSelection(editText.getText().length());
                        return true; // Consume the touch event
                    }
                }
                // Let other touch events be handled
            }
            return false;
        });
    }

    // Method to tint a drawable with a specified color
    public static Drawable tintDrawable(Context context, @DrawableRes int drawableResId, @ColorRes int colorResId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableResId);
        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorResId));
        }
        return drawable;
    }

    void setonClicklistenerForgotPassword() {
        forgotPassword = (TextView) findViewById(R.id.forgotpassword);

        forgotPassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create and show an AlertDialog
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("Forgot Password");
                        alertDialogBuilder.setMessage("Email Address:johnvelllacuna@gmail.com   Password:123456789");

                        alertDialogBuilder.setPositiveButton("Back",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                }
        );
    }

    void setoncheckedlistener() {
        rememberMeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putString(PREF_EMAIL, emailEditText.getText().toString());
                editor.putString(PREF_PASSWORD, passwordEditText.getText().toString());
                editor.putBoolean(PREF_REMEMBER_ME, true);
                editor.apply();
            } else {
                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.remove(PREF_EMAIL);
                editor.remove(PREF_PASSWORD);
                editor.putBoolean(PREF_REMEMBER_ME, false);
                editor.apply();
            }
        });
    }

    private void animateImageView() {
        // Set initial translation outside of the screen
        int initialTranslationY = imageView.getHeight() + dpToPx(380); // Adjusted to include margin
        imageView.setTranslationY(initialTranslationY);
        imageView.setAlpha(0f); // Initially invisible

        // Animate translationY and alpha properties to bring the image view into the screen
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, alphaAnimator);
        animatorSet.setDuration(2000); // Duration of animation in milliseconds
        animatorSet.start();
    }

    private void animateCardView() {
        // Set initial translation outside of the screen
        int initialTranslationY = cardView.getHeight() + dpToPx(20); // Adjusted to include margin
        cardView.setTranslationY(initialTranslationY);
        cardView.setAlpha(0f); // Initially invisible

        // Animate translationY and alpha properties to bring the card view into the screen
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(cardView, "translationY", 0);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(cardView, "alpha", 1f);

        // Animate child views of the linear layout
        AnimatorSet childAnimatorSet = new AnimatorSet();
        ArrayList<Animator> childAnimators = new ArrayList<>();
        for (int i = 0; i < linearlayout.getChildCount(); i++) {
            View childView = linearlayout.getChildAt(i);
            ObjectAnimator childAlphaAnimator = ObjectAnimator.ofFloat(childView, "alpha", 0f, 1f);
            childAlphaAnimator.setStartDelay(i * 100); // Delay each child view's animation
            childAlphaAnimator.setDuration(500); // Duration of each child view's animation
            childAnimators.add(childAlphaAnimator);
        }
        ObjectAnimator[] childAnimatorArray = childAnimators.toArray(new ObjectAnimator[0]);
        childAnimatorSet.playTogether(childAnimatorArray);

        // Combine translation and alpha animators with child animators
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, alphaAnimator, childAnimatorSet);
        animatorSet.setDuration(3000); // Duration of animation in milliseconds
        animatorSet.start();
    }


    // Utility method to convert dp to pixels
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    void loginclicklistener() {
        materialbutton = findViewById(R.id.Materialbutton);
        try {
            materialbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String emailedit = emailEditText.getText().toString();
                        String pass = passwordEditText.getText().toString();

                        if (emailedit.equals("johnvelllacuna@gmail.com") && pass.equals("123456789")) {
                            Intent intent = new Intent(MainActivity.this, navigationdrawer.class);
                            startActivity(intent);
                        } else {
                            // Show an alert dialog
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            alertDialogBuilder.setTitle("Incorrect Credentials");
                            alertDialogBuilder.setMessage("Please try again");
                            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Clear email and password fields if "OK" is clicked
                                    emailEditText.setText("");
                                    passwordEditText.setText("");
                                }
                            });
                            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing if "Cancel" is clicked
                                }
                            });
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Handle any exceptions within the onClick method
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions when setting the click listener
        }
    }
}



