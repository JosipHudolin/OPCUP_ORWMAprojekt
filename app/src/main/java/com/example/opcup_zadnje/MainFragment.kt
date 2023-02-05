package com.example.opcup_zadnje

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment


class MainFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val newInputBtn = view.findViewById<Button>(R.id.buttonNewInput)

        val qImageView = view.findViewById<ImageView>(R.id.imageViewNaslovna)

        qImageView.setImageResource(R.drawable.carinski_grb_2020_plava);

        val linkBtn = view.findViewById<Button>(R.id.buttonLink)

        linkBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://mfin.gov.hr/"))
            startActivity(browserIntent)
        }

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