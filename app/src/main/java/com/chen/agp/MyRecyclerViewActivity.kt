package com.chen.agp

import android.os.Bundle
import android.text.Layout
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget1.LinearLayoutManager
import androidx.recyclerview.widget1.RecyclerView
import com.chen.agp.databinding.ActivityMyrecyclerLayoutBinding
import com.chen.base_utils.KLog

class MyRecyclerViewActivity:AppCompatActivity(), View.OnClickListener {
    private   val TAG = "MyRecyclerViewActivity"
  //  ActivityPaingLayoutBinding
    lateinit var  mBinding:ActivityMyrecyclerLayoutBinding
    lateinit var  mRecyclerView: RecyclerView
      var  mDataList = ArrayList<String>();
    lateinit var  mAdapter:MyRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyrecyclerLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mRecyclerView = mBinding.pagingContainer
        for (x in 0..11){
            mDataList.add("明月几时有:$x")
        }
        initView()

    }

    fun initView(){
        mAdapter = MyRvAdapter(this@MyRecyclerViewActivity)
        mBinding.pagingContainer.adapter =mAdapter
        mAdapter.setNewData(mDataList)
        mBinding.pagingContainer.layoutManager = LinearLayoutManager(this@MyRecyclerViewActivity,
            LinearLayoutManager.VERTICAL,false)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(v: View?) {

        if (v != null) {
          val holder =  mRecyclerView.getChildViewHolder(v) as MyRvHolder

            KLog.d(TAG,"currentHolder.Position:${holder.layoutPosition}");
            if(holder.layoutPosition ==1){
                mDataList.removeAt(1)
                mDataList.add(1,"wahaha")
                mAdapter.notifyItemChanged(1)

            }else{
                mDataList = ArrayList<String>()
                for (x in 0 ..9){
                    mDataList.add("nihao:$x")
                }
                mAdapter.setNewData(mDataList)
            }
        }




    }
}
class MyRvHolder(val buttonView: AppCompatButton):
    RecyclerView.ViewHolder(buttonView){

}

class MyRvAdapter(val listener: View.OnClickListener): RecyclerView.Adapter<MyRvHolder>(){

      var mDatas: List<String>?  =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRvHolder {
         var mItemView =  AppCompatButton(parent.context)
        val mLayoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,360)
        mItemView.layoutParams = mLayoutParams
        return  MyRvHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return mDatas?.size?:0
    }

    override fun onBindViewHolder(holder: MyRvHolder, position: Int) {
        holder.buttonView.text = mDatas?.get(position)
        holder.itemView.setOnClickListener(listener)
    }

    public fun setNewData(newData:List<String>?){
        mDatas = newData as ArrayList<String>;
        notifyDataSetChanged()
    }

}