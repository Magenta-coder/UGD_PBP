package com.example.ugd3_pbp.entity

class obat (var name: String, var jumlah: Int) {

    companion object{
        @JvmField
        var listofObat = arrayOf(
            obat("Paracetamol", 100),
            obat("Acarbose", 200),
            obat("Hydrocodone", 100),
            obat("Lisinopril", 150),
            obat("metformin", 230),
            obat("Hydrocholorothiazide", 120),
            obat("Amoxicillin", 300),
            obat("Prilosec", 120),
            obat("Antimo", 140),
            obat("Prinivil", 160)
        )
    }

}