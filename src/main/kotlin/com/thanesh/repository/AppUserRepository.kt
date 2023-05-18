package com.thanesh.repository

import com.thanesh.model.AppUser
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository(databaseName = "someDb")
interface AppUserRepository : CrudRepository<AppUser, String> {

    @MongoFindQuery("{name: {\$regex: :name, \$options: 'i'}}")
    fun findByNameLike(name: String): List<AppUser>

    fun findByEmail(email: String): List<AppUser>
}