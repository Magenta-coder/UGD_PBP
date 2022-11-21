package com.example.ugd3_pbp.api

class UserApi {
    companion object {
        val BASE_URL = "http://192.168.43.54/tubes_pbp/"

        val ADD_URL = BASE_URL + "createData.php"
        val UPDATE_URL = BASE_URL + "user/"
        val LOGIN_URL = BASE_URL + "checkLogin.php"
        val CHECK_LOGIN_URL = BASE_URL + "checkUser.php"
    }
}