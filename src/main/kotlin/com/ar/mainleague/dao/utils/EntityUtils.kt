package com.ar.mainleague.dao.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

open class EntityUtils {
    fun <T> findByIdOrThrow(repository: CrudRepository<T, Long>, id: Long): T {
        return repository.findByIdOrNull(id) ?: throw NoSuchElementException("No se pudo encontrar la entidad con ID $id")
    }

    fun <T> deleteByIdOrThrow(repository: CrudRepository<T, Long>, id: Long) {
        if (!repository.existsById(id)) {
            throw NoSuchElementException("No se pudo encontrar la entidad con ID $id para eliminar")
        }
        repository.deleteById(id)
    }
}