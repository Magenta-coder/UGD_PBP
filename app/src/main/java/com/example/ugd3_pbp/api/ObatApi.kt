package com.example.ugd3_pbp.api

class ObatApi {
    companion object {

    val BASE_URL = "http://192.168.43.53/tubes_pbp/"

    val ADD_URL = UserApi.BASE_URL + "createObat.php"
    val UPDATE_URL = UserApi.BASE_URL + "obat/"
    val DELETE_URL = UserApi.BASE_URL + "obat/"
    val GET_ALL_URL = UserApi.BASE_URL + "obat/"
    val GET_BY_ID_URL = UserApi.BASE_URL + "obat/"
}
}