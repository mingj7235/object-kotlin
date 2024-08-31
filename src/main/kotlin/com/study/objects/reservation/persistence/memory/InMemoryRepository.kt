package com.study.objects.reservation.persistence.memory

abstract class InMemoryRepository<T : Any> {
    private var currentId: Long = 1L
    private val entities = mutableListOf<T>()

    protected fun findMany(condition: (T) -> Boolean): List<T> = entities.filter(condition)

    protected fun findOne(condition: (T) -> Boolean): T? = entities.find(condition)

    fun insert(entity: T) {
        setIdIfPossible(entity)
        entities.add(entity)
    }

    private fun setIdIfPossible(entity: T) {
        try {
            val idField = entity::class.java.getDeclaredField("id")
            idField.isAccessible = true
            idField.set(entity, currentId)
            currentId++
        } catch (e: Exception) {
            // 예외 처리
        }
    }
}
