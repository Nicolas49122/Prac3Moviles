package com.delacruz.nicolas.laboratoriocalificado03
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.delacruz.nicolas.laboratoriocalificado03.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var teacherAdapter: TeacherAdapter
    private val teacherList = mutableListOf<Teacher>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        teacherAdapter = TeacherAdapter(this, teacherList)
        binding.recyclerView.adapter = teacherAdapter

        // Cargar profesores desde la API
        loadTeachers()
    }

    private fun loadTeachers() {
        RetrofitClient.apiService.getTeachers().enqueue(object : Callback<List<Teacher>> {
            override fun onResponse(call: Call<List<Teacher>>, response: Response<List<Teacher>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        teacherList.clear()
                        teacherList.addAll(it)
                        teacherAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Teacher>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
