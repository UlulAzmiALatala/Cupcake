package ulul.azmi.a.latala.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ulul.azmi.a.latala.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Mengaktifkan tampilan edge-to-edge, sehingga aplikasi menggunakan seluruh layar termasuk area di sekitar notch dan status bar.
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            // Menyediakan tema khusus "CupcakeTheme" untuk tampilan aplikasi.
            CupcakeTheme {
                // Memanggil komponen utama aplikasi yang mendefinisikan UI utama.
                CupcakeApp()
            }
        }
    }
}
