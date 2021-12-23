package br.com.vostre.visid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.vostre.visid.domain.model.Projeto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjetoDao {

    @Query("SELECT * FROM projeto ORDER BY dataCadastro")
    fun listarTodos() : Flow<List<Projeto>>

    @Query("SELECT * FROM projeto WHERE id = :id")
    suspend fun carregar(id: String) : Projeto?

    @Insert(onConflict = REPLACE)
    suspend fun salvar(projeto: Projeto)



}