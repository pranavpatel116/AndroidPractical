package com.dev.practical.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.dev.practical.R
import com.dev.practical.databinding.ActivityRegisterBinding
import com.dev.practical.extra.GetPath
import com.dev.practical.extra.Keys
import com.dev.practical.extra.Utils
import com.dev.practical.extra.ValidationInputs
import com.dev.practical.firebase.FirebaseReferneces
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class RegisterActivity : BaseActivity() {

    // BINDING
    lateinit var binding : ActivityRegisterBinding

    // FIREBASE AUTH
    private lateinit var mFirebaseAuth: FirebaseAuth

    // ARRAY LIST
    var emailList : ArrayList<String> = ArrayList()

    // BOOLEAN
    var isPassword: Boolean = false

    // INTEGER
    var request: Int = 0

    // APP PERMISSIONS
    private val permit = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    // STRING
    var uploadedImageUrl : String = ""

    // URI
    var uploadedImageUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        // INIT FIREBASE AUTH
        mFirebaseAuth = FirebaseAuth.getInstance()

        // INIT REGISTER BUTTON CLICK
        initRegisterButtonClick()

        // INIT PASSWORD ICON CLICK
        initPasswordIconClick()

        // REQUEST PERMISSIONS
        requestPermissions()
    }

    // REQUEST PERMISSIONS
    private fun requestPermissions(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permit, request)
        } else {
            initAddImage()
        }
    }

    // INIT ADD IMAGE
    private fun initAddImage(){
        binding.ivEdit.setOnClickListener {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permit, request)
            } else {
                selectProfile()
            }
        }
    }

    // IMAGE SELECTION POPUP
    private fun selectProfile() {
        val items = arrayOf<String>(
            getString(R.string.take_picture),
            getString(R.string.open_gallery),
            getString(R.string.cancel)
        )
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.select_picture))
        builder.setItems(items, DialogInterface.OnClickListener { dialog, item ->
            val result = Utils.checkPermission(context)
            if (item == 0) {
                if (result) {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                        cameraIntent()
                    } else {
                        openCameraIntent(this)
                    }

                }
            } else if (item == 1) {
                if (result) {
                    galleryIntent()
                }
            } else if (item == 2) {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    // OPEN CAMERA INTENT
    private fun cameraIntent() {
        requestedOrientation = resources.configuration.orientation
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Keys.REQUEST_CAMERA)
    }

    // OPEN GALLERY INTENT
    private fun galleryIntent() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, Keys.SELECT_FILE)
    }

    // OPEN CAMERA INTENT FOR NEWER OS
    fun openCameraIntent(context: Context) {
        val pictureIntent = Intent(
            MediaStore.ACTION_IMAGE_CAPTURE
        )
        if (pictureIntent.resolveActivity(context.packageManager) != null) {
            //Create a file to store the image
            var photoFile: File? = null
            try {
                photoFile = Utils.createImageFile(context)
            } catch (ex: IOException) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    context,
                    context.packageName + ".provider",
                    photoFile
                )
                pictureIntent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    photoURI
                )
                startActivityForResult(pictureIntent, Utils.REQUEST_CAPTURE_IMAGE)
            }
        }
    }

    // GET GALLERY IMAGE RESULT
    private fun onSelectFromGalleryResult(data: Intent) {
        var bm: Bitmap? = null
        try {
            try {
                bm =
                    Utils.decodeSampledBitmapFromFile(
                        Utils.getRealPathFromURI(data.data, context),
                        200,
                        200
                    )

                binding.ivUserProfile.setImageBitmap(bm)

            } catch (e: IOException) {
                e.printStackTrace()
            }
            val compressBitmap = compressImage(bm!!)
            val tempUri: Uri = Utils.getImageUri(context, compressBitmap!!)!!
            val s = getRealPathFromURI(tempUri, context)

            uploadedImageUrl = s!!
            uploadedImageUri = tempUri
            binding.ivUserProfile.setImageBitmap(compressBitmap)

        } catch (e: IOException) {
            Log.e("OnSelectGallery->", e.printStackTrace().toString())
        } catch (e: Exception) {
            Log.e("OnSelectGallery->", e.printStackTrace().toString())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //val permissionLocation = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
        if (grantResults.isNotEmpty()) {
            if (requestCode == request) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestPermissions()
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(context, resources.getString(R.string.denied_permission), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // GET CAMERA CAPTURE RESULT
    private fun onCaptureImageResult(data: Intent) {
        val thumbnail = Utils.getRotateImage(data.extras!!.get("data").toString(), data.extras!!.get("data") as Bitmap)
        val bytes = ByteArrayOutputStream()
        binding.ivUserProfile.setImageBitmap(thumbnail)

        thumbnail!!.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val compressBitmap = compressImage(thumbnail)
        val tempUri = Utils.getImageUri(context, compressBitmap!!)
        val s = Utils.getRealPathFromURI(tempUri, context)
        uploadedImageUrl = s!!
        uploadedImageUri = tempUri!!
        binding.ivUserProfile.setImageBitmap(compressBitmap)
    }

    private fun compressImage(image: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        image.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            baos
        ) //Compression quality, here 100 means no compression, the storage of compressed data to baos
        var options = 90
        while (baos.toByteArray().size / 1024 > 1000) {  //Loop if compressed picture is greater than 400kb, than to compression
            baos.reset() //Reset baos is empty baos
            image.compress(
                Bitmap.CompressFormat.JPEG,
                options,
                baos
            ) //The compression options%, storing the compressed data to the baos
            options -= 10 //Every time reduced by 10
        }
        val isBm = ByteArrayInputStream(baos.toByteArray()) //The storage of compressed data in the baos to ByteArrayInputStream
        return BitmapFactory.decodeStream(isBm, null, null)
    }

    // GET IMAGE PATH
    @SuppressLint("Recycle")
    fun getRealPathFromURI(
        uri: Uri?,
        context: Context
    ): String? {
        var idx = 0
        var cursor: Cursor? = null
        try {
            cursor = context.contentResolver.query(uri!!, null, null, null, null)
            if (cursor != null) cursor.moveToFirst()
            idx = cursor!!.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.e("RealPathFromURI->", e.message!!)
        }
        return if (cursor != null) cursor.getString(idx) else ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Keys.REQUEST_CAMERA || requestCode == Keys.SELECT_FILE) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
                    if (resultCode == Activity.RESULT_OK) {
                        if (requestCode == Keys.SELECT_FILE) {
                            if (data != null) {
                                onSelectFromGalleryResult(data)
                            }
                        } else if (requestCode == Keys.REQUEST_CAMERA) {
                            if (data != null) {
                                onCaptureImageResult(data)
                            }
                        }
                    }
                } else {
                    if (requestCode == Keys.REQUEST_CAMERA) {
                        val imagePath = Utils.imageFilePath

                        val bmOptions = BitmapFactory.Options()
                        bmOptions.inJustDecodeBounds = true
                        BitmapFactory.decodeFile(imagePath, bmOptions)
                        val photoW = bmOptions.outWidth
                        val photoH = bmOptions.outHeight

                        // Determine how much to scale down the image
                        val scaleFactor = Math.min(photoW / 100, photoH / 100)

                        // Decode the image file into a Bitmap sized to fill the View
                        bmOptions.inJustDecodeBounds = false
                        bmOptions.inSampleSize = scaleFactor
                        bmOptions.inPurgeable = true

                        val bm = BitmapFactory.decodeFile(imagePath, BitmapFactory.Options())
                        val bitmap = Utils.getRotateImage(imagePath, bm)

                        try {
                            uploadedImageUrl = Utils.saveImage(bitmap!!, context)!!
                            val tempUri: Uri = Utils.getImageUri(this, bitmap)!!
                            uploadedImageUri = tempUri
                            binding.ivUserProfile.setImageBitmap(bitmap)
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }
                    } else if (requestCode == Keys.SELECT_FILE) {
                        if (data != null) {
                            val contentURI: Uri = data.data!!
                            try {
                                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, contentURI)
                                val path: String = GetPath.getPath(context, contentURI)!!
                                binding.ivUserProfile.setImageBitmap(bitmap)
                                uploadedImageUrl = Utils.saveImage(bitmap!!, context)!!
                                val tempUri: Uri = Utils.getImageUri(this, bitmap)!!
                                uploadedImageUri = tempUri
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }


    fun uploadImage(path: Uri?) {
        try {
            uploadedImageUrl = "uploading"
            val storage = FirebaseStorage.getInstance()
            val storageReference = storage.reference
            if (path != null) {

                val ref: StorageReference = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(path)
                    .addOnSuccessListener { taskSnapshot ->
                        ref.getDownloadUrl()
                            .addOnSuccessListener(OnSuccessListener<Uri> { downloadPhotoUrl -> //Now play with downloadPhotoUrl
                                //Store data into Firebase Realtime Database
                                uploadedImageUrl = downloadPhotoUrl.toString()
                                sessionManager.profilePic = uploadedImageUrl
                                //FirebaseReferneces.updateImageUrlInUser(sessionManager.userId.toString(), uploadedImageUrl)
                            })
                    }
                    .addOnFailureListener {

                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress: Double = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    // INIT REGISTER BUTTON CLICK
    private fun initRegisterButtonClick(){
        binding.cvRegister.setOnClickListener {
            checkValidation()
        }
    }

    // INIT PASSWORD ICON CLICK
    private fun initPasswordIconClick(){
        binding.ivPassword.setOnClickListener {
            if (isPassword){
                isPassword = false
                binding.ivPassword.setImageResource(R.drawable.ic_visible_password)
                binding.edPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.edPassword.setSelection(binding.edPassword.text.toString().trim().length)
            } else {
                isPassword = true
                binding.ivPassword.setImageResource(R.drawable.ic_invisible_password)
                binding.edPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.edPassword.setSelection(binding.edPassword.text.toString().trim().length)
            }
        }

    }

    // CHECK VALIDATION
    private fun checkValidation(){
        if (uploadedImageUri == null){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_profile))
        } else if (binding.edFullName.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_full_name))
        } else if (binding.edEmail.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_email))
        } else if (!ValidationInputs.isValidEmail(binding.edEmail.text.toString().trim())){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_valid_email))
        } else if (binding.edPassword.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_password))
        } else {
            isUsersExists()
        }
    }

    // CHECK FIREBASE DATA AVIALABLE
    private fun isUsersExists(){
        FirebaseReferneces.getDatabaseReference(Keys.firebaseUsers).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    if (snapshot.value is ArrayList<*>) {
                        val objectMap: ArrayList<Map<String, Any>?> = snapshot.value as ArrayList<Map<String, Any>?>
                        var userId : String = ""
                        for (i in 0 until objectMap.size) {
                            if (objectMap[i] != null) {
                                userId = objectMap[i]!!.getValue(Keys.firebaseUserId) as String
                                if (objectMap[i]!!.containsKey(Keys.firebaseUserEmail)){
                                    val email = objectMap[i]!!.getValue(Keys.firebaseUserEmail) as String
                                    emailList.add(email)
                                }
                            }
                        }
                        if (emailList.size > 0){
                            if (emailList.contains(binding.edEmail.text.toString().trim())){
                                Utils.showAlertCustom(context, context.resources.getString(R.string.email_exists))
                            } else {
                                sessionManager.userId = userId.toInt() + 1
                                sessionManager.name = binding.edFullName.text.toString().trim()
                                sessionManager.email = binding.edEmail.text.toString().trim()
                                sessionManager.password = binding.edPassword.text.toString().trim()
                                if (uploadedImageUri != null){
                                    uploadImage(uploadedImageUri)
                                }
                                setFirebaseLogin(binding.edEmail.text.toString().trim(), binding.edPassword.text.toString().trim())
                            }
                        }
                    } else {
                        sessionManager.userId = 0
                        sessionManager.name = binding.edFullName.text.toString().trim()
                        sessionManager.email = binding.edEmail.text.toString().trim()
                        sessionManager.password = binding.edPassword.text.toString().trim()
                        if (uploadedImageUri != null){
                            uploadImage(uploadedImageUri)
                        }
                        setFirebaseLogin(binding.edEmail.text.toString().trim(), binding.edPassword.text.toString().trim())

                    }
                } catch (e : Exception){
                    e.printStackTrace()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(
                    "Database Error->",
                    error.details
                )
            }

        })
    }

    private fun setFirebaseLogin(
        email : String,
        password : String
    ) {
        dialogLoader.showProgressDialog()

            mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {
                if (!it.isSuccessful) {
                    Log.e("Firebase Login", "failed")
                    setFirebaseRegistration(email, password)
                } else {
                    Log.e("Firebase Login", "success")

                    FirebaseReferneces.getDatabaseReference(Keys.firebaseUsers).child(sessionManager.userId.toString()).addListenerForSingleValueEvent(object :
                        ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val objectMap: Map<String, Any>? =
                                dataSnapshot.value as HashMap<String, Any>?
                            if (objectMap != null) {
                                val id = objectMap[Keys.firebaseUserId].toString()
                                if (id.contains(java.lang.String.valueOf(sessionManager.userId))) {
                                    FirebaseReferneces.updateUserDetails(
                                        sessionManager.userId.toString(),
                                        sessionManager.name,
                                        sessionManager.email,
                                        sessionManager.profilePic)
                                }
                            } else {
                                FirebaseReferneces.createUser(
                                    sessionManager.userId.toString(),
                                    sessionManager.name,
                                    sessionManager.email,
                                    sessionManager.profilePic)
                            }
                            dialogLoader.hideProgressDialog()
                            finish()
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.e(
                                "Database Error->",
                                databaseError.details
                            )
                        }

                    })

                }
            })

    }


    private fun setFirebaseRegistration(
       email : String,
       password : String

    ) {
        mFirebaseAuth!!.createUserWithEmailAndPassword(
            email, password
        )
            .addOnFailureListener { e -> e.printStackTrace() }
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    setFirebaseLogin(email, password)
                } else {
                    Log.e("ERROR", "")
                }
            }
    }
}