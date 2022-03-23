package com.example.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_message.name


class login : Fragment() {

    private lateinit var communicate:Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
        communicate=activity as Communicator
        var helper1 = helper(requireActivity().application)
        var db = helper1.readableDatabase
        var btn = view.findViewById<Button>(R.id.button3_si)
        var btn1 = view.findViewById<Button>(R.id.button3_sig)
        var number1 = view.findViewById<EditText>(R.id.phone_si)
        btn.setOnClickListener {
            //Log.d("hii", number1.toString())
            var numb = number1.text.toString()
            val selectQuery = "SELECT  * FROM user_d WHERE number = $numb"
            Log.d("hi", numb)
            val cursor = db.rawQuery(selectQuery, null)
            var bundle:Bundle=Bundle()


            if (cursor.getCount() > 0) {
                if(cursor.moveToNext())
                {
                    communicate.passData(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
                }
            }
            else{
               signup()
            }
        }
        btn1.setOnClickListener {
           signup()
        }
        return view
    }
    private fun signup()
    {
        val fragment: Fragment = message()
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}