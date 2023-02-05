package com.example.opcup_zadnje

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val newInputBtn = view.findViewById<Button>(R.id.buttonNewInput)

        newInputBtn.setOnClickListener{
            val fragment = NewInputFragment()
            val transaction=fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainlayout,fragment)?.commit()
        }

        val myInputsBtn = view.findViewById<Button>(R.id.buttonMyInputs)

        myInputsBtn.setOnClickListener {
            val fragment = MyInputsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainlayout,fragment)?.commit()
        }

        val calculatorBtn = view.findViewById<Button>(R.id.buttonCalculator)

        calculatorBtn.setOnClickListener {
            val fragment = CalculatorFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainlayout,fragment)?.commit()
        }

        return view
    }
}