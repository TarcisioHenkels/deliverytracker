package br.com.henkels.deliverytracker;

import br.com.henkels.deliverytracker.GoogleApiClient.ApplicationContext;
import br.com.henkels.deliverytracker.GoogleApiClient.GoogleApiClientManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMapReadyCallback {

	private GoogleMap map = null;
	private Location location = null;
	private Marker marker = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		new GoogleApiClientManager(new ApplicationContextImpl());

	}

	private class ApplicationContextImpl implements ApplicationContext {

		@Override
		public Context getContext() {
			return MainActivity.this;
		}

		@Override
		public void locationChange(Location location) {
			MainActivity.this.locationChange(location);
		}

	}

	private void locationChange(Location newLocation) {
		this.location = newLocation;
		if (map != null && location != null) {
			LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
			if (marker == null) {
				marker = map.addMarker(new MarkerOptions().title("Você!").snippet("Você mesmo...").position(latLng));
			} else {
				marker.setPosition(latLng);
			}
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 13));
		}
	}
	
	@Override
	public void onMapReady(GoogleMap map) {
		this.map = map;
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		locationChange(location);
	}

}
