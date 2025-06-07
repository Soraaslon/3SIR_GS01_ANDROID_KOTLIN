package soraaslon.com.github.gs01

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import soraaslon.com.github.gs01.model.EventoModel
import soraaslon.com.github.gs01.viewmodel.EventoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var edtLocal: EditText
    private lateinit var edtTipo: EditText
    private lateinit var edtGrau: EditText
    private lateinit var edtData: EditText
    private lateinit var edtPessoas: EditText
    private lateinit var btnIncluir: Button
    private lateinit var btnIdentificacao: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventoAdapter: EventoAdapter

    private val listaEventos = mutableListOf<EventoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Lista de eventos extremos"

        edtLocal = findViewById(R.id.edtLocal)
        edtTipo = findViewById(R.id.edtTipo)
        edtGrau = findViewById(R.id.edtGrau)
        edtData = findViewById(R.id.edtData)
        edtPessoas = findViewById(R.id.edtPessoas)
        btnIncluir = findViewById(R.id.btnIncluir)
        btnIdentificacao = findViewById(R.id.btnIdentificacao)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        eventoAdapter = EventoAdapter(listaEventos) { position ->
            listaEventos.removeAt(position)
            eventoAdapter.filtrar("")
            eventoAdapter.notifyItemRemoved(position)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = eventoAdapter

        btnIncluir.setOnClickListener {
            val local = edtLocal.text.toString().trim()
            val tipo = edtTipo.text.toString().trim()
            val grau = edtGrau.text.toString().trim()
            val data = edtData.text.toString().trim()
            val pessoasStr = edtPessoas.text.toString().trim()

            if (local.isEmpty() || tipo.isEmpty() || grau.isEmpty() || data.isEmpty() || pessoasStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pessoas = pessoasStr.toIntOrNull()
            if (pessoas == null || pessoas <= 0) {
                Toast.makeText(
                    this,
                    "Número de pessoas deve ser maior que zero",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val evento = EventoModel(local, tipo, grau, data, pessoas)
            listaEventos.add(evento)
            eventoAdapter.filtrar("")

            eventoAdapter.notifyItemInserted(listaEventos.size - 1)

            limparCampos()
        }

        btnIdentificacao.setOnClickListener {
            val intent = Intent(this, IdentificacaoActivity::class.java)
            startActivity(intent)
        }

        val edtBusca = findViewById<EditText>(R.id.edtBusca)

        edtBusca.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                eventoAdapter.filtrar(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    private fun limparCampos() {
        edtLocal.text.clear()
        edtTipo.text.clear()
        edtGrau.text.clear()
        edtData.text.clear()
        edtPessoas.text.clear()
    }

}