package com.example.ngers.cursvalute.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ngers on 14.10.16.
 */
public class ValCurs implements Parcelable {
    public static final int VACURS_ID = 1;

    private Integer id = VACURS_ID;

    @Attribute(name = "Date")
    private String date;

    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true)
    private List<Valute> valutes;


    public ValCurs(Integer id, String date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.valutes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Valute> getValutes() {
        return valutes;
    }

    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.date);
        dest.writeString(this.name);
        dest.writeTypedList(this.valutes);
    }



    public ValCurs() {
    }

    protected ValCurs(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.date = in.readString();
        this.name = in.readString();
        this.valutes = in.createTypedArrayList(Valute.CREATOR);
    }

    public static final Creator<ValCurs> CREATOR = new Creator<ValCurs>() {
        @Override
        public ValCurs createFromParcel(Parcel source) {
            return new ValCurs(source);
        }

        @Override
        public ValCurs[] newArray(int size) {
            return new ValCurs[size];
        }
    };
}
