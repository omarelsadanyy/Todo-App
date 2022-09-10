package com.example.todo_app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.todo_app.R

class settingsFragment:Fragment() {
    lateinit var spinner:Spinner
    lateinit var spinner2:Spinner
     lateinit var auto_box:AutoCompleteTextView
    val mode_str = arrayOf("Light mode", "Dark mode")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   spinner=requireView().findViewById(R.id.planets_spinner)
        auto_box=requireView().findViewById(R.id.auto_box)
       // spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.list_language,
            R.layout.custom_spinner
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        val modes=resources.getStringArray(R.array.list_mode)
        val arrayAdapter=ArrayAdapter(requireContext(), R.layout.custom_spinner_dropdown,modes)
        auto_box.setAdapter(arrayAdapter)
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.list_mode,
//            R.layout.custom_spinner
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
//            // Apply the adapter to the spinner
//            auto_box.setAdapter(adapter)
//        }
        auto_box.setOnItemClickListener { parent, vieww, position, id ->
            val globalstring = parent?.getItemAtPosition(position).toString()
            if (globalstring == "Dark mode") {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else if (globalstring == "Light mode") {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

//        val arrayAdapter=ArrayAdapter<String>(requireContext(),R.layout.custom_spinner,mode_str)
//        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
//        spinner2.adapter=arrayAdapter
//       spinner2.onItemSelectedListener=object :OnItemSelectedListener{
//           override fun onItemSelected(
//               parent: AdapterView<*>?,
//               view: View?,
//               position: Int,
//               id: Long
//           ) {
//
//             val  globalstring=parent?.getItemAtPosition(position).toString()
//               if(globalstring=="Dark mode"){
//                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//               }
//               else if(globalstring=="Light mode"){
//                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//               }
//           }
//
//           override fun onNothingSelected(parent: AdapterView<*>?) {
//               TODO("Not yet implemented")
//           }
//
//       }
//        spinner2.onItemSelectedListener=object :OnItemSelectedListener{
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//             globalstring=parent.getItemAtPosition(position)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//        ArrayAdapter.createFromResource(
//            requireContext(),
//            R.array.list_mode,
//            R.layout.custom_spinner
//        ).also { adapter ->
//            // Specify the layout to use when the list of choices appears
//            adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
//            // Apply the adapter to the spinner
//            spinner2.adapter = adapter
//        }
 // initview()


        }

//    private fun initview() {
//        spinner2.onItemSelectedListener = object : OnItemSelectedListener {
//
//            override fun onItemSelected(
//                parentView: AdapterView<*>?,
//                selectedItemView: View,
//                position: Int,
//                id: Long
//            ) {
//                // your code here
//                when(position) {
//                    0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//
//                    1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                }
//
//            }
//            override fun onNothingSelected(parentView: AdapterView<*>?) {
//                // your code here
//
//            }
//        }
//    }

//    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        if(spinner2.selectedItem== "Light mode" ){
//
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                }
//        if(spinner2.selectedItem=="Dark mode" ){
//
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//
//                }
//    }
//
//    override fun onNothingSelected(parent: AdapterView<*>?) {
//
//    }
}
