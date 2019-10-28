package com.nav.navigationcircus.core

import org.json.JSONArray
import org.json.JSONObject

data class EventError(val errorCode: String,
                      val errorMessage: String,
                      val target: String = "",
                      val innerErrorList: List<EventError> = emptyList(),
                      val exception: Exception? = null) {


    fun toJson(): JSONObject {

        val jsonObject = JSONObject()

        jsonObject.put("code", errorCode)
        jsonObject.put("message", errorMessage)
        if (target.isNotEmpty()) {
            jsonObject.put("target", target)
        }

        val array = JSONArray()
        innerErrorList.forEach {
            array.put(it.toJson())
        }

        if (array.length() > 0)
            jsonObject.put("details", array)

        exception?.let {
            jsonObject.put("exception", it)
        }

        return jsonObject

    }


    companion object {
        const val INTERNAL_ERROR = "INTERNAL_ERROR_APP"
        const val PARSE_ERROR = "PARSE_ERROR"


        @JvmStatic
        fun createInternalError(errorMessage: String,
                                target: String,
                                exception: Exception? = null,
                                innerErrorList: List<EventError> = emptyList()): EventError {
            return EventError(errorCode = INTERNAL_ERROR,
                errorMessage = errorMessage,
                target = target,
                innerErrorList = innerErrorList,
                exception = exception)
        }

    }
}


enum class ErrorCode(val code: String){
    UNHANDLER("Unhandler"),
    FAIL_CHARGE("FailCharge"),
    EXCEED_ATTEMPTS("ExceedAttempts"),
    NOT_FOUND_CARD("NotFoundCard"),
    INVALID_AMOUNT("InvalidAmount"),
    INVALID_CARD("InvalidCard");

    companion object {
        @JvmStatic
        fun getErrorFromCode(code: String): ErrorCode{
            for(error in values()){
                if(error.code == code)
                    return error
            }
            return UNHANDLER
        }
    }
}