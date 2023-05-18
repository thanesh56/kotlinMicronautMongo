package com.thanesh.controller

import com.thanesh.model.Address
import com.thanesh.model.AppUser
import com.thanesh.request.AppUserRequest
import com.thanesh.request.SearchRequest
import com.thanesh.service.AppUserService
import com.thanesh.service.impl.AppUserServiceImpl
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Status
import io.micronaut.http.exceptions.HttpStatusException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/api")
class AppUserController(private val appUserService: AppUserService) {

    @Get("/appUser")
    fun getAllAppUser(): Iterable<AppUser> {
        log.info("Get All AppUsers")
        return appUserService.getAllAppUser()
    }

    @Get("/appUser/{id}")
    fun getAppUser(id: String): AppUser {
        log.info("Get AppUser by id : {}", id)
        try {
            return appUserService.getAppUser(id)
        } catch (e: Exception) {
            throw HttpStatusException(HttpStatus.NOT_FOUND, "User with id: $id is not found!")
        }
    }

    @Post("/appUser")
    @Status(HttpStatus.CREATED)
    fun saveAppUser(@Body appUserRequest: AppUserRequest): AppUser {
        log.info("Get AppUser : {}", appUserRequest)

        return appUserService.insert(
            appUser = appUserRequest.toModel()
        )
    }


    @Put("/appUser/{id}")
    fun updateAppUser(@Body appUserRequest: AppUserRequest, @PathVariable id: String): AppUser {
        log.info("Update AppUser : {} where id : {}", appUserRequest, id)
        return appUserService.update(
            appUser = appUserRequest.toModel(id)
        )


    }

    @Delete("/appUser/{id}")
    @Status(HttpStatus.NO_CONTENT)
    fun deleteAppUser(id: String): Boolean {
        log.info("Delete AppUser where id is : {}", id)
        return appUserService.delete(id)
    }

    //User Finder
    @Post("/search")
    fun search(searchRequest: SearchRequest): List<AppUser> {
        AppUserServiceImpl.log.info("Search AppUser where searchRequest : {}", searchRequest)
        return appUserService.search(searchRequest)
    }

    companion object {
        var log: Logger = LoggerFactory.getLogger(AppUserController::class.java)
    }
}

private fun AppUserRequest.toModel(): AppUser =
    AppUser(
        name = this.name,
        email = this.email,
        address = Address(
            city = this.city,
            state = this.state,
            country = this.country,
            zipcode = this.zipcode
        )
    )

private fun AppUserRequest.toModel(id: String): AppUser =
    AppUser(
        id = id,
        name = this.name,
        email = this.email,
        address = Address(
            city = this.city,
            state = this.state,
            country = this.country,
            zipcode = this.zipcode
        )
    )

