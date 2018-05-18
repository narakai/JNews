package clem.app.jnews.retrofit

import clem.app.jnews.bean.NewsItem
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Retrofit请求api
 */
interface RetrofitService {

    @Headers("Authorization: ContentType eyJpdiI6Ik05VkVtQng1dEFlQis4bEZcL3ZcDJzUExYUGxJRUdDVnl2andEaWdSaEtOdUh4T3FzUm")
    @GET("news")
    fun getNewsList(
            @Query("category") category: String
    ): Deferred<List<NewsItem>>
}