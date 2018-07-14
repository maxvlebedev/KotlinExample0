package com.example.user.kotlinexample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var status = ""
    var blankNumber = ""
    var carriedName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun check(view: View) {
        if (editText.text.isEmpty()){
            val textString = "Requirement is empty"
            val toastMe = Toast.makeText(this, textString, Toast.LENGTH_SHORT)
            toastMe.show()
        }
        else
        {

            val toastMe = Toast.makeText(this, editText.text, Toast.LENGTH_SHORT)
            toastMe.show()
        }
    }
    fun requesr(){
        val ref = FirebaseDatabase.getInstance().reference
        val myQuery = ref.orderByChild("VehicleNumber").equalTo("Р642МР197")
        val postListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            }

            override  fun  onDataChange(p0:DataSnapshot? ){
                this@MainActivity.status = p0?.children?.first()?.children?.elementAt(11)?.value as String
                this@MainActivity.blankNumber = p0?.children?.first()?.children?.elementAt(0)?.value as String
                this@MainActivity.carriedName = p0?.children?.first()?.children?.elementAt(1)?.value as String
                val toastMe = Toast.makeText(this@MainActivity, this@MainActivity.carriedName, Toast.LENGTH_LONG)

                toastMe.show()
                detail.text = status

            }

        }
    }

    fun tabDetail(view:View)
    {
        val intent = Intent(this, Detail::class.java)
        intent.putExtra("status",this@MainActivity.status)
        startActivity(intent)

        val statusString = intent.getStringExtra("status")
        textView.text = statusString
    }
}
