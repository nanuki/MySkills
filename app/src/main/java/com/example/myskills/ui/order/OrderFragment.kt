package com.example.myskills.ui.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myskills.MainViewModel
import com.example.myskills.R
import com.example.myskills.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val orderViewModel : MainViewModel by activityViewModels()

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonCancelOrder.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)

        }

        orderViewModel.listSentorder.observe(viewLifecycleOwner) {

            if (it.size == 0){
                binding.orderTitle.text = getString(R.string.you_have_not_order)
            }
            else{
                binding.orderTitle.text = ""
            }
            val recycleradapter = SentOrderAdapter(root.context,it,orderViewModel)
            _binding!!.recyclerview.adapter = recycleradapter
            _binding!!.recyclerview.layoutManager = LinearLayoutManager(root.context)


        }


        orderViewModel.getsentOrderData()




        orderViewModel.order.observe(viewLifecycleOwner) {

            orderViewModel.resetbadge(it.totalcount)


        }
        orderViewModel.getOrder()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}