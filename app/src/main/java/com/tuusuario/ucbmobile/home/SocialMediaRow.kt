package fureverlove.ucb

import android.content.Intent
import android.net.Uri

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tuusuario.ucbmobile.R


@Composable
fun SocialMediaRow() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icons_whatsa),
            contentDescription = "WhatsApp",
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/59112345678"))
                    context.startActivity(intent)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.icons_facebook),
            contentDescription = "Facebook",
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/tuPagina"))
                    context.startActivity(intent)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.icons_twitter),
            contentDescription = "Twitter",
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/tuPerfil"))
                    context.startActivity(intent)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.icons_pinterest),
            contentDescription = "Pinterest",
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/tuUsuario"))
                    context.startActivity(intent)
                }
        )
    }
}
