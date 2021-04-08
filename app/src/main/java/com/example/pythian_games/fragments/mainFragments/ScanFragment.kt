package com.example.pythian_games.fragments.mainFragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.pythian_games.R
import com.example.pythian_games.camera.CameraSource
import com.example.pythian_games.camera.CameraSourcePreview
import com.example.pythian_games.camera.OcrDetectorProcessor
import com.example.pythian_games.viewModels.ScanViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.text.TextRecognizer
import com.google.android.material.snackbar.Snackbar
import java.io.*


class ScanFragment : Fragment() {

    companion object {
        fun newInstance() = ScanFragment()
    }

    private lateinit var viewModel: ScanViewModel

    // Intent request code to handle updating play services if needed.
    private val RC_HANDLE_GMS = 9001

    // Permission request codes need to be < 256
    private val RC_HANDLE_CAMERA_PERM = 2

    // Constants used to pass extra data in the intent
    val AutoFocus = "AutoFocus"
    val UseFlash = "UseFlash"
    val TextBlockObject = "String"

    private lateinit var cameraSource: CameraSource
    private lateinit var preview: CameraSourcePreview

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view1 = inflater.inflate(R.layout.fragment_scan, container, false)
        preview = view1.findViewById(R.id.preview)

        return view1
    }

    override fun onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment: Boolean) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
        println("changed!")
        if (this::preview.isInitialized) {
            preview.stop()
            preview.release()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScanViewModel::class.java)
        // TODO: Use the ViewModel

        var autoFocus = true
        var useFlash = false

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        val rc: Int = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        )

        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource(autoFocus, useFlash)
        } else {
            Log.w(
                "ScanFragment",
                "no permission"
            )
            requestCameraPermission()
        }
    }

    override fun onResume() {
        super.onResume()
        startCameraSource()
    }

    override fun onPause() {
        super.onPause()
        Log.w("ScanFragment", "paused")
        preview.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("ScanFragment", "destroyed")
        preview.release()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    private fun createCameraSource(autoFocus: Boolean, useFlash: Boolean) {

        // A text recognizer is created to find text.  An associated multi-processor instance
        // is set to receive the text recognition results, track the text, and maintain
        // graphics for each text block on screen.  The factory is used by the multi-processor to
        // create a separate tracker instance for each text block.
        val textRecognizer = TextRecognizer.Builder(context).build()
        textRecognizer.setProcessor(OcrDetectorProcessor(findNavController()))

        if (!textRecognizer.isOperational) {
            // Note: The first time that an app using a Vision API is installed on a
            // device, GMS will download a native libraries to the device in order to do detection.
            // Usually this completes before the app is run for the first time.  But if that
            // download has not yet completed, then the above call will not detect any text,
            // barcodes, or faces.
            //
            // isOperational() can be used to check if the required native libraries are currently
            // available.  The detectors will automatically become operational once the library
            // downloads complete on device.
            Log.w(
                "ScanFragment",
                "Detector dependencies are not yet available."
            )

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            /*val lowstorageFilter = IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW)
            val hasLowStorage = registerReceiver(null, lowstorageFilter) != null
            if (hasLowStorage) {
                Toast.makeText(this, R.string.low_storage_error, Toast.LENGTH_LONG).show()
                Log.w(
                    com.google.android.gms.samples.vision.ocrreader.OcrCaptureActivity.TAG,
                    getString(R.string.low_storage_error)
                )
            }*/
        }

        // Creates and starts the camera.  Note that this uses a higher resolution in comparison
        // to other detection examples to enable the text recognizer to detect small pieces of text.
        cameraSource = CameraSource.Builder(requireContext().applicationContext, textRecognizer)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1280, 1024)
            .setRequestedFps(10.0f)
            .setFlashMode(if (useFlash) Camera.Parameters.FLASH_MODE_TORCH else null)
            .setFocusMode(if (autoFocus) Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO else null)
            .build()
    }

    @Throws(SecurityException::class)
    private fun startCameraSource() {
        // check that the device has play services available.
        val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
            requireContext().applicationContext
        )
        if (code != ConnectionResult.SUCCESS) {
            val dlg = GoogleApiAvailability.getInstance().getErrorDialog(
                this,
                code,
                RC_HANDLE_GMS
            )
            dlg.show()
        }
        try {
            preview.start(cameraSource)
        } catch (e: IOException) {
            Log.e(
                "ScanFragment",
                "Unable to start camera source.",
                e
            )
            cameraSource.release()
        }
    }

    private fun requestCameraPermission() {
        Log.w(
            "ScanFragment",
            "Camera permission is not granted. Requesting permission"
        )
        val permissions = arrayOf(Manifest.permission.CAMERA)
        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CAMERA
            )
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissions,
                RC_HANDLE_CAMERA_PERM
            )
            return
        }
        val listener = View.OnClickListener {
            ActivityCompat.requestPermissions(
                requireActivity(), permissions,
                RC_HANDLE_CAMERA_PERM
            )
        }
        Snackbar.make(
            preview, "Access to camera is needed",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("OK", listener)
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(
                "ScanFragment",
                "Got unexpected permission result: $requestCode"
            )
            super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
            return
        }
        if (grantResults.size != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(
                "ScanFragment",
                "Camera permission granted - initialize the camera source"
            )
            // we have permission, so create the camerasource
            /*val autoFocus: Boolean = getIntent().getBooleanExtra(
                com.google.android.gms.samples.vision.ocrreader.OcrCaptureActivity.AutoFocus,
                true
            )
            val useFlash: Boolean = getIntent().getBooleanExtra(
                com.google.android.gms.samples.vision.ocrreader.OcrCaptureActivity.UseFlash,
                false
            )*/
            createCameraSource(true, false)
            return
        }
        Log.e(
            "ScanFragment",
            "Permission not granted: results len = " + grantResults.size +
                    " Result code = " + if (grantResults.size > 0) grantResults[0] else "(empty)"
        )
        /*val listener =
            DialogInterface.OnClickListener { dialog, id -> finish() }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Multitracker sample")
            .setMessage(R.string.no_camera_permission)
            .setPositiveButton(R.string.ok, listener)
            .show()*/
    }
}