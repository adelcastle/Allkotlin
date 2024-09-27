package com.example.myapplication

import android.content.Intent
import android.graphics.Color.parseColor
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ShareActionProvider

import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.*

import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activityToolBar : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tool_bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)

        toolbar.title = "Titulo de toolbar"
        //toolbar.setTitle("Titulo de toolbar2")
        toolbar.setTitleTextColor(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  //hbilita boton de retroceso



    }
  //asociar nuestro elemento de menu a toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)  //implementamos el menu
      val itemBuscar = menu?.findItem(R.id.barSearch)
      val vistaBuscar = itemBuscar?.actionView as android.widget.SearchView
     /*
      val itemCompartir = menu.findItem(R.id.Compartir)
      val shareActionProvider: ShareActionProvider =  MenuItemCompat.getActionProvider(itemCompartir) as ShareActionProvider
        compartirIntent(shareActionProvider)
      itemCompartir?.setOnMenuItemClickListener {
          Toast.makeText(this,"Clic en compartir",Toast.LENGTH_SHORT).show()
          false
      }
      */
      vistaBuscar.queryHint = "Buscar en la app"
         // Eventos de buscar
            vistaBuscar.setOnQueryTextFocusChangeListener(){vista,hasFocus->
                if(hasFocus){
                    Toast.makeText(this,"Has dado click en buscar",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Has dado click fuera de buscar",Toast.LENGTH_SHORT).show()
                }
            }

          vistaBuscar.setOnQueryTextListener(object: OnQueryTextListener,
              android.widget.SearchView.OnQueryTextListener {
              override fun onQueryTextSubmit(query: String?): Boolean {
                  Toast.makeText(this@activityToolBar,"TextoEnvio: " + query,Toast.LENGTH_SHORT).show()
                  return false
              }

              override fun onQueryTextChange(newText: String?): Boolean {
                 Toast.makeText(applicationContext,"TextoDigitar: " + newText,Toast.LENGTH_SHORT).show()
                  return false
              }

          })




      return super.onCreateOptionsMenu(menu)

    }
// Permite mapeo de eventos con elementos del menu según item seleccionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this,"Clic en Item del toolbar",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.Compartir -> {
                Toast.makeText(this,"Clic compartir",Toast.LENGTH_SHORT).show()

                share3()
                return false
            }

        else ->{ return super.onOptionsItemSelected(item)}
    }

    }

/*    private fun compartirIntent(shareActionProvider : ShareActionProvider){

        if(shareActionProvider != null){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Este es un mensaje compartido.")
            shareActionProvider.setShareIntent(intent)

        }

    }*/

    private fun share(){

        val intent = intent.apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"Este es un mensaje compartido.")
            type = "image/jpeg"
            putExtra(Intent.EXTRA_SUBJECT,"Este es un asunto.")
            putExtra(Intent.EXTRA_TITLE,"Este es un titulo.")
        }
        val shareintent = Intent.createChooser(intent,null)
        startActivity(shareintent)
    }
    private fun share2(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, Uri.EMPTY)
        intent.type = "image/jpeg"
        startActivity(Intent.createChooser(intent,null))
    }

    private fun share3(){
        val uriArray: ArrayList<Uri> =arrayListOf(Uri.EMPTY,Uri.EMPTY)
        val intent = Intent().apply {

            action = Intent.ACTION_SEND_MULTIPLE
            putParcelableArrayListExtra(Intent.EXTRA_STREAM,uriArray)
            type = "image/jpeg"
        }

        startActivity(Intent.createChooser(intent,null))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }








}