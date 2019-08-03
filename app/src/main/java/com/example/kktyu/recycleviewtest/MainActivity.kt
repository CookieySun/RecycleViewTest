package com.example.kktyu.recycleviewtest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var dataList = mutableListOf<RowModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Life Cycle", "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = recycler_list

        dataList = createDataList()

        val adapter = ViewAdapter(dataList, object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext, rowModel.title, Toast.LENGTH_LONG).show()
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun createDataList(): MutableList<RowModel> {
        Log.d("Life Cycle", "createDataList")
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

    //カードのスワイプアクションの定義
    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //スワイプ時に実行
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //データリストからスワイプしたデータを削除
                dataList.removeAt(viewHolder.adapterPosition)

                //リストからスワイプしたカードを削除
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

            //スワイプした時の背景を設定
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                background.color = Color.parseColor("#f44336")
                if (dX < 0)
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                else
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        itemView.left + dX.toInt(),
                        itemView.bottom
                    )

                background.draw(c)
            }
        })
}