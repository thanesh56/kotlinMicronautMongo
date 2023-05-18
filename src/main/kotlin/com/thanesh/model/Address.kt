package com.thanesh.model

import io.micronaut.serde.annotation.Serdeable.Serializable
import io.micronaut.serde.annotation.Serdeable.Deserializable

@Serializable
@Deserializable
data class Address (
    val city: String,
    val state: String,
    val country: String,
    val zipcode: Int
)
