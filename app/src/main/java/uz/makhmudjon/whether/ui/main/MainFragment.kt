package uz.makhmudjon.whether.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.whether.R
import com.example.whether.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment: Fragment() {

    lateinit var binding:FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProviders.of(this,MainVMFactory(this.activity!!))[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.image = {
            Glide.with(this)
                .load("https:$it").into(binding.root.imageholder)
        }

        viewModel.whether.observe(this, Observer {
                if(it!=null){

                }
        })

    }
}
