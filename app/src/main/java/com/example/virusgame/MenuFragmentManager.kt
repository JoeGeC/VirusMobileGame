package com.example.virusgame

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MenuFragmentManager(var context: Context, var fragmentManager: FragmentManager) {
    fun openFragment(fragment: Fragment) {
        removeFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.subFragmentContainer, fragment, context.getString(R.string.subFragment))
        fragmentTransaction.commit()
    }

    fun removeFragment() {
        val fragmentToRemove = fragmentManager.findFragmentByTag(context.getString(R.string.subFragment))
        if(fragmentToRemove != null) fragmentManager.beginTransaction().remove(fragmentToRemove).commit()
    }
}