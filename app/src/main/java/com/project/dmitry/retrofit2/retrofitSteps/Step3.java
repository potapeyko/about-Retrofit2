package com.project.dmitry.retrofit2.retrofitSteps;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DmitryPC on 21.06.2018.
 */

public class Step3 {
//    Для синхронного запроса используйте метод Call.execute(), для асинхронного - метод Call.enqueue().
//Call instances can be executed either synchronously or asynchronously.
//    Each instance can only be used once, but calling clone() will create a new instance that can be used.
//
//    On Android, callbacks will be executed on the main thread. On the JVM, callbacks will happen on the same
//    thread that executed the HTTP request.


    //если делать запросы асинхронно, то необходимо передавать в Call.enqueue() колбек с обработчиком.
    //https://futurestud.io/tutorials/retrofit-synchronous-and-asynchronous-requests
//    лучше синхронный вызов и выводить в другой поток при помощи rx
//    Объект для запроса к серверу создаётся в простейшем случае следующим образом
public static final String BASE_URL = "http://api.example.com/";
//    Gson: com.squareup.retrofit2:converter-gson
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//    Можно создать свой конвертер (Create a class that extends the Converter.Factory class

//    получили объект Retrofit, содержащий базовый URL и способность преобразовывать JSON-данные с помощью указанного конвертера Gson.
//    Далее в его методе create() указываем наш класс интерфейса с запросами к сайту.
    Step2 userService = retrofit.create(Step2.class);
//    После этого мы получаем объект Call и вызываем метод enqueue() (для асинхронного вызова)
//    и создаём для него Callback. Запрос будет выполнен в отдельном потоке, а результат придет в Callback в main-потоке.
//Для отмены запроса используется метод Call.cancel().


//    В библиотеку можно внедрить перехватчики для изменения заголовков
//    при помощи класса Interceptor из OkHttp. Сначала следует создать объект перехватчика и
//    передать его в OkHttp, который в свою очередь следует явно подключить в Retrofit.Builder через
//    метод client().
//
//    Поддержка перехватчиков/interceptors для обработки заголовков запросов, например,
//    для работы с токенами авторизации в заголовке Authorization.

    OkHttpClient client =  new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Настраиваем запросы
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("Authorization", "auth-token")
                            .method(original.method(), original.body())
                            .build();
                    //или можно так
//                    HttpUrl url = request.url().newBuilder()
//                            .addQueryParameter("api_key", BuildConfig.API_KEY)
//                            .build();
//                    request = request.newBuilder().url(url).build();
                    Response response = chain.proceed(request);

                    return response;
                }})
            .build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://your.api.url/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())//for rxjava
        .build();
}
