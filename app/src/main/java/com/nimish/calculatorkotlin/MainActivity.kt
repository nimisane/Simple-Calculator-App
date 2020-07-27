package com.nimish.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var primaryTextView:TextView
    lateinit var secondaryTextView: TextView

    private var operand1:Double = 0.0
    private var operand2:Double = 0.0
    private var result:Double = 0.0
    var op = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TextViews
        primaryTextView = findViewById(R.id.number2_textview)
        secondaryTextView = findViewById(R.id.number1_textview)

        //number buttons
        val button0: Button = findViewById(R.id.number_0)
        val button1: Button = findViewById(R.id.number_1)
        val button2: Button = findViewById(R.id.number_2)
        val button3: Button = findViewById(R.id.number_3)
        val button4: Button = findViewById(R.id.number_4)
        val button5: Button = findViewById(R.id.number_5)
        val button6: Button = findViewById(R.id.number_6)
        val button7: Button = findViewById(R.id.number_7)
        val button8: Button = findViewById(R.id.number_8)
        val button9: Button = findViewById(R.id.number_9)

        //decimal
        val decimalButton:Button = findViewById(R.id.decimal)

        //operators
        val add:Button = findViewById(R.id.plus_operator)
        val sub:Button = findViewById(R.id.minus_operator)
        val multiply:Button = findViewById(R.id.multiplication_operator)
        val divide:Button = findViewById(R.id.divide_operator)
        val modulos:Button = findViewById(R.id.mode_opetator)
        val equalTo:Button = findViewById(R.id.equal)
        val changeSign:Button = findViewById(R.id.num_sign)
        val clear:Button = findViewById(R.id.clear)

        //Number onClick Listener
        val numClickListener = View.OnClickListener { v ->
            val value = v as Button
            primaryTextView.append(value.text)
        }

        //operator onClick Listener
        val operatorClickListener = View.OnClickListener {v ->
            val operator = (v as Button).text.toString()
            try {
                operand1 = primaryTextView.text.toString().toDouble()
                var s = secondaryTextView.text.toString()
                secondaryTextView.text = s+" "+operand1+" "+operator
                primaryTextView.text = ""
                when(operator){
                    "+" -> op = 1
                    "-" -> op = 2
                    "*" -> op = 3
                    "/" -> op = 4
                    "%" -> op = 5
                }
            }


            catch(e: NumberFormatException){
                primaryTextView.text = ""
                Log.d("NumberFormat",e.toString())
            }
            
        }

        //operator onClick Listener
        add.setOnClickListener(operatorClickListener)
        sub.setOnClickListener(operatorClickListener)
        multiply.setOnClickListener(operatorClickListener)
        divide.setOnClickListener(operatorClickListener)
        modulos.setOnClickListener(operatorClickListener)

        //seting onClick listener
        button0.setOnClickListener(numClickListener)
        button1.setOnClickListener(numClickListener)
        button2.setOnClickListener(numClickListener)
        button3.setOnClickListener(numClickListener)
        button4.setOnClickListener(numClickListener)
        button5.setOnClickListener(numClickListener)
        button6.setOnClickListener(numClickListener)
        button7.setOnClickListener(numClickListener)
        button8.setOnClickListener(numClickListener)
        button9.setOnClickListener(numClickListener)

        decimalButton.setOnClickListener(numClickListener)

        clear.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                primaryTextView.text = ""
                secondaryTextView.text = ""
                operand1 = 0.0
                operand2 = 0.0
                result = 0.0
            }

        })

        changeSign.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                try{
                    operand1 = primaryTextView.text.toString().toDouble()
                    result = -1*operand1
                    primaryTextView.text = result.toString()
                }
                catch (e: NumberFormatException){
                    Log.d("signError",e.toString())
                }
            }

        })

        equalTo.setOnClickListener(object: View.OnClickListener{

            override fun onClick(v: View?){
                try {
                    operand2 = primaryTextView.text.toString().toDouble()
                    when (op) {
                        1 -> {
                            result = operand1 + operand2
                        }
                        2 -> result = operand1 - operand2
                        3 -> result = operand1 * operand2
                        4 -> {
                            result = if (operand2 == 0.0) {
                                Double.NaN
                            } else {
                                operand1 / operand2
                            }
                        }
                        5 -> result = operand1 % operand2
                    }
                    secondaryTextView.text = ""
                    primaryTextView.text = result.toString()
                }
                catch (e: NumberFormatException){
                    Log.d("equalTo",e.toString())
                }
            }
        })

    }

}