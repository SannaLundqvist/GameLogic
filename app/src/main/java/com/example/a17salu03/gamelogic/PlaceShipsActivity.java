package com.example.a17salu03.gamelogic;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceShipsActivity extends AppCompatActivity implements GridFragment.OnItemClickedListener {

    private int selectedPosition;
    private boolean[] isShipAtPosition;
    private Button doneBtn = null;
    private ImageView img_2r = null;
    private ImageView img_3r = null;
    private ImageView img_4r = null;
    private TextView txt_2r = null;
    private TextView txt_3r = null;
    private TextView txt_4r = null;
    //används för att ta reda på vilket skepp som ska placeras (än så
    //länge i en förvald ordning... bör vara enkelt att få i spelarvald ordning
    private int shipNbr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_ships);

        doneBtn = findViewById(R.id.done);
        img_2r = findViewById(R.id.ship_2r_draw);
        img_3r = findViewById(R.id.ship_3r_draw);
        img_4r = findViewById(R.id.ship_4r_draw);
        txt_2r = findViewById(R.id.ship_2r_text);
        txt_3r = findViewById(R.id.ship_3r_text);
        txt_4r = findViewById(R.id.ship_4r_text);

        isShipAtPosition = new boolean[49];

        img_2r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shipsLeft = Integer.parseInt(txt_2r.getText().toString());
                if(shipsLeft > 0){
                    shipNbr = 0;
                    shipsLeft --;
                    txt_2r.setText(shipsLeft + "");
                }else{
                    //Toast.makeText(this, R.string.placed_all_ships, Toast.LENGTH_SHORT);
                }
            }
        });
        img_3r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shipsLeft = Integer.parseInt(txt_3r.getText().toString());
                if(shipsLeft > 0){
                    shipNbr = 1;
                    shipsLeft --;
                    txt_2r.setText(shipsLeft + "");
                }else{
                    //Toast.makeText(this, R.string.placed_all_ships, Toast.LENGTH_SHORT);
                }
            }
        });
        img_4r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shipsLeft = Integer.parseInt(txt_4r.getText().toString());
                if(shipsLeft > 0){
                    shipNbr = 2;
                    shipsLeft --;
                    txt_4r.setText(shipsLeft + "");
                }else{
                    //Toast.makeText(this, R.string.placed_all_ships, Toast.LENGTH_SHORT);
                }
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //skicka tillbaka informationen till StartActivity
                //kanske behövs låsas innan man valt position minst en gång, eller testas
                //isShipAtPosition skickas vidare
            }
        });

        GridFragment playerGrid = new GridFragment();
        FragmentTransaction playerft = getSupportFragmentManager().beginTransaction();
        playerft.replace(R.id.fragment_container_player, playerGrid);
        playerft.addToBackStack(null);
        playerft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        playerft.commit();


    }

    @Override
    public void onItemClicked(int position) {
        switch (shipNbr){
            case 0 :
                placeShipAtPosition(position, 2, true);
                return;
            case 1 :
                placeShipAtPosition(position, 3, true);
                return;
            case 2 :
                placeShipAtPosition(position, 4, true);
                break;
        }
        shipNbr ++;
    }

    /**
     * Hjälpmetod för att visa vart skeppen finns
     * justs nu går det att lägga två skepp ovanpå varadra och
     * om det hamnar utanför den högra begrensningen kommer den att
     * fortsätta under.
     * @param startPosition
     * @param lenght
     * @param isHorizontal finns som tillägg för framtiden, används ej
     */
    private void placeShipAtPosition(int startPosition, int lenght, boolean isHorizontal){
        while(lenght > 0){
            isShipAtPosition[startPosition + lenght - 1] = true;
            lenght --;
        }
    }
}
