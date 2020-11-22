package com.anandgaur.techugotask.ui.offers

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.anandgaur.techugotask.R
import com.anandgaur.techugotask.databinding.FragmentDetailsBinding
import com.anandgaur.techugotask.databinding.FragmentHomeBinding
import com.anandgaur.techugotask.utills.Utills
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var mDashboardViewModel: OffersViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mTabAdapter: OffersTabAdapter
    private lateinit var binding: FragmentHomeBinding

    private var positionItem = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view: View = binding.root
        binding.lifecycleOwner = this
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDashboardViewModel = ViewModelProviders.of(this).get(OffersViewModel::class.java)

        mTabAdapter = OffersTabAdapter(this, requireActivity())

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Log.e("tabselected pos", "${tab?.position}")
                binding.pager.setCurrentItem(tab?.position!!, false)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // pager.setCurrentItem(tab?.position!!, false)

                //Log.e("tabReselected pos", "${tab?.position}")

            }
        })


        binding.pager.adapter = mTabAdapter
        setDividers()

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = Utills.getTabTitile(position, requireActivity())

            binding.pager.setCurrentItem(tab.position, false)

        }.attach()

        binding.pager.isUserInputEnabled = true

    }

    fun setDividers() {
        val root: View = binding.tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_NONE
            val drawable = GradientDrawable()
            drawable.setColor(resources.getColor(R.color.colorAccent))
            drawable.setSize(2, 1)
            root.dividerPadding = 5
            root.dividerDrawable = drawable
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}