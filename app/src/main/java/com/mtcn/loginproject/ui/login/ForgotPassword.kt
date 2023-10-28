package com.mtcn.loginproject.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mtcn.loginproject.R
import com.mtcn.loginproject.navigation.Destination
import com.mtcn.loginproject.ui.components.BottomInfoTextSection
import com.mtcn.loginproject.ui.components.CustomButton
import com.mtcn.loginproject.ui.components.EmailOutTextField
import com.mtcn.loginproject.ui.components.ErrorImageAuth
import com.mtcn.loginproject.ui.components.ImageLogin
import com.mtcn.loginproject.ui.components.ProgressBarLoading
@Composable
fun ForgotPassword(
    navigation: NavController,
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean,
    onClickForgotPassword: (email: String) -> Unit,
    dismissDialog: () -> Unit,
    imageError: Boolean
) {


    var email by rememberSaveable { mutableStateOf(value = "") }

    val isValidate by derivedStateOf { email.isNotBlank() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.height(20.dp))

        ImageLogin(painter = painterResource(id = R.drawable.forgot_password))

        Spacer(modifier = modifier.height(15.dp))

        Text(
            text = "Trouble logging in?",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        EmailOutTextField(
            textValue = email,
            onValueChange = { email = it },
            onClickButton = { email = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = modifier.height(25.dp))

        CustomButton(
            onclick = { onClickForgotPassword(email) },
            "REGISTER",
            enabled = isValidate
        )

        Spacer(modifier = Modifier.height(25.dp))


        BottomInfoTextSection(
            onclick = {
                navigation.navigate(Destination.Login.route)
            },
            "",
            "Back to login"
        )
    }

    ErrorImageAuth(isImageValidate = imageError,
        onDismissRequest = { dismissDialog() },
        onConfirmation = { dismissDialog() },
        painter = painterResource(id = R.drawable.warning),
        "ErrorImage",
        "Sorry, Your Registration Couldn't Be Completed!"
    )

    ProgressBarLoading(isLoading = loadingProgressBar)
}
