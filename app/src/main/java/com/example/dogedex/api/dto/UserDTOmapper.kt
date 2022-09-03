package com.example.dogedex.api.dto

import com.example.dogedex.model.User

class UserDTOmapper {
    fun fromUserDTOToUserDomain(userDTO: UserDTO) =
        User(userDTO.id,userDTO.email,userDTO.authenticationToken)

}