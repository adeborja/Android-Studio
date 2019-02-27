package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class listStringConverter
{
    @TypeConverter
    public static List<String> jsonToObject(String value)
    {
        List<String> listaFinal = null;

        if(value!=null)
        {
            Gson gson = new Gson();
            Type tipo = new TypeToken<ArrayList<String>>(){}.getType();
            ArrayList<String> aux = gson.fromJson(value, tipo);
            listaFinal = aux;
        }

        return listaFinal;
    }


    @TypeConverter
    public static String objectToJson(List<String> value)
    {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {}.getType();

        return value == null ? null : gson.toJson(value, listType);
    }
}
