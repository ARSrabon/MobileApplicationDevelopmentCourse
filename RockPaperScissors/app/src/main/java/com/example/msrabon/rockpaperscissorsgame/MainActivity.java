package com.example.msrabon.rockpaperscissorsgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView current_result;
    TextView player_one_score;
    TextView player_two_score;

    Button player_one_rock;
    Button player_one_paper;
    Button player_one_scissors;
    Button player_two_rock;
    Button player_two_paper;
    Button player_two_scissors;

    ImageView player_one_selection;
    ImageView player_two_selection;

    int flag_1;
    int flag_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Player 1 Rock Paper Scissors Button initialized
        player_one_paper = (Button) findViewById(R.id.btn_p1_paper);
        player_one_rock = (Button) findViewById(R.id.btn_p1_rock);
        player_one_scissors = (Button) findViewById(R.id.btn_p1_scissors);

        // Player 2 Rock Paper Scissors Button initialized
        player_two_paper = (Button) findViewById(R.id.btn_p2_paper);
        player_two_rock = (Button) findViewById(R.id.btn_p2_rock);
        player_two_scissors = (Button) findViewById(R.id.btn_p2_scissors);

        current_result = (TextView) findViewById(R.id.txt_current_result); // current game result

        //player one and two scores.
        player_one_score = (TextView) findViewById(R.id.txt_player_one_score);
        player_two_score = (TextView) findViewById(R.id.txt_player_two_score);

        //
        player_one_selection = (ImageView) findViewById(R.id.img_view_p1_select);
        player_two_selection = (ImageView) findViewById(R.id.img_view_p2_select);


        player_one_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_1 = 1;
                player_one_btn_disable();
                getgamewinner();
                player_one_selection.setImageResource(R.mipmap.img_rock);
            }
        });

        player_one_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_1 = 2;
                player_one_btn_disable();
                getgamewinner();
                player_one_selection.setImageResource(R.mipmap.img_paper);
            }
        });

        player_one_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_1 = 3;
                player_one_btn_disable();
                getgamewinner();
                player_one_selection.setImageResource(R.mipmap.img_scissors);
            }
        });

        player_two_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_2 = 1;
                player_two_btn_disable();
                getgamewinner();
                player_two_selection.setImageResource(R.mipmap.img_rock);
            }
        });

        player_two_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_2 = 2;
                player_two_btn_disable();
                getgamewinner();
                player_two_selection.setImageResource(R.mipmap.img_paper);
            }
        });

        player_two_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag_2 = 3;
                player_two_btn_disable();
                getgamewinner();
                player_two_selection.setImageResource(R.mipmap.img_scissors);
            }
        });

    }

    private void getgamewinner() {
        String winner = rock_paper_scissors(flag_1,flag_2);
        if(winner != null){
            if (winner.equals("p1")){
                current_result.setText("Result: Player 1 wins");
                player_one_score.setText(String.valueOf(Integer.parseInt(player_one_score.getText().toString())+1));
            }else if(winner.equals("p2")){
                current_result.setText("Result: Player 2 wins");
                player_two_score.setText(String.valueOf(Integer.parseInt(player_two_score.getText().toString())+1));
            }else if(winner.equals("draw")){
                current_result.setText("Result: It's a draw !!!");
            }
            flag_1=0;
            flag_2=0;
            player_one_btn_enable();
            player_two_btn_enable();
        }else {
            Toast.makeText(MainActivity.this, "Wait for other player to bid.", Toast.LENGTH_SHORT).show();
        }
    }

    void player_one_btn_disable() {

        player_one_scissors.setEnabled(false);
        player_one_paper.setEnabled(false);
        player_one_rock.setEnabled(false);

    }

    void player_one_btn_enable() {
        player_one_scissors.setEnabled(true);
        player_one_paper.setEnabled(true);
        player_one_rock.setEnabled(true);
    }

    void player_two_btn_enable() {
        player_two_scissors.setEnabled(true);
        player_two_paper.setEnabled(true);
        player_two_rock.setEnabled(true);
    }

    void player_two_btn_disable() {

        player_two_scissors.setEnabled(false);
        player_two_paper.setEnabled(false);
        player_two_rock.setEnabled(false);

    }


    protected String rock_paper_scissors(int player1, int player2) {

        if (player1 == 0 && player2 == 0) {
            return null;
        } else if (player1 == player2) {
            return "draw";
        } else if (player1 == 1) {
            switch (player2) {
                case 2:
                    return "p2";
                case 3:
                    return "p1";
            }
        } else if (player1 == 2) {
            switch (player2) {
                case 1:
                    return "p1";
                case 3:
                    return "p2";
            }
        } else if (player1 == 3) {
            switch (player2) {
                case 1:
                    return "p2";
                case 2:
                    return "p1";
            }
        }
        return null;
    }
}
