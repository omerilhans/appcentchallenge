package com.omerilhanli.appcentchallenge.ui.base

/**
 * ViewModelden loosly coupled bir şekilde view'i navigate etmemiz için kullandığımız
 * navigator interface'lerinde bulunması gereken ortak davranışı sağlar.
 */
interface BaseNavigator {
    fun handleError(error: Throwable)
}