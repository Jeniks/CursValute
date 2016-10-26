package com.example.ngers.cursvalute.view;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngers.cursvalute.R;
import com.example.ngers.cursvalute.databinding.ConverterFragmentBinding;
import com.example.ngers.cursvalute.model.Valute;
import com.example.ngers.cursvalute.presenter.ConverterValutePresenter;


/**
 * Created by ngers on 25.10.16.
 */

public class ConverterValuteFragment extends Fragment {
    private ConverterFragmentBinding binding;
    private ConverterValutePresenter presenter;

    private CurrencyAdapter adapterHave;
    private CurrencyAdapter adapterWant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.converter_fragment,
                container, false);

        presenter = new ConverterValutePresenter();
        presenter.create(getActivity());

        iniData();

        return binding.getRoot();
    }

    private void iniData() {

        createAdapter();
        binding.valHave.setText(presenter.getValuteHave().getCharCode());
        binding.valWant.setText(presenter.getValuteWant().getCharCode());

        binding.listHaveValute.setAdapter(adapterHave);
        binding.listHaveValute.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.listWantValute.setAdapter(adapterWant);
        binding.listWantValute.setLayoutManager(new LinearLayoutManager(getActivity()));

        setListener();
    }

    private void setListener() {
        binding.etHave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    double sum = Double.parseDouble(charSequence.toString());
                    binding.etWant.setText(presenter.getVauteWantValue(sum));
                } else
                    binding.etWant.setText(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.valHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.listHaveValute.getVisibility() == View.GONE)
                    binding.listHaveValute.setVisibility(View.VISIBLE);
                else
                    binding.listHaveValute.setVisibility(View.GONE);
            }
        });

        binding.valWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.listWantValute.getVisibility() == View.GONE)
                    binding.listWantValute.setVisibility(View.VISIBLE);
                else
                    binding.listWantValute.setVisibility(View.GONE);
            }
        });
    }

    private void setSumWant() {
        if (binding.etHave.getText().toString().length() > 0) {
            double sumHave = Double.parseDouble(binding.etHave.getText().toString());
            binding.etWant.setText(presenter.getVauteWantValue(sumHave));
        }
    }

    private void createAdapter() {
        adapterHave = new CurrencyAdapter(presenter.getValutes(), new CurrencyAdapter.OnClickItem() {
            @Override
            public void OnClickItem(Valute valute) {
                binding.listHaveValute.setVisibility(View.GONE);
                binding.valHave.setText(valute.getCharCode());
                presenter.setValuteHave(valute);
                setSumWant();
            }
        });
        adapterWant = new CurrencyAdapter(presenter.getValutes(), new CurrencyAdapter.OnClickItem() {
            @Override
            public void OnClickItem(Valute valute) {
                binding.listWantValute.setVisibility(View.GONE);
                binding.valWant.setText(valute.getCharCode());
                presenter.setValuteWant(valute);
                setSumWant();
            }
        });
    }

}
