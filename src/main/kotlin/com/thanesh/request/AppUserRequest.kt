package com.thanesh.request

data class AppUserRequest(
    val name: String,
    val email: String,
    val city: String,
    val state: String,
    val country: String,
    val zipcode: Int,

)
