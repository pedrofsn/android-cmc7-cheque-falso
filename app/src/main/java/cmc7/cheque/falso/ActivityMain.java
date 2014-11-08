package cmc7.cheque.falso;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class ActivityMain extends ActionBarActivity {

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentValidacao())
                    .commit();
        }

        loadInterstitial();
    }

    private void loadInterstitial() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(AdUnitId.AD_ID);

        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (interstitial.isLoaded())
            interstitial.show();
    }
}
