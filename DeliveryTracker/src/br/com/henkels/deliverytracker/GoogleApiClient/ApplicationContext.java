package br.com.henkels.deliverytracker.GoogleApiClient;

import android.content.Context;
import android.location.Location;

public interface ApplicationContext {

	Context getContext();

	void locationChange(Location location);

}
