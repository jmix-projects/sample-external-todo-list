package com.company.app.external;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Component
public class ExternalTodoService {

    public List<ExternalTodo> loadTodos() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()));

        ExternalApi externalApi = builder.build().create(ExternalApi.class);

        Call<List<ExternalTodo>> call = externalApi.loadTodos();
        try {
            Response<List<ExternalTodo>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
