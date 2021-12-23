package br.com.vostre.visid.domain.usecase

import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.CorRepository
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarregarCoresProjetoUseCase @Inject constructor(
    private val repository: CorRepository
) {

    suspend operator fun invoke(id: String): Flow<List<Cor>> {
        return repository.listarTodosPorProjeto(id)
    }

}