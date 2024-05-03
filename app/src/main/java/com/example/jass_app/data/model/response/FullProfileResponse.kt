package com.example.jass_app.data.model.response

class FullProfileResponse {
    var id: Int = 0
    var userName: String = ""
    var personal_info: FullPersonalInfo? = null
    var profile_visibility: String = ""
    var avatar: String? = null
    var images: MutableList<String>? = null
    var friends_status: String = ""
    var friends_ids: MutableList<Int>? = null
}