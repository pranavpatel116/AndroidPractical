package com.dev.practical.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.dev.practical.R
import com.dev.practical.databinding.ActivityRegisterBinding
import com.dev.practical.extra.Keys
import com.dev.practical.extra.Utils
import com.dev.practical.extra.ValidationInputs
import com.dev.practical.firebase.FirebaseReferneces
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
import java.util.HashMap

class RegisterActivity : BaseActivity() {

    // BINDING
    lateinit var binding : ActivityRegisterBinding

    // FIREBASE AUTH
    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        // INIT FIREBASE AUTH
        mFirebaseAuth = FirebaseAuth.getInstance()

        // INIT REGISTER BUTTON CLICK
        initRegisterButtonClick()
    }

    // INIT REGISTER BUTTON CLICK
    private fun initRegisterButtonClick(){
        binding.cvRegister.setOnClickListener {
            checkValidation()
        }
    }


    // CHECK VALIDATION
    private fun checkValidation(){
        if (binding.edFullName.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_full_name))
        } else if (binding.edEmail.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_email))
        } else if (!ValidationInputs.isValidEmail(binding.edEmail.text.toString().trim())){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_valid_email))
        } else if (binding.edPassword.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_password))
        } else {

        }
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
                                        sessionManager.email)
                                }
                            } else {
                                FirebaseReferneces.createUser(
                                    sessionManager.userId.toString(),
                                    sessionManager.name,
                                    sessionManager.email
                                )
                            }
                            dialogLoader.hideProgressDialog()

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