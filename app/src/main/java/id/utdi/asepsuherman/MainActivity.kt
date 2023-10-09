package id.utdi.asepsuherman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.utdi.asepsuherman.ui.theme.ArtspaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceTheme {
                // Inisialisasi tema aplikasi menggunakan ArtspaceTheme.
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtSpace()
                }
            }
        }
    }
}

data class ArtPiece(val imageId: Int, val description: String, val title: String)

@Composable
fun ArtSpace() {
    // Menyimpan daftar karya seni yang akan ditampilkan dalam aplikasi.
    val artPieces = remember {
        listOf(
            // Setiap objek ArtPiece berisi gambar, deskripsi, dan judul karya seni.
            ArtPiece(R.drawable.plus_size_casual_flight_jacket_men___navy_blue___3xl_removebg_preview, "Deskripsi karya seni 1.", "Judul Karya Seni 1"),
            ArtPiece(R.drawable.men_jacket_new_fashion_casual_men_coat_solid_pilot_bomber_jacket___gray___usa_m_removebg_preview, "Deskripsi karya seni 2.", "Judul Karya Seni 2"),
            ArtPiece(R.drawable.blouson_militaire_de_la_nasa___gris___4xl_removebg_preview, "Deskripsi karya seni 3.", "Judul Karya Seni 3"),
            ArtPiece(R.drawable.japanese_embroidered_jacket_removebg_preview, "Deskripsi karya seni 4.", "Judul Karya Seni 4")
        )
    }

    // Variabel untuk melacak indeks karya seni saat ini.
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menampilkan gambar karya seni saat ini.
        Image(
            painter = painterResource(id = artPieces[currentIndex].imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(7.dp))
        // Menampilkan judul karya seni dengan teks yang tebal.
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                append(artPieces[currentIndex].title)
            }
        })
        Spacer(modifier = Modifier.height(7.dp))
        // Menampilkan deskripsi karya seni.
        Text(text = artPieces[currentIndex].description)
        Spacer(modifier = Modifier.height(14.dp))
        // Tombol "Previous" dan "Next" untuk mengganti karya seni yang ditampilkan.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0
            ) {
                Text(text = "Previous")
            }
            Button(
                onClick = { if (currentIndex < artPieces.size - 1) currentIndex++ },
                enabled = currentIndex < artPieces.size - 1
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtspaceTheme {
        ArtSpace()
    }
}
