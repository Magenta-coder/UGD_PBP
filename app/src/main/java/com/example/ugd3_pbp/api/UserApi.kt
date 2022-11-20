package com.example.ugd3_pbp.api

class UserApi {
    companion object {
        val BASE_URL = "http://192.168.43.54/tubes_pbp/"

        val GET_ALL_URL = BASE_URL + "user/"
        val GET_BY_ID_URL = BASE_URL + "user/"
        val ADD_URL = BASE_URL + "createData.php"
        val UPDATE_URL = BASE_URL + "user/"
        val DELETE_URL = BASE_URL + "user/"
    }
}