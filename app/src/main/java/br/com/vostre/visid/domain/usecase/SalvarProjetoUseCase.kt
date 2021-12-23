package br.com.vostre.visid.domain.usecase

import br.com.vostre.visid.domain.model.Projeto
import br.com.vostre.visid.domain.repository.ProjetoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SalvarProjetoUseCase @Inject constructor(
    private val repository: ProjetoRepository
) {

    operator fun invoke(projeto: Projeto){

        runBlocking {
            repository.salvar(projeto)
        }

    }

}