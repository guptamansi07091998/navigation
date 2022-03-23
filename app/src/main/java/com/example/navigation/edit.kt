package com.example.navigation

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.navigation.databinding.FragmentEditBinding
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_list.*


class edit : Fragment() {
    lateinit var edit:User_data
    lateinit var binding:FragmentEditBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_edit, container, false)
        var view:View=binding.root
        val update: Button = view.findViewById(R.id.bt)
        var name= view.findViewById<EditText>(R.id.Name)
        var helper1= helper(requireActivity().application)
        var db=helper1.readableDatabase
       update.setOnClickListener {
         //  Toast.makeText(context,edit.name_data.toString(),Toast.LENGTH_LONG).show()
       edit?.setData(name!!.text.toString())
           edit?.setpData(Phone!!.text.toString())
           edit?.setmData(EmailAddress!!.text.toString())
           edit?.setaData(Address!!.text.toString())
           var ct= ContentValues()
           ct.put("name", name.text.toString())
           ct.put("number",Phone.text.toString())
           ct.put("email_address",EmailAddress.text.toString())
           ct.put("address",Address.text.toString())
           val db = helper1.writableDatabase
           db.update("user_d", ct, "number=${Phone.text.toString()}", arrayOf())

       }
        return view
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edit = ViewModelProvider(requireActivity()).get(User_data::class.java)
        binding.edit1=edit
    }*/
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      edit = ViewModelProvider(requireActivity()).get(User_data::class.java)



    }
}


