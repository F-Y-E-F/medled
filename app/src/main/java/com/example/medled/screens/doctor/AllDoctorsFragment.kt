package com.example.medled.screens.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medled.R
import com.example.medled.adapters.recycler_view.DoctorsRecyclerViewAdapter
import com.example.medled.adapters.recycler_view.MedicineFormsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_all_doctors.*


class AllDoctorsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.fragment_all_doctors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //doctorsTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        //doctorsTypeRecyclerView.adapter = MedicineFormsRecyclerViewAdapter()

        doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        doctorsRecyclerView.adapter = DoctorsRecyclerViewAdapter()

    }

}