package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.TypeConverter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Converters
{
    //@TypeConverter
    public static Bitmap getBitmapFromImage(String url)
    {
        File imgFile = new File(url);
        FileInputStream fis = null;
        Bitmap bitmap = null;

        try
        {
            fis = new FileInputStream(imgFile);
            bitmap = BitmapFactory.decodeStream(fis);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fis.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    //@TypeConverter
    public static byte[] getBytesFromBitmap(Bitmap b, int quality)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, quality, stream);
        byte[] data = stream.toByteArray();
        return data;
    }

    //@TypeConverter
    public static Bitmap getBitmapfromBytes(byte[] b)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
        return bitmap;

        //Si no funciona:
        //https://stackoverflow.com/questions/7620401/how-to-convert-byte-array-to-bitmap/40882445#40882445
    }
}
