package br.com.vostre.visid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.vostre.visid.domain.model.Cor
import br.com.vostre.visid.domain.model.Projeto
import kotlinx.coroutines.flow.Flow

@Dao
interface CorDao {

    @Query("SELECT * FROM cor WHERE projeto = :id ORDER BY nome")
    fun listarTodosPorProjeto(id: String) : Flow<List<Cor>>

    @Query("SELECT * FROM cor WHERE id = :id")
    suspend fun carregar(id: String) : Cor?

    @Insert(onConflict = REPLACE)
    suspend fun salvar(cor: Cor)



}