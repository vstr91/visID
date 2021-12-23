package br.com.vostre.visid.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Cor(

    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    var nome: String = "",
    var cor: String = "000000",
    var projeto: String = ""
)
