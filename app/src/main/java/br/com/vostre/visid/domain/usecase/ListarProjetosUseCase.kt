package br.com.vostre.visid.domain.usecase

import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListarProjetosUseCase @Inject constructor(
    private val repository: ProjetoRepository
) {

    operator fun invoke(): Flow<List<Projeto>> {
        return repository.listarTodos()
    }

}