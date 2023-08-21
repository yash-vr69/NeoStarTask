package com.example.neosofttask.utils

enum class IndianStates {
    SELECT,
    ANDHRA_PRADESH,
    ARUNACHAL_PRADESH,
    ASSAM,
    BIHAR,
    CHHATTISGARH,
    GOA,
    GUJARAT,
    HARYANA,
    HIMACHAL_PRADESH,
    JHARKHAND,
    KARNATAKA,
    KERALA,
    MADHYA_PRADESH,
    MAHARASHTRA,
    MANIPUR,
    MEGHALAYA,
    MIZORAM,
    NAGALAND,
    ODISHA,
    PUNJAB,
    RAJASTHAN,
    SIKKIM,
    TAMIL_NADU,
    TELANGANA,
    TRIPURA,
    UTTAR_PRADESH,
    UTTARAKHAND,
    WEST_BENGAL,
    ANDAMAN_AND_NICOBAR_ISLANDS,
    CHANDIGARH,
    DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU,
    LAKSHADWEEP,
    DELHI,
    PUDUCHERRY;

    override fun toString(): String {
        return name.replace('_', ' ')
    }

}