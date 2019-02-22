package m.vk.k004_recyclerviewmultilayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var customAdapter: CustomAdapter
    private lateinit var dataSet : ArrayList<ModelItem>
    private var arrChar = 'A'..'Z'

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelableArrayList("dataSet",dataSet)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        dataSet = savedInstanceState!!.getParcelableArrayList("dataSet")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        onSetClickView()
        onSetDataToList()
    }

    private fun initView() {
        dataSet = ArrayList()
        reyView.layoutManager = GridLayoutManager(this,1)
        reyView.setHasFixedSize(true)
        customAdapter = CustomAdapter(dataSet)
        reyView.adapter = customAdapter
    }
    private fun onSetClickView(){
        customAdapter.setOnClickItem(object : CustomAdapter.clickItem{
            override fun onClickItem(position: Int, title: String, subTitle: String) {
                Toast.makeText(this@MainActivity,"คุณคลิ๊ก : " + title + "\n" + subTitle,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onSetDataToList(){
        for ((index, txtChar) in arrChar.withIndex()){
            if (index % 3 == 0){
                dataSet.add(ModelItem(txtChar.toString(),"ตำแหน่งที่ " + (dataSet.size + 1) + " : $index % 3 = " + (index % 3),1))
                customAdapter.notifyDataSetChanged()
            }else{
                dataSet.add(ModelItem(txtChar.toString(),"ตำแหน่งที่ " + (dataSet.size + 1)+ " : $index % 3 = " + (index % 3),2))
                customAdapter.notifyDataSetChanged()
            }

        }
    }
}
