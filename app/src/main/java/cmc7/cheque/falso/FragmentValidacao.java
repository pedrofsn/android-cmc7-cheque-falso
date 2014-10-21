package cmc7.cheque.falso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pedro.sousa on 21/10/2014.
 */
public class FragmentValidacao extends Fragment implements View.OnClickListener {

    private EditText editTextCmc7;
    private Button buttonOk;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    private Validador validador;
    private String cmc7;

    public FragmentValidacao() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_validacao, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextCmc7 = (EditText) view.findViewById(R.id.editTextCmc7);
        buttonOk = (Button) view.findViewById(R.id.buttonOk);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textView = (TextView) view.findViewById(R.id.textView);
    }

    @Override
    public void onStart() {
        super.onStart();

        validador = new Validador();

        buttonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                cmc7 = editTextCmc7.getText().toString();

                if (cmc7 != null && !cmc7.equals("")) {
                    if (validador.validarCMC7(cmc7)) {
                        Toast.makeText(getActivity(), "Cheque verdadeiro", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Cheque possivelmente falso", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Preencha o campo de CMC7", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void setResultadoValidacaoCheque(boolean caso) {
        if(caso) {

        } else {

        }

        //imageView.setBackground(R.id.ic);
    }
}