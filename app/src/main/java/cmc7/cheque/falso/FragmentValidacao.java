package cmc7.cheque.falso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by pedro.sousa on 21/10/2014.
 */
public class FragmentValidacao extends Fragment implements View.OnClickListener, TextWatcher {

    private EditText editTextCmc7;
    private Button buttonOk;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    private Validador validador;
    private String cmc7anterior;
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
        validador = new Validador(getActivity());
        editTextCmc7.addTextChangedListener(this);
        buttonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOk:
                if (cmc7anterior == cmc7)
                    linearLayout.setVisibility(View.GONE);

                cmc7 = editTextCmc7.getText().toString();
                setResultadoValidacaoCheque(validador.validarCMC7(cmc7));
                break;
        }
    }

    private void setResultadoValidacaoCheque(boolean caso) {
        linearLayout.setVisibility(View.VISIBLE);
        int drawable;
        int string;

        if (caso) {
            drawable = R.drawable.ic_positivo;
            string = R.string.cheque_possivelmente_verdadeiro;
        } else {
            drawable = R.drawable.ic_negativo;
            string = R.string.cheque_possivelmente_falso;
        }

        imageView.setBackgroundResource(drawable);
        textView.setText(string);

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
        if (cmc7anterior != null)
            if (!editable.toString().equals(cmc7anterior))
                linearLayout.setVisibility(View.GONE);
    }
}