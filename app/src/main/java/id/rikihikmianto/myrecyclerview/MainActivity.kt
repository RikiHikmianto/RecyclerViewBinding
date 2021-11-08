package id.rikihikmianto.myrecyclerview

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.rikihikmianto.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Hero>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHero.setHasFixedSize(true)

        list.addAll(listHero)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvHero.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvHero.layoutManager = LinearLayoutManager(this)
        }
        val listHeroAdapter = ListHeroAdapter(list)
        binding.rvHero.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Memilih" + hero.name, Toast.LENGTH_SHORT).show()
    }

    private val listHero: ArrayList<Hero>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.getStringArray(R.array.data_photo)
            val listHero = ArrayList<Hero>()
            for (i in dataName.indices) {
                val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i])
                listHero.add(hero)

            }
            return listHero
        }
}