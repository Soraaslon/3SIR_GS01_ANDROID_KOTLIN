package soraaslon.com.github.gs01.model

class EventoModel {

    var nomeLocal: String = ""
    var tipoEvento: String = ""
    var grauImpacto: String = ""
    var dataEvento: String = ""
    var pessoasAfetadas: Int = 0

    constructor(
        nomeLocal: String,
        tipoEvento: String,
        grauImpacto: String,
        dataEvento: String,
        pessoasAfetadas: Int
    ) {
        this.nomeLocal = nomeLocal
        this.tipoEvento = tipoEvento
        this.grauImpacto = grauImpacto
        this.dataEvento = dataEvento
        this.pessoasAfetadas = pessoasAfetadas
    }
}