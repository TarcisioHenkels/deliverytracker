package br.com.henkels.deliverytracker.GoogleApiClient;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GoogleApiClientManager implements ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

	private final GoogleApiClient googleApiClient;
	private final ApplicationContext ctx;

	public GoogleApiClientManager(ApplicationContext ctx) {
		this.ctx = ctx;
		this.googleApiClient = buildClient(ctx.getContext());
		this.googleApiClient.connect();
	}

	private GoogleApiClient buildClient(Context ctx) {
		GoogleApiClient googleApiClient = new GoogleApiClient.Builder(ctx)//
				.addConnectionCallbacks(this)//
				.addOnConnectionFailedListener(this)//
				.addApi(LocationServices.API)//
				.build();
		return googleApiClient;
	}

	@Override
	public void onConnected(Bundle arg0) {
		Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
		if (location != null) {
			ctx.locationChange(location);
		}
		LocationRequest mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(10000);
		mLocationRequest.setFastestInterval(5000);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		System.out.println(arg0);

	}

	@Override
	public void onLocationChanged(Location arg0) {
		ctx.locationChange(arg0);
	}

}
