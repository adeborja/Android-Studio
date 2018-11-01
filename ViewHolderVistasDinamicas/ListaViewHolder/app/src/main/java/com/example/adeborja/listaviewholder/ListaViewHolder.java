package com.example.adeborja.listaviewholder;

import android.app.ListActivity;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaViewHolder extends ListActivity {

    TextView cajaTexto;
    List<clsEquipo> listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_view_holder);

        cajaTexto = (TextView)findViewById(R.id.txvCajaTexto);

        rellenarLista();

        //setListAdapter(new IconicAdapter<clsEquipo>(this, R.layout.fila_con_imagen, R.id.txvFilaConImagen, listaEquipos));
        setListAdapter(new MisEquiposAdapter());
    }

    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {
        cajaTexto.setText(listaEquipos.get(posicion).getNombre());
    }

    //crear la clase interna ViewHolder. Hay que uno por cada diseño de fila
    class ViewHolder
    {
        TextView lab;
        ImageView img;

        //Añadido constructor sin parametros para poder inicializarlos
        ViewHolder(){}

        ViewHolder (TextView nLab, ImageView nImg)
        {
            this.lab = nLab;
            this.img = nImg;
        }

        public TextView getLab()
        {
            return this.lab;
        }

        public ImageView getImg()
        {
            return this.img;
        }
    }

    class ViewHolderDos
    {
        TextView lab;
        ImageView img, imgDos;

        ViewHolderDos(){}

        ViewHolderDos (TextView nLab, ImageView nImg, ImageView nImgDos)
        {
            this.lab = nLab;
            this.img = nImg;
            this.imgDos = nImgDos;
        }

        public TextView getLab()
        {
            return this.lab;
        }

        public ImageView getImg()
        {
            return this.img;
        }

        public ImageView getImgDos()
        {
            return this.imgDos;
        }
    }

    //crear la clase interna IconicAdapter
    /*class IconicAdapter<T> extends ArrayAdapter<T>
    {
        //Constructor
        IconicAdapter(Context c, int resourceId, int textId, List<T> objetos)
        {
            super(c, resourceId, textId, objetos);
        }

        public View getView(int posicion, View convertView, ViewGroup padre)
        {
            //Variables
            View fila = convertView;
            ViewHolder holder;
            TextView lab;
            ImageView img;

            if(fila==null)
            {
                LayoutInflater inflador = getLayoutInflater();
                fila = inflador.inflate(R.layout.fila_con_imagen, padre, false);

                //HAY QUE PONER QUE BUSQUE EL ID EN LA FILA, EN LA FILA, EN LA FILA!!!!!!!! QUE SI NO, LO BUSCA EN ICONICADAPTER Y AHI NO ESTA
                lab = (TextView)fila.findViewById(R.id.txvFilaConImagen);
                img = (ImageView)fila.findViewById(R.id.imgFilaConImagen);

                holder = new ViewHolder(lab, img);
                fila.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) fila.getTag();
            }

            holder.getLab().setText(listaEquipos.get(posicion).getNombre());

            //if(listaEquipos.get(posicion).getId()==0)
            //{
            //    holder.getImg().setImageResource(R.mipmap.ic_launcher);
            //}
            //else
            //{
            //    holder.getImg().setImageResource(R.mipmap.ic_launcher_round);
            //}

            switch (listaEquipos.get(posicion).getId())
            {
                case 0:
                case 4:
                case 8:
                    holder.getImg().setImageResource(R.mipmap.ic_launcher);
                    break;
                default:
                    holder.getImg().setImageResource(R.mipmap.ic_launcher_round);
                    break;

            }

            return (fila);
        }
    }*/

    //Metodo para rellenar un array de equipos
    public void rellenarLista()
    {
        listaEquipos = new ArrayList<clsEquipo>(0);

        listaEquipos.add(new clsEquipo(0,"Sevilla FC"));
        listaEquipos.add(new clsEquipo(1,"FC Barcelona"));
        listaEquipos.add(new clsEquipo(2,"Real Madrid"));
        listaEquipos.add(new clsEquipo(3,"Atletico Madrid"));
        listaEquipos.add(new clsEquipo(4,"Valencia"));
        listaEquipos.add(new clsEquipo(5,"Real Betis"));
        listaEquipos.add(new clsEquipo(6,"Villarreal"));
        listaEquipos.add(new clsEquipo(7,"Getafe"));
        listaEquipos.add(new clsEquipo(8,"Alaves"));
        listaEquipos.add(new clsEquipo(9,"Athletic Bilbao"));
        listaEquipos.add(new clsEquipo(10,"Celta"));
        listaEquipos.add(new clsEquipo(11,"Eibar"));
        listaEquipos.add(new clsEquipo(12,"Espanyol"));
        listaEquipos.add(new clsEquipo(13,"Girona"));
        listaEquipos.add(new clsEquipo(14,"Huesca"));
        listaEquipos.add(new clsEquipo(15,"Leganes"));
        listaEquipos.add(new clsEquipo(16,"Levante"));
        listaEquipos.add(new clsEquipo(17,"Rayo Vallecano"));
        listaEquipos.add(new clsEquipo(18,"Real Sociedad"));
        listaEquipos.add(new clsEquipo(19,"Valladolid"));
    }



    public class MisEquiposAdapter extends BaseAdapter
    {
        public MisEquiposAdapter()
        {
            super();
        }

        //pulsar control+U en los nombres de los metodos para ver que devuelven
        @Override
        public clsEquipo getItem(int posicion)
        {
            return listaEquipos.get(posicion);
        }

        @Override
        public long getItemId(int posicion)
        {
            long id = listaEquipos.get(posicion).getId();

            return id;
        }

        @Override
        public int getCount()
        {
            return listaEquipos.size();
        }

        @Override
        public int getViewTypeCount()
        {
            return 2;
        }

        @Override
        public int getItemViewType(int posicion)
        {
            int devolver = posicion%2;

            return devolver;
        }


        @Override
        public View getView(int posicion, View convertView, ViewGroup padre)
        {
            View fila = convertView;
            int tipo = getItemViewType(posicion);
            clsEquipo equipo;

            TextView nombre;
            ImageView imgUno, imgDos;

            ViewHolder holderUnaImagen = new ViewHolder();
            ViewHolderDos holderDosImagenes = new ViewHolderDos();
            TextView hLab;
            ImageView hImg, hImgDos;

            if(fila==null)
            {
                LayoutInflater inflador = getLayoutInflater();



                if(tipo==0)
                {
                    fila = inflador.inflate(R.layout.fila_con_imagen, padre, false);

                    //Con ViewHolder
                    hLab = (TextView) fila.findViewById(R.id.txvNombre);
                    hImg = (ImageView) fila.findViewById(R.id.imgUno);

                    holderUnaImagen = new ViewHolder(hLab, hImg);

                    fila.setTag(holderUnaImagen);
                    //fin
                }
                else
                {
                    fila = inflador.inflate(R.layout.fila_dos_imagenes, padre, false);

                    //Con ViewHolder
                    hImgDos = (ImageView) fila.findViewById(R.id.imgDos);

                    hLab = (TextView) fila.findViewById(R.id.txvNombre);
                    hImg = (ImageView) fila.findViewById(R.id.imgUno);

                    holderDosImagenes = new ViewHolderDos(hLab, hImg, hImgDos);

                    fila.setTag(holderDosImagenes);
                    //fin
                }
            }
            //Con ViewHolder
            else
            {
                if(tipo==0)
                {
                    holderUnaImagen = (ViewHolder) fila.getTag();
                }
                else
                {
                    holderDosImagenes = (ViewHolderDos) fila.getTag();
                }
            }
            //fin

            //Sin viewholder
            /*equipo = listaEquipos.get(posicion);

            nombre = (TextView) fila.findViewById(R.id.txvNombre);
            imgUno = (ImageView) fila.findViewById(R.id.imgUno);

            nombre.setText(equipo.getNombre());
            imgUno.setImageResource(R.mipmap.ic_launcher);

            if(tipo==1)
            {
                imgDos = (ImageView) fila.findViewById(R.id.imgDos);
                imgDos.setImageResource(R.mipmap.ic_launcher);
                imgUno.setImageResource(R.mipmap.ic_launcher_round);
            }
            */
            //fin

            //Con ViewHolder
            if(tipo==0)
            {
                holderUnaImagen.getLab().setText(listaEquipos.get(posicion).getNombre());
                holderUnaImagen.getImg().setImageResource(R.mipmap.ic_launcher);
            }
            else
            {
                holderDosImagenes.getLab().setText(listaEquipos.get(posicion).getNombre());
                holderDosImagenes.getImg().setImageResource(R.mipmap.ic_launcher_round);
                holderDosImagenes.getImgDos().setImageResource(R.mipmap.ic_launcher);
            }
            //fin



            return fila;
        }


    }


}
