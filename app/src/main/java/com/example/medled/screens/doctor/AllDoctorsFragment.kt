package com.example.medled.screens.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medled.R
import com.example.medled.adapters.recycler_view.DoctorTypesRecyclerViewAdapter
import com.example.medled.adapters.recycler_view.DoctorsRecyclerViewAdapter
import com.example.medled.adapters.recycler_view.MedicineFormsRecyclerViewAdapter
import com.example.medled.models.DoctorTypeCard
import com.example.medled.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_all_doctors.*


class AllDoctorsFragment : Fragment() , ChangeDoctorTypeInterface{


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.fragment_all_doctors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doctorsTypeRecyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        doctorsTypeRecyclerView.adapter = DoctorTypesRecyclerViewAdapter(setupDoctorsTypesCards(),this)

        doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        getActiveDoctors()
    }

    //--------------------------------| get all doctors cards |---------------------------------------
    private fun setupDoctorsTypesCards():ArrayList<DoctorTypeCard>{
        return arrayListOf<DoctorTypeCard>(
            DoctorTypeCard("Pediatrician",R.drawable.doctor_avatar_1,true),
            DoctorTypeCard("Neurologist",R.drawable.doctor_avatar_1,false),
            DoctorTypeCard("Family Doctor",R.drawable.doctor_avatar_1,false),
            DoctorTypeCard("Psychiatrist",R.drawable.doctor_avatar_1,false),
            DoctorTypeCard("Pulmonologist",R.drawable.doctor_avatar_1,false),
            DoctorTypeCard("Dermatologist",R.drawable.doctor_avatar_1,false),
            DoctorTypeCard("Cardiologist",R.drawable.doctor_avatar_1,false)

        )
    }
    //================================================================================================

    //----------------| Change doctor type |-------------------
    override fun changeType(doctorType: String) {
        Toast.makeText(requireContext(),doctorType,Toast.LENGTH_SHORT).show()
    }
    //=========================================================

    private fun getActiveDoctors(){
        val arrayListOfDoctors = arrayListOf<User>()
        val myRef = FirebaseDatabase.getInstance().getReference("Doctors")

        myRef.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context,"Error + ${p0.message}",Toast.LENGTH_SHORT).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
                arrayListOfDoctors.clear()
                for(i in p0.children){
                    val doctor = i.getValue(User::class.java)!!
                    arrayListOfDoctors.add(doctor)
                }

                if(doctorsRecyclerView!=null)doctorsRecyclerView.adapter = DoctorsRecyclerViewAdapter(arrayListOfDoctors)
            }
        })

    }

}