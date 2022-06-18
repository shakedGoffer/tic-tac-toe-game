package com.example.homework6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button[][] btns =new Button[3][3];
    Button btnAgain;
    TextView tvDisply;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btns[0][0] = (Button) findViewById(R.id.btn0);
        btns[0][1] = (Button) findViewById(R.id.btn1);
        btns[0][2] = (Button) findViewById(R.id.btn2);
        btns[1][0] = (Button) findViewById(R.id.btn3);
        btns[1][1] = (Button) findViewById(R.id.btn4);
        btns[1][2] = (Button) findViewById(R.id.btn5);
        btns[2][0] = (Button) findViewById(R.id.btn6);
        btns[2][1] = (Button) findViewById(R.id.btn7);
        btns[2][2] = (Button) findViewById(R.id.btn8);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                btns[i][j].setOnClickListener(this);
        }



        btnAgain=(Button)findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(this);
        btnAgain.setVisibility(View.INVISIBLE);

        tvDisply=(TextView)findViewById(R.id.tvDisply);
        tvDisply.setVisibility(View.INVISIBLE);




    }

    @Override
    public void onClick(View v)
    {
        if(v==btnAgain)
        {
            count =0;
            btnAgain.setVisibility(View.INVISIBLE);
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                    btns[i][j].setText("");
            }
            tvDisply.setVisibility(View.INVISIBLE);
        }
        else
        {
            userTurn(v);
        }

    }

    public void userTurn(View v)
    {
        Button btn = (Button) v;
        if(btn.getText().toString().equals("")==true)
        {
            btn.setText("O");
            count++;
            if (DoweHaveAwinner())
            {
                return;
            }
            else
            {
                ComputerTurn();
            }
        }
        else
            Toast.makeText(this,"Error,try agin",Toast.LENGTH_LONG).show();
    }

    public void ComputerTurn()
    {
        if (DoweHaveAwinner())
        {
            return;
        }
        else
        {
            boolean check =false;
            Random rand = new Random();
            while(!check)
            {
                int Row=rand.nextInt(10)%3;
                int Col=rand.nextInt(10)%3;
                if(btns[Row][Col].getText().toString().equals("")==true);
                {
                    btns[Row][Col].setText("X");
                    check=true;
                }
            }
            count++;
        }
    }

    public boolean DoweHaveAwinner()
    {
        if (isWin() > 0)
        {
            if (isWin() == 1) {
                tvDisply.setText("You Win");
            } else if (isWin() == 2) {
                tvDisply.setText("Computer Win");
            }
            btnAgain.setVisibility(View.VISIBLE);
            tvDisply.setVisibility(View.VISIBLE);
            return true;
        }
        else if (count > 8)
        {
            tvDisply.setVisibility(View.VISIBLE);
            tvDisply.setText("No One Win");
            btnAgain.setVisibility(View.VISIBLE);
            return  true;
        }
        return  false;
    }



    public int isWin()
    {
        if(count > 4)
        {
            int j = 0;
            for (int i = 0; i < 3; i++) {
                if (btns[i][j].getText().toString().length() > 0 && btns[i][j].getText().toString().equals(btns[i][j + 1].getText().toString()) && btns[i][j].getText().toString().equals(btns[i][j + 2].getText().toString())) {
                    if (btns[i][j].getText().toString().equals("O"))
                        return 1;//0 win  - odd counting
                    return 2;//x win
                }
                else if (btns[j][i].getText().toString().length() > 0 && btns[j][i].getText().toString().equals(btns[j + 1][i].getText().toString()) && btns[j][i].getText().toString().equals(btns[j + 2][i].getText().toString())) {
                    if (btns[j][i].getText().toString().equals("O"))
                        return 1;//user win - even win
                    return 2;
                }

            }//end of for
            //checking diagonal
            if (btns[0][0].getText().toString().length() > 0 && btns[0][0].getText().toString().equals(btns[1][1].getText().toString()) && btns[0][0].getText().toString().equals(btns[2][2].getText().toString())) {
                if (btns[0][j].getText().toString().equals("O"))
                    return 1;//0 win  - odd counting
                return 2;//x win
            }
            if (btns[0][2].getText().toString().length() > 0 && btns[0][2].getText().toString().equals(btns[1][1].getText().toString()) && btns[0][2].getText().toString().equals(btns[2][0].getText().toString())) {
                if (btns[0][j].getText().toString().equals("O"))
                    return 1;//o win  - odd counting
                return 2;
            }


        }
        return -1;//no one win
    }

}