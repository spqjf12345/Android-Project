package com.example.imagerecyclerview

import android.media.Image
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_image.view.*

class ImageAdapter(private val item:ArrayList<item>): RecyclerView.Adapter<ImageAdapter.ViewHolder>(){
    //viewholder 단위 객체로 view의 데이터를 설정
    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: item){
            view.thumbnail.setImageDrawable(item.image)
            view.title.text = item.title
            view.setOnClickListener(listener)
        }
        // var image = itemView.findViewById<ImageView>(R.id.gallery)
    }


    // 보여줄 아이템 갯수만큼 view 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_image, parent, false)
        return ImageAdapter.ViewHolder(inflatedView)
    }

    //보여줄 아이템의 갯수
    override fun getItemCount(): Int {
        val size = item.size
        return size
    }
    //생성된 view에 보여줄 데이터 설정
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = item[position]
        val listener = View.OnClickListener { it-> Toast.makeText(it.context, "Clicked: ${item.title}",Toast.LENGTH_SHORT).show()

        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
        //holder.image.setImageResource(ImageList)


        //list_image에 ADD 시킨 ImageList를 imageresource로 출력
        // holder.image.setImageResource()
    }
}