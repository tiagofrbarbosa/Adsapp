package tech.infofun.adsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by tfbarbosa on 22/05/17.
 */
public class InterstitialActivity extends Activity{
    private Button mShowButton;
    private InterstitialAd mInterstitial;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);

        mShowButton = (Button) findViewById(R.id.showButton);
        mShowButton.setEnabled(false);
    }

    public void loadInterstitial(View unusedView){
        mShowButton.setEnabled(false);
        mShowButton.setText(getResources().getString(R.string.interstitial_loading));

        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitial.setAdListener(new ToastAdListener(this){
            @Override
            public void onAdLoaded(){
                super.onAdLoaded();
                mShowButton.setText(getResources().getString(R.string.interstitial_show));
                mShowButton.setEnabled(true);
            }
            @Override
            public void onAdFailedToLoad(int errorCode){
                super.onAdFailedToLoad(errorCode);
                mShowButton.setText(getmErrorReason());
            }
        });

        AdRequest ar = new AdRequest.Builder().build();
        mInterstitial.loadAd(ar);
    }

    public void showInterstitial(View unusedView){
        if(mInterstitial.isLoaded()){
            mInterstitial.show();
        }

        mShowButton.setText(getResources().getString(R.string.interstitial_not_ready));
        mShowButton.setEnabled(false);
    }
}
