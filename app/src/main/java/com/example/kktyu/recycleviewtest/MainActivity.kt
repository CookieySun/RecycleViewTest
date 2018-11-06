package com.example.kktyu.recycleviewtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Life Cycle","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = recycler_list
        val adapter = ViewAdapter(createDataList(), object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext, rowModel.title,Toast.LENGTH_LONG).show()
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter
    }

    private fun createDataList(): List<RowModel> {
        Log.d("Life Cycle","createDataList")
        val dataList = mutableListOf<RowModel>()
        for (i in 0..49) {
            val data: RowModel = RowModel().also {
                it.title = "タイトル" + i + "だよ"
                it.detail = "詳細" + i + "個目だよ"
            }
            dataList.add(data)
        }
        return dataList
    }
}