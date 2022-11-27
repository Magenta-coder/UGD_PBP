package com.example.ugd3_pbp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ugd3_pbp.databinding.ActivityPdfactivityBinding
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.BarcodeQRCode
import com.itextpdf.text.pdf.PdfDocument
import com.itextpdf.text.pdf.PdfWriter

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class PDFActivity : AppCompatActivity() {
    private var binding: ActivityPdfactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityPdfactivityBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)


        binding!!.buttonSave.setOnClickListener {
            val nama = binding!!.editTextName.text.toString()
            val umur = binding!!.editTextUmur.text.toString()
            val tlp = binding!!.editTextHp.text.toString()
            val alamat = binding!!.editTextAlamat.text.toString()
            val obat = binding!!.editTextObat.text.toString()

            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (nama.isEmpty() && umur.isEmpty() && tlp.isEmpty() && alamat.isEmpty() && obat.isEmpty()) {
                        Toast.makeText(
                            applicationContext,
                            "Semuanya tidak boleh kosong",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
//                        createPdf(nama, umur, tlp, alamat, obat)
                    }
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }


        }
    }

   @SuppressLint("ObsoleteSdkInt")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Throws(FileNotFoundException::class)
   
   private fun createPdf(nama: String, umur: String, tlp: String, alamat: String, obat: String) {
        val pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
        val file = File(pdfPath, "Data_Customer_Apotek.pdf")
        FileOutputStream(file)

        val writer = PdfWriter(file)
        val pdfDocument = PdfDocument(writer)
        val document = Document(pdfDocument)
        document.setMargins(5f, 5f, 5f, 5f)
        @SuppressLint("UseCompatLoadingForDrawables") val d = getDrawable(R.drawable.heart)

        val bitmap = (d as BitmapDrawable?)!!.bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val bitmapdata = stream.toByteArray()
        val imageData = ImageDataFactor.create(bitmapdata)
        val image = Image(imageData)
        val namapengguna = Paragraph("Identitas Pengguna").alignment
        val group = Paragraph (
            """
                Berikut adalah
                Nama Pengguna Aplikasi Apoteker
            """.trimIndent()).setTextAlignment(TextAlignment.CENTER).setFontSize(12f)

       val width = floatArrayOf(100f, 100f)
        val table = Table(width)

        table.setHorizontalAlignment(HorizontalAlignment.CENTER)
        table.addCell(Cell().add(Paragraph("Nama Diri")))
        table.addCell(Cell().add(Paragraph(nama)))
        table.addCell(Cell().add(Paragraph("Umur")))
        table.addCell(Cell().add(Paragraph(umur)))
        table.addCell(Cell().add(Paragraph("No  Telepon")))
        table.addCell(Cell().add(Paragraph(tlp)))
        table.addCell(Cell().add(Paragraph("Alamat Domisili")))
        table.addCell(Cell().add(Paragraph(alamat)))
        table.addCell(Cell().add(Paragraph("Nama Obat")))
        table.addCell(Cell().add(Paragraph(obat)))

        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        table.addCell(Cell().add(Paragraph("Tanggal Buat PDF")))
        table.addCell(Cell().add(Paragraph(LocalDate.now().format(dateTimeFormatter))))
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a")
        table.addCell(Cell().add(Paragraph("Pukul Pembuatan ")))
        table.addCell(Cell().add(Paragraph(LocalTime.now().format(timeFormatter))))

        val barcodeQRCode = BarcodeQRCode("""
            $nama
            $umur
            $tlp
            $alamat
            $obat
            ${LocalDate.now().format(dateTimeFormatter)}
            ${LocalTime.now().format(timeFormatter)}
        """.trimIndent())

        val qrCodeObject = barcodeQRCode.createFormXObject(ColorConstants.BLACK, pdfDocument)
        val qrCodeImage = Image(qrCodeObject).setWidth(80f).setHorizontalAlignment(HorizontalAlignment.CENTER)
        document.add(image)
        document.add(namapengguna)
        document.add(group)
        document.add(table)
        document.add(qrCodeImage)
        document.close()
        Toast.makeText(applicationContext, "PDF Created", Toast.LENGTH_SHORT).show()
    }

}