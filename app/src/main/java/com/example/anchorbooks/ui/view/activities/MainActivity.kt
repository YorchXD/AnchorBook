package com.example.anchorbooks.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anchorbooks.R
import com.example.anchorbooks.ui.view.fragments.BooksFragment
import com.example.anchorbooks.ui.view.fragments.DetailBookFragment
import com.example.anchorbooks.ui.view.interfaces.IComunicateBook
import com.example.anchorbooks.ui.viewModel.BookViewModel

class MainActivity : AppCompatActivity(), IComunicateBook {
    private lateinit var viewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        setContentView(R.layout.activity_main)
        viewBooks()
    }

    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contentBookFragment, fragment)
        fragmentTransaction.commit()
    }

    override fun viewBooks() {
        replaceFragment(BooksFragment())
    }

    override fun viewDetailBook() {
        replaceFragment(DetailBookFragment())
    }
}