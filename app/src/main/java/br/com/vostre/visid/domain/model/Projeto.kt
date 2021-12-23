package br.com.vostre.visid.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import org.joda.time.DateTime

@Entity
data class Projeto(

    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    var nome: String = "",
    var descricao: String = "",
    var dataCadastro: DateTime = DateTime.now(),
    var ultimaAlteracao: DateTime = DateTime.now()
)
