package com.example.pythian_games.camera

import android.util.Log
import android.util.SparseArray
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.pythian_games.R
import com.example.pythian_games.fragments.mainFragments.CardFragment
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import kotlinx.android.synthetic.main.fragment_card.view.*

class OcrDetectorProcessor(val nav: NavController) : Detector.Processor<TextBlock> {
    override fun release() {
        println("released")
    }

    override fun receiveDetections(detections: Detector.Detections<TextBlock>?) {
        val items: SparseArray<TextBlock> = detections!!.detectedItems
        for (i in 0 until items.size()) {
            val item = items.valueAt(i)
            if (item != null && item.value != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.value)
                if (item.value.contains('#')) {
                    //TODO передать номер в новый фрагмент
                    println("---------------${item.value}")
                    val bundle = bundleOf("cardNum" to item.value)
                    nav.navigate(R.id.CardFragment, bundle)
                }
                //val graphic = OcrGraphic(graphicOverlay, item)
                //graphicOverlay.add(graphic)
            }
        }
    }
}