package com.example.countriesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.countriesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // точка входа

        binding.searchButton.setOnClickListener {
            val countryName = binding.countryNameEditText.text.toString()

            // в фоновом потоке, параллельно с основным кодом
            lifecycleScope.launch {
                try {
                    val countries = restCountriesAPI.getCountryByName(countryName)
/*                .execute().body()!! // синхронный запрос*/
                    val country = countries[0]


                    binding.countryNameTextView.text = country.name
                    binding.capitalTextView.text = country.capital
                    binding.populationTextView.text = formatNumber(country.population)
                    binding.areaTextView.text = formatNumber(country.area)
                    binding.languagesTextView.text = languagesToString(country.languages)

                    loadSvg(binding.imageView, country.flag)

                    binding.resultLayout.visibility == View.VISIBLE
                    binding.statusLayout.visibility == View.INVISIBLE
                }catch (e:Exception){
                    binding.statusTextView.text = "Страна не найдена"
                    binding.statusImageView.setImageResource(R.drawable.ic_baseline_error_outline_24)
                    binding.resultLayout.visibility == View.INVISIBLE
                    binding.statusLayout.visibility == View.VISIBLE
                }
            }
        }
    }

}

//fun main(){
//    val lang1 = Language("Russian")
//    val lang2 = Language("English")
//    val langs = listOf(lang1, lang2)
//
//    val result = languagesToString(langs)
//    println(result)
//}