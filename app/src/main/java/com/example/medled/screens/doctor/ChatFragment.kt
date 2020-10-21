package com.example.medled.screens.doctor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medled.R
import com.example.medled.adapters.recycler_view.MessagesRecyclerViewAdapter
import com.example.medled.databases.real_time_database.RealTimeDatabase
import com.example.medled.models.Message
import com.example.medled.models.Request
import com.example.medled.view_models.CurrentUserViewModel
import com.example.medled.view_models.RequestViewModel
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment(),ChatInterface {

    private lateinit var requestViewModel: RequestViewModel
    private lateinit var currentUserViewModel: CurrentUserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentUserViewModel = ViewModelProvider(requireActivity()).get(CurrentUserViewModel::class.java)
        requestViewModel =  ViewModelProvider(requireActivity()).get(RequestViewModel::class.java)

        setChatInfo()
        messagesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    //------------------| Get request info from database |----------------------
    private fun setChatInfo(){
        val database = RealTimeDatabase()
        requestViewModel.getRequest().observe(viewLifecycleOwner, Observer {requestId->
            database.getRequestById(requireView(),requestId,this)
        })
    }
    //============================================================================

    //----------------------------| Listen to the request changed |---------------------------------
    override fun onRequestChanged(request: Request) {
         currentUserViewModel.getUser().observe(viewLifecycleOwner, Observer { currentUser->
             if(currentUser!!.isDoctor){
                chatMemberName.text = request.patient!!.name
                chatMemberBio.text = request.patient.bio
             }else{
                 chatMemberName.text = request.doctor!!.name
                 chatMemberBio.text = request.doctor.medicineBranch
             }

             val msgs = arrayListOf<Message>(
                 Message("SIEMA","L9z3hTmMtKVB7wn1l3OyHMVtYDA3"),
                 Message("SIEMA","f4UHcGg0e6UC4ZZWhFRSRWk1hVo2"),
                 Message("SIEMA","L9z3hTmMtKVB7wn1l3OyHMVtYDA3"),
                 Message("SIEMA","f4UHcGg0e6UC4ZZWhFRSRWk1hVo2"),
                 Message("SIEMA","L9z3hTmMtKVB7wn1l3OyHMVtYDA3"),
                 Message("SIEMA","f4UHcGg0e6UC4ZZWhFRSRWk1hVo2"),
                 Message("SIEMA","f4UHcGg0e6UC4ZZWhFRSRWk1hVo2"),
                 Message("SIEMA","f4UHcGg0e6UC4ZZWhFRSRWk1hVo2")
             )
             messagesRecyclerView.adapter = MessagesRecyclerViewAdapter(currentUser.id,msgs)
         })
    }
    //==============================================================================================

}