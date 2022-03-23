package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarMenu
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar.*

lateinit var toggle:ActionBarDrawerToggle

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar2)
        val nav_view=findViewById<NavigationView>(R.id.nav)
        toggle= ActionBarDrawerToggle(this, drawer,toolbar2,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        nav.setNavigationItemSelectedListener (this)
        setToolbarTitle("Login")
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,login()).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer.closeDrawers()
        if(item.itemId==R.id.home){
            setToolbarTitle("Home")
            changeFragment(home())
        }
        if(item.itemId==R.id.msg){
            setToolbarTitle("Login")
            changeFragment(login())
        }
        if(item.itemId==R.id.edit){
            setToolbarTitle("Edit Profile")
            changeFragment(edit())
        }
        if(item.itemId==R.id.contact){
            setToolbarTitle("Contact Details")
            changeFragment(contacts())
        }

        return true
    }

    private fun setToolbarTitle(title: String){
        supportActionBar?.title = title
    }

    private fun changeFragment(frag: Fragment){
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,frag).commit()
    }

    override fun passData(namei: String, phonei: String,maili: String ,addressi: String) {
        val bundle=Bundle()
        bundle.putString("name",namei)
        bundle.putString("mail",maili)
        bundle.putString("phone",phonei)
        bundle.putString("address",addressi)
        setToolbarTitle("Home")
        val transaction=supportFragmentManager.beginTransaction()
        val fragment2=home()
        fragment2.arguments=bundle
        transaction.replace(R.id.fragment_container,fragment2)
        transaction.commit()
    }


}