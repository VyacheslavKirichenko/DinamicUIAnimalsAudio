package com.example.dinamicuianimalsaudio

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private  val ANIMALS = listOf("Goose",
    "Chiken",
    "Cow",
    "Cat",
    "Dog",
    "Pig",
    "Donkey",
    "Horse",
    "Goat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (anim in ANIMALS) {
            createAnimal(anim)
        }

    }
    fun  createAnimal(animalName:String){
        val animal = layoutInflater.inflate(R.layout.animal, null)
        val image = animal.findViewById<ImageButton>(R.id.animal_image)
        image.setOnClickListener {
            showAnimalInfo(animalName)
        }
        var animalNameView = animal.findViewById<TextView>(R.id.animal_name)
        val visited = animal.findViewById<CheckBox>(R.id.checkBox)

        val animalName2 = animalName.toLowerCase().replace(" ","")
        val imageID = resources.getIdentifier(animalName2, "drawable", packageName)
        image.setImageResource(imageID)
        grid_of_animals.addView(animal)

        animalNameView.text = animalName
    }
    fun  showAnimalInfo(animalName: String){

        val animalName2 = animalName.toLowerCase().replace(" ","")
        val textFileID = resources.getIdentifier(animalName2, "raw", packageName)
        val fileText =  resources.openRawResource(textFileID).bufferedReader().readText()
        val mp3FileID = resources.getIdentifier(animalName2 + "_vote", "raw", packageName)
        val mp = MediaPlayer.create(this, mp3FileID)
        mp.start()


        val builder = AlertDialog.Builder(this)
            builder.setTitle("Info about $animalName")
            builder.setMessage(fileText)
        builder.setPositiveButton("Ok") {_, _ ->
            mp.stop()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
