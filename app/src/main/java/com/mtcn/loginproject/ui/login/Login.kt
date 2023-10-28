package com.mtcn.loginproject.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mtcn.loginproject.R
import com.mtcn.loginproject.navigation.Destination
import com.mtcn.loginproject.ui.components.BottomInfoTextSection
import com.mtcn.loginproject.ui.components.CustomButton
import com.mtcn.loginproject.ui.components.EmailOutTextField
import com.mtcn.loginproject.ui.components.ErrorImageAuth
import com.mtcn.loginproject.ui.components.ImageLogin
import com.mtcn.loginproject.ui.components.PasswordOutTextField
import com.mtcn.loginproject.ui.components.ProgressBarLoading
import com.mtcn.loginproject.ui.components.SocialMediaIcons


private var toast: Toast? = null

@Composable
fun Login(
    navigation: NavController,
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean,
    onclickLogin: (email: String, password: String) -> Unit,
    dismissDialog: () -> Unit,
    imageError: Boolean
) {
    val context = LocalContext.current
    LoginPage(navigation,modifier,loadingProgressBar,onclickLogin,dismissDialog,imageError, context)
}

@Composable
fun LoginPage(
    navigation: NavController,
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean,
    onclickLogin: (email: String, password: String) -> Unit,
    dismissDialog: () -> Unit,
    imageError: Boolean,
    context: Context
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val isValidate by derivedStateOf { email.isNotBlank() && password.isNotBlank() }
    val focusManager = LocalFocusManager.current



    // Initialize/open an instance of EncryptedSharedPreferences on below line.
    val sharedPreferences = remember {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        EncryptedSharedPreferences.create(
            "preferences",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    LaunchedEffect(Unit) {
        val nameStr = sharedPreferences.getString("email", null)
        val passwordStr = sharedPreferences.getString("password", null)

        if (nameStr != null && passwordStr != null) {
            email = nameStr
            password = passwordStr
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.height(15.dp))

        ImageLogin(painter = painterResource(id = R.drawable.user))

        Spacer(modifier = modifier.height(15.dp))

        EmailOutTextField(
            textValue = email,
            onValueChange = { email = it },
            onClickButton = { email = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = modifier.height(15.dp))

        PasswordOutTextField(
            textValue = password,
            onValueChange = { password = it },
            onDone = {
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = modifier.height(25.dp))

        CustomButton(
            onclick = {
                        // on below line we are setting data in our shared preferences.
                        // creating a master key for encryption of shared preferences.
                        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

                        // Initialize/open an instance of EncryptedSharedPreferences on below line.
                        val sharedPreferences = EncryptedSharedPreferences.create(
                            // passing a file name to share a preferences
                            "preferences",
                            masterKeyAlias,
                            context,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                        )
                        // on below line we are storing data in shared preferences file.
                        sharedPreferences.edit().putString("email", email).apply()
                        sharedPreferences.edit().putString("password", password).apply()
                        // A toast is shown for user reference that the text is
                        // copied to the clipboard
                        Toast.makeText(context, "Saved Data.." + email + password, Toast.LENGTH_SHORT).show()

                        onclickLogin(email, password)
                      },
            "LOGIN",
            enabled = isValidate
        )

        Spacer(modifier = Modifier.height(25.dp))

        ForgotPassword(
            onclick = {
                showToastMessage(context, "Forgot Password Clicked")
            },
            "Forgot Password?"
        )

        Spacer(modifier = modifier.height(25.dp))

        SocialMediaIcons(onclickFacebook = {
            showToastMessage(context, "Facebook Clicked")
        }, onclickLinkedin = {
            showToastMessage(context, "Linkedin Clicked")
        }, onclickGoogle = {
            showToastMessage(context, "Google Clicked")
        })

        Spacer(modifier = modifier.height(25.dp))

        BottomInfoTextSection(
            onclick = {
                navigation.navigate(Destination.Register.route)
            },
            "New user?",
            "Create Account"
        )
    }


    ErrorImageAuth(isImageValidate = imageError,
        onDismissRequest = { dismissDialog() },
        onConfirmation = { dismissDialog() },
        painter = painterResource(id = R.drawable.warning),
        "ErrorImage",
        "Email or Password is wrong!"
    )

    ProgressBarLoading(isLoading = loadingProgressBar)
}

@Composable
fun ForgotPassword(
    onclick: () -> Unit,
    text: String,
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            ),
            modifier = Modifier.clickable {
                onclick()
            }
        )
    }
}



fun showToastMessage(context: Context, message: String) {
    // Cancel the previous toast if it's showing
    toast?.cancel()

    // Create and show the new toast
    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast?.show()
}