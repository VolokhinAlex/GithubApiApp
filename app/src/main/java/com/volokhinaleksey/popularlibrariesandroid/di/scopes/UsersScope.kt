package com.volokhinaleksey.popularlibrariesandroid.di.scopes

import javax.inject.Scope

/**
 * The scope that will live all the time while lives the screen with a list of users
 * In the following example [UsersModule]
 * @sample  -   @UsersScope
 *              fun usersRepository()
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UsersScope