package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListaImagenesConverter
{
    @TypeConverter
    public static ListaImagenes jsonToObject(String value)
    {
        //Esto devuelve object
        /*Gson gson = new Gson();
        Type tipo = new TypeToken<Uri[]>(){}.getType();

        return value == null ? null : gson.fromJson(value, tipo);*/



        //Esto da el error Expected BEGIN_ARRAY but was BEGIN_OBJECT
        /*Gson gson = new Gson();
        Type tipo = new TypeToken<List<Uri>>(){}.getType();
        Object lista1 = gson.fromJson(value, tipo);
        List<Uri> lista2 = (List<Uri>) lista1;
        ListaImagenes listaFinal = new ListaImagenes();
        listaFinal.setImagenes(lista2);

        return value == null ? null : listaFinal;*/



        Gson gson = new Gson();
        Type tipo = new TypeToken<List<String>>(){}.getType();
        List<String> lista1 = gson.fromJson(value, tipo);

        //ArrayList<String> listdata = new ArrayList<String>(0);
        List<Uri> listdata2 = new ArrayList<Uri>(0);
        JSONArray jsonArray = (JSONArray) lista1;
        if(jsonArray != null)
        {
            for(int i=0;i<jsonArray.length();i++)
            {
                try
                {
                    listdata2.add(Uri.parse(jsonArray.getString(i)));
                }
                catch (org.json.JSONException e)
                {

                }

            }
        }

        ListaImagenes listaFinal = new ListaImagenes();
        listaFinal.setImagenes(listdata2);

        return value == null ? null : listaFinal;
    }

    @TypeConverter
    public static String objectToJson(ListaImagenes value)
    {
        Gson gson = new Gson();

        return value == null ? null : gson.toJson(value);
    }
}
