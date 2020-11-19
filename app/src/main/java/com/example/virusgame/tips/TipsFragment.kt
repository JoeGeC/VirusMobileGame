package com.example.virusgame.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virusgame.MenuListener
import com.example.virusgame.R
import kotlinx.android.synthetic.main.shop.doneButton
import kotlinx.android.synthetic.main.tips.*

class TipsFragment(private val menuListener: MenuListener) : Fragment(), View.OnClickListener {
    private lateinit var tipsAdapter: TipListAdapter
    private var tipViewManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doneButton.setOnClickListener(this)
        setupTips()
        menuListener.onMenuOpened()
    }

    private fun setupTips() {
        tipsAdapter = TipListAdapter(TipList.getList(context!!))
        tipsList.apply {
            setHasFixedSize(true)
            layoutManager = tipViewManager
            adapter = tipsAdapter
        }
    }

    override fun onClick(v: View) {
        if(v.id == R.id.doneButton) closeFragment()
    }

    private fun closeFragment() {
        menuListener.onMenuClosed()
        fragmentManager!!.beginTransaction().remove(this).commit()
    }
}
