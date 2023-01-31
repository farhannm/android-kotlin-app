package com.farhan.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhan.movieapp.Models.Tv
import com.farhan.movieapp.Models.TvResponse
import com.farhan.movieapp.Service.TvApiInterface
import com.farhan.movieapp.Service.TvApiService
import kotlinx.android.synthetic.main.fragment_tv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvFrag : Fragment() {
    private val tv = arrayListOf<Tv>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tv.layoutManager = LinearLayoutManager(this.context)
        rv_tv.setHasFixedSize(true)
        getTvData{ tv : List<Tv> ->
            rv_tv.adapter = TvAdapter(tv)
        }
        showRecyclerView()
    }

    private fun showRecyclerView() {
        rv_tv.layoutManager = LinearLayoutManager(this.context)
        rv_tv.adapter = TvAdapter(tv)
    }

    private fun getTvData(callback: (List<Tv>) -> Unit) {
        val apiService = TvApiService.getInstance().create(TvApiInterface::class.java)
        apiService.getTvList().enqueue(object : Callback<TvResponse>{
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                return callback(response.body()!!.result_tv)
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {

            }

        })
    }
}