package com.visioncameratextdetector.textdetectorframeprocessor

import android.graphics.Point
import android.graphics.Rect
import android.media.Image
import android.util.Log
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.mrousavy.camera.frameprocessors.Frame
import com.mrousavy.camera.frameprocessors.FrameProcessorPlugin
import com.mrousavy.camera.frameprocessors.VisionCameraProxy
import java.util.HashMap

class TextDetectorFrameProcessorPlugin(
  proxy: VisionCameraProxy,
  options: Map<String, Any>?
) : FrameProcessorPlugin() {

  private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

  override fun callback(frame: Frame, arguments: Map<String, Any>?): HashMap<String, Any>? {
    val data = WritableNativeMap()

    val mediaImage: Image = frame.image
    val image = InputImage.fromMediaImage(mediaImage, frame.imageProxy.imageInfo.rotationDegrees)

    return try {
      val visionText: Text = Tasks.await(recognizer.process(image))

      if (visionText.text.isEmpty()) {
        @Suppress("UNCHECKED_CAST")
        return WritableNativeMap().toHashMap() as HashMap<String, Any>
      }

      data.putString("text", visionText.text)

      @Suppress("UNCHECKED_CAST")
      data.toHashMap() as HashMap<String, Any>
    } catch (e: Exception) {
      Log.e("TextDetector", "Text recognition error: ${e.localizedMessage}")
      null
    }
  }
}
