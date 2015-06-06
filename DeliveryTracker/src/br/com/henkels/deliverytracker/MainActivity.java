package br.com.henkels.deliverytracker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements OnMapReadyCallback {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

	}

	@Override
	public void onMapReady(GoogleMap map) {
		LatLng myHome = new LatLng(-26.9436298, -49.12729204);

		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(myHome, 13));

		map.addMarker(new MarkerOptions().title("Tada!").snippet("To te vendo cara...").position(myHome));
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	}

}
