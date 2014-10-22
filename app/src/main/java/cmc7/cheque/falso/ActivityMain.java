package cmc7.cheque.falso;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class ActivityMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new FragmentValidacao())
                    .commit();
        }
    }


}
