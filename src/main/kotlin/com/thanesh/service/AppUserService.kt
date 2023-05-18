package com.thanesh.service

import com.thanesh.model.AppUser
import com.thanesh.request.SearchRequest

interface AppUserService {
    fun insert(appUser: AppUser): AppUser

    fun update(appUser: AppUser): AppUser

    fun getAppUser(id: String): AppUser

    fun getAllAppUser(): Iterable<AppUser>

    fun delete(id: String): Boolean

    fun search(searchRequest: SearchRequest): List<AppUser>
}