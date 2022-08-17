package com.example.myskills.ui.cart

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myskills.MainViewModel
import com.example.myskills.R
import com.example.myskills.databinding.FragmentCartBinding
import com.example.myskills.databinding.FragmentMakeOrderBinding
import com.example.myskills.repository.room.CartEntity
import com.example.myskills.repository.room.OrderEntity
import com.example.myskills.repository.room.SentorderEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.util.*

class CartFragment : Fragment(), KoinComponent {

    var price = 0
    var count = 0
    var status = false
    var order : OrderEntity? = null
    val cartViewModel : MainViewModel by activityViewModels()
    var list = mutableListOf<CartEntity>()

    private lateinit var sentorder: SentorderEntity


    private var _binding: FragmentCartBinding? = null
    val scope = CoroutineScope(Dispatchers.IO)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        // val busketViewModel = ViewModelProvider(this).get(BusketViewModel::class.java)

        _binding = FragmentCartBinding.inflate(inflater, container, false)

        val root: View = binding.root

        _binding?.buttonMakeOrder?.setOnClickListener {
            if (count == 0 || status ){
                Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)

            }
            else{

                crateOrder()
            }

        }

        cartViewModel.order.observe(viewLifecycleOwner) {
            sentorder = SentorderEntity(
                0,
                it.date,
                it.status,
                it.address,
                it.phone_number,
                it.totalcount,
                it.totalprice
            )

            count = it.totalcount
            price = it.totalprice
            status = it.status

            cartViewModel.resettotalprice(it.totalprice)
            cartViewModel.resetbadge(it.totalcount)


            if (count == 0) {
                _binding!!.buttonMakeOrder.setText("GO TO MENU")

            } else {
                _binding!!.buttonMakeOrder.setText(getString(R.string.continue_to_checkout))

                order = OrderEntity(1, it.date, it.status, "", it.phone_number, count, price)

            }
        }

        cartViewModel.getOrder()

        cartViewModel.totalprice.observe(viewLifecycleOwner) {
            if (it == 0) {
                _binding!!.price.setText("")

            } else {
                _binding!!.price.setText("PRICE($it$)")
            }


        }

        cartViewModel.badge.observe(viewLifecycleOwner) {

            if (it == 0 ) {
                _binding!!.count.setText("Oops, its empty here!")
            } else {
                _binding!!.count.setText("ITEMS ($it)")

            }
        }




        cartViewModel.listMenuBusket.observe(viewLifecycleOwner) {
            // val navView: BottomNavigationView = binding.navView


//            for (item in it){
//                if (item.state == false){
//                    list.add(item)
//
//                }
//            }

            val recycleradapter = CartAdaptor(root.context,it,cartViewModel)
            _binding!!.recyclerview.adapter = recycleradapter
            _binding!!.recyclerview.layoutManager = LinearLayoutManager(root.context)




        }

        cartViewModel.getBuscet()


        return root
    }



//    private fun makeOrder() {
//        val current = LocalDateTime.now().toString()
//        order?.date = current
//        order?.let { busketViewModel.updateOrder(it) }
//        order?.status = true
//
//
//    }


    fun crateOrder() {
        val builder = context?.let { AlertDialog.Builder(it) }
        // builder?.setTitle(getString(R.string.requires_address_phone))

        //  val view = LayoutInflater.from(context).inflate(R.layout.fragment_make_order,binding.root,false)
        val orderBinding = FragmentMakeOrderBinding.inflate(layoutInflater)
        builder?.setView(orderBinding.root)

        builder?.setPositiveButton(getString(R.string.CONTINUE), DialogInterface.OnClickListener { dialogInterface, i ->
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val datetime = "$day/$month/$year // $hour:$minute"
            val orderEntity = OrderEntity(1,"00.00.00",false,"kk","",0,0)
            cartViewModel.updateOrder(orderEntity )
            sentorder.date = datetime
            sentorder.adres = orderBinding.address.text.toString()
            sentorder.phone_number = orderBinding.phone.text.toString()

            cartViewModel.createsentorder(sentorder)
            scope.launch {
                val list: MutableList<CartEntity> = cartViewModel.getbusketdata()
                for (item in list){
                    cartViewModel.deletBusket(item.id)
                }
                cartViewModel.resetbadge(0)
                cartViewModel.resettotalprice(0)
            }
            cartViewModel.getsentOrderData()
            Navigation.findNavController(binding.root).navigate(R.id.navigation_menu)



        })
        builder?.setNegativeButton(getString(R.string.CANCEL), DialogInterface.OnClickListener { dialogInterface, i ->
            Navigation.findNavController(binding.root).navigate(R.id.navigation_cart)
            dialogInterface.cancel()
        })
        builder?.show()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}