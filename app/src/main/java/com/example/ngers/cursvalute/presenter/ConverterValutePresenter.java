package com.example.ngers.cursvalute.presenter;

import android.content.Context;

import com.example.ngers.cursvalute.model.Valute;
import com.example.ngers.cursvalute.model.dataBase.DataBaseHandler;

import java.util.List;

/**
 * Created by ngers on 26.10.16.
 */

public class ConverterValutePresenter implements Presenter{
    Valute valuteHave;
    Valute valuteWant;

    List<Valute> valutes;

    DataBaseHandler db;
    @Override
    public void create(Context context) {
        db = new DataBaseHandler(context);
        valutes = db.getValcurs().getValutes();

        valuteHave = valutes.get(0);
        valuteWant = valutes.get(0);
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

    public String getVauteWantValue(double inputSum){
        return String.valueOf((inputSum / (getHaveValue())) * (getWantValue()));
    }

    public double getHaveValue() {
        return  Double.parseDouble(valuteHave.getValue()) / valuteHave.getNominal();
    }

    public double getWantValue() {
        return Double.parseDouble(valuteWant.getValue()) / valuteWant.getNominal();
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
