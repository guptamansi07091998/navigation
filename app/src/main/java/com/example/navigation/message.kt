package com.example.navigation

import android.app.Application
import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.name
import kotlinx.android.synthetic.main.fragment_message.*

class message : Fragment() {
    private lateinit var communicate:Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_message, container, false)
        communicate=activity as Communicator
        var helper1= helper(requireActivity().application)
        var db=helper1.readableDatabase
        var btn=view.findViewById<Button>(R.id.button1)
        var name=view.findViewById<EditText>(R.id.name)
        var number=view.findViewById<EditText>(R.id.number)
        var mail=view.findViewById<EditText>(R.id.email)
        var address=view.findViewById<EditText>(R.id.adress)
        btn.setOnClickListener {
            var ct= ContentValues()
            ct.put("name", name.text.toString())
            ct.put("number",number.text.toString())
            ct.put("email_address",mail.text.toString())
            ct.put("address",address.text.toString())
            db.insert("user_d",null,ct)
            communicate.passData(name.text.toString(),number.text.toString(),mail.text.toString(),address.text.toString())
            name.setText("")
            number.setText("")
            mail.setText("")
            address.setText("")
            name.requestFocus()
        }
        return view
    }


}