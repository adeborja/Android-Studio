package com.example.adeborja.fragmentsuno;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private boolean tablet;
    private Context context;
    private Personaje personajeSeleccionado;

    private LiveData<List<Personaje>> listaLiveData;
    private PersonajeRepository repository;

    public MainViewModel(@NonNull Application application)
    {
        super(application);
        repository = new PersonajeRepository(application);
        listaLiveData = repository.obtenerPersonajesLiveData();
    }


    public LiveData<List<Personaje>> getListaLiveData()
    {
        return listaLiveData;
    }

    public void insert(Personaje p)
    {
        repository.insert(p);
    }

    public void update(Personaje p)
    {
        repository.update(p);
    }

    public void delete(Personaje p)
    {
        repository.delete(p);
    }



    public Personaje getPersonajeSeleccionado() {
        return personajeSeleccionado;
    }

    public void setPersonajeSeleccionado(Personaje personajeSeleccionado) {
        this.personajeSeleccionado = personajeSeleccionado;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet)
    {
        this.tablet = tablet;
    }

    public void rellenarLista()
    {
        Personaje p;
        PersonajeRepository r = new PersonajeRepository(getApplication());
        Uri retrato = Utilidades.getUriToDrawable(this.context, R.drawable.goku);

        List<String> listStringImagenes = new ArrayList<>(0);
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.goku01).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.goku02).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.goku03).toString());
        p = new Personaje("Son Goku", "Goku", "Son Goku (孫悟空 Son Gokū), originalmente llamado Zero en Estados Unidos e Hispanoamérica (doblaje de Harmony Gold) y posteriormente Gokú en este último, es el protagonista principal del manga y anime de Dragon Ball creado por Akira Toriyama. Su nombre real y de nacimiento es Kakarrot (カカロット Kakarotto) y es uno de los pocos Saiyan que lograron sobrevivir a la destrucción total del Planeta Vegeta del Universo 7. Es el segundo hijo de Bardock y Gine, hermano menor de Raditz, nieto adoptivo de Son Gohan, esposo de Chi-Chi, padre de Son Gohan y Son Goten, a su vez también es el abuelo de Pan y ancestro de Son Goku Jr.\n" +
                "\n" +
                "Originalmente enviado a la Tierra como un bebé de infiltración con la misión de conquistarla. Sin embargo, el caer por un barranco le proporcionó un brutal golpe que si bien casi lo mata, este alteró su memoria y anuló todos los instintos violentos de su especie, lo que lo hizo crecer con un corazón puro y bondadoso, pero conservando todos los poderes de su raza. No obstante, en la nueva continuidad de Dragon Ball se establece que él fue enviado por sus padres a la Tierra con el objetivo de sobrevivir a toda costa a la destrucción de su planeta por parte de Freezer. Más tarde, Kakarrot, ahora conocido como Son Goku, se convertiría en el líder de los Guerreros Z, así como el mayor defensor de la Tierra y del Universo 7, logrando mantenerlos a salvo de la destrucción en innumerables ocasiones, a pesar de no considerarse a sí mismo como un héroe.", retrato, listStringImagenes, 0);
        r.insert(p);

        retrato = Utilidades.getUriToDrawable(this.context, R.drawable.vegeta);
        listStringImagenes = new ArrayList<>(0);
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.vegeta01).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.vegeta02).toString());

        p = new Personaje("Vegeta", "Vegeta", "Vegeta IV (ベジータ四世 Bejīta Yon-sei), frecuentemente reconocido como el Príncipe Vegeta (ベジータ王子 Bejīta-ōji) y más conocido simplemente como Vegeta (ベジータ Bejīta), es el deuteragonista de Dragon Ball Z, Dragon Ball Z Kai, Dragon Ball GT y Dragon Ball Super. Es el hijo mayor del Rey Vegeta III, así como el príncipe más reciente de la Familia Real Saiyan y uno de los pocos superviviente tras el Genocidio de los Saiyan del Planeta Vegeta del Universo 7, destruido a manos de Freezer. Es el eterno rival de Son Goku, hermano mayor de Tarble, el esposo de Bulma, padre de Trunks y Bra y ancestro de Vegeta Jr.\n" +
                "\n" +
                "A pesar de que a inicios de Dragon Ball Z, Vegeta cumple un papel antagónico, más adelante decide rebelarse ante el Imperio de Freezer y se vuelve un aliado clave para los Guerreros Z, a tal punto que con el paso del tiempo llegaría a cambiar su manera de ser, optando por permanecer y vivir en la Tierra para luchar junto a ellos contra las inminentes amenazas. Él, junto a Piccolo, es de los antiguos enemigos de Son Goku que ha evolucionando de ser un villano, a antihéroe, y finalmente a un héroe a lo largo del transcurso de la historia llegando a convertirse en el deuteragonista de la serie.", retrato, listStringImagenes, 0);
        r.insert(p);

        retrato = Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks);
        listStringImagenes = new ArrayList<>(0);
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks01).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks02).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks03).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks04).toString());
        listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks05).toString());

        p = new Personaje("Trunks", "Trunks del futuro", "Trunks del Futuro Alternativo (未来トランクス Mirai Torankusu), también conocido como Trunks del futuro, es un guerrero mestizo Saiyan/terrícola, hijo de las contrapartes de Bulma y Vegeta, Bulma del futuro y Vegeta del futuro, así como alumno de Gohan del futuro y aprendiz de Kaio-shin quien, con ayuda de su Máquina del tiempo, consigue viajar al tiempo de Son Goku proveniente de una línea de tiempo apocalíptica.\n" +
                "\n" +
                "Como el único bastión de los Guerreros Z del Futuro Alternativo, es sin duda uno de los personajes más recurrentes en la historia de Dragon Ball, sea como un protagonista o como apoyo, como se vio en su rol de Patrullero del Tiempo. ", retrato, listStringImagenes, 0);
        r.insert(p);

        /*retrato = Utilidades.getUriToDrawable(this.context, R.drawable.ic_launcher_background);
        listStringImagenes = null;
        p = new Personaje("Son Gohan", "Gohan", "", retrato, listStringImagenes, 0);
        r.insert(p);*/

    }
}
