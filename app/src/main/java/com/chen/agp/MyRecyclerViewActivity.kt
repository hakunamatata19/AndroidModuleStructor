package com.chen.agp

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget1.LinearLayoutManager
import androidx.recyclerview.widget1.RecyclerView
import androidx.recyclerview.widget1.RecyclerView.ItemAnimator
import com.chen.agp.databinding.ActivityMyrecyclerLayoutBinding
import com.chen.base_utils.KLog
import com.chen.view.CardPresentLayoutMananger
import com.chen.view.ItemTouchHelperCallback
import com.chen.view.MySimpleLayoutManager
import com.chen.view.SlideLayoutManager

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
       // mBinding.pagingContainer.itemAnimator =MyRvAdapter.MyItemAnimator()
        mAdapter.setNewData(mDataList)
        /*mBinding.pagingContainer.layoutManager = LinearLayoutManager(this@MyRecyclerViewActivity,
            LinearLayoutManager.VERTICAL,false)*/
       // mBinding.pagingContainer.layoutManager = CardPresentLayoutMananger()
        mBinding.pagingContainer.layoutManager = MySimpleLayoutManager( )

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


            }else if(holder.layoutPosition ==0){
                mAdapter.notifyItemChanged(0,"waka")
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

    override fun addChangePayload(payload: Any?) {
        KLog.d("MyRvHolder","addChangedPayLolad:$payload");
        super.addChangePayload(payload)
    }

}

class MyRvAdapter(val listener: View.OnClickListener): RecyclerView.Adapter<MyRvHolder>(){

      var mDatas: List<String>?  =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRvHolder {
         var mItemView =  AppCompatButton(parent.context)
        mItemView.setBackgroundColor(Color.DKGRAY)
        mItemView.gravity=Gravity.CENTER
        val mLayoutParams = RecyclerView.LayoutParams(360,360)
        mItemView.minWidth=360
        mItemView.minHeight=360
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


    class MyItemAnimator:ItemAnimator(){
        private   val TAG = "MyRecyclerViewActivity"
        override fun animateDisappearance(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo?
        ): Boolean {
            KLog.d(TAG,"animateDisappearance");
            return true;
        }

        override fun animateAppearance(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo?,
            postLayoutInfo: ItemHolderInfo
        ): Boolean {

            KLog.d(TAG,"animateAppearance");
            return false;
        }

        override fun animatePersistence(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun animateChange(
            oldHolder: RecyclerView.ViewHolder,
            newHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun runPendingAnimations() {
            TODO("Not yet implemented")
        }

        override fun endAnimation(item: RecyclerView.ViewHolder) {
            TODO("Not yet implemented")
        }

        override fun endAnimations() {
            TODO("Not yet implemented")
        }

        override fun isRunning(): Boolean {
            TODO("Not yet implemented")
        }

    }

}