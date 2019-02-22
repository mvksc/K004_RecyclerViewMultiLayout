package m.vk.k004_recyclerviewmultilayout

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view_type_i.view.*
import kotlinx.android.synthetic.main.item_view_type_ii.view.*

class CustomAdapter(var dataSet: ArrayList<ModelItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mClickItem: clickItem? = null

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].viewType
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         when (viewType) {
             Type_View.TYPE_VIEW_SINGLE -> {
                 return ViewHolderSingle(
                     LayoutInflater.from(parent.context)
                         .inflate(R.layout.item_view_type_i, parent, false)
                 )
             }

             Type_View.TYPE_VIEW_DUAL -> {
                 return ViewHolderDual(
                     LayoutInflater.from(parent.context)
                         .inflate(R.layout.item_view_type_ii, parent, false)
                 )
             }
             else -> {
                 throw NullPointerException("View holder for type $viewType not found")
             }
         }
    }

    override fun getItemCount(): Int {
       return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderSingle){
            holder.txtPosition.text = dataSet[position].position
            holder.txtTitle.text = dataSet[position].title
            holder.lnContentItem?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (mClickItem != null)
                        mClickItem?.onClickItem(position,dataSet[position].title,dataSet[position].position)
                }
            })
        }else if (holder is ViewHolderDual){
            holder.txtPosition1.text = dataSet[position].position
            holder.txtTitle1.text = dataSet[position].title
            holder.txtPosition2.text = dataSet[position].position
            holder.txtTitle2.text = dataSet[position].title
            holder.lnContentItem1?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (mClickItem != null)
                        mClickItem?.onClickItem(position,dataSet[position].title,dataSet[position].position)
                }
            })
            holder.lnContentItem2?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (mClickItem != null)
                        mClickItem?.onClickItem(position,dataSet[position].title,dataSet[position].position)
                }
            })
        }
    }


    class ViewHolderSingle(itemView: View) : RecyclerView.ViewHolder(itemView){
        val lnContentItem = itemView.lnContentItem!!
        val txtTitle = itemView.tvTitle!!
        val txtPosition = itemView.tvPosition!!
    }
    class ViewHolderDual(itemView: View) : RecyclerView.ViewHolder(itemView){
        val lnContentItem1 = itemView.lnContentItem1!!
        val txtTitle1 = itemView.tvTitle1!!
        val txtPosition1 = itemView.tvPosition1!!
        val lnContentItem2 = itemView.lnContentItem2!!
        val txtTitle2 = itemView.tvTitle2!!
        val txtPosition2 = itemView.tvPosition2!!
    }

    fun setOnClickItem(rClickItem: clickItem){
        mClickItem = rClickItem
    }
    interface clickItem{
        fun onClickItem(position: Int,title: String,subTitle: String)
    }
}