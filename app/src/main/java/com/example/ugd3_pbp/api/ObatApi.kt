package com.example.ugd3_pbp.api

class ObatApi {
    companion object {

    val BASE_URL = "http://192.168.43.54/tubes_pbp/"

    val ADD_URL = UserApi.BASE_URL + "createData.php"
    val UPDATE_URL = UserApi.BASE_URL + "obat/"
    val LOGIN_URL = UserApi.BASE_URL + "checkLogin.php"
    val DELETE_URL = UserApi.BASE_URL + "obat/"
    val GET_ALL_URL = UserApi.BASE_URL + "obat/"
    val GET_BY_ID_URL = UserApi.BASE_URL + "obat/"
}
}