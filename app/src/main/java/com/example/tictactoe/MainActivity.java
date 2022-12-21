package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //    player  0 - X and 1 - O
    boolean gameActive=true;
    int activeplayer=0;
    int [] gamestate={2,2,2,2,2,2,2,2,2};
    // Gamestate
    // 0 -0
    // 1 - X
    //2 - Blank
    //Wining Posistions total 8 only
    int [][]winningPos = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}
                        };
    public void playerTap(View view){
        ImageView img = (ImageView) view;

        int tappedImage=Integer.parseInt(img.getTag().toString());
//        if(!gameActive){
//            gameReset(view);
//        }
        if(gamestate[tappedImage]==2){
            gamestate[tappedImage]=activeplayer;
            img.setTranslationZ(-1000f);
            if(activeplayer==0){
                img.setImageResource(R.drawable.x);
                activeplayer=1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activeplayer=0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn to play");
            }
            img.animate().translationZBy(1000f).setDuration(300);
        }


        //check if someone won or not
        for(int[] win:winningPos){
            if(gamestate[win[0]]==gamestate[win[1]] && gamestate[win[1]]==gamestate[win[2]] && gamestate[win[0]]!=2){
                //somebody won
                gameActive=false;
//                gameReset(view);
                String winner ;
                if(gamestate[win[0]]==1){
                    winner = "O has won";
                }
                else{
                    winner = "X has won";
                }
                onWinPopUp(winner,view);

                TextView status = findViewById(R.id.status);
                status.setText(winner);
            }
        }
        //Game Draw
        if(gameActive){
            boolean notdraw=false;
            for(int i=0;i<gamestate.length;i++){
                if(gamestate[i]==2){
                    notdraw=true;
                }
            }
            if(!notdraw){
                TextView status = findViewById(R.id.status);
                status.setText("Game Draw");
                onDrawPopUp(view);
            }
        }

    }

    public void onDrawPopUp(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Play Again");
        alertDialogBuilder.setTitle("GAME Draw");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        gameReset(view);
    }

    public void onWinPopUp(String winner,View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Congratulations "+ winner);

        alertDialogBuilder.setTitle("GAME Won");
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        gameReset(view);
    }

    public void gameReset(View view){
        gameActive=true;
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    }
}