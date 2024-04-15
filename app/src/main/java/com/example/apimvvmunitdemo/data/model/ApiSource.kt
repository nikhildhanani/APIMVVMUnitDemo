package com.example.apimvvmunitdemo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ApiSource(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) : Serializable

/*
fun ApiSource.toEntitySource(): Source {
    return Source(
        id = id,
        name = name
    )
}*/
