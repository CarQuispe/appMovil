package com.tuusuario.ucbmobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.tuusuario.ucbmobile.ui.theme.UCBMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UCBMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    MobilePlansScreen(
                        onBack = { finish() },
                        onWhatsAppClick = {
                            val mensaje = "Hola, UCB mobile, preciso su ayuda"
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, mensaje)
                                setPackage("com.whatsapp")
                            }

                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                val intentAlternativo = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_TEXT, mensaje)
                                }
                                context.startActivity(
                                    Intent.createChooser(
                                        intentAlternativo,
                                        "Compartir via"
                                    )
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}