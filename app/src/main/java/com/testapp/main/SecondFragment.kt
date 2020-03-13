package com.testapp.main

import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.testapp.model.User
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment() {

    private var updateData: Boolean = false
    private val TAG = SecondFragment::class.java.simpleName;

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_second, container, false)
    }

    public fun setData(flag :Boolean){
        updateData = flag
        Log.e(TAG, "setData updateData  = " + updateData)
        if (flag) {
            etLname.setText(MainActivity.user.lastName)
            etFname.setText(MainActivity.user.firstName)
        } else {
            etLname.setText("")
            etFname.setText("")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSave.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(etFname.text.toString())) {
                Toast.makeText(activity, "Enter first name", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(etLname.text.toString())) {
                Toast.makeText(activity, "Enter last name", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (updateData) {
                val user = MainActivity.user
                user.firstName = etFname.text.toString();
                user.lastName = etLname.text.toString();
                updateTask(user).execute()
                Toast.makeText(activity, "User Updated", Toast.LENGTH_SHORT).show()
            } else {
                val user = User()
                user.firstName = etFname.text.toString();
                user.lastName = etLname.text.toString();
                insertTask(user,activity).execute()
            }
        })
    }

    class insertTask(
        val user: User,
        val activity: FragmentActivity?
    ) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            MyApp.getAppDb()?.userDao()?.insertAll(user)
            return null;
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Toast.makeText(activity, "User added successfully", Toast.LENGTH_SHORT).show()
            activity?.etFname?.setText("")
            activity?.etLname?.setText("")
        }
    }

    class updateTask(val user: User) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            MyApp.getAppDb()?.userDao()?.updateUser(user)
            return null;
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
        }
    }
}