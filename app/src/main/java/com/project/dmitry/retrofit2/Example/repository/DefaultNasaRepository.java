package com.project.dmitry.retrofit2.Example.repository;

import android.support.annotation.NonNull;



public class DefaultNasaRepository implements NasaRepository {

    @NonNull
    @Override
    public Observable<DayPicture> dayPicture() {
        return ApiFactory.getNasaService()
                .todayPicture()
                .compose(RxUtils.async());
    }
}
