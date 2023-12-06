package com.chen.agp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PostProcessor
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget1.LinearLayoutManager
import androidx.recyclerview.widget1.RecyclerView
import androidx.recyclerview.widget1.RecyclerView.ItemAnimator
import com.chen.agp.MyRecyclerViewActivity.Companion.mDataList
import com.chen.agp.databinding.ActivityMyrecyclerLayoutBinding
import com.chen.base_utils.KLog
import com.chen.view.CardPresentLayoutMananger
import com.chen.view.ItemKeyRemoveAnimator
import com.chen.view.ItemTouchHelperCallback
import com.chen.view.MySimpleLayoutManager
import com.chen.view.MySimpleLayoutManager.Companion.POSITION_OFFSET
import com.chen.view.MySimpleLayoutManager.Companion.SCALE_OFFSET
import com.chen.view.SlideLayoutManager

class MyRecyclerViewActivity:AppCompatActivity(), View.OnClickListener {
    private   val TAG = "MyRecyclerViewActivity"
  //  ActivityPaingLayoutBinding
    lateinit var  mBinding:ActivityMyrecyclerLayoutBinding
    lateinit var  mRecyclerView: RecyclerView

    lateinit var  mAdapter:MyRvAdapter
    companion object{
        var  mDataList = ArrayList<String>();
    }
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
        /*mBinding.pagingContainer.layoutManager = LinearLayoutManager(this@MyRecyclerViewActivity,
            LinearLayoutManager.VERTICAL,false)*/
       // mBinding.pagingContainer.layoutManager = CardPresentLayoutMananger()
        mBinding.pagingContainer.layoutManager = MySimpleLayoutManager( this)
        mBinding.pagingContainer.itemAnimator = ItemKeyRemoveAnimator()
         mBinding.pagingContainer.layoutAnimation= AnimationUtils.loadLayoutAnimation(this@MyRecyclerViewActivity,
             R.anim.layout_animation_fall_down)

    }

    override fun onResume() {
        super.onResume()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClick(v: View?) {

        if (v != null) {
          val holder =  mRecyclerView.getChildViewHolder(v) as MyRvHolder

            KLog.d(TAG,"currentHolder.Position:${holder.layoutPosition}");
            if(holder.layoutPosition ==1){
                mDataList.removeAt(1)
                mDataList.add(1,"wahaha")
                mAdapter.notifyItemChanged(1)


            }else if(holder.layoutPosition ==0){
                //mDataList.add(0,"nihaohhhh")
               /* mDataList.removeAt(0)
                mAdapter.notifyItemRemoved(0)*/
                mRecyclerView.offsetChildrenVertical(POSITION_OFFSET)
            }else{
                mDataList = ArrayList<String>()
                for (x in 0 ..9){
                    mDataList.add("nihao:$x")
                }
                mAdapter.notifyDataSetChanged()
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
    private   val TAG = "MyRecyclerViewActivity"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRvHolder {
         var mItemView =  AppCompatButton(parent.context)
        mItemView.setBackgroundColor(Color.DKGRAY)
        mItemView.setBackgroundResource(R.drawable.btn_selector)
        mItemView.gravity=Gravity.CENTER
        val mLayoutParams = RecyclerView.LayoutParams(360,360)
        mItemView.minWidth=360
        mItemView.minHeight=360
        mItemView.layoutParams = mLayoutParams
        return  MyRvHolder(mItemView)
    }

    override fun getItemCount(): Int {
        return mDataList?.size?:0
    }

    override fun onBindViewHolder(holder: MyRvHolder, position: Int) {
        KLog.d(TAG,"bindViewHodler:${position}, hodler:${holder.adapterPosition}")
        val view = holder.itemView
      val params =  view.layoutParams as RecyclerView.LayoutParams;
        /* view.scaleX = (1- position * SCALE_OFFSET).toFloat()
         view.scaleY = (1- position * SCALE_OFFSET ).toFloat()
        view.translationY = (position*POSITION_OFFSET).toFloat()*/

        holder.buttonView.text = mDataList?.get(position)
        holder.itemView.setOnClickListener(listener)
    }





}