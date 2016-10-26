package com.example.ngers.cursvalute.model.dataBase;


import com.example.ngers.cursvalute.model.ValCurs;

/**
 * Created by ngers on 15.10.16.
 */

public interface IDataBaseHandler {
    public void addValcurs(ValCurs valCurs);
    public ValCurs getValcurs();
    public void deleteValCurs();
}
