package com.chen.agp

import android.os.Bundle
import android.text.Layout
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget1.LinearLayoutManager
import androidx.recyclerview.widget1.RecyclerView
import com.chen.agp.databinding.ActivityMyrecyclerLayoutBinding

class MyRecyclerViewActivity:AppCompatActivity() {

  //  ActivityPaingLayoutBinding
    lateinit var  mBinding:ActivityMyrecyclerLayoutBinding
    lateinit var  mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyrecyclerLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mRecyclerView = mBinding.pagingContainer
        initView()
    }

    fun initView(){
        mBinding.pagingContainer.adapter =MyRvAdapter()
        mBinding.pagingContainer.layoutManager = LinearLayoutManager(this@MyRecyclerViewActivity,
            LinearLayoutManager.VERTICAL,false)
    }

    override fun onResume() {
        super.onResume()
    }
}
class MyRvHolder(val buttonView: AppCompatButton):
    RecyclerView.ViewHolder(buttonView){

}

class MyRvAdapter: RecyclerView.Adapter<MyRvHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRvHolder {
         var mItemView =  AppCompatButton(parent.context)
        val mLayoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,360)
        mItemView.layoutParams = mLayoutParams
        return  MyRvHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: MyRvHolder, position: Int) {
        holder.buttonView.text = "明月几时有"+position
    }

}