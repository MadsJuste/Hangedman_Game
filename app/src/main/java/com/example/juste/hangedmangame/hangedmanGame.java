package com.example.juste.hangedmangame;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class hangedmanGame extends AppCompatActivity implements View.OnClickListener {
    HangedmanLogic logic = new HangedmanLogic();
    private Button button1;
    private ImageView iw;
    private TextView tw ;
    private TextView status;
    private TextView guesses;
    private ListView listView;

    int nummer;
    String[] alfabet = {"a", "b", "c","d", "e","f", "g","h", "i","j", "k","l", "m","n",
            "o","p", "q","r", "s","t", "u","v", "x","y", "z","w", "æ","ø", "å"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangedman_game);
        iw = (ImageView) findViewById(R.id.imageView2);
        tw = (TextView) findViewById(R.id.textView2);

    }


    @Override
    public void onClick(View v) {
        String charecter;
        if (v == button1) {
            charecter = alfabet[nummer];
            if(logic.getUsedLetter().contains(charecter)){
                tw.setText("you have already guessed on this letter");
            }else {
                logic.guessLetter(charecter);
                status.setText(logic.getVisableWord());
                if (!logic.isLastLetterCorrect()) {
                    guesses.setText(guesses.getText() + " " + charecter);
                }
                if (logic.isTheGameWon()) {
                    status.setText("You have guessed the word: " + logic.getWord() + " and won");
                }
                if (logic.isTheGameLost()) {
                    status.setText("You have failed to guess the word: " + logic.getWord() + " and lost");
                }
                logic.logStatus();
            }

        }

        switch(logic.getNumberOfWrongWords()){
            case 1 : iw.setImageResource(R.drawable.forkert1);
                break;
            case 2 : iw.setImageResource(R.drawable.forkert2);
                break;
            case 3 : iw.setImageResource(R.drawable.forkert3);
                break;
            case 4 : iw.setImageResource(R.drawable.forkert4);
                break;
            case 5 : iw.setImageResource(R.drawable.forkert5);
                break;
            case 6 : iw.setImageResource(R.drawable.forkert6);
                break;


            //
        }

    }
}
