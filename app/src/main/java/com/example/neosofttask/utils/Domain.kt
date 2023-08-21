package com.example.neosofttask.utils

enum class Domain {

    SELECT,
    APPLICATION_SCRIPTING,
    ARRAY_PROGRAMMING,
    ARTIFICIAL_INTELLIGENCE_REASONING,
    CLOUD_COMPUTING,
    COMPUTATIONAL_STATISTICS,
    E_COMMERCE;

    override fun toString(): String {
        return name.replace('_', ' ')
    }

}