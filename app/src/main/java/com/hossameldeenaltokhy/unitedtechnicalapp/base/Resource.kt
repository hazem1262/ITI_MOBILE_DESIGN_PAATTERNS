package com.hossameldeenaltokhy.unitedtechnicalapp.base

import com.google.gson.Gson
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Created by hazem.ashraf on 6/14/2019.
 * the base class for all HTTP calls in the app
 */
class Resource<out T> constructor(var status: Status, val data: T?, var exception: Exception?) {

	enum class Status {
		SUCCESS, ERROR
	}

	companion object {
		// creates a SUCCESS resource for HTTP 200, and ERROR otherwise
		fun <T> create(response: Response<T>?): Resource<T> {
			return when (response?.code()) {
				HttpURLConnection.HTTP_OK -> {
					success(response.body())
				}
				else ->{
					// serializing the error body [not used in this project but it is generic way of handling errors]
					val gSon = Gson()
					val errorBody = gSon.fromJson(response?.errorBody()?.string(), ApiErrorResponse::class.java)
					error(
						Exception(errorBody.statusMessage)
					)
				}
			}
		}

		fun <T> success(data: T?): Resource<T> {
			return Resource(
				Status.SUCCESS,
				data,
				null
			)
		}

		fun <T> error(exception: Exception?): Resource<T> {
			return Resource(
				Status.ERROR,
				null,
				exception
			)
		}

	}
}
