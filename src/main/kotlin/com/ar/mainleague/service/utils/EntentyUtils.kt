package com.ar.mainleague.service.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

open class EntentyUtils {
    fun <T> findByIdOrThrow(repository: CrudRepository<T, Long>, id: Long): T {
        return repository.findByIdOrNull(id) ?: throw NotFoundException("No se pudo encontrar la entidad con ID $id")
    }

    fun <T> deleteByIdOrThrow(repository: CrudRepository<T, Long>, id: Long) {
        if (!repository.existsById(id)) {
            throw NotFoundException("No se pudo encontrar la entidad con ID $id para eliminar")
        }
        repository.deleteById(id)
    }
}