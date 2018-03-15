package uk.ac.napier.movie_guide.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.ac.napier.movie_guide.model.MovieResponse;

/**
 * Created by leawhitelaw on 09/03/2018.
 */

public interface Service {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);


}
