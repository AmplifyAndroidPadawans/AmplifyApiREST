package com.example.amplifyapirest

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify

class AmplifyApiRESTApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)

            Log.i("AmplifyApiRESTApplication", "Initialized Amplify.")
        } catch (e: AmplifyException) {
            Log.e("AmplifyApiRESTApplication", "Could not initialize Amplify", e)
        }
    }

}