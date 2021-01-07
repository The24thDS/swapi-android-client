package dev.davidsima.swdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dev.davidsima.swdata.activities.FilmsActivity;
import dev.davidsima.swdata.activities.PeopleActivity;
import dev.davidsima.swdata.activities.PlanetsActivity;
import dev.davidsima.swdata.activities.SpeciesListActivity;
import dev.davidsima.swdata.activities.StarshipsActivity;
import dev.davidsima.swdata.activities.VehiclesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFilmsClick(View v) {
        Intent intent = new Intent(getApplicationContext(), FilmsActivity.class);
        startActivity(intent);
    }

    public void onPeopleClick(View v) {
        Intent intent = new Intent(getApplicationContext(), PeopleActivity.class);
        startActivity(intent);
    }

    public void onPlanetsClick(View v) {
        Intent intent = new Intent(getApplicationContext(), PlanetsActivity.class);
        startActivity(intent);
    }

    public void onSpeciesClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SpeciesListActivity.class);
        startActivity(intent);
    }

    public void onVehiclesClick(View v) {
        Intent intent = new Intent(getApplicationContext(), VehiclesActivity.class);
        startActivity(intent);
    }

    public void onStarshipsClick(View v) {
        Intent intent = new Intent(getApplicationContext(), StarshipsActivity.class);
        startActivity(intent);
    }
}
