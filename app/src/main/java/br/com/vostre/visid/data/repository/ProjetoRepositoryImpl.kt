package br.com.vostre.visid.data.repository

import br.com.vostre.visid.data.AppDatabase
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjetoRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : ProjetoRepository {
    override fun listarTodos(): Flow<List<Projeto>> {
        return db.getProjetoDao().listarTodos()
    }

    override suspend fun carregar(id: String): Projeto? {
        return db.getProjetoDao().carregar(id)
    }

    override suspend fun salvar(projeto: Projeto) {
        db.getProjetoDao().salvar(projeto)
    }

    override fun editar() {
        TODO("Not yet implemented")
    }

    override fun excluir() {
        TODO("Not yet implemented")
    }
}