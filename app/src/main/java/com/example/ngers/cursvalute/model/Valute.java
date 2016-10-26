package com.example.ngers.cursvalute.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ngers on 13.10.16.
 */
@Root(name = "Valute")
public class Valute implements Parcelable {
    @Attribute(name = "ID")
    private String id;

    @Element(name = "NumCode")
    private Integer numCode;

    @Element(name = "CharCode")
    private String charCode;

    @Element(name = "Nominal")
    private Integer nominal;

    @Element(name = "Name")
    private String name;

    @Element(name = "Value")
    private String value;

    public Valute(String id, Integer numCode, String charCode, Integer nominal, String name, String value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value.replace(",",".");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Valute() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.numCode);
        dest.writeString(this.charCode);
        dest.writeValue(this.nominal);
        dest.writeString(this.name);
        dest.writeString(this.value);
    }

    protected Valute(Parcel in) {
        this.id = in.readString();
        this.numCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.charCode = in.readString();
        this.nominal = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.value = in.readString();
    }

    public static final Creator<Valute> CREATOR = new Creator<Valute>() {
        @Override
        public Valute createFromParcel(Parcel source) {
            return new Valute(source);
        }

        @Override
        public Valute[] newArray(int size) {
            return new Valute[size];
        }
    };
}
