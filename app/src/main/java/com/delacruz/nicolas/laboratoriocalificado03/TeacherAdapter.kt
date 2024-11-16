package com.delacruz.nicolas.laboratoriocalificado03

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.delacruz.nicolas.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private val context: Context,
    private val teacherList: List<Teacher>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teacherList[position]
        holder.binding.apply {
            // Configuramos los datos en el ViewBinding
            textViewName.text = "${teacher.firstName} ${teacher.lastName}"
            Glide.with(context).load(teacher.photoUrl).into(imageViewPhoto)

            // Click simple para llamar al teléfono
            root.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${teacher.phone}"))
                context.startActivity(intent)
            }

            // Click largo para enviar un correo
            root.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${teacher.email}"))
                context.startActivity(intent)
                true
            }
        }
    }

    override fun getItemCount(): Int = teacherList.size
}
