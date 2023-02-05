package com.example.opcup_zadnje

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CalculatorFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)


        val calculateBtn = view.findViewById<Button>(R.id.buttonCalculate)

        val backBtn = view.findViewById<Button>(R.id.buttonCalculatorBack)

        calculateBtn.setOnClickListener {
            val cent1: Double = view.findViewById<EditText>(R.id.editText1cent).text.toString().toDouble()
            val cent2: Double = view.findViewById<EditText>(R.id.editText2cent).text.toString().toDouble()
            val cent5: Double = view.findViewById<EditText>(R.id.editText5cent).text.toString().toDouble()
            val cent10: Double = view.findViewById<EditText>(R.id.editText10cent).text.toString().toDouble()
            val cent20: Double = view.findViewById<EditText>(R.id.editText20cent).text.toString().toDouble()
            val cent50: Double = view.findViewById<EditText>(R.id.editText50cent).text.toString().toDouble()
            val euro1: Double = view.findViewById<EditText>(R.id.editText1euro).text.toString().toDouble()
            val euro2: Double = view.findViewById<EditText>(R.id.editText2euro).text.toString().toDouble()
            val euro5: Double = view.findViewById<EditText>(R.id.editText5euro).text.toString().toDouble()
            val euro10: Double = view.findViewById<EditText>(R.id.editText10euro).text.toString().toDouble()
            val euro20: Double = view.findViewById<EditText>(R.id.editText20euro).text.toString().toDouble()
            val euro50: Double = view.findViewById<EditText>(R.id.editText50euro).text.toString().toDouble()
            val euro100: Double = view.findViewById<EditText>(R.id.editText100euro).text.toString().toDouble()
            val euro200: Double = view.findViewById<EditText>(R.id.editText200euro).text.toString().toDouble()

            val calculation = view.findViewById<TextView>(R.id.textView14)
            val calculationNumber: Double = (cent1*0.01)+(cent2*0.02)+(cent5*0.05)+(cent10*0.1)+(cent20*0.2)+(cent50*0.5)+(euro1*1)+(euro2*2)+(euro5*5)+(euro10*10)+(euro20*20)+(euro50*50)+(euro100*100)+(euro200*200)
            val calculationString = calculationNumber.toString()
            calculation.text = calculationString
        }

        backBtn.setOnClickListener {
            val fragment = MainFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.mainlayout,fragment)?.commit()
        }



        return view
    }

}