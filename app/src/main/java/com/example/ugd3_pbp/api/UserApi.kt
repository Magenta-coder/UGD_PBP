package com.example.ugd3_pbp.api

class UserApi {
    companion object {
        val BASE_URL = "http://192.168.43.54/api/"

        val GET_ALL_URL = BASE_URL + "user/"
        val GET_BY_ID_URL = BASE_URL + "user/"
        val ADD_URL = BASE_URL + "user"
        val UPDATE_URL = BASE_URL + "user/"
        val DELETE_URL = BASE_URL + "user/"
    }
}