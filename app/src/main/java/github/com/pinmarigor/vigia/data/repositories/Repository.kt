package github.com.pinmarigor.vigia.data.repositories

interface Repository<T> {
    suspend fun create(item: T)
    suspend fun update(item: T)
    suspend fun delete(id: String)
    suspend fun getById(id: String): T?
    suspend fun getAll(): List<T>
}