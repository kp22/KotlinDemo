package com.testapp.main

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.testapp.adapter.ListAdapter
import com.testapp.model.User
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ListAdapter(listOf())
        recycleView.adapter = adapter;
        getUserData(adapter).execute()
    }

    public fun updateMydata() {
        getUserData(adapter).execute()
    }

    class getUserData(val recycleView: ListAdapter) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            val list: List<User> = MyApp?.getAppDb()?.userDao()?.getAll() as List<User>
            recycleView.updateList(list)
            return null;
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            recycleView.notifyDataSetChanged();
        }
    }

}