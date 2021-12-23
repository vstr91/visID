package br.com.vostre.visid.domain.repository

import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto
import kotlinx.coroutines.flow.Flow

interface CorRepository {

    fun listarTodosPorProjeto(id: String) : Flow<List<Cor>>

    suspend fun carregar(id: String) : Cor?

    suspend fun salvar(cor: Cor)

    fun editar()

    fun excluir()

}