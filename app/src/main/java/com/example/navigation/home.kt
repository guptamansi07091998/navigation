package com.example.navigation

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigation.databinding.FragmentEditBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class home : Fragment() {
  // lateinit var binding:FragmentEditBinding
    lateinit var edit:User_data
    lateinit var name:String
    lateinit var mail:String
    lateinit var phone:String
    lateinit var address:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       var view:View=inflater.inflate(R.layout.fragment_home, container, false)
        //binding = DataBindingUtil.setContentView(this, R.layout.fragment_edit)

       name= arguments?.getString("name").toString()
        mail= arguments?.getString("mail").toString()
        phone= arguments?.getString("phone").toString()
        address= arguments?.getString("address").toString()

        view.name_a.text=name
        view.mail_a.text=mail
        view.mobile_a.text=phone
        view.address_a.text=address

        var button = view.findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            requestPermissions()
        }

        var btn=view.findViewById<FloatingActionButton>(R.id.fab)
       btn.setOnClickListener {
           val fragment: Fragment = edit()
           val fragmentManager = requireActivity().supportFragmentManager
           val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
           fragmentTransaction.replace(R.id.fragment_container, fragment)
           fragmentTransaction.addToBackStack(null)
           fragmentTransaction.commit()
    }

        return view
    }




    private fun hasWritePermission(): Boolean =
        context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_GRANTED

    private fun hasReadPermission():Boolean=
        context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_EXTERNAL_STORAGE) } == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions()
    {
        var arr= mutableListOf<String>()
        if(!hasWritePermission())
            arr.add( android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(!hasReadPermission())
            arr.add( android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if(arr.isNotEmpty())
            ActivityCompat.requestPermissions(
                context as Activity,
                arr.toTypedArray(),
                0
            )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty() )
        {
            for(i in grantResults.indices)
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permission request", "$permissions[i] granted")
                }
        }
    }

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edit = ViewModelProvider(requireActivity()).get(User_data::class.java)
        binding.edit1=edit
        binding.lifecycleOwner=this
    }*/
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      edit = ViewModelProvider(requireActivity()).get(User_data::class.java)
        edit!!.getData().observe(viewLifecycleOwner, Observer {
           name_a!!.text = it
        })
        edit!!.getpData().observe(viewLifecycleOwner, Observer {
            mobile_a!!.text = it
        })
        edit!!.getmData().observe(viewLifecycleOwner, Observer {
            mail_a!!.text = it
        })
        edit!!.getaData().observe(viewLifecycleOwner, Observer {
            address_a!!.text = it
        })
    }

    }
