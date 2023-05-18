package com.thanesh.service.impl

import com.thanesh.model.AppUser
import com.thanesh.repository.AppUserRepository
import com.thanesh.request.SearchRequest
import com.thanesh.service.AppUserService
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
open class AppUserServiceImpl(private val appUserRepository: AppUserRepository) : AppUserService {

    override fun insert(appUser: AppUser): AppUser {
        log.info("Insert AppUser : {}", appUser)
        return appUserRepository.save(appUser)
    }

    override fun update(appUser: AppUser): AppUser {
        log.info("Update AppUser : {}", appUser)
        return appUserRepository.update(appUser)
    }

    override fun getAppUser(id: String): AppUser {
        log.info("Get AppUser  where id : {}", id)
        return appUserRepository.findById(id).orElse(null)
    }

    override fun getAllAppUser(): Iterable<AppUser> {
        log.info("Get All AppUsers")
        return appUserRepository.findAll()
    }

    override fun delete(id: String): Boolean {
        try {
            log.info("Delete AppUser where id: {}", id)
            val appUser = getAppUser(id)
            appUserRepository.delete(appUser)
            return true
        } catch (e: Exception) {
            log.info("Unable to delete AppUser where id: {}", id)
            e.printStackTrace()
            return false
        }
    }

    override fun search(searchRequest: SearchRequest): List<AppUser> {
        return when {
            searchRequest.name != null -> searchByName(searchRequest.name)
            searchRequest.email != null -> searchByEmail(searchRequest.email)
            else -> emptyList()
        }

    }

    private fun searchByEmail(email: String): List<AppUser> {
        return appUserRepository.findByEmail(email)
    }

    private fun searchByName(name: String): List<AppUser> {
       return  appUserRepository.findByNameLike(name)
    }

    companion object {
        var log: Logger = LoggerFactory.getLogger(AppUserServiceImpl::class.java)
    }


}