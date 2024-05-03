package com.example.jass_app.data.model

class MyProfile {
    var id: Int = 0
    var userEmail = ""
    var userName: String = ""
    var personal_info: MyPersonalInfo? = null
    var avatar: String? = null
    var images: MutableList<String> = mutableListOf()
    var profile_settings: MyProfileSettings? = null
    var friendsIds: MutableList<Int> = mutableListOf()

    var my_friend_inviting: MutableList<Int> = mutableListOf()
    var friend_inviting_me: MutableList<Int> = mutableListOf()
}