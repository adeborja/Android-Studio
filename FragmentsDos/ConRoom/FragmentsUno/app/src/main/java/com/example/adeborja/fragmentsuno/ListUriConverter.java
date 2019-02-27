package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListUriConverter
{

    @TypeConverter
    public static List<Uri> jsonToObject(String value)
    {
        List<Uri> listaFinal = null;

        if(value!=null)
        {
            Gson gson = new Gson();
            Type tipo = new TypeToken<List<Uri>>(){}.getType();
            Object aux = gson.fromJson(value, tipo);
            listaFinal = (List<Uri>) aux;
        }

        return listaFinal;
    }


    @TypeConverter
    public static String objectToJson(List<Uri> value)
    {
        Gson gson = new Gson();

        return value == null ? null : gson.toJson(value);
    }
}
