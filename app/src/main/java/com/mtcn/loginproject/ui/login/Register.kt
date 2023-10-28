package com.mtcn.loginproject.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mtcn.loginproject.R
import com.mtcn.loginproject.navigation.Destination
import com.mtcn.loginproject.ui.components.*

@Composable
fun Register(
    navigation: NavController,
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean,
    onClickRegister: (firstName: String, lastName: String, email: String, password: String) -> Unit,
    dismissDialog: () -> Unit,
    imageError: Boolean
) {

    var firstName by rememberSaveable { mutableStateOf(value = "") }
    var lastName by rememberSaveable { mutableStateOf(value = "") }
    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    val isValidate by derivedStateOf { firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && password.isNotBlank() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.height(15.dp))

        ImageLogin(painter = painterResource(id = R.drawable.group))

        Spacer(modifier = modifier.height(15.dp))

        NameOutTextField(
            "First Name",
            textValue = firstName,
            onValueChange = { firstName = it },
            onClickButton = { firstName = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = modifier.height(15.dp))

        NameOutTextField(
            "Last Name",
            textValue = lastName,
            onValueChange = { lastName = it },
            onClickButton = { lastName = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

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
            onclick = { onClickRegister(firstName, lastName, email, password) },
            "REGISTER",
            enabled = isValidate
        )

        Spacer(modifier = Modifier.height(25.dp))


        BottomInfoTextSection(
            onclick = {
                navigation.navigate(Destination.Login.route)
            },
            "Already have an account?",
            "Login"
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
