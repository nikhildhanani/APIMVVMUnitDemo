package com.example.apimvvmunitdemo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ApiArticle(

    @SerializedName("title")
    val title: String ,
    @SerializedName("description")
    val description: String ,
    @SerializedName("url")
    val url: String ,
    @SerializedName("urlToImage")
    val imageUrl: String ,
    @SerializedName("source")
    val source: ApiSource
):Serializable

/*
fun ApiArticle.toArticleEntity(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = source.toEntitySource()
    )
}*/
