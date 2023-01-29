package br.com.schmidt.composestudy

import br.com.schmidt.modulecomposestudy.R

data class UserProfile constructor(val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf<UserProfile>(
    UserProfile(name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(name = "Alexandre", status = false, drawableId = R.drawable.rick_profile)
)