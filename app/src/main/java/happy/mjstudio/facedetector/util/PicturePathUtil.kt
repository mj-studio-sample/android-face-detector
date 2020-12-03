package happy.mjstudio.facedetector.util

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object PicturePathUtil {
    fun getPictureUri(context: Context, fileName: String, directoryName: String = "sample"): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/$directoryName")
            }
        }

        return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
}