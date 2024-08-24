package com.boa.rickandmortytest.domain.base

/**
 * Base Mapper with standard behaviour. Must be specified class to convert and result class types.
 */
abstract class BaseMapper<I, O> {
    abstract fun map(input: I): O

    fun mapAll(input: Collection<I>?): List<O> =
        input?.mapNotNull { map(it) }?.distinct() ?: emptyList()
}