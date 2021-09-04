package com.dev.practical.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.dev.practical.R
import com.dev.practical.databinding.ActivityLoginBinding
import com.dev.practical.extra.Keys
import com.dev.practical.extra.Utils
import com.dev.practical.extra.ValidationInputs
import com.dev.practical.firebase.FirebaseReferneces
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.HashMap

class LoginActivity : BaseActivity() {

    // BINDING
    lateinit var binding : ActivityLoginBinding

    // FIREBASE AUTH
    private lateinit var mFirebaseAuth: FirebaseAuth

    // BOOLEAN
    var isPassword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // INIT FIREBASE AUTH
        mFirebaseAuth = FirebaseAuth.getInstance()

        // INIT SIGN UP CLICK
        initSignupClick()

        // INIT PASSWORD ICON CLICK
        initPasswordIconClick()

        // INIT LOGIN CLICK
        initLoginButtonClick()

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

    // INIT SIGN UP CLICK
    private fun initSignupClick(){
        binding.tvSignUp.setOnClickListener {
            val signup = Intent(context, RegisterActivity :: class.java)
            startActivity(signup)
        }
    }

    // INIT LOGIN CLICK
    private fun initLoginButtonClick(){
        binding.cvLogin.setOnClickListener {
            checkValidation()
        }
    }

    // CHECK VALIDATION
    private fun checkValidation(){
        if (binding.edEmail.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_email))
        } else if (!ValidationInputs.isValidEmail(binding.edEmail.text.toString().trim())){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_valid_email))
        } else if (binding.edPassword.text.toString().trim().isEmpty()){
            Utils.showAlertCustom(context, context.resources.getString(R.string.error_empty_password))
        } else {
            setFirebaseLogin(binding.edEmail.text.toString().trim(), binding.edPassword.text.toString().trim())
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
            } else {
                Log.e("Firebase Login", "success")

                FirebaseReferneces.getDatabaseReference(Keys.firebaseUsers).child(sessionManager.userId.toString()).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dialogLoader.hideProgressDialog()
                        val objectMap: Map<String, Any>? =
                            dataSnapshot.value as HashMap<String, Any>?
                        if (objectMap != null) {
                            val id = objectMap[Keys.firebaseUserId].toString()
                            sessionManager.profilePic = objectMap[Keys.firebaseUserProfilePic].toString()
                            sessionManager.isUserLoggedIn = true
                            if (id.contains(java.lang.String.valueOf(sessionManager.userId))) {
                                val main = Intent(context, MainActivity :: class.java)
                                main.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                context.startActivity(main)
                            }
                        }

                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        dialogLoader.hideProgressDialog()
                        Log.e(
                            "Database Error->",
                            databaseError.details
                        )
                    }

                })

            }
        })

    }

}