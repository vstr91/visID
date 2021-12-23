package br.com.vostre.visid.domain.repository

import br.com.vostre.visid.domain.model.Projeto
import kotlinx.coroutines.flow.Flow

interface ProjetoRepository {

    fun listarTodos() : Flow<List<Projeto>>

    suspend fun carregar(id: String) : Projeto?

    suspend fun salvar(projeto: Projeto)

    fun editar()

    fun excluir()

}