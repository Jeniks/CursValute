package com.example.ngers.cursvalute.presenter;

import android.content.Context;

import com.example.ngers.cursvalute.model.ValCurs;
import com.example.ngers.cursvalute.model.Valute;
import com.example.ngers.cursvalute.model.dataBase.DataBaseHandler;

import java.util.List;

/**
 * Created by ngers on 26.10.16.
 */

public class ConverterValutePresenter implements Presenter {
    Valute valuteHave;
    Valute valuteWant;

    List<Valute> valutes;

    DataBaseHandler db;

    @Override
    public void create(Context context) {
        db = new DataBaseHandler(context);
        ValCurs valCurs = db.getValcurs();
        if (valCurs != null)
            valutes = db.getValcurs().getValutes();

        if (valutes != null && valutes.size() > 0) {
            valuteHave = valutes.get(0);
            valuteWant = valutes.get(0);
        }
    }


    public List<Valute> getValutes() {
        return valutes;
    }

    public Valute getValuteHave() {
        return valuteHave;
    }

    public Valute getValuteWant() {
        return valuteWant;
    }

    public void setValuteHave(Valute valuteHave) {
        this.valuteHave = valuteHave;
    }

    public void setValuteWant(Valute valuteWant) {
        this.valuteWant = valuteWant;
    }

    public String getVauteWantValue(double inputSum) {
        return String.valueOf((inputSum / (getHaveValue())) * (getWantValue()));
    }

    public double getHaveValue() {
        if (valuteHave != null)
            return Double.parseDouble(valuteHave.getValue()) / valuteHave.getNominal();
        else return 1;
    }

    public double getWantValue() {
        if (valuteWant != null)
            return Double.parseDouble(valuteWant.getValue()) / valuteWant.getNominal();
        else return 1;
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
