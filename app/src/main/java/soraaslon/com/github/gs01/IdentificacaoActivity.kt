package soraaslon.com.github.gs01

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class IdentificacaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.identificacao_layout)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}