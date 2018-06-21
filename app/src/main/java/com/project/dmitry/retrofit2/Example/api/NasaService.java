package com.project.dmitry.retrofit2.Example.api;

import retrofit2.http.GET;
import rx.Observable;


public interface NasaService {

    @GET("/planetary/apod")
    Observable<DayPicture> todayPicture();

}
