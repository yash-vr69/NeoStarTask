package com.example.neosofttask.utils

enum class Education {
    SELECT,
    PRIMARY_SCHOOL,
    SECONDARY_SCHOOL,
    HIGH_SCHOOL,
    INTERMEDIATE,
    DIPLOMA,
    BACHELOR,
    MASTER,
    DOCTORATE,
    POST_DOCTORATE;

    override fun toString(): String {
        return name.replace('_', ' ')
    }
}