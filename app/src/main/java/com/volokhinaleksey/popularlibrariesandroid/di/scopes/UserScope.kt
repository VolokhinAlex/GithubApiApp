package com.volokhinaleksey.popularlibrariesandroid.di.scopes

import javax.inject.Scope

/**
 * The scope that will live all the time while lives the screen with details data of user
 * In the following example [UserModule]
 * @sample  -   @UserScope
 *              fun userRepository()
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope