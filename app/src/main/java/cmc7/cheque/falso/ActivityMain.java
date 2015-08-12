package cmc7.cheque.falso;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText editTextCmc7;
    private Button buttonOk;
    private LinearLayout linearLayout;
    private ImageView imageViewValidadeCheque;
    private TextView textView;
    private LinearLayout linearLayoutAd;

    private Validador validador;
    private String cmc7anterior = "";
    private String cmc7 = "";

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCmc7 = (EditText) findViewById(R.id.editTextCmc7);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        imageViewValidadeCheque = (ImageView) findViewById(R.id.imageViewValidadeCheque);
        textView = (TextView) findViewById(R.id.textView);
        linearLayoutAd = (LinearLayout) findViewById(R.id.linearLayoutAd);

        loadInterstitial();
    }

    @Override
    public void onStart() {
        super.onStart();
        validador = new Validador(this, editTextCmc7);
        editTextCmc7.addTextChangedListener(this);
        buttonOk.setOnClickListener(this);

        addAndLoadAdViewBanner();
    }

    private void addAndLoadAdViewBanner() {
        AdView adView = new AdView(this);
        adView.setAdUnitId(AdUnitId.AD_ID);
        adView.setAdSize(AdSize.BANNER);
        adView.loadAd(new AdRequest.Builder()
                .build());

        linearLayoutAd.addView(adView);
    }

    private void loadInterstitial() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(AdUnitId.AD_ID);

        AdRequest adRequest = new AdRequest.Builder().build();

        interstitial.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                if (cmc7anterior.equals(cmc7)) {
                    linearLayout.setVisibility(View.GONE);
                }

                cmc7 = editTextCmc7.getText().toString();

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editTextCmc7.getWindowToken(), 0);

                setResultadoValidacaoCheque(validador.validarCMC7(cmc7));
                break;
        }
    }

    private void setResultadoValidacaoCheque(Boolean caso) {
        int drawable = 0;
        int string = 0;

        if (caso != null) {
            if (caso) {
                drawable = R.drawable.ic_positivo;
                string = R.string.cheque_possivelmente_verdadeiro;
            } else {
                drawable = R.drawable.ic_negativo;
                string = R.string.cheque_possivelmente_falso;
            }

            imageViewValidadeCheque.setBackgroundResource(drawable);
            textView.setText(string);

            linearLayout.setVisibility(View.VISIBLE);
        }


        cmc7anterior = cmc7;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (cmc7anterior != null) {
            if (!editable.toString().equals(cmc7anterior)) {
                linearLayout.setVisibility(View.GONE);
            }

            editTextCmc7.setError(null);
        }
    }
}
