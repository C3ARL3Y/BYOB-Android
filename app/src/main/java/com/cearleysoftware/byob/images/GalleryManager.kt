package com.cearleysoftware.byob.images

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.cearleysoftware.byob.constants.Constants
import com.cearleysoftware.byob.extensions.isPermissionGranted
import com.cearleysoftware.byob.extensions.requestPermission

class GalleryManager(private val context: Application) {

    private var onHasExternalStoragePermission: (() -> Unit)? = null
    private var imageResult: ((String, Uri) -> Unit)? = null

    fun startGallery(activity: Activity, onGalleryResult: (String, Uri) -> Unit){
        activity as AppCompatActivity
        hasExternalStoragePermission(activity){
            start(activity, onGalleryResult)
        }
    }

    private fun hasExternalStoragePermission(activity: AppCompatActivity, onSuccess: () -> Unit) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            if (activity.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)){
                onSuccess()
            }
            else{
                onHasExternalStoragePermission = onSuccess
                activity.requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Constants.READ_EXTERNAL_STORAGE_REQUEST_CODE)
            }
        }
        else {
            onSuccess()
        }
    }

    private fun start(activity: Activity, onGalleryResult: (String, Uri) -> Unit){
        imageResult = onGalleryResult
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
        }
    }

    fun onRequestPermissionsResult(grantResults: IntArray) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            onHasExternalStoragePermission?.invoke()
            onHasExternalStoragePermission = null
        }
    }

    fun onActivityResult(resultCode: Int, result: Intent?){
        val data = result?.data
        if (data != null && resultCode == Activity.RESULT_OK) {
            val path = pathFromGalleryResult(data)
            if (path != null) {
                imageResult?.invoke(path, data)
                imageResult = null
            }
            else{
               // LoggingManager.logException("Path from result is null")
            }
        }
    }

    private fun pathFromGalleryResult(selectedImageUri: Uri): String?{
        var mSelectedImagePath = getPath(selectedImageUri)
        if (mSelectedImagePath == null) {
            var wholeID: String
            try {
                wholeID = DocumentsContract
                        .getDocumentId(selectedImageUri)
                if (wholeID != null && wholeID.contains(":")) {
                    wholeID = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
                return null
            }


            val column = arrayOf(MediaStore.Images.Media.DATA)

            // where id is equal to
            val sel = MediaStore.Images.Media._ID + "=?"
            val cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, arrayOf<String>(wholeID), null)
            val columnIndex = cursor.getColumnIndex(column[0])

            if (cursor.moveToFirst()) {
                mSelectedImagePath = cursor.getString(columnIndex)
                cursor.close()
                return mSelectedImagePath
            }
            cursor.close()
        }
        return mSelectedImagePath
    }

    private fun getPath(uri: Uri?): String? {
        if (uri == null) {
            return null
        }
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            val columnIndex = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val path = cursor.getString(columnIndex)
            cursor.close()
            return path
        }
        return uri.path
    }

    fun onDestroy() {
        onHasExternalStoragePermission = null
        imageResult = null
    }

}