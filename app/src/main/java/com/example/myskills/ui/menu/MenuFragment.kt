package com.example.myskills.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myskills.MainViewModel
import com.example.myskills.databinding.FragmentMenuBinding
import com.example.myskills.repository.room.MenuEntity

class MenuFragment : Fragment() {

    private val homeViewModel : MainViewModel by activityViewModels()


    private var _binding: FragmentMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.listMenuEntity.observe(viewLifecycleOwner){
            val list1 = mutableListOf<MenuEntity>()
            val list2 = mutableListOf<MenuEntity>()
            val list3 = mutableListOf<MenuEntity>()
            for (item in it){
                if (item.category == "pizza"){
                    list1.add(item)
                }
                if (item.category == "burger"){
                    list2.add(item)
                }
                if (item.category == "juice"){
                    list3.add(item)
                }
            }
            val recycleradapter = MenuFragmentAdaptor(root.context,list1,binding.root,homeViewModel)
            _binding!!.recyclerviewPizza.adapter = recycleradapter
            _binding!!.recyclerviewPizza.layoutManager = LinearLayoutManager(root.context,
                LinearLayoutManager.HORIZONTAL, false)

            val recycleradapter2 = MenuFragmentAdaptor(root.context,list2,binding.root,homeViewModel)
            _binding!!.recyclerviewBurger.adapter = recycleradapter2
            _binding!!.recyclerviewBurger.layoutManager = LinearLayoutManager(root.context,
                LinearLayoutManager.HORIZONTAL, false)

            val recycleradapter3= MenuFragmentAdaptor(root.context,list3,binding.root,homeViewModel)
            _binding!!.recyclerviewJuice.adapter = recycleradapter3
            _binding!!.recyclerviewJuice.layoutManager = LinearLayoutManager(root.context,
                LinearLayoutManager.HORIZONTAL, false)



        }

        homeViewModel.showmenu1()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}