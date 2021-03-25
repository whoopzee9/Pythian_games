package com.example.pythian_games.fragments.mainFragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.pythian_games.R
import com.example.pythian_games.viewModels.MainViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ScanFragment : Fragment() {

    companion object {
        fun newInstance() = ScanFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var imageView: ImageView
    private lateinit var outputFileUri: Uri

    private val REQUEST_TAKE_PHOTO = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_scan, container, false)
        imageView = view.findViewById(R.id.imageView2)
        var button = view.findViewById<Button>(R.id.btn_scan)
        button.setOnClickListener {
            onScanButtonClicked(it)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun onScanButtonClicked(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        println(image)
        outputFileUri = FileProvider.getUriForFile(
            requireContext(),
            requireContext().applicationContext.packageName + ".provider",
            image
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        startActivityForResult(intent, REQUEST_TAKE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    val thumbnailBitmap = data.extras?.get("data") as Bitmap
                    // Какие-то действия с миниатюрой
                    imageView.setImageBitmap(thumbnailBitmap)
                }
            } else {
                // Какие-то действия с полноценным изображением,
                // сохранённым по адресу outputFileUri
                imageView.setImageURI(outputFileUri)
            }
        }
    }

}