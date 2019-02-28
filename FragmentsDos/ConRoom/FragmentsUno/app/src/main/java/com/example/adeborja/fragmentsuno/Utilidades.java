package com.example.adeborja.fragmentsuno;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

public class Utilidades
{

    public static final int PICK_IMAGE = 100;
    public static final int PICK_GALLERY = 101;
    public static int PERMISO_LEER_GALERIA = 1;

    public static final Uri getUriToDrawable(@NonNull Context context,
                                             @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId) );
        return imageUri;
    }

    public static void pedirPermisoGaleria(Activity activity)
    {
        final Activity mActivity = activity;
        new AlertDialog.Builder(mActivity)
                .setTitle(R.string.cuadro_pedir_permisos_titulo)
                .setMessage(R.string.cuadro_pedir_permisos_desc)
                .setPositiveButton(R.string.cuadro_pedir_permisos_aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_LEER_GALERIA);
                    }
                })
                .create()
                .show();

        //ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_LEER_GALERIA);
    }

    public static String getPath(Uri uri, Context context)
    {
        String path = null;
        String[] proyeccion = null;
        Cursor cursor = null;
        int columnIndex = 0;

        if(uri!=null)
        {
            proyeccion = new String[]{MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, proyeccion, null,null,null);

            if(cursor!=null)
            {
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(columnIndex);
                cursor.close();
            }
            else
            {
                path = uri.getPath();
            }
        }

        return path;
    }

}
