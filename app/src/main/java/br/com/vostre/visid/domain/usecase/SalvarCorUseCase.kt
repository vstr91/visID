package br.com.vostre.visid.domain.usecase

import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.CorRepository
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SalvarCorUseCase @Inject constructor(
    private val repository: CorRepository
) {

    operator fun invoke(cor: Cor){

        runBlocking {
            repository.salvar(cor)
        }

    }

}