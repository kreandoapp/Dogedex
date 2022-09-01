package com.example.dogedex.api.dto

import com.example.dogedex.Dog

class DogDTOMapper {

   private fun fromDogDToDogDomain(dogDTO : DogDTO) : Dog {
        return Dog(
            dogDTO.id,dogDTO.index,dogDTO.name,dogDTO.type,dogDTO.heightFemale,
            dogDTO.heightMale,dogDTO.imageUrl,dogDTO.lifeExpectancy,dogDTO.temperament,
            dogDTO.weightFemale,dogDTO.weightMale)
    }

    fun fromDogDTOListToDogDomainList(dogDTOList : List<DogDTO>) : List<Dog>{
        return dogDTOList.map { fromDogDToDogDomain(it) }
    }
}