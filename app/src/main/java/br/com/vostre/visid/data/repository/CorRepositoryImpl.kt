package br.com.vostre.visid.data.repository

import br.com.vostre.visid.data.AppDatabase
import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.CorRepository
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CorRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : CorRepository {
    override fun listarTodosPorProjeto(id: String): Flow<List<Cor>> {
        return db.getCorDao().listarTodosPorProjeto(id)
    }

    override suspend fun carregar(id: String): Cor? {
        return db.getCorDao().carregar(id)
    }

    override suspend fun salvar(cor: Cor) {
        db.getCorDao().salvar(cor)
    }

    override fun editar() {
        TODO("Not yet implemented")
    }

    override fun excluir() {
        TODO("Not yet implemented")
    }
}