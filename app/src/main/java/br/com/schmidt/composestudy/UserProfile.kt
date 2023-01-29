package br.com.schmidt.composestudy

data class UserProfile constructor(val id: Int, val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf<UserProfile>(
    UserProfile(id = 1, name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(id = 2, name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(id = 3, name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(id = 4, name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(id = 5, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 6, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 7, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 8, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 9, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 10, name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(id = 11, name = "Adriano", status = true, drawableId = R.drawable.rick_profile),
    UserProfile(id = 12, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 13, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile),
    UserProfile(id = 14, name = "Alexandre", status = false, drawableId = R.drawable.rick_profile)
)