package br.com.vostre.visid.domain.usecase

import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarregarProjetoUseCase @Inject constructor(
    private val repository: ProjetoRepository
) {

    suspend operator fun invoke(id: String): Projeto? {
        return repository.carregar(id)
    }

}