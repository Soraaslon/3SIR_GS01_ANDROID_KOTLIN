package soraaslon.com.github.gs01.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import soraaslon.com.github.gs01.R
import soraaslon.com.github.gs01.model.EventoModel


class EventoAdapter(
    private val eventos: MutableList<EventoModel>,
    private val onEventoRemoved: (Int) -> Unit
) :
    RecyclerView.Adapter<EventoAdapter.ItemViewHolder>() {

    private var eventosFiltrados: MutableList<EventoModel> = eventos.toMutableList()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nomeLocalTextView: TextView = view.findViewById(R.id.nomeLocalTextView)
        private val tipoEventoTextView: TextView = view.findViewById(R.id.tipoEventoTextView)
        private val grauImpactoTextView: TextView = view.findViewById(R.id.grauImpactoTextView)
        private val dataEventoTextView: TextView = view.findViewById(R.id.dataEventoTextView)
        private val pessoasAfetadasTextView: TextView =
            view.findViewById(R.id.pessoasAfetadasTextView)
        private val removeButton: ImageButton = view.findViewById(R.id.removeButton)

        fun bind(evento: EventoModel) {
            nomeLocalTextView.text = "Nome: ${evento.nomeLocal}"
            tipoEventoTextView.text = "Tipo: ${evento.tipoEvento}"
            grauImpactoTextView.text = "Grau: ${evento.grauImpacto}"
            dataEventoTextView.text = "Data: ${evento.dataEvento}"
            pessoasAfetadasTextView.text = "Pessoas: ${evento.pessoasAfetadas}"
            
            removeButton.setOnClickListener {
                onEventoRemoved(getBindingAdapterPosition())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.evento_layout, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = eventosFiltrados.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = eventosFiltrados[position]
        holder.bind(item)
    }

    fun filtrar(query: String) {
        eventosFiltrados = if (query.isEmpty()) {
            eventos.toMutableList()
        } else {
            eventos.filter {
                it.nomeLocal.contains(query, ignoreCase = true) ||
                        it.tipoEvento.contains(query, ignoreCase = true)
            }.toMutableList()
        }

        notifyDataSetChanged()
    }
}