package com.example.ugd3_pbp.entity

import com.example.ugd3_pbp.R

class obat (var name:String, var harga:Int,var gambar:Int)
{
    companion object{
        @JvmField
        var listOfObat = arrayOf(
            obat("Remdesivir",15000, R.drawable.remdesivir),
            obat("Alvescco",31000, R.drawable.inhaler),
            obat("Chloroquine",21000, R.drawable.chloroquine),
            obat("Entrostop",31000, R.drawable.entrostop),
            obat("Ivermectin",17000, R.drawable.ivermectin),
            obat("Komix",9000, R.drawable.komix),
            obat("Panadol",12000, R.drawable.panadol),
            obat("Salonpas",11000, R.drawable.salonpas),
            obat("Tolak Angin",7000, R.drawable.tolakangin),
            obat("CDR",11500,R.drawable.cdr)

        )
    }
}