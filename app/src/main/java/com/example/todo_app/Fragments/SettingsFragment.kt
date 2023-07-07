package com.example.todo_app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.todo_app.R

class SettingsFragment:Fragment() {
    lateinit var spinner:Spinner
     lateinit var auto_box:AutoCompleteTextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = requireView().findViewById(R.id.planets_spinner)
        auto_box = requireView().findViewById(R.id.auto_box)

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
        val modes = resources.getStringArray(R.array.list_mode)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_dropdown, modes)
        auto_box.setAdapter(arrayAdapter)

        auto_box.setOnItemClickListener { parent, vieww, position, id ->
            val globalstring = parent?.getItemAtPosition(position).toString()
            if (globalstring == "Dark mode") {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else if (globalstring == "Light mode") {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }
}
