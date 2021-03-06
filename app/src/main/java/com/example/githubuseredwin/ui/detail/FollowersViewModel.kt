package com.example.githubuseredwin.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuseredwin.api.RetrofitClient
import com.example.githubuseredwin.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setFollowers(username: String) {
        RetrofitClient.apiInstance.getFollowers(username).enqueue(object :
            Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if(response.isSuccessful) {
                    listFollowers.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d(FAILURE, "${t.message}")
            }

        })
    }

    fun getFollowers(): LiveData<ArrayList<User>> {
        return listFollowers
    }

    companion object {
        private const val FAILURE = "Failure"
    }

}