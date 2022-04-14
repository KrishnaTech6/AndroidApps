package com.example.todolistmaker

import androidx.room.*

@Dao
interface DAO {
    @Insert
    suspend fun insertTodo(entity: Entity)
    @Update
    suspend fun updateTodo(entity: Entity)
    @Delete
    suspend fun deleteTodo(entity: Entity)

    @Query("Delete from To_Do")
    suspend fun deleteAll()

    @Query("Select * from To_Do")
    suspend fun getTodo():List<Todo>

}