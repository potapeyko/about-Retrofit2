package com.project.dmitry.retrofit2.Example.repository;

import android.support.annotation.NonNull;

import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import rx.Observable;

/**
 * @author Artur Vasilov
 */
public interface NasaRepository {

    @NonNull
    Observable<DayPicture> dayPicture();

}
